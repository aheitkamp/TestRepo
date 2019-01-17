package com.kccs.education.wells;

import java.util.regex.Pattern;

public enum FileNameTokenEnum {
	CurrentYear4Digit("\\(YYYY\\)", 0, "yyyy", false),
	CurrentYear2Digit("\\(YY\\)", 0, "yy", false),
	CurrentMonthShortName("\\(CMM\\)", 10, "MMM", false),
	CurrentMonthLongName("\\(CMMM\\)", 10, "MMMM", false),
	CurrentMonth2Digit("\\(CM\\)", 10, "MM", false),
	PriorMonthShortName("\\(PMM\\)", 10, "MMM", false),
	PriorMonthLongName("\\(PMMM\\)", 10, "MMMM", false),
	PriorMonth2Digit("\\(PM\\)", 10, "MM", false),
	LastBusinessDayOfMonth("\\(LBD\\)", 20, "dd", false),
	FirstBusinessDayOfMonth("\\(FBD\\)", 20, "dd", false),
	FixedDayOfMonth("\\(\\$(\\d{1,2})\\$\\)", 20, "dd", true),		// this regx captures the day in group 1
	LastCalendarDayOfMonth("\\(CCL\\)", 20, "dd", false),
	DayOfMonth("\\(DD\\)", 20, "dd", false),
	MidDayOfMonth("\\(MD\\)", 20, "dd", false),
	MidDayOfMonthNotSunday("\\(CCMD\\)", 20, "dd", false);
	
	private static final String ANYTHING_SUFFIX_REGX = ".*$";
	private static final String ANYTHING_PREFIX_REGX = "^.*?";
	
	private String tokenRegularExpression;
	private Integer dateAdjustmentPriority;
	private String dateFormatPattern;
	private boolean requiresData;
	private Pattern tokenPattern;
	private Pattern tokenContainsPattern;

	private FileNameTokenEnum(String tokenRegularExpression, int dateAdjustmentPriority, String dateFormatPattern, boolean requiresData) {
		this.tokenRegularExpression = tokenRegularExpression;
		this.dateAdjustmentPriority = dateAdjustmentPriority;
		this.dateFormatPattern = dateFormatPattern;
		this.requiresData = requiresData;
		compileRegexPatterns();
	}
	
	private void compileRegexPatterns() {
		this.tokenPattern = Pattern.compile(tokenRegularExpression);
		this.tokenContainsPattern = Pattern.compile(ANYTHING_PREFIX_REGX + tokenRegularExpression + ANYTHING_SUFFIX_REGX);
		
	}

	public static FileNameTokenEnum fromValue(String token) {
		for (FileNameTokenEnum fileNameTokenEnum : FileNameTokenEnum.values()) {
			if (token.matches(fileNameTokenEnum.getTokenRegularExpression())) {
				return fileNameTokenEnum;
			}
		}
		return null;
	}

	public String getTokenRegularExpression() {
		return tokenRegularExpression;
	}

	public void setTokenRegularExpression(String tokenRegularExpression) {
		this.tokenRegularExpression = tokenRegularExpression;
	}

	public Integer getDateAdjustmentPriority() {
		return dateAdjustmentPriority;
	}

	public void setDateAdjustmentPriority(Integer dateAdjustmentPriority) {
		this.dateAdjustmentPriority = dateAdjustmentPriority;
	}

	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	
	public boolean isRequiresData() {
		return requiresData;
	}

	public void setRequiresData(boolean requiresData) {
		this.requiresData = requiresData;
	}

	public Pattern getTokenPattern() {
		return tokenPattern;
	}

	public Pattern getTokenContainsPattern() {
		return tokenContainsPattern;
	}
}
