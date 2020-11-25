package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.database.AbstractDataBaseFactory;
import com.dhl.g05.database.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.mockdata.JsonMockDataDb;

public class InitializeSeasonStateTest {
	private AbstractState state;
	@BeforeClass
	public static void setup() {
		AbstractCommunicationFactory.setFactory(new CommunicationFactory());
		AbstractDataBaseFactory.setFactory(new DataBaseFactory());
		AbstractStateMachineFactory.setFactory(
				new StateMachineFactory(
						AbstractCommunicationFactory.getFactory().getCommunication(),
						new LeagueModelJson()
						)
				);
	}

	@Before
	public void init() {
		state = AbstractStateMachineFactory.getFactory().getInitializeSeasonState();
	}
	
	@Test
	public void seasonInitializeTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		state.setLeague(mock.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
	
	@Test
	public void dateInitializeTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		state.setLeague(mock.league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
	
}
