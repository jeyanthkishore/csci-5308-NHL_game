package com.dhl.g05.statemachine;

import static org.junit.Assert.assertNull;
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
import com.dhl.g05.database.DataBaseFactoryMock;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.IScheduleModel;
import com.dhl.g05.leaguesimulation.ScheduleModel;
import com.dhl.g05.leaguesimulation.StandingsMock;
import com.dhl.g05.player.AbstractPlayerFactory;
import com.dhl.g05.player.PlayerFactory;
import com.dhl.g05.trading.AbstractTradingFactory;
import com.dhl.g05.trading.TradingFactory;

public class PersistStateTest {
	private AbstractState state;
	
	
	 @BeforeClass
	    public static void setup() {
	        AbstractCommunicationFactory.setFactory(new CommunicationFactory());
	        AbstractDataBaseFactory.setFactory(new DataBaseFactoryMock());
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
		state = AbstractStateMachineFactory.getFactory().getPersistState();
	}
	
	@Test
	public void performTaskTest() {
		StandingsMock mock = new StandingsMock();
		ILeague league = mock.createDummyLeague();
		league.setLeagueName("HockeyLeague");
		
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(true);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setCurrentUserTeam("TeamName");
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNull(state.getNextState());
	}
	
	@Test
	public void performTaskAdvancedTimeTest() {
		StandingsMock mock = new StandingsMock();
		ILeague league = mock.createDummyLeague();
		league.setLeagueName("HockeyLeague");
		
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(false);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setCurrentUserTeam("TeamName");
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
}
