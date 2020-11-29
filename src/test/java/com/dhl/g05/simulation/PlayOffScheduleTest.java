package com.dhl.g05.simulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;

public class PlayOffScheduleTest {
	private AbstractState state;

	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = stateFactory.createPlayOffState();
	}
	
	@Test
	public void performTaskTest() {
		StandingMockData mock = new StandingMockData();
		DateHandler.instance().performDateAssignment(2020);
		ILeague league = mock.createDummyLeague();
		league.setLeagueCurrentDate(LocalDate.of(2021, Month.APRIL, 07));
		league.getLeagueStanding().createStandingList(league);
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNotNull(state.getLeague().getLeagueSchedule().getPlayoffSeasonSchedule());
		assertTrue(state.getNextState() instanceof TrainingState);
	}

}
