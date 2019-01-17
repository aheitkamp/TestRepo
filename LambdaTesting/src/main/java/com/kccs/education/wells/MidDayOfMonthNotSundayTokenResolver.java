package com.kccs.education.wells;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class MidDayOfMonthNotSundayTokenResolver extends DefaultTokenResolver {

	private static final int DAY_15 = 15;
	private static final int DAY_14 = 14;


	@Override
	public LocalDate adjustDate(LocalDate date) {
		LocalDate adjustedDate = date.withDayOfMonth(DAY_15);
		if (adjustedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			adjustedDate = adjustedDate.withDayOfMonth(DAY_14);
		}
		return adjustedDate;
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return adjustDate(date);
	}
}