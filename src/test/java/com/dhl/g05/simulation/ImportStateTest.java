package com.dhl.g05.simulation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;

public class ImportStateTest {
	private AbstractState state;

	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createImportState();
	}

	@Test
	public void performCreateTeamStateTest() {
		String userInput = "src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNotNull(state.getLeague());
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}

	@Test
	public void performStateTaskFailTest() {
		String userInput = "src/test/java/com/dhl/g05/jsontestfiles/jsonInvalidInfo.json";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		assertFalse(state.performStateTask());
		assertNull(state.getLeague());
	}
	
	@Test
	public void performLeagueNullTaskTest() {
		String userInput = "src/test/java/com/dhl/g05/jsontestfiles/jsonBadConferenceInfo.json";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		assertFalse(state.performStateTask());
		assertNull(state.getLeague());
	}
	
	
	@Test
	public void exitNoFileTest() {
		String userInput = "\r\n";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof LoadTeamState);
	}

}
