package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class TradeStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createTradeState();
	}
	
	@Test
	public void performTaskTest() {
		ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		state.setLeague(data.getLeague());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
}
