package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ImportStateTest {
	private ImportState state;
	
	@Before
	public void init() {
		state = new ImportState(new StateMachine());
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
		state.exit();
		assertNotNull(state.getOuterStateMachine());
		assertNotEquals(state.getOuterStateMachine().getCurrentState(),state);
	}

}
