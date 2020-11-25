package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.database.AbstractDataBaseFactory;
import com.dhl.g05.database.DataBaseFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.IScheduleModel;
import com.dhl.g05.leaguesimulation.ScheduleModel;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.trading.AbstractTradingFactory;
import com.dhl.g05.trading.TradingFactory;

public class TrainingStateTest {
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
	        AbstractPlayerFactory.setFactory(new PlayerFactory());
	        AbstractTradingFactory.setFactory(new TradingFactory());
	    }

	@Before
	public void init() {
		state = AbstractStateMachineFactory.getFactory().getTrainingState();
	}
	
	
	@Test
	public void performTaskTrainingTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue(), Month.AUGUST, 30));
		mock.league.setDaysSinceStatIncrease(140);
		
		state.setLeague(mock.league);
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
		data.league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		data.league.setDaysSinceStatIncrease(140);
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(true);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		data.league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setLeague(data.league);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
	
	@Test
	public void performTaskStimulateGameTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		data.league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		data.league.setDaysSinceStatIncrease(140);
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(false);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		data.league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setLeague(data.league);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof StimulateGameState);
	}
	
}
