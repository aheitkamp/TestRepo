package com.kccs.education.wells;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultTokenResolver implements TokenResolver {
	
	private Map<FileNameTokenEnum, DateTimeFormatter> dateFormatterMap = new ConcurrentHashMap<>();
	
	@PostConstruct 
	private void buildDateFormatterMap() {
		FileNameTokenEnum[] values = FileNameTokenEnum.values();
		for (FileNameTokenEnum fileNameTokenEnum : values) {
			dateFormatterMap.put(fileNameTokenEnum, DateTimeFormatter.ofPattern(fileNameTokenEnum.getDateFormatPattern()));
		}
	}
	
	@Autowired
	protected CalendarService calendarService;

	@Override
	public LocalDate adjustDate(LocalDate date) {
		return date;
	}

	@Override
	public LocalDate adjustDate(LocalDate date, String value) {
		return date;
	}

	@Override
	public String resolve(String tokenizedString, FileNameTokenEnum fileNameTokenEnum, LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fileNameTokenEnum.getDateFormatPattern());
		String formatedValue = formatter.format(date);
		return fileNameTokenEnum.getTokenPattern().matcher(tokenizedString).replaceAll(formatedValue);
	}

	public CalendarService getCalendarService() {
		return calendarService;
	}

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
}
