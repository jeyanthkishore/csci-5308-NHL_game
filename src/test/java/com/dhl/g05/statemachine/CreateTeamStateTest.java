package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockLeagueModelValidationFails;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class CreateTeamStateTest {
	private CreateTeamState state;
	private StateMachine stateMachine;
	private HashMap<String,Object> teamDetails;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine(new MockPlayerCommunication(),new MockLeagueModel());
		state = new CreateTeamState(stateMachine);
		stateMachine.setCurrentState(state);
		state.setTeamDetails(new HashMap<String,Object>());
		teamDetails = new HashMap<String,Object>();
		teamDetails.put("teamName", "name");
		teamDetails.put("teamManager", "name");
		teamDetails.put("teamCoach", "name");
	}

	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotNull(state.getTeamDetails());
		
	}
	
	@Test
	public void testPerformStateTask() {
		state.setTeamDetails(teamDetails);
		state.getOuterStateMachine().setLeagueModel(new MockLeagueModel());
		assertTrue(state.performStateTask());
		assertNotNull(state.getTeam());
	}

	@Test
	public void testPerformStateTaskFails() {
		state.getOuterStateMachine().setLeagueModel(new MockLeagueModelValidationFails());
		assertFalse(state.performStateTask());
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}
	
	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

	
	@Test
	public void testExitFails() {
		state.getOuterStateMachine().setLeagueModel(new MockLeagueModelValidationFails());
		state.performStateTask();
		assertFalse(state.exit());
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}

}
