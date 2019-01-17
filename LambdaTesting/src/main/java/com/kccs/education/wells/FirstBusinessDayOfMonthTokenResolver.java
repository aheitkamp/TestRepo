package com.kccs.education.wells;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class FirstBusinessDayOfMonthTokenResolver extends DefaultTokenResolver {
	
	@Override
	public LocalDate adjustDate(LocalDate date) {
		return calendarService.firstBusinessDayOfMonth(date);
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return calendarService.firstBusinessDayOfMonth(date);
	}
}