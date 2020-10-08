package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.MockPlayerCommunication;

public class LoadTeamStateTest {
	private LoadTeamState state;
	private StateMachine stateMachine;
	
	
	@Before
	public void init() {
		stateMachine = new StateMachine();
		state = new LoadTeamState(stateMachine);
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
		state.performStateTask();
		assertNotNull(state.getOuterStateMachine().getLeague());
	}
	

	@Test
	public void testExit() {
		state.exit();
		assertNotNull(state.getOuterStateMachine().getLeague());
		assertNotEquals(state,stateMachine.getCurrentState());
	}


}
