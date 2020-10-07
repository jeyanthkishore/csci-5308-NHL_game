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
	}

	@Test
	public void testEnterState() {
		stateMachine.enterState();
		assertNotNull(stateMachine.getCurrentState());
		assertTrue(((StateMock)stateMachine.getCurrentState()).didEnterState());	
	}

	@Test
	public void testRunState() {
		stateMachine.runState();
		assertNotNull(stateMachine.getCurrentState());
		assertTrue(((StateMock)stateMachine.getCurrentState()).didRunState());	
	}

	@Test
	public void testExitState() {
		stateMachine.exitState();
		assertNotNull(stateMachine.getCurrentState());
		assertTrue(((StateMock)stateMachine.getCurrentState()).didExitState());	
	}


}
