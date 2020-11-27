package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;

public class PlayerChoiceStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		state = stateFactory.getPlayerChoiceState();
	}

	@Test
	public void performStateTaskTest() {
		String userInput = "5";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof SimulateState);
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
