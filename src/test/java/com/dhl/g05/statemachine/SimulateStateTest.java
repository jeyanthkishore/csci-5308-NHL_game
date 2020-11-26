package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;

public class SimulateStateTest {
	private AbstractState state;

	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		state = stateFactory.getStimulateState(0);
	}

	@Test
	public void testEnter() {
		state.enter();
		state.performStateTask();
		assertFalse(state.exit());
	}

}
