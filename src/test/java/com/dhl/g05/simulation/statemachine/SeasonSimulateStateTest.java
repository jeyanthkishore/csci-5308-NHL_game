package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class SeasonSimulateStateTest {
	private AbstractState state;

	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createStimulateState(0);
	}

	@Test
	public void testEnter() {
		state.enter();
		state.performStateTask();
		assertFalse(state.exit());
	}

}
