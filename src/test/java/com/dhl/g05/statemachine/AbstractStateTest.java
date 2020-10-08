package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockLeagueModel;
import com.dhl.g05.MockPlayerCommunication;

public class AbstractStateTest {
	private StateMock state;
	private StateMachine stateMachine;
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		stateMachine.setLeagueModel(new MockLeagueModel());
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
		
		state = new StateMock(stateMachine);
	}
	
	@Test
	public void testRunInnerStateMachine() {
		StateMachine innerStateMachine = stateMachine;
		state.setInnerStateMachine(innerStateMachine);
		StateMock newInnerState = new StateMock(innerStateMachine);

		assertNotNull(state.getInnerStateMachine());
		state.getInnerStateMachine().setCurrentState(newInnerState);
		assertTrue(state.runInnerStateMachine());

		assertNotEquals(state.getInnerStateMachine().getCurrentState(),state);
		assertEquals(state.getInnerStateMachine().getCurrentState(),newInnerState);
	}
	

	@Test
	public void testMarkStateCompleted() {
		state.markStateCompleted();
		assertTrue(state.didStateComplete());
	}


}
