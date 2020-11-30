package com.dhl.g05.simulation;

import com.dhl.g05.simulation.leaguesimulation.StandingMockData;

public class SimulationMockConcreteFactory extends SimulationMockAbstractFactory{

	@Override
	public StandingMockData createStandingMock() {
		return new StandingMockData();
	}

}
