package com.dhl.g05.trading;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CheckLossPointTest {

	@Test
	public void checkLossPointTest() {

		CheckLossPoint checkLossPoint = new CheckLossPoint();
		checkLossPoint.setLossCount(10);
		checkLossPoint.setLossPoint(8);
		assertTrue(checkLossPoint.checkLossPoint());
	}

}
