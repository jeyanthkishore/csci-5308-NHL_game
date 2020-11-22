package com.dhl.g05.leaguesimulation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class DateHandler {
	private static DateHandler instance;
	private static LocalDate regularSeasonStartDate;
	private static LocalDate regularSeasonEndDate;
	private static LocalDate playOffStartDate;
	private static LocalDate playOffEndDate;
	private static LocalDate tradeDeadLine;
	
	public void performDateAssignment(int year) {
		LocalDate regularSeason = LocalDate.of(year + 1, Month.APRIL, 1);
		regularSeasonStartDate = LocalDate.of(year, Month.OCTOBER, 1);
		regularSeasonEndDate = regularSeason.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		playOffStartDate  = regularSeason.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.WEDNESDAY));
		playOffEndDate = LocalDate.of(year + 1, Month.JUNE, 1);
		LocalDate tradeMonth = LocalDate.of(year + 1, Month.FEBRUARY, 1);
		tradeDeadLine = tradeMonth.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
	}

	public static DateHandler getInstance() {
		if (instance == null) {
			instance = new DateHandler();
		} 
		return instance;
	}

	public boolean isRegularSeasonEndDate(LocalDate inputDate) {
		return inputDate.isEqual(regularSeasonEndDate);
	}

	public boolean isTradeDeadlinePassed(LocalDate inputDate) {
		return inputDate.isAfter(tradeDeadLine);
	}
}
