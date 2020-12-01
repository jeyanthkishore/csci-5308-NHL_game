package com.dhl.g05.simulation;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.simulation.leaguesimulation.StandingMockData;
import com.dhl.g05.simulation.statemachine.MockAbstractState;

public class SimulationMockConcreteFactory extends SimulationMockAbstractFactory{

	@Override
	public StandingMockData createStandingMock() {
		return new StandingMockData();
	}

	@Override
	public MockAbstractState createMockAbstractState(IPlayerCommunication communication) {
		return new MockAbstractState(communication);
	}

}
