package com.dhl.g05.simulation;

public class SimulationMockConcreteFactory extends SimulationMockAbstractFactory{

	@Override
	public StandingMockData createStandingMock() {
		return new StandingMockData();
	}

}
