package com.kccs.education.wells;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class MidDayOfMonthTokenResolver extends DefaultTokenResolver {

	private static final int DAY_15 = 15;


	@Override
	public LocalDate adjustDate(LocalDate date) {
		return date.withDayOfMonth(DAY_15);
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return adjustDate(date);
	}
}