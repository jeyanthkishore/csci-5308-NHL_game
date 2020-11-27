package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.mockdata.JsonMockDataDb;

public class TradeStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		state = stateFactory.getTradeState();
	}
	
	@Test
	public void performTaskTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		state.setLeague(data.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
}
