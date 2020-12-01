package com.dhl.g05.simulation.leaguesimulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.ModelMockAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;

public class ScheduleModelTest {
	private static SimulationAbstractFactory simulationFactory;
	private static ModelAbstractFactory modelFactory;

	@BeforeClass
	public static void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	}

	@Test
	public void getFirstConferenceTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IConference conference = modelFactory.createConferenceModel();
		schedule.setFirstConference(conference);
		assertSame(conference,schedule.getFirstConference());
	}

	@Test
	public void getSecondConferenceTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IConference conference = modelFactory.createConferenceModel();
		schedule.setSecondConference(conference);
		assertSame(conference,schedule.getSecondConference());
	}
	
	@Test
	public void setFirstConferenceTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IConference conference = modelFactory.createConferenceModel();
		schedule.setFirstConference(conference);
		assertSame(conference,schedule.getFirstConference());
	}

	@Test
	public void setSecondConferenceTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IConference conference = modelFactory.createConferenceModel();
		schedule.setSecondConference(conference);
		assertSame(conference,schedule.getSecondConference());
	}

	@Test
	public void getFirstDivisionTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IDivision division = modelFactory.createDivisionModel();
		schedule.setFirstDivision(division);
		assertSame(division,schedule.getFirstDivision());
	}

	@Test
	public void getSecondDivisionTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IDivision division = modelFactory.createDivisionModel();
		schedule.setSecondDivision(division);
		assertSame(division,schedule.getSecondDivision());
	}

	@Test
	public void setFirstDivisionTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IDivision division = modelFactory.createDivisionModel();
		schedule.setFirstDivision(division);
		assertSame(division,schedule.getFirstDivision());
	}

	@Test
	public void setSecondDivisionTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		IDivision division = modelFactory.createDivisionModel();
		schedule.setSecondDivision(division);
		assertSame(division,schedule.getSecondDivision());
	}
	
	@Test
	public void etFirstTeamTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		ITeam team = modelFactory.createTeamModel();
		schedule.setFirstTeam(team);
		assertSame(team,schedule.getFirstTeam());
	}

	@Test
	public void getSecondTeamTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		ITeam team = modelFactory.createTeamModel();
		schedule.setSecondTeam(team);
		assertSame(team,schedule.getSecondTeam());
	}

	@Test
	public void getScheduleDateTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		LocalDate sampleDate = LocalDate.of(2020, Month.SEPTEMBER, 30);
		schedule.setScheduleDate(sampleDate);
		assertSame(sampleDate,schedule.getScheduleDate());
	}
	
	@Test
	public void setScheduleDateTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		LocalDate sampleDate = LocalDate.of(2020, Month.NOVEMBER, 30);
		schedule.setScheduleDate(sampleDate);
		assertSame(sampleDate,schedule.getScheduleDate());
	}

	@Test
	public void getWinningTeamTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		ITeam team = modelFactory.createTeamModel();
		schedule.setWinningTeam(team);
		assertSame(team,schedule.getWinningTeam());
	}
	
	@Test
	public void setWinningTeamTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		ITeam team = modelFactory.createTeamModel();
		schedule.setWinningTeam(team);
		assertSame(team,schedule.getWinningTeam());
	}

	@Test
	public void isGameCompletedTest() {
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		schedule.setIsGameCompleted(true);
		assertTrue(schedule.getIsGameCompleted());
	}

	@Test
	public void generateRegularSeasonTest() {
		ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		DateHandler.instance().performDateAssignment(2020);
		mock.getLeague().setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.generateRegularSeason(mock.getLeague());
		assertNotNull(mock.getLeague().getLeagueSchedule());
	}

	@Test
	public void generatePlayOffTest() {
		SimulationMockAbstractFactory simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState();
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		StandingMockData dummyStandings = simulationMockFactory.createStandingMock();
		ILeague league = dummyStandings.createDummyLeague();
		DateHandler.instance().performDateAssignment(2020);
		league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		ILeagueStanding standings = simulationFactory.createLeagueStanding();
		standings.setStandingList(dummyStandings.createDummyStandings(league));
		schedule.generatePlayOff(league,standings);
		assertNotNull(league.getLeagueSchedule());
	}

}
