package com.dhl.g05.simulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

import org.junit.BeforeClass;
import org.junit.Test;

public class DateHandlerTest {

	@BeforeClass
	public static void init() {
		DateHandler dateObject  = DateHandler.instance();
		dateObject.performDateAssignment(Year.now().getValue());
	}

	@Test
	public void isRegularSeasonEndDateTest() {
		LocalDate regularSeason = LocalDate.of(Year.now().getValue() + 1, Month.APRIL, 1);
		LocalDate newDate = regularSeason.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));
		assertTrue(DateHandler.instance().isRegularSeasonEndDate(newDate));
	}

	@Test
	public void isRegularSeasonFailEndDateTest() {
		LocalDate regularSeason = LocalDate.of(Year.now().getValue() + 1, Month.APRIL, 1);
		LocalDate newDate = regularSeason.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		assertFalse(DateHandler.instance().isRegularSeasonEndDate(newDate));
	}

	@Test
	public void isTradeDeadlinePassedTest() {
		LocalDate tradeMonth = LocalDate.of(Year.now().getValue() + 1, Month.FEBRUARY, 1);
		LocalDate newDate = tradeMonth.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
		assertTrue(DateHandler.instance().isTradeDeadlinePassed(newDate.plusDays(1)));
	}

	@Test
	public void isTradeDeadlinePassedFailTest() {
		LocalDate tradeMonth = LocalDate.of(Year.now().getValue() + 1, Month.FEBRUARY, 1);
		assertFalse(DateHandler.instance().isTradeDeadlinePassed(tradeMonth.plusDays(1)));
	}

	@Test
	public void getRegularSeasonStartDateTest() {
		assertEquals(LocalDate.of(Year.now().getValue(), Month.OCTOBER, 1),DateHandler.instance().getRegularSeasonStartDate());
	}

	@Test
	public void getPlayoffSeasonStartDateTest() {
		LocalDate regularSeason = LocalDate.of(Year.now().getValue() + 1, Month.APRIL, 1);
		LocalDate newDate  = regularSeason.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.WEDNESDAY));
		assertEquals(newDate,DateHandler.instance().getPlayoffSeasonStartDate());
	}

	@Test
	public void getDaysBetweenRegularSeasonTest() {
		assertEquals(184,DateHandler.instance().getDaysBetweenRegularSeason());
	}

	@Test
	public void getDaysBetweenPlayoffTest() {
		assertEquals(48,DateHandler.instance().getDaysBetweenPlayoff());
	}

	@Test
	public void isRegularSeasonActiveTest() {
		LocalDate date = LocalDate.of(Year.now().getValue(), Month.OCTOBER, 10);
		assertTrue(DateHandler.instance().isRegularSeasonActive(date));
	}

	@Test
	public void isRegularSeasonInactiveTest() {
		LocalDate date = LocalDate.of(Year.now().getValue(), Month.AUGUST, 10);
		assertFalse(DateHandler.instance().isRegularSeasonActive(date));
	}

	@Test
	public void isPlayoffSeasonActiveTest() {
		LocalDate date = LocalDate.of(Year.now().getValue()+1, Month.MAY, 20);
		assertTrue(DateHandler.instance().isPlayoffSeasonActive(date));
	}

	@Test
	public void isPlayoffSeasonInactiveTest() {
		LocalDate date = LocalDate.of(Year.now().getValue(), Month.AUGUST, 20);
		assertFalse(DateHandler.instance().isPlayoffSeasonActive(date));
	}

	@Test
	public void isTodayPlayerDraftDateTest() {
		LocalDate date = LocalDate.of(Year.now().getValue() + 1, Month.JULY, 15);
		assertTrue(DateHandler.instance().isTodayPlayerDraftDate(date));
	}

	@Test
	public void isTodayNotPlayerDraftDateTest() {
		LocalDate date = LocalDate.of(Year.now().getValue() + 1, Month.JULY, 16);
		assertFalse(DateHandler.instance().isTodayPlayerDraftDate(date));
	}

}
