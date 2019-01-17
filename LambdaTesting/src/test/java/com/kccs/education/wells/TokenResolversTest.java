package com.kccs.education.wells;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class TokenResolversTest {
	
	private DefaultTokenResolver defaultResolver;
	private FixedDayOfMonthTokenResolver fixedDayOfMonthTokenResolver;
	private PriorMonthTokenResolver priorMonthTokenResolver;
	private LastBusinessDayOfMonthTokenResolver lastDayOfMonthTokenResolver;
	private FirstBusinessDayOfMonthTokenResolver firstBusinessDayOfMonthTokenResolver;
	private MidDayOfMonthTokenResolver midDayOfMonthTokenResolver;
	private MidDayOfMonthNotSundayTokenResolver midDayOfMonthNotSundayTokenResolver;
	private LastCalendarDayOfMonthTokenResolver lastCalendarDayOfMonthTokenResolver;

	@Before
	public void setup() {
		defaultResolver = new DefaultTokenResolver();
		CalendarService calendarService = new CalendarService();
		defaultResolver.setCalendarService(calendarService);
		fixedDayOfMonthTokenResolver = new FixedDayOfMonthTokenResolver();
		fixedDayOfMonthTokenResolver.setCalendarService(calendarService);
		priorMonthTokenResolver = new PriorMonthTokenResolver();
		priorMonthTokenResolver.setCalendarService(calendarService);
		lastDayOfMonthTokenResolver = new LastBusinessDayOfMonthTokenResolver();
		lastDayOfMonthTokenResolver.setCalendarService(calendarService);
		firstBusinessDayOfMonthTokenResolver = new FirstBusinessDayOfMonthTokenResolver();
		firstBusinessDayOfMonthTokenResolver.setCalendarService(calendarService);
		midDayOfMonthTokenResolver = new MidDayOfMonthTokenResolver();
		midDayOfMonthTokenResolver.setCalendarService(calendarService);
		midDayOfMonthNotSundayTokenResolver = new MidDayOfMonthNotSundayTokenResolver();
		midDayOfMonthNotSundayTokenResolver.setCalendarService(calendarService);
		lastCalendarDayOfMonthTokenResolver = new LastCalendarDayOfMonthTokenResolver();
		lastCalendarDayOfMonthTokenResolver.setCalendarService(calendarService);
	}
	
	@Test
	public void testFixDateResolverAdjustDate() {
		LocalDate now = LocalDate.of(2019, 01, 15);
		LocalDate adjustedDate = fixedDayOfMonthTokenResolver.adjustDate(now, "20");
		String tokenizedString = "myfile.($20$).txt";
		String resolved = fixedDayOfMonthTokenResolver.resolve(tokenizedString, FileNameTokenEnum.FixedDayOfMonth, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.18.txt", resolved);
	}

	@Test
	public void testPriorMonthResolver() {
		LocalDate date = LocalDate.of(2019, 01, 15);
		LocalDate adjustedDate = priorMonthTokenResolver.adjustDate(date);
		String tokenizedString = "myfile.(PMM).(YYYY).txt";
		String resolved = fixedDayOfMonthTokenResolver.resolve(tokenizedString, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		resolved = priorMonthTokenResolver.resolve(resolved, FileNameTokenEnum.PriorMonthShortName, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.Dec.2018.txt", resolved);
	}
	
	@Test
	public void testPriorMonthLastBusinessDay() {
		LocalDate date = LocalDate.of(2019, 01, 15);
		LocalDate adjustedDate = priorMonthTokenResolver.adjustDate(date);
		adjustedDate = lastDayOfMonthTokenResolver.adjustDate(adjustedDate);
		String tokenizedString = "myfile.(YY)_(PM)_(LBD).txt";
		String resolved = priorMonthTokenResolver.resolve(tokenizedString, FileNameTokenEnum.PriorMonth2Digit, adjustedDate);
		resolved = lastDayOfMonthTokenResolver.resolve(resolved, FileNameTokenEnum.LastBusinessDayOfMonth, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear2Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.18_12_31.txt", resolved);
	}
	
	@Test
	public void testPriorMonthFirstBusinessDay() {
		LocalDate date = LocalDate.of(2019, 01, 15);
		LocalDate adjustedDate = priorMonthTokenResolver.adjustDate(date);
		adjustedDate = firstBusinessDayOfMonthTokenResolver.adjustDate(adjustedDate);
		String tokenizedString = "myfile.(YYYY)_(PM)_(FBD).txt";
		String resolved = priorMonthTokenResolver.resolve(tokenizedString, FileNameTokenEnum.PriorMonth2Digit, adjustedDate);
		resolved = firstBusinessDayOfMonthTokenResolver.resolve(resolved, FileNameTokenEnum.FirstBusinessDayOfMonth, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.2018_12_03.txt", resolved);
	}

	@Test
	public void testLastCalendarDayOfMonth_SundayShouldChangeToSaturday() {
		// Last of of March 2019 is Sunday the 31st.  This should adjust to Saturday the 30th.
		LocalDate date = LocalDate.of(2019, 03, 15);		
		LocalDate adjustedDate = lastCalendarDayOfMonthTokenResolver.adjustDate(date);
		String tokenizedString = "myfile.(YYYY)_(CM)_(CCL).txt";
		String resolved = defaultResolver.resolve(tokenizedString, FileNameTokenEnum.CurrentMonth2Digit, adjustedDate);
		resolved = lastCalendarDayOfMonthTokenResolver.resolve(resolved, FileNameTokenEnum.LastCalendarDayOfMonth, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.2019_03_30.txt", resolved);
	}
	
	@Test
	public void testLastCalendarDayOfMonth_ShoudBeJanuary32st() {
		LocalDate date = LocalDate.of(2020, 10, 15);		
		LocalDate adjustedDate = lastCalendarDayOfMonthTokenResolver.adjustDate(date);
		String tokenizedString = "myfile.(YYYY)_(CM)_(CCL).txt";
		String resolved = defaultResolver.resolve(tokenizedString, FileNameTokenEnum.CurrentMonth2Digit, adjustedDate);
		resolved = lastCalendarDayOfMonthTokenResolver.resolve(resolved, FileNameTokenEnum.LastCalendarDayOfMonth, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.2020_10_31.txt", resolved);
	}

	@Test
	public void testDayOfMonth() {
		LocalDate date = LocalDate.of(2020, 10, 15);		
		LocalDate adjustedDate = defaultResolver.adjustDate(date);
		String tokenizedString = "myfile.(YYYY)_(CM)_(DD).txt";
		String resolved = defaultResolver.resolve(tokenizedString, FileNameTokenEnum.CurrentMonth2Digit, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.DayOfMonth, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.2020_10_15.txt", resolved);
	}

	@Test
	public void testMidDayOfMonth_15thFallsOnSunday() {
		LocalDate date = LocalDate.of(2020, 11, 01);		
		LocalDate adjustedDate = midDayOfMonthTokenResolver.adjustDate(date);
		String tokenizedString = "myfile.(YYYY)_(CM)_(MD).txt";
		String resolved = defaultResolver.resolve(tokenizedString, FileNameTokenEnum.CurrentMonth2Digit, adjustedDate);
		resolved = midDayOfMonthTokenResolver.resolve(resolved, FileNameTokenEnum.MidDayOfMonth, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.2020_11_15.txt", resolved);
	}

	@Test
	public void testMidDayOfMonthNotSunday_15thFallsOnSunday() {
		LocalDate date = LocalDate.of(2020, 11, 01);		
		LocalDate adjustedDate = midDayOfMonthNotSundayTokenResolver.adjustDate(date);
		String tokenizedString = "myfile.(YYYY)_(CM)_(CCMD).txt";
		String resolved = defaultResolver.resolve(tokenizedString, FileNameTokenEnum.CurrentMonth2Digit, adjustedDate);
		resolved = midDayOfMonthNotSundayTokenResolver.resolve(resolved, FileNameTokenEnum.MidDayOfMonthNotSunday, adjustedDate);
		resolved = defaultResolver.resolve(resolved, FileNameTokenEnum.CurrentYear4Digit, adjustedDate);
		System.out.println(resolved);
		assertEquals("myfile.2020_11_14.txt", resolved);
	}
	
	
}
