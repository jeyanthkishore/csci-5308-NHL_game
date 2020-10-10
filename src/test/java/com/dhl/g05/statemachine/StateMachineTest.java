package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateMachineTest {
	StateMachine stateMachine;
	StateMock state;

	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new StateMock(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.setLeagueModel(new MockLeagueModel());
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
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
