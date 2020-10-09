package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockLeagueModel;
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
		stateMachine.setLeagueModel(new MockLeagueModel());

	}

	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotNull(state.getFileName());
	}

	@Test
	public void testPerformStateTask() {
		state.setFileName("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json");
		assertTrue(state.performStateTask());
		assertNotNull(state.getOuterStateMachine());
		assertNotNull(state.getOuterStateMachine().getLeague());
	}

	@Test
	public void testExitWithFile() {
		state.setFileName("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}
	
	@Test
	public void testExitNoFile() {
		state.setFileName("");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof LoadTeamState);
	}

}
