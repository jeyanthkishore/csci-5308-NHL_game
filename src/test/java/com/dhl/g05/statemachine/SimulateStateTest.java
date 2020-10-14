package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class SimulateStateTest {
	public SimulateState state1;
	public StateMock state2;
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	
	@Before
	public void init() {
		outerStateMachine = new StateMachine(new MockPlayerCommunication(), new MockLeagueModel());
		innerStateMachine = new StateMachine(new MockPlayerCommunication(), new MockLeagueModel());
		state1 = new SimulateState(outerStateMachine);
		state2 = new StateMock(innerStateMachine);
		outerStateMachine.setCurrentState(state1);

		state1.setInnerStateMachine(innerStateMachine);
		innerStateMachine.setCurrentState(state2);
		state1.setPlayerInput("3");

	}
	
	@Test
	public void testEnter() {
		assertTrue(state1.enter());
		assertEquals(state1.getInnerStateMachine(), innerStateMachine);
		assertTrue(state1.getInnerStateMachine().getCurrentState() instanceof StateMock);
	}

	@Test
	public void testPerformStateTask() {
	    state1.getInnerStateMachine().setCurrentState(state2);
		assertTrue(state1.performStateTask());
	}

	@Test
	public void testExit() {
		assertTrue(state1.exit());
		assertTrue(state1.getNextState() == null);
	}
	
	@Test
	public void testValidateInputGoodInput() {
		assertTrue(state1.validateInput());
	}
	
	@Test
	public void testValidateInputBadInput() {
		state1.setPlayerInput("not an int");
		assertFalse(state1.validateInput());
	}

}