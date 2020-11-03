package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class StateMachineTest {
	StateMachine stateMachine;
	StateMock state;

	@Before
	public void init() {
		stateMachine = new StateMachine(new MockPlayerCommunication(),new MockLeagueModel());
		state = new StateMock(stateMachine);
		stateMachine.setCurrentState(state);
		state.setNextState(null);
	}

	@Test
	public void testEnterState() {
		assertNotNull(stateMachine.getCurrentState());
		assertTrue(stateMachine.enterState());
	}

	@Test
	public void testRunState() {
		assertNotNull(stateMachine.getCurrentState());
		assertTrue(stateMachine.runState());
	}

	@Test
	public void testExitState() {
		assertTrue(stateMachine.exitState());
	}
}
