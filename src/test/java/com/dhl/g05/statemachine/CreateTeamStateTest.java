package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CreateTeamStateTest {
	private CreateTeamState state;
	private StateMachine stateMachine;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new CreateTeamState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
		stateMachine.setLeagueModel(new MockLeagueModel());
	}

	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotNull(state.getTeamDetails());
		
	}

	@Test
	public void testPerformStateTaskFails() {
		state.setTeamDetails(new HashMap<String,Object>());
		assertFalse(state.performStateTask());
		assertNotNull(state.getTeam());
	}
	
	@Test
	public void testExit() {
		assertTrue(state.exit());
	}


}
