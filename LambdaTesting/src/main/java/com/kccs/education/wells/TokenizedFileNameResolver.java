package com.kccs.education.wells;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenizedFileNameResolver {

	@Autowired
	private DefaultTokenResolver defaultTokenResolver;

	@Autowired
	private FirstBusinessDayOfMonthTokenResolver firstBusinessDayOfMonthTokenResolver;

	@Autowired
	private PriorMonthTokenResolver priorMonthTokenResolver;

	@Autowired
	private LastBusinessDayOfMonthTokenResolver lastBusinessDayOfMonthTokenResolver;

	@Autowired
	private FixedDayOfMonthTokenResolver fixedDayOfMonthTokenResolver;

	@Autowired
	private MidDayOfMonthTokenResolver midDayOfMonthTokenResolver;

	@Autowired
	private MidDayOfMonthNotSundayTokenResolver midDayOfMonthNotSundayTokenResolver;
	
	@Autowired
	private LastCalendarDayOfMonthTokenResolver lastCalendarDayOfMonthTokenResolver;

	final private Map<FileNameTokenEnum, TokenResolver> tokenResolverMap = new ConcurrentHashMap<>();

	@PostConstruct
	protected void buildMap() {
		tokenResolverMap.put(FileNameTokenEnum.CurrentYear4Digit, defaultTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.CurrentYear2Digit, defaultTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.CurrentMonth2Digit, defaultTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.CurrentMonthLongName, defaultTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.CurrentMonthShortName, defaultTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.PriorMonth2Digit, priorMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.PriorMonthLongName, priorMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.PriorMonthShortName, priorMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.FirstBusinessDayOfMonth, firstBusinessDayOfMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.LastBusinessDayOfMonth, lastBusinessDayOfMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.FixedDayOfMonth, fixedDayOfMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.DayOfMonth, defaultTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.MidDayOfMonth, midDayOfMonthTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.MidDayOfMonthNotSunday, midDayOfMonthNotSundayTokenResolver);
		tokenResolverMap.put(FileNameTokenEnum.LastCalendarDayOfMonth, lastCalendarDayOfMonthTokenResolver);
	}

	public String resolve(String tokenizedFileName, LocalDate startingdate) {
		Map<FileNameTokenEnum, String> tokenMap = createTokenEnumValueMap(tokenizedFileName);
		LocalDate adjustedDate = adjustDate(tokenMap, startingdate, tokenizedFileName);
		return resolveTokens(tokenizedFileName, tokenMap.keySet(), adjustedDate);
	}

	private String resolveTokens(String tokenizedFileName, Set<FileNameTokenEnum> tokenEnums, LocalDate adjustedDate) {
		String resolvedFileName = tokenizedFileName;
		for (FileNameTokenEnum fileNameTokenEnum : tokenEnums) {
			TokenResolver tokenResolver = tokenResolverMap.get(fileNameTokenEnum);
			resolvedFileName = tokenResolver.resolve(resolvedFileName, fileNameTokenEnum, adjustedDate);
		}
		return resolvedFileName;
	}

	private LocalDate adjustDate(Map<FileNameTokenEnum, String> tokenMap, LocalDate startingdate, String tokenizedFileName) {
		List<FileNameTokenEnum> fileNameTokenEnums = new ArrayList<>(tokenMap.keySet());
		Collections.sort(fileNameTokenEnums, (e1, e2) -> e1.getDateAdjustmentPriority().compareTo(e2.getDateAdjustmentPriority()));
		LocalDate adjustedDate = startingdate;
		for (FileNameTokenEnum fileNameTokenEnum : fileNameTokenEnums) {
			adjustedDate = adjustDate(tokenMap, adjustedDate, fileNameTokenEnum);
		}
		return adjustedDate;
	}

	private LocalDate adjustDate(Map<FileNameTokenEnum, String> tokenMap, LocalDate baseDate, FileNameTokenEnum fileNameTokenEnum) {
		TokenResolver tokenResolver = tokenResolverMap.get(fileNameTokenEnum);
		LocalDate adjustedDate = baseDate;
		if (tokenResolver != null) {
			if (fileNameTokenEnum.isRequiresData()) {
				adjustedDate = tokenResolver.adjustDate(adjustedDate, tokenMap.get(fileNameTokenEnum));
			} else {
				adjustedDate = tokenResolver.adjustDate(adjustedDate);
			}
		}
		return adjustedDate;
	}

	private Map<FileNameTokenEnum, String> createTokenEnumValueMap(String tokenizedFileName) {
		Map<FileNameTokenEnum, String> tokenMap = new HashMap<>();
		for (FileNameTokenEnum fileNameTokenEnum : FileNameTokenEnum.values()) {
			Pattern pattern = fileNameTokenEnum.getTokenContainsPattern();
			Matcher matcher = pattern.matcher(tokenizedFileName);
			if (matcher.find()) {
				if (matcher.groupCount() == 0) {
					tokenMap.put(fileNameTokenEnum, null);
				} else {
					tokenMap.put(fileNameTokenEnum, matcher.group(1));
				}
			}
		}
		return tokenMap;
	}

	public DefaultTokenResolver getDefaultTokenResolver() {
		return defaultTokenResolver;
	}

	public void setDefaultTokenResolver(DefaultTokenResolver defaultTokenResolver) {
		this.defaultTokenResolver = defaultTokenResolver;
	}

	public FixedDayOfMonthTokenResolver getFixedDayOfMonthTokenResolver() {
		return fixedDayOfMonthTokenResolver;
	}

	public void setFixedDayOfMonthTokenResolver(FixedDayOfMonthTokenResolver fixedDayOfMonthTokenResolver) {
		this.fixedDayOfMonthTokenResolver = fixedDayOfMonthTokenResolver;
	}

	public PriorMonthTokenResolver getPriorMonthTokenResolver() {
		return priorMonthTokenResolver;
	}

	public void setPriorMonthTokenResolver(PriorMonthTokenResolver priorMonthTokenResolver) {
		this.priorMonthTokenResolver = priorMonthTokenResolver;
	}

	public FirstBusinessDayOfMonthTokenResolver getFirstBusinessDayOfMonthTokenResolver() {
		return firstBusinessDayOfMonthTokenResolver;
	}

	public void setFirstBusinessDayOfMonthTokenResolver(FirstBusinessDayOfMonthTokenResolver firstBusinessDayOfMonthTokenResolver) {
		this.firstBusinessDayOfMonthTokenResolver = firstBusinessDayOfMonthTokenResolver;
	}

	public LastBusinessDayOfMonthTokenResolver getLastBusinessDayOfMonthTokenResolver() {
		return lastBusinessDayOfMonthTokenResolver;
	}

	public void setLastBusinessDayOfMonthTokenResolver(LastBusinessDayOfMonthTokenResolver lastBusinessDayOfMonthTokenResolver) {
		this.lastBusinessDayOfMonthTokenResolver = lastBusinessDayOfMonthTokenResolver;
	}

	public MidDayOfMonthTokenResolver getMidDayOfMonthTokenResolver() {
		return midDayOfMonthTokenResolver;
	}

	public void setMidDayOfMonthTokenResolver(MidDayOfMonthTokenResolver midDayOfMonthTokenResolver) {
		this.midDayOfMonthTokenResolver = midDayOfMonthTokenResolver;
	}

	public LastCalendarDayOfMonthTokenResolver getLastCalendarDayOfMonthTokenResolver() {
		return lastCalendarDayOfMonthTokenResolver;
	}

	public void setLastCalendarDayOfMonthTokenResolver(LastCalendarDayOfMonthTokenResolver lastCalendarDayOfMonthTokenResolver) {
		this.lastCalendarDayOfMonthTokenResolver = lastCalendarDayOfMonthTokenResolver;
	}

	public MidDayOfMonthNotSundayTokenResolver getMidDayOfMonthNotSundayTokenResolver() {
		return midDayOfMonthNotSundayTokenResolver;
	}

	public void setMidDayOfMonthNotSundayTokenResolver(
			MidDayOfMonthNotSundayTokenResolver midDayOfMonthNotSundayTokenResolver) {
		this.midDayOfMonthNotSundayTokenResolver = midDayOfMonthNotSundayTokenResolver;
	}

}
