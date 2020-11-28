package com.dhl.g05.simulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.MockPlayerCommunication;
import com.dhl.g05.simulation.AbstractState;
import com.dhl.g05.simulation.CreateTeamState;
import com.dhl.g05.simulation.LoadTeamState;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class ImportStateTest {
	private AbstractState state;
	private static MockPlayerCommunication communicate;

	@Before
	public void init() {
//		communicate = new MockPlayerCommunication();
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createImportState();
	}

	@Test
	public void testPerformStateTask() {
		String userInput = "src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNotNull(state.getLeague());;
		assertTrue(state.getNextState() instanceof CreateTeamState);
	}

	@Test
	public void testPerformStateFailTask() {
		String userInput = "src/test/java/com/dhl/g05/jsontestfiles/jsonInvalidInfo.json";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNotNull(state);
		assertNull(state.getLeague());
	}
	
	@Test
	public void testPerformLeagueNullTask() {
		String userInput = "src/test/java/com/dhl/g05/jsontestfiles/jsonBadConferenceInfo.json";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNotNull(state);
		assertNull(state.getLeague());
	}
	
	
	@Test
	public void testExitNoFile() {
		String userInput = "\r\n";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof LoadTeamState);
	}

}
