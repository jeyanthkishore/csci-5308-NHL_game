package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class TrainingConfigTest {
	
	private static SimulationAbstractFactory simulationFactory;
	
	@Before
	public void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	}
	
	@Test
	public void getDaysUntilStatusTest() {
		ITraining train = simulationFactory.createTrainingConfig();
		train.setDaysUntilStatIncreaseCheck(100);
		assertSame(100,train.getDaysUntilStatIncreaseCheck());
	}
	
	@Test
	public void setDaysUntilStatusTest() {
		ITraining train = simulationFactory.createTrainingConfig();
		train.setDaysUntilStatIncreaseCheck(110);
		assertSame(110,train.getDaysUntilStatIncreaseCheck());
	}
	
	@Test
	public void validationTest() {
		ITraining train = simulationFactory.createTrainingConfig();
		train.setDaysUntilStatIncreaseCheck(-9);
		assertSame(TrainingConstant.TrainingDaysError,train.Validate());
	}
	
	@Test
	public void validationExceedTest() {
		ITraining train = simulationFactory.createTrainingConfig();
		train.setDaysUntilStatIncreaseCheck(400);
		assertSame(TrainingConstant.TrainingDaysError,train.Validate());
	}
	
	@Test
	public void validationSuccessTest() {
		ITraining train = simulationFactory.createTrainingConfig();
		train.setDaysUntilStatIncreaseCheck(100);
		assertSame(TrainingConstant.Success,train.Validate());
	}
}
