package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TrainingConfigTest {
	
	@Test
	public void getDaysUntilStatusTest() {
		ITraining train = new TrainingConfig();
		train.setDaysUntilStatIncreaseCheck(100);
		assertSame(100,train.getDaysUntilStatIncreaseCheck());
	}
	
	@Test
	public void setDaysUntilStatusTest() {
		ITraining train = new TrainingConfig();
		train.setDaysUntilStatIncreaseCheck(110);
		assertSame(110,train.getDaysUntilStatIncreaseCheck());
	}
	
	@Test
	public void validationTest() {
		ITraining train = new TrainingConfig();
		train.setDaysUntilStatIncreaseCheck(-9);
		assertSame(TrainingConstant.TrainingDaysError,train.Validate());
	}
	
	@Test
	public void validationExceedTest() {
		ITraining train = new TrainingConfig();
		train.setDaysUntilStatIncreaseCheck(400);
		assertSame(TrainingConstant.TrainingDaysError,train.Validate());
	}
	
	@Test
	public void validationSuccessTest() {
		ITraining train = new TrainingConfig();
		train.setDaysUntilStatIncreaseCheck(100);
		assertSame(TrainingConstant.Success,train.Validate());
	}
}
