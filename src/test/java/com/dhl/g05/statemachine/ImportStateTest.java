package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockPlayerCommunication;

public class ImportStateTest {
	private ImportState state;
	private StateMachine stateMachine;
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new ImportState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
	}

	@Test
	public void testEnter() {
		state.enter();
		assertNotNull(state.getFileName());
	}

	@Test
	public void testPerformStateTask() {
		state.performStateTask();
		assertNotNull(state.getOuterStateMachine());
		assertNotNull(state.getOuterStateMachine().getLeague());
	}

	@Test
	public void testExit() {
		assertNotNull(state.getOuterStateMachine().getLeague());
		state.exit();
		assertNotNull(state.getOuterStateMachine());
		assertNotEquals(state.getOuterStateMachine().getCurrentState(),state);
	}

}
