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
		state = new PlayerChoiceState(stateMachine, "message");
		stateMachine.setCurrentState(state);
		stateMachine.setPlayerCommunication(new MockPlayerCommunication());
		stateMachine.setLeagueModel(new MockLeagueModel());

	}
	
	@Test
	public void testPerformStateTask() {
		assertTrue(state.performStateTask());
		assertNotNull(state.getChoice());
	}

	@Test
	public void testExitToCreate() {
		state.setChoice("create");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}
	
	@Test
	public void testExitToLoad() {
		state.setChoice("load");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof LoadTeamState);
	}
	
	@Test
	public void testExitToSimulate() {
		state = new PlayerChoiceState(stateMachine, "message", new SimulateState(stateMachine));
		state.setChoice("1");
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof SimulateState);
		assertTrue(state.getNextState().getPlayerInput().equals("1"));
	}

}
