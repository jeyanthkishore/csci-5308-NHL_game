package com.dhl.g05.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.operation.DbPersistanceMock;
import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockLeagueModelValidationFails;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class LoadTeamStateTest {
	private LoadTeamState state;
	private StateMachine stateMachine;


	@Before
	public void init() {
		stateMachine = new StateMachine(new MockPlayerCommunication(),new MockLeagueModel());
		state = new LoadTeamState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.getLeagueModel().setLeague(new LeagueModel(null, null, null,null, null,null, new DbPersistanceMock()));
	}

	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotNull(state.getTeamName());
	}

	@Test
	public void testPerformStateTaskSucceeds() {
		assertTrue(state.performStateTask());
		assertNotNull(state.getOuterStateMachine().getLeagueModel().getLeague());
	}

	@Test
	public void testPerformStateTaskFails() {
		state.getOuterStateMachine().setLeagueModel(new MockLeagueModelValidationFails());
		state.setTeamName("");
		assertFalse(state.performStateTask());
		assertNull(state.getNextState());
	}

	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

}
