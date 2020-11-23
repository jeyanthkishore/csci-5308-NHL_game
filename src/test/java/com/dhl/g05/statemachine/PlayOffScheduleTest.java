package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.StandingsMock;

import filehandler.DatabaseMockFactory;

public class PlayOffScheduleTest {
	private AbstractState state;
	@BeforeClass
	public static void setup() {
		AbstractCommunicationFactory.setFactory(new CommunicationFactory());
		AbstractDataBaseFactory.setFactory(new DatabaseMockFactory());
		AbstractStateMachineFactory.setFactory(
				new StateMachineFactory(
						AbstractCommunicationFactory.getFactory().getCommunication(),
						new LeagueModelJson()
						)
				);
	}

	@Before
	public void init() {
		state = AbstractStateMachineFactory.getFactory().getPlayOffState();
	}
	
	@Test
	public void performTaskTest() {
		StandingsMock mock = new StandingsMock();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		ILeague league = mock.createDummyLeague();
		league.setLeagueCurrentDate(LocalDate.of(2021, Month.APRIL, 07));
		league.getLeagueStanding().createStandings(league);
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof TrainingState);
	}

}
