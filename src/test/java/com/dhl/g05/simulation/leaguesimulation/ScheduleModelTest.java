package com.dhl.g05.simulation.leaguesimulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.TeamModel;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.LeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.ScheduleModel;

public class ScheduleModelTest {

	@Test
	public void getFirstConferenceTest() {
		IScheduleModel schedule = new ScheduleModel();
		IConference conference = new ConferenceModel();
		schedule.setFirstConference(conference);
		assertSame(conference,schedule.getFirstConference());
	}
	
	@Test
	public void getSecondConferenceTest() {
		IScheduleModel schedule = new ScheduleModel();
		IConference conference = new ConferenceModel();
		schedule.setSecondConference(conference);
		assertSame(conference,schedule.getSecondConference());
	}
	
	@Test
	public void getFirstDivisionTest() {
		IScheduleModel schedule = new ScheduleModel();
		IDivision division = new DivisionModel();
		schedule.setFirstDivision(division);
		assertSame(division,schedule.getFirstDivision());
	}
	
	@Test
	public void getSecondDivisionTest() {
		IScheduleModel schedule = new ScheduleModel();
		IDivision division = new DivisionModel();
		schedule.setSecondDivision(division);
		assertSame(division,schedule.getSecondDivision());
	}
	
	@Test
	public void getFirstTeamTest() {
		IScheduleModel schedule = new ScheduleModel();
		ITeam team = new TeamModel();
		schedule.setFirstTeam(team);
		assertSame(team,schedule.getFirstTeam());
	}
	
	@Test
	public void getSecondTeamTest() {
		IScheduleModel schedule = new ScheduleModel();
		ITeam team = new TeamModel();
		schedule.setSecondTeam(team);
		assertSame(team,schedule.getSecondTeam());
	}
	
	@Test
	public void getScheduleDateTest() {
		IScheduleModel schedule = new ScheduleModel();
		LocalDate sampleDate = LocalDate.of(2020, Month.SEPTEMBER, 30);
		schedule.setScheduleDate(sampleDate);
		assertSame(sampleDate,schedule.getScheduleDate());
	}
	
	@Test
	public void getWinningTeamTest() {
		IScheduleModel schedule = new ScheduleModel();
		ITeam team = new TeamModel();
		schedule.setWinningTeam(team);
		assertSame(team,schedule.getWinningTeam());
	}
	
	@Test
	public void isGameCompletedTest() {
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(true);
		assertTrue(schedule.getIsGameCompleted());
	}
	
	@Test
	public void generateRegularSeasonTest() {
		IScheduleModel schedule = new ScheduleModel();
		LeagueMockData mock = new LeagueMockData();
		DateHandler.instance().performDateAssignment(2020);
		mock.league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.generateRegularSeason(mock.league);
		assertNotNull(mock.league.getLeagueSchedule());
	}
	
	@Test
	public void generatePlayOffTest() {
		IScheduleModel schedule = new ScheduleModel();
		StandingMockData dummyStandings = new StandingMockData();
		ILeague league = dummyStandings.createDummyLeague();
		DateHandler.instance().performDateAssignment(2020);
		league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		ILeagueStanding standings = new LeagueStanding();
		standings.setStandingList(dummyStandings.createDummyStandings(league));
		schedule.generatePlayOff(league,standings);
		assertNotNull(league.getLeagueSchedule());
	}
	
}
