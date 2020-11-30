package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class InitializeSeasonStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createInitializeSeasonState();
	}
	
	@Test
	public void seasonInitializeTest() {
		ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		state.setLeague(mock.getLeague());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
	
	@Test
	public void dateInitializeTest() {
		ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.getLeague().setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		state.setLeague(mock.getLeague());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
	
}
