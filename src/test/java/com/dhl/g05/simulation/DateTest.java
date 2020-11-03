package com.dhl.g05.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DateTest {
	public Date date1;
	public Date date2;
	
	
	@Before
	public void init() {
		date1 = Date.getInstance();
		date2 = Date.getInstance();
	}


	@Test
	public void testGetInstance() {
		assertNotNull(date1);
		assertEquals(date1,date2);
	}


	@Test
	public void testShouldTrainingOccurTrue() {
		date1.setDaysSinceStatIncreaseCheck(101);
		date1.setDaysUntilStatIncreaseCheck(100);
		assertTrue(date1.ShouldTrainingOccur());
	}

	@Test
	public void testShouldTrainingOccurFalse() {
		date1.setDaysSinceStatIncreaseCheck(50);
		date1.setDaysUntilStatIncreaseCheck(100);
		assertFalse(date1.ShouldTrainingOccur());
	}

	
	@Test
	public void testIsTrainingDeadlinePassedTrue() {
		date1.setDaysSinceStatIncreaseCheck(101);
		date1.setDaysUntilStatIncreaseCheck(100);
		assertTrue(date1.IsTrainingDeadlinePassed());
	}
	
	@Test
	public void testIsTrainingDeadlinePassedFalse() {
		date1.setDaysSinceStatIncreaseCheck(50);
		date1.setDaysUntilStatIncreaseCheck(100);
		assertFalse(date1.IsTrainingDeadlinePassed());
	}

	@Test
	public void testProgressDays() {
		date1.setDay(5);
		date1.progressDays(10);
		assertEquals(15,date1.getDay());
	}

	@Test
	public void testGetDay() {
		date1.setDay(10);
		assertEquals(10,date1.getDay());
	}

	@Test
	public void testGetMonth() {
		date1.setMonth(9);
		assertEquals(date1.getMonth(),9);
	}

	@Test
	public void testGetYear() {
		date1.setYear(2020);
		assertEquals(date1.getYear(),2020);
	}

	@Test
	public void testSetDay() {
		date1.setDay(10);
		assertEquals(10,date1.getDay());
	}

	@Test
	public void testSetMonth() {
		date1.setMonth(9);
		assertEquals(date1.getMonth(),9);
	}

	@Test
	public void testSetYear() {
		date1.setYear(2020);
		assertEquals(date1.getYear(),2020);
	}

	@Test
	public void testSetDaysUntilStatIncreaseCheck() {
		date1.setDaysUntilStatIncreaseCheck(10);
		assertEquals(date1.getDaysUntilStatIncreaseCheck(),10);
	}

	@Test
	public void testGetDaysUntilStatIncreaseCheck() {
		date1.setDaysUntilStatIncreaseCheck(10);
		assertEquals(date1.getDaysUntilStatIncreaseCheck(),10);
	}
	
	@Test
	public void testSetDaysSinceStatIncreaseCheck() {
		date1.setDaysSinceStatIncreaseCheck(10);
		assertEquals(date1.getDaysSinceStatIncreaseCheck(),10);
	}

	@Test
	public void testGetDaysSinceStatIncreaseCheck() {
		date1.setDaysSinceStatIncreaseCheck(10);
		assertEquals(date1.getDaysSinceStatIncreaseCheck(),10);
	}
	
}
