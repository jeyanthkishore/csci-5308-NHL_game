package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class CreateTeamStateTest {
	private CreateTeamState state;
	private StateMachine stateMachine;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine(new MockPlayerCommunication(),new MockLeagueModel());
		state = new CreateTeamState(stateMachine);
		stateMachine.setCurrentState(state);

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
