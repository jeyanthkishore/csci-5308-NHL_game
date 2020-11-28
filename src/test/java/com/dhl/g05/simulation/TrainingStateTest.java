package com.dhl.g05.simulation;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.simulation.AbstractState;
import com.dhl.g05.simulation.AgingState;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.IScheduleModel;
import com.dhl.g05.simulation.ScheduleModel;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.StimulateGameState;
import com.dhl.g05.simulation.TradeState;

public class TrainingStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createTrainingState();
	}
	
	
	@Test
	public void performTaskTrainingTest() {
		LeagueMockData mock = new LeagueMockData();
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
		LeagueMockData data = new LeagueMockData();
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
		LeagueMockData data = new LeagueMockData();
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
