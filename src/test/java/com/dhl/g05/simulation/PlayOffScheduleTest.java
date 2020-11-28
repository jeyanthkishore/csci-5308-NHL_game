package com.dhl.g05.simulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.AbstractState;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.StateMachineAbstractFactory;
import com.dhl.g05.simulation.TrainingState;

public class PlayOffScheduleTest {
	private AbstractState state;

	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createPlayOffState();
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
