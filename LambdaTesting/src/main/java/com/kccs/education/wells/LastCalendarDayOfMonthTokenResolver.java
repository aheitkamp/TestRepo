package com.kccs.education.wells;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

import org.springframework.stereotype.Component;

@Component
public class LastCalendarDayOfMonthTokenResolver extends DefaultTokenResolver {

	private static final int ONE_DAY = 1;

	@Override
	public LocalDate adjustDate(LocalDate date) {
		LocalDate adjustedDate = date.with(TemporalAdjusters.lastDayOfMonth());
		if (adjustedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			adjustedDate = adjustedDate.minus(Period.ofDays(ONE_DAY));
		}
		return adjustedDate;
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return adjustDate(date);
	}
}