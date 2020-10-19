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
	public void testSaveDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testShouldTrainingOccur() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsTrainingDeadlinePassed() {
		fail("Not yet implemented");
	}

	@Test
	public void testProgressDays() {
		fail("Not yet implemented");
	}

	@Test
	public void testProgressWeeks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDaysUntilStatIncreaseCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDaysUntilStatIncreaseCheck() {
		fail("Not yet implemented");
	}

}
