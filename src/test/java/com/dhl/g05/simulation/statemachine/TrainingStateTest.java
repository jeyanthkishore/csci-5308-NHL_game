package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;

public class TrainingStateTest {
	private AbstractState state;
	private static SimulationAbstractFactory simulationFactory;
	private static ModelMockAbstractFactory modelMockFactory;
	
	@Before
	public void init() {
		modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = simulationFactory.createTrainingState();
		DateHandler.instance().performDateAssignment(Year.now().getValue());
	}
	
	
	@Test
	public void performTaskTrainingTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ILeague league = mock.getLeague();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue(), Month.AUGUST, 30));
		league.setDaysSinceStatIncrease(140);
		
		state.setLeague(league);
		
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof TradeState);
	}
	
	@Test
	public void performTaskAgingTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ILeague league = mock.getLeague();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		league.setDaysSinceStatIncrease(140);
		
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		schedule.setIsGameCompleted(true);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
	
	@Test
	public void performTaskStimulateGameTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ILeague league = mock.getLeague();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		league.setDaysSinceStatIncrease(140);
		
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		schedule.setIsGameCompleted(false);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setLeague(league);
		
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof SimulateGameState);
	}
	
}
