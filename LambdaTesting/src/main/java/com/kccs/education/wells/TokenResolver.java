package com.kccs.education.wells;

import java.time.LocalDate;

public interface TokenResolver {
	public LocalDate adjustDate(LocalDate date);
	public LocalDate adjustDate(LocalDate date, String value);
	public String resolve(String tokenizedString, FileNameTokenEnum fileNameTokenEnum, LocalDate date);
}
