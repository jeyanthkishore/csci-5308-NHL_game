package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

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
	}

	@Test
	public void testEnter() {
		state.enter();
		assertNotNull(state.getTeamDetails());
	}

	@Test
	public void testPerformStateTask() {
		state.performStateTask();
		assertNotNull(state.getTeam());
	}


}
