package com.kccs.education.wells;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        CalendarService.class,
        DefaultTokenResolver.class,
        FirstBusinessDayOfMonthTokenResolver.class,
        FixedDayOfMonthTokenResolver.class,
        LastBusinessDayOfMonthTokenResolver.class,
        LastCalendarDayOfMonthTokenResolver.class,
        MidDayOfMonthNotSundayTokenResolver.class,
        MidDayOfMonthTokenResolver.class,
        PriorMonthTokenResolver.class,
        TokenizedFileNameResolver.class
})
public class SpringTokenFileNameResolverTest {

	@Autowired
	private TokenizedFileNameResolver resolver;
	
	@Test
	public void test() {
		runTest(LocalDate.of(2019, 01, 15), "myFile_(YY)_(PM)_(FBD).txt");
		runTest(LocalDate.of(2019, 01, 15), "myFile_(YYYY)_(CM)_(LBD).txt");
		runTest(LocalDate.of(2019, 01, 15), "myFile_(YYYY)_(CMM)_(DD).txt");
		runTest(LocalDate.of(2019, 02, 15), "myFile_(YYYY)_(CMMM)_(CCMD).txt");
		runTest(LocalDate.of(2019, 02, 15), "myFile_(YYYY)_(CMMM)_(CCL).txt");
		runTest(LocalDate.of(2020, 05, 15), "myFile_(YYYY)_(CMMM)_(LBD).txt");
	}

	private void runTest(LocalDate date, String tokenizedFileName) {
		String resolved = resolver.resolve(tokenizedFileName, date);
		System.out.println(">>>>>>" + resolved);
	}
	
	

}
