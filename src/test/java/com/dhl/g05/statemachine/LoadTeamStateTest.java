package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.operation.DbPersistanceMock;
import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class LoadTeamStateTest {
	private LoadTeamState state;
	private StateMachine stateMachine;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine(new MockPlayerCommunication(),new MockLeagueModel());
		state = new LoadTeamState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.getLeagueModel().setLeague(new LeagueObject(null, null, null, new DbPersistanceMock()));
	}
	
	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotNull(state.getTeamDetails());
	}
	

	@Test
	public void testPerformStateTask() {
		assertTrue(state.performStateTask());
		assertNotNull(state.getOuterStateMachine().getLeagueModel().getLeague());
	}
	

	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

}
