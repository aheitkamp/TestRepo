package com.kccs.education.datetime;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.next;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateTimeExamples {

	public static void main(String[] args) {

		LocalDate lDate = LocalDate.now();
		System.out.println(lDate);
		System.out.println("last day of year " + lDate.with(lastDayOfYear()));
		System.out.println("next Thursday " + lDate.with(next(DayOfWeek.THURSDAY)));

		lDate = LocalDate.of(1961, Month.FEBRUARY, 20);
		System.out.println(lDate);

//		Arrays.stream(TimeZone.getAvailableIDs())
//			.filter(x -> x.startsWith("Europe/"))
//			.forEach(x -> System.out.println(x + " " + LocalTime.now(ZoneId.of(x))));
//	
		LocalTime lTime = LocalTime.now(ZoneId.of("US/Alaska"));
		System.out.println(lTime);

		lTime = LocalTime.now();
		System.out.println(lTime.plusHours(4));

		lTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
		System.out.println("truncated to seconds " + lTime);

		LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		System.out.println("LocalDateTime: " + ldt);

		Chronology chronology = Chronology.ofLocale(Locale.TRADITIONAL_CHINESE);
		System.out.println("chronology " + chronology.eras());

		ZoneId.getAvailableZoneIds().stream().forEach(x -> System.out.println(x));

		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Indian/Kerguelen"));
		System.out.println("ZonedDateTime " + now);
		now = now.plusHours(6);
		System.out.println(now.getDayOfWeek());
		System.out.println("ZonedDateTime " + now);

//		ZoneOffsetTransition zot = ZoneId.systemDefault().getRules().getTransition(LocalDateTime.now());
//		System.out.println(zot);
//		System.out.println(zot.getOffsetAfter());

		OffsetDateTime odt = OffsetDateTime.now(ZoneId.systemDefault());
		System.out.println(odt);

		odt = odt.minusMonths(4);

		System.out.println(odt);

		Duration dur = Duration.ofHours(6);
		System.out.println(dur);
		dur = dur.plusMinutes(30);
		System.out.println(dur);

		ldt = LocalDateTime.now().plus(dur);
		System.out.println(ldt);

		// Date based year, month, day
		Period period = Period.ofMonths(3);
		System.out.println(ldt.plus(period));

		// enum classes Year, YearMonth, MonthDay, Month, DayOfWeek

		System.out.println(MonthDay.now().getDayOfMonth());

		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println(instant.getEpochSecond());

		Clock clock = Clock.systemDefaultZone();
		System.out.println("Clock: " + clock.instant());
		clock = Clock.tick(clock, Duration.ofSeconds(30));
		System.out.println("Clock: " + clock.instant());

		DateTimeFormatter dtPattern = DateTimeFormatter.ofPattern("EEEE MM/dd/yyyy 'at' hh:mm a z");
		System.out.println(dtPattern.format(ZonedDateTime.now()));

		LocalDateTime ldt1 = LocalDateTime.now();
		LocalDateTime ldt2 = ldt1.plusDays(3);
		System.out.println("Days beteen " + ldt1 + " and " + ldt2 + " = " + DAYS.between(ldt1, ldt2));
		System.out.println("Hours beteen " + ldt1 + " and " + ldt2 + " = " + HOURS.between(ldt1, ldt2));

		Period period1 = Period.between(LocalDate.of(1963, 3, 11), LocalDate.now());
		System.out.println("years: " + period1.getYears());
		System.out.println("Months: " + period1.getMonths());
		System.out.println("Total Months: " + period1.toTotalMonths());
	}

}
