package com.kccs.education.wells;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

@Component
public class PriorMonthTokenResolver extends DefaultTokenResolver {

	private static final int ONE_DAY = 1;

	@Override
	public LocalDate adjustDate(LocalDate date) {
		return date.minus(Period.ofMonths(ONE_DAY));
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return adjustDate(date);
	}
}