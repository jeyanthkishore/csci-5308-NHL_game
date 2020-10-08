package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockPlayerCommunication;

public class CreateTeamStateTest {
	private CreateTeamState state;
	private StateMachine stateMachine;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new CreateTeamState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
	}

	@Test
	public void testEnter() {
		state.enter();
		assertNotNull(state.getTeamDetails());
	}

	@Test
	public void testPerformStateTask() {
		assertNotNull(state.getTeamDetails());
		state.performStateTask();
		assertNotNull(state.getTeam());
	}


}
