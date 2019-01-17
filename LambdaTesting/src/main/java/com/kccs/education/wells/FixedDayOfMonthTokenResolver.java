package com.kccs.education.wells;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class FixedDayOfMonthTokenResolver extends DefaultTokenResolver {
	
	@Override
	public LocalDate adjustDate(LocalDate date) {
		throw new UnsupportedOperationException("FirstDayOfMonthTokenResolver requires a value for the day of the month");
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String dayOfMonth) {
		assert dayOfMonth != null;
		LocalDate workingDate = date.withDayOfMonth(Integer.valueOf(dayOfMonth));
		return calendarService.businessDayAdjustBack(workingDate);
	}
}