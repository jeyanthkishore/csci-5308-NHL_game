package com.dhl.g05.statemachine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.StandingsMock;

public class PlayOffScheduleTest {
	private AbstractState state;

	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		state = stateFactory.getPlayOffState();
	}
	
	@Test
	public void performTaskTest() {
		StandingsMock mock = new StandingsMock();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
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
