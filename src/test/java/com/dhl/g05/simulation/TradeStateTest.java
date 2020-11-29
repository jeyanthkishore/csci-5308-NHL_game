package com.dhl.g05.simulation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.LeagueMockData;

public class TradeStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createTradeState();
	}
	
	@Test
	public void performTaskTest() {
		LeagueMockData data = new LeagueMockData();
		state.setLeague(data.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
}
