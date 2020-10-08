package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockLeagueModel;
import com.dhl.g05.MockPlayerCommunication;
import com.dhl.g05.leagueobjects.LeagueObject;

public class LoadTeamStateTest {
	private LoadTeamState state;
	private StateMachine stateMachine;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new LoadTeamState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
		stateMachine.setLeagueModel(new MockLeagueModel());
		stateMachine.setLeague(new LeagueObject(null, null, null));
	}
	
	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotNull(state.getTeamDetails());
	}
	

	@Test
	public void testPerformStateTask() {
		assertTrue(state.performStateTask());
		assertNotNull(state.getOuterStateMachine().getLeague());
	}
	

	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

}
