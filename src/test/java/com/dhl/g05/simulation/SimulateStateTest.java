package com.dhl.g05.simulation;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;

public class SimulateStateTest {
	private AbstractState state;

	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createStimulateState(0);
	}

	@Test
	public void testEnter() {
		state.enter();
		state.performStateTask();
		assertFalse(state.exit());
	}

}
