package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.database.DatabaseMockAbstractFactory;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class LoadTeamStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createLoadTeamState();
		DatabaseMockAbstractFactory mockDatabaseState = ApplicationTestConfiguration.instance().getDatabaseMockConcreteFactoryState();
	    DatabaseState state = mockDatabaseState.createMockDatabaseState();
		ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	}

	
	@Test
	public void loadTeamTest() {
		String userInput = "deserializeLeague";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
        
		state.enter();
		state.performStateTask();
		state.exit();
		assertSame(state.getLeague().getLeagueName(),"HockeyLeague");
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}
	
	@Test
	public void noUniqueTeamTest() {
		String userInput = "Striker Six";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(testInput);
		state.enter();
		assertFalse(state.performStateTask());
	}

}
