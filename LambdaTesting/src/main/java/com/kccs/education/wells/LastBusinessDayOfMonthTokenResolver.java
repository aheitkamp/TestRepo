package com.kccs.education.wells;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class LastBusinessDayOfMonthTokenResolver extends DefaultTokenResolver {

	@Override
	public LocalDate adjustDate(LocalDate date) {
		return calendarService.lastBusinessDayOfMonth(date);
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return calendarService.lastBusinessDayOfMonth(date);
	}
}