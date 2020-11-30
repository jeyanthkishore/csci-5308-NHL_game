package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertSame;
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
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.ILeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;

public class SimulateGameStateTest {
	private AbstractState state;
	private SimulationAbstractFactory simulateFactory;

	@Before
	public void init() {
		simulateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = simulateFactory.createStimulateGameState();
	}
	
	@Test
	public void simulateGameTest() {
		SimulationMockAbstractFactory simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState();
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		
		IScheduleModel schedule = simulateFactory.createScheduleModel();
		List<IScheduleModel> scheduleList = new ArrayList<>();
		DateHandler.instance().performDateAssignment(Year.now().getValue());
		ITeam teamA = modelFactory.createTeamModel();
		teamA.setTeamStrength(40);
		ITeam teamB = modelFactory.createTeamModel();
		teamA.setTeamStrength(30);
		schedule.setFirstTeam(teamA);
		schedule.setSecondTeam(teamB);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue(), Month.NOVEMBER, 30));
		schedule.setIsGameCompleted(false);
		scheduleList.add(schedule);
		ILeagueSchedule leagueSchdeule = simulateFactory.createLeagueSchedule();
		leagueSchdeule.setRegularSeasonSchedule(scheduleList);
		
		ILeague league = modelMockFactory.createLeagueMockData().getLeague();	
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue(), Month.NOVEMBER, 30));
		league.setLeagueSchedule(leagueSchdeule);
		
		List<IStandingModel> leagueStandings = simulationMockFactory.createStandingMock().createDummyStandings(league);
		ILeagueStanding leagueStanding = simulateFactory.createLeagueStanding();
		leagueStanding.setStandingList(leagueStandings);
		league.setLeagueStanding(leagueStanding);
		
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertSame(teamA,schedule.getWinningTeam());
		assertTrue(state.getNextState() instanceof InjuryCheckState);
	}
}
