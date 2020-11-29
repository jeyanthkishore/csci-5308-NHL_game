package com.dhl.g05.simulation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.database.DatabaseMockFactoryState;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.LeagueModel;

public class LoadTeamStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createLoadTeamState();
		DatabaseState state = new DatabaseMockFactoryState();
		ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	}

	
	@Test
	public void operationTest() {
		String userInput = "TeamName";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
        ILeague league = new LeagueModel(); 
        state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertSame(state.getLeague().getLeagueName(),"HockeyLeague");
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}
	
	@Test
	public void operationNoTeamTest() {
		String userInput = "Striker Six";
		ByteArrayInputStream testInput = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(testInput);
		state.enter();
		assertFalse(state.performStateTask());
	}

	@Test
	public void testExit() {
		assertTrue(state.exit());
		assertTrue(state.getNextState() instanceof PlayerChoiceState);
	}

}
