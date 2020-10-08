package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockLeagueModel;
import com.dhl.g05.MockPlayerCommunication;

public class PlayerChoiceStateTest {
	public PlayerChoiceState state;
	private StateMachine stateMachine;
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new PlayerChoiceState(stateMachine);
		stateMachine.setCurrentState(state);
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
		stateMachine.setLeagueModel(new MockLeagueModel());

	}
	
	@Test
	public void testEnter() {
		assertTrue(state.enter());
		assertNotEquals(stateMachine.getCurrentState(),state);
	}

	@Test
	public void testPerformStateTask() {
		assertTrue(state.performStateTask());
		assertNotNull(state.getChoice());
	}

	@Test
	public void testExitCreate() {
		state.setChoice("create");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}
	
	@Test
	public void testExitLoad() {
		state.setChoice("load");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof LoadTeamState);
	}

}
