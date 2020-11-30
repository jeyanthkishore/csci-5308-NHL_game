package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.AbstractState;
import com.dhl.g05.simulation.statemachine.SeasonSimulateState;

public class PlayerChoiceStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createPlayerChoiceState();
	}

	@Test
	public void performStateTaskTest() {
		String userInput = "5";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof SeasonSimulateState);
	}

	@Test
	public void performStateTaskFailTest() {
		String userInput = "Jeyanth";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		assertFalse(state.performStateTask());
	}
	
}
