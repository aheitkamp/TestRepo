package com.kccs.education.wells;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileNameTokenEnumTest {

	@Test
	public void testFromValue() {
		testFromValue("(YYYY)", FileNameTokenEnum.CurrentYear4Digit);
		testFromValue("(YY)", FileNameTokenEnum.CurrentYear2Digit);
		testFromValue("(CM)", FileNameTokenEnum.CurrentMonth2Digit);
		testFromValue("(CMMM)", FileNameTokenEnum.CurrentMonthLongName);
		testFromValue("(CMM)", FileNameTokenEnum.CurrentMonthShortName);
		testFromValue("(PM)", FileNameTokenEnum.PriorMonth2Digit);
		testFromValue("(PMMM)", FileNameTokenEnum.PriorMonthLongName);
		testFromValue("(PMM)", FileNameTokenEnum.PriorMonthShortName);
		testFromValue("(LBD)", FileNameTokenEnum.LastBusinessDayOfMonth);
		testFromValue("(FBD)", FileNameTokenEnum.FirstBusinessDayOfMonth);
		testFromValue("($25$)", FileNameTokenEnum.FixedDayOfMonth);
		testFromValue("(CCL)", FileNameTokenEnum.LastCalendarDayOfMonth);
		testFromValue("(MD)", FileNameTokenEnum.MidDayOfMonth);
		testFromValue("(CCMD)", FileNameTokenEnum.MidDayOfMonthNotSunday);
		testFromValue("(DD)", FileNameTokenEnum.DayOfMonth);
	}
	
	private void testFromValue(String token, FileNameTokenEnum expectedFileNameTokenEnum) {
		FileNameTokenEnum fileNameTokenEnum = FileNameTokenEnum.fromValue(token);
		assertEquals(expectedFileNameTokenEnum, fileNameTokenEnum);

	}

}
