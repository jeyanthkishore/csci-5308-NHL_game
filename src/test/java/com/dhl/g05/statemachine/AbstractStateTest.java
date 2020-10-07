package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractStateTest {
private ImportState state;
	
	@Before
	public void init() {
		state = new ImportState(new StateMachine());
	}
	
	@Test
	public void testTransitionState() {
		state.transitionState(new CreateTeamState(state.getOuterStateMachine()));
		assertNotNull(state.getOuterStateMachine());
		assertNotEquals(state.getOuterStateMachine().getCurrentState(),state);
	}

	@Test
	public void testRunInnerStateMachine() {
		StateMachine innerState = new StateMachine();
		state.setInnerStateMachine(innerState);
		StateMock newState = new StateMock(innerState);

		assertNotNull(state.getInnerStateMachine());
		state.getInnerStateMachine().setCurrentState(newState);
		state.runInnerStateMachine();

		assertNotEquals(state.getInnerStateMachine().getCurrentState(),state);
		assertEquals(state.getInnerStateMachine().getCurrentState(),newState);
	}

}
