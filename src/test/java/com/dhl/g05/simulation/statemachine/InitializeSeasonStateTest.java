package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.AbstractState;
import com.dhl.g05.simulation.statemachine.AdvanceTimeState;

public class InitializeSeasonStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createInitializeSeasonState();
	}
	
	@Test
	public void seasonInitializeTest() {
		LeagueMockData mock = new LeagueMockData();
		state.setLeague(mock.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
	
	@Test
	public void dateInitializeTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		state.setLeague(mock.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
	
}
