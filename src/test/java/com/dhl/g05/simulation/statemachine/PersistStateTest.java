package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertNull;
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
import com.dhl.g05.database.DatabaseMockAbstractFactory;
import com.dhl.g05.database.DatabaseState;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.StandingMockData;

public class PersistStateTest {
	private AbstractState state;
	private static SimulationAbstractFactory simulationFactory;
	
	@Before
	public void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = simulationFactory.createPersistState();
		DatabaseMockAbstractFactory mockDatabaseState = ApplicationTestConfiguration.instance().getDatabaseMockConcreteFactoryState();
	    DatabaseState state = mockDatabaseState.createMockDatabaseState();
		ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	}
	
	@Test
	public void performTaskTest() {
		SimulationMockAbstractFactory simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		ILeague league = mock.createDummyLeague();
		league.setLeagueName("HockeyLeague");
		
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		schedule.setIsGameCompleted(true);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setCurrentUserTeam("serializeLeague");
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertNull(state.getNextState());
	}
	
	@Test
	public void performTaskAdvancedTimeTest() {
		SimulationMockAbstractFactory simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		ILeague league = mock.createDummyLeague();
		league.setLeagueName("HockeyLeague");
		
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		schedule.setIsGameCompleted(false);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setCurrentUserTeam("serializeLeague");
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceTimeState);
	}
}
