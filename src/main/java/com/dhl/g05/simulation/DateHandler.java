package com.dhl.g05.simulation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import static java.time.temporal.ChronoUnit.DAYS;

public class DateHandler {
	private static DateHandler instance;
	private static LocalDate regularSeasonStartDate;
	private static LocalDate regularSeasonEndDate;
	private static LocalDate playOffStartDate;
	private static LocalDate playOffEndDate;
	private static LocalDate tradeDeadLine;
	private static LocalDate playerDraftDate;

	public void performDateAssignment(int year) {
		LocalDate regularSeason = LocalDate.of(year + 1, Month.APRIL, 1);
		regularSeasonStartDate = LocalDate.of(year, Month.OCTOBER, 1);
		regularSeasonEndDate = regularSeason.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		playOffStartDate  = regularSeason.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.WEDNESDAY));
		playOffEndDate = LocalDate.of(year + 1, Month.JUNE, 1);
		LocalDate tradeMonth = LocalDate.of(year + 1, Month.FEBRUARY, 1);
		tradeDeadLine = tradeMonth.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
		playerDraftDate = LocalDate.of(year + 1, Month.JULY, 15);
	}

	public static DateHandler instance() {
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

	public long getDaysBetweenRegularSeason() {
		return DAYS.between(regularSeasonStartDate, regularSeasonEndDate);
	}

	public long getDaysBetweenPlayoff() {
		return DAYS.between(playOffStartDate, playOffEndDate);
	}

	public LocalDate getRegularSeasonStartDate() {
		return regularSeasonStartDate;
	}

	public LocalDate getPlayoffSeasonStartDate() {
		return playOffStartDate;
	}

	public boolean isRegularSeasonActive(LocalDate date) {
		return date.isEqual(regularSeasonStartDate) ||
				(date.isAfter(regularSeasonStartDate) && date.isBefore(regularSeasonEndDate)) ||
				date.isEqual(regularSeasonEndDate);
	}

	public boolean isPlayoffSeasonActive(LocalDate date) {
		return date.isEqual(playOffStartDate) ||
				(date.isAfter(playOffStartDate) && date.isBefore(playOffEndDate)) ||
				date.isEqual(playOffEndDate);
	}
	
	public boolean isTodayPlayerDraftDate(LocalDate currentdate) {
		return currentdate.isEqual(playerDraftDate);
	}
	
}
