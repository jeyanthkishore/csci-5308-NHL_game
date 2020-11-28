package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.MockPlayerCommunication;
import com.dhl.g05.database.DatabaseMockFactoryState;
import com.dhl.g05.database.DatabaseState;

public class LoadTeamStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createLoadTeamState();
		DatabaseState state = new DatabaseMockFactoryState();
		ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	}

	
	@Test
	public void operationTest() {
		String userInput = "Striker Six";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}
	
//	@Test
//	public void operationNoTeamTest() {
//		communicate.commandLineInput("Striker");
//		state.enter();
//		assertFalse(state.performStateTask());
//	}

	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

}
