package com.kccs.education.wells;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CalendarService {
	
	private Set<LocalDate> holidays = new HashSet<>();
	
	@PostConstruct
	// TODO Replace with with DB Calendar
	protected void loadDefaultCalendar() {
		holidays.add(LocalDate.of(2018, 1, 1));
		holidays.add(LocalDate.of(2018, 1, 15));
		holidays.add(LocalDate.of(2018, 2, 19));
		holidays.add(LocalDate.of(2018, 5, 28));
		holidays.add(LocalDate.of(2018, 7, 4));
		holidays.add(LocalDate.of(2018, 9, 3));
		holidays.add(LocalDate.of(2018, 10, 8));
		holidays.add(LocalDate.of(2018, 11, 12));
		holidays.add(LocalDate.of(2018, 11, 22));
		holidays.add(LocalDate.of(2018, 12, 25));

		holidays.add(LocalDate.of(2019, 1, 1));
		holidays.add(LocalDate.of(2019, 1, 21));
		holidays.add(LocalDate.of(2019, 2, 18));
		holidays.add(LocalDate.of(2019, 5, 27));
		holidays.add(LocalDate.of(2019, 7, 4));
		holidays.add(LocalDate.of(2019, 9, 2));
		holidays.add(LocalDate.of(2019, 10, 14));
		holidays.add(LocalDate.of(2019, 11, 11));
		holidays.add(LocalDate.of(2019, 11, 28));
		holidays.add(LocalDate.of(2019, 12, 25));

		holidays.add(LocalDate.of(2019, 1, 1));
		holidays.add(LocalDate.of(2019, 1, 21));
		holidays.add(LocalDate.of(2019, 2, 18));
		holidays.add(LocalDate.of(2019, 5, 27));
		holidays.add(LocalDate.of(2019, 7, 4));
		holidays.add(LocalDate.of(2019, 9, 2));
		holidays.add(LocalDate.of(2019, 10, 14));
		holidays.add(LocalDate.of(2019, 11, 11));
		holidays.add(LocalDate.of(2019, 11, 28));
		holidays.add(LocalDate.of(2019, 12, 25));
	}
	
	public LocalDate lastBusinessDayOfMonth(LocalDate date) {
		return businessDayAdjustBack(date.with(TemporalAdjusters.lastDayOfMonth()));
	}
	
	public LocalDate firstBusinessDayOfMonth(LocalDate date) {
		return businessDayAdjustForward(date.with(TemporalAdjusters.firstDayOfMonth()));
	}
	
	public LocalDate businessDayAdjustBack(LocalDate date) {
		LocalDate businessDate = date;
		while (businessDate.getDayOfWeek() ==  DayOfWeek.SATURDAY || businessDate.getDayOfWeek() ==  DayOfWeek.SUNDAY || holidays.contains(businessDate)) {
			businessDate = businessDate.minus(Period.ofDays(1));
		}
		return businessDate;
	}

	public LocalDate businessDayAdjustForward(LocalDate date) {
		LocalDate businessDate = date;
		// TODO add holiday calendar support
		while (businessDate.getDayOfWeek() ==  DayOfWeek.SATURDAY || businessDate.getDayOfWeek() ==  DayOfWeek.SUNDAY || holidays.contains(businessDate)) {
			businessDate = businessDate.plus(Period.ofDays(1));
		}
		return businessDate;
	}
}
