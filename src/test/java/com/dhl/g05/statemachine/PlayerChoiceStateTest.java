package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerChoiceStateTest {
	public PlayerChoiceState state;
	private StateMachine stateMachine;
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new PlayerChoiceState(stateMachine);
		stateMachine.setCurrentState(state);
	}
	
	@Test
	public void testEnter() {
		state.enter();
		assertNotEquals(stateMachine.getCurrentState(),state);
	}

	@Test
	public void testPerformStateTask() {
		state.performStateTask();
		assertNotNull(state.getChoice());
	}

	@Test
	public void testExitCreate() {
		state.setChoice("create");
		state.exit();
		assertTrue(stateMachine.getCurrentState() instanceof CreateTeamState);
	}
	
	@Test
	public void testExitLoad() {
		state.setChoice("load");
		state.exit();
		assertTrue(stateMachine.getCurrentState() instanceof LoadTeamState);
	}

}
