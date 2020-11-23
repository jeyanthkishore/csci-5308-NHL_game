package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.TrainingConfig;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.mockdata.JsonMockDataDb;

import filehandler.DatabaseMockFactory;

public class TrainingStateTest {
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
		state = AbstractStateMachineFactory.getFactory().getTrainingState();
	}
	
	
	@Test
	public void performTaskTradeTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		data.league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue(), Month.DECEMBER, 30));
		data.league.setDaysSinceStatIncrease(140);
		state.setLeague(data.league);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof TradeState);
	}
	
	@Test
	public void performTaskAgingTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		data.league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.MARCH, 30));
		data.league.setDaysSinceStatIncrease(140);
		state.setLeague(data.league);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
	
}
