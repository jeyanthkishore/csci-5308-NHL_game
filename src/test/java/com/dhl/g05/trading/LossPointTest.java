package com.dhl.g05.trading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LossPointTest {

	@Test
	public void checkLossPointTest1() {

		LossPoint checkLossPoint = new LossPoint();
		checkLossPoint.setLossCount(10);
		checkLossPoint.setLossPoint(8);
		assertTrue(checkLossPoint.checkLossPoint());
	}

	@Test
	public void checkLossPointTest2() {

		LossPoint checkLossPoint = new LossPoint();
		checkLossPoint.setLossCount(0);
		checkLossPoint.setLossPoint(8);
		assertFalse(checkLossPoint.checkLossPoint());
	}

	@Test
	public void checkLossPointTest3() {

		LossPoint checkLossPoint = new LossPoint();
		// lossCount is not set
		checkLossPoint.setLossPoint(8);
		assertFalse(checkLossPoint.checkLossPoint());
	}

}
