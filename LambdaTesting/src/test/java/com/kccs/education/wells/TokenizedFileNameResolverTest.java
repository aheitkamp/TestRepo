package com.kccs.education.wells;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class TokenizedFileNameResolverTest {
	
	private TokenizedFileNameResolver resolver = new TokenizedFileNameResolver();
	
	private DefaultTokenResolver defaultResolver;
	private FixedDayOfMonthTokenResolver fixedDayOfMonthTokenResolver;
	private PriorMonthTokenResolver priorMonthTokenResolver;
	private LastBusinessDayOfMonthTokenResolver lastBusinessDayOfMonthTokenResolver;
	private FirstBusinessDayOfMonthTokenResolver firstBusinessDayOfMonthTokenResolver;
	private MidDayOfMonthTokenResolver midDayOfMonthTokenResolver; 	
	private MidDayOfMonthNotSundayTokenResolver midDayOfMonthNotSundayTokenResolver; 	
	private LastCalendarDayOfMonthTokenResolver lastCalendarDayOfMonthTokenResolver; 	

	@Before
	public void setup() {
		defaultResolver = new DefaultTokenResolver();
		CalendarService calendarService = new CalendarService();
		calendarService.loadDefaultCalendar();
		defaultResolver.setCalendarService(calendarService);
		fixedDayOfMonthTokenResolver = new FixedDayOfMonthTokenResolver();
		fixedDayOfMonthTokenResolver.setCalendarService(calendarService);
		priorMonthTokenResolver = new PriorMonthTokenResolver();
		priorMonthTokenResolver.setCalendarService(calendarService);
		lastBusinessDayOfMonthTokenResolver = new LastBusinessDayOfMonthTokenResolver();
		lastBusinessDayOfMonthTokenResolver.setCalendarService(calendarService);
		firstBusinessDayOfMonthTokenResolver = new FirstBusinessDayOfMonthTokenResolver();
		firstBusinessDayOfMonthTokenResolver.setCalendarService(calendarService);
		midDayOfMonthTokenResolver = new MidDayOfMonthTokenResolver();
		midDayOfMonthTokenResolver.setCalendarService(calendarService);
		midDayOfMonthNotSundayTokenResolver = new MidDayOfMonthNotSundayTokenResolver();
		midDayOfMonthNotSundayTokenResolver.setCalendarService(calendarService);
		lastCalendarDayOfMonthTokenResolver = new LastCalendarDayOfMonthTokenResolver();
		lastCalendarDayOfMonthTokenResolver.setCalendarService(calendarService);
		
		
		resolver.setDefaultTokenResolver(defaultResolver);
		resolver.setFirstBusinessDayOfMonthTokenResolver(firstBusinessDayOfMonthTokenResolver);
		resolver.setLastBusinessDayOfMonthTokenResolver(lastBusinessDayOfMonthTokenResolver);
		resolver.setPriorMonthTokenResolver(priorMonthTokenResolver);
		resolver.setMidDayOfMonthTokenResolver(midDayOfMonthTokenResolver);
		resolver.setMidDayOfMonthNotSundayTokenResolver(midDayOfMonthNotSundayTokenResolver);
		resolver.setFixedDayOfMonthTokenResolver(fixedDayOfMonthTokenResolver);
		resolver.setLastCalendarDayOfMonthTokenResolver(lastCalendarDayOfMonthTokenResolver);
		resolver.setFixedDayOfMonthTokenResolver(fixedDayOfMonthTokenResolver);
		resolver.buildMap();
	}

	@Test
	public void testFixedDayIsHoliday() {
		// Christmas 2018
		String resolved = resolver.resolve("myFile_(YY)_(PM)_($25$).txt", LocalDate.of(2019, 01, 15));
		System.out.println(resolved);
		assertEquals("myFile_18_12_24.txt", resolved);
		
		// Memorial day 20019
		resolved = resolver.resolve("myFile_(YY)_(CM)_($27$).txt", LocalDate.of(2019, 05, 15));
		System.out.println(resolved);
		assertEquals("myFile_19_05_24.txt", resolved);

	}

	
	@Test
	public void testPriorMonthFirstBusinessDay() {
		String resolved = resolver.resolve("myFile_(YY)_(PM)_(FBD).txt", LocalDate.of(2019, 01, 15));
		System.out.println(resolved);
		assertEquals("myFile_18_12_03.txt", resolved);
	}
		
	public void testPriorMonthLastBusinessDay() {
		String resolved = resolver.resolve("myFile_(YY)_(PM)_(LBD).txt", LocalDate.of(2019, 01, 15));
		System.out.println(resolved);
		assertEquals("myFile_18_12_31.txt", resolved);
	}
		
	public void testCurrentMonthFirstBusinessDay() {
		String resolved = resolver.resolve("myFile_(YYYY)_(CMM)_(FBD).txt", LocalDate.of(2019, 9, 15));
		System.out.println(resolved);
		assertEquals("myFile_2019_09_02.txt", resolved);
	}
	
	@Test
	public void testMidDayOfNotSundayMonth() {
		String resolved = resolver.resolve("myFile_(YY)_(CM)_(CCMD).txt", LocalDate.of(2019, 01, 12));
		System.out.println(resolved);
		assertEquals("myFile_19_01_15.txt", resolved);
		
		// Sunday test
		resolved = resolver.resolve("myFile_(YY)_(CM)_(CCMD).txt", LocalDate.of(2020, 11, 01));
		System.out.println(resolved);
		assertEquals("myFile_20_11_14.txt", resolved);

		resolved = resolver.resolve("myFile_(YY)_(CM)_(CCMD).txt", LocalDate.of(2019, 01, 20));
		System.out.println(resolved);
		assertEquals("myFile_19_01_15.txt", resolved);
	}
	
	@Test
	public void testMidDayOfMonth() {
		String resolved = resolver.resolve("myFile_(YY)_(CM)_(MD).txt", LocalDate.of(2019, 01, 12));
		System.out.println(resolved);
		assertEquals("myFile_19_01_15.txt", resolved);
		
		resolved = resolver.resolve("myFile_(YY)_(CM)_(MD).txt", LocalDate.of(2020, 11, 01));
		System.out.println(resolved);
		assertEquals("myFile_20_11_15.txt", resolved);
		
		resolved = resolver.resolve("myFile_(YY)_(CM)_(MD).txt", LocalDate.of(2019, 01, 20));
		System.out.println(resolved);
		assertEquals("myFile_19_01_15.txt", resolved);
	}

	@Test
	public void testLastCalendarDayOfMonth() {
		String resolved = resolver.resolve("myFile_(YY)_(CM)_(CCL).txt", LocalDate.of(2019, 06, 12));
		System.out.println(resolved);
		assertEquals("myFile_19_06_29.txt", resolved);
		
		resolved = resolver.resolve("myFile_(YY)_(CM)_(CCL).txt", LocalDate.of(2019, 01, 05));
		System.out.println(resolved);
		assertEquals("myFile_19_01_31.txt", resolved);
		
		resolved = resolver.resolve("myFile_(YY)_(CM)_(CCL).txt", LocalDate.of(2020, 10, 20));
		System.out.println(resolved);
		assertEquals("myFile_20_10_31.txt", resolved);
	}

	@Test
	public void testDayOfMonth() {
		String resolved = resolver.resolve("myFile_(YY)_(CM)_(DD).txt", LocalDate.of(2019, 06, 12));
		System.out.println(resolved);
		assertEquals("myFile_19_06_12.txt", resolved);
		
		resolved = resolver.resolve("myFile_(YY)_(CM)_(DD).txt", LocalDate.of(2019, 01, 05));
		System.out.println(resolved);
		assertEquals("myFile_19_01_05.txt", resolved);
		
		resolved = resolver.resolve("myFile_(YY)_(CM)_(DD).txt", LocalDate.of(2020, 10, 20));
		System.out.println(resolved);
		assertEquals("myFile_20_10_20.txt", resolved);
	}
}
