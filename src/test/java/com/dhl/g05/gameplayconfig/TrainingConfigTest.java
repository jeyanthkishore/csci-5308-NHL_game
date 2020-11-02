package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TrainingConfigTest {
	
	@Test
	public void trainingConfigConstructorTest() {
		TrainingConfig train = new TrainingConfig(100);
		assertSame(100,train.getDaysUntilStatIncreaseCheck());
	}
	
	@Test
	public void getDaysUntilStatusTest() {
		TrainingConfig train = new TrainingConfig(100);
		assertSame(100,train.getDaysUntilStatIncreaseCheck());
	}
	
}
