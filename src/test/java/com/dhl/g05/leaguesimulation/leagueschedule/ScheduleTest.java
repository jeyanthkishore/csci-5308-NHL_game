package com.dhl.g05.leaguesimulation.leagueschedule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.StandingsMock;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.LeagueStanding;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class ScheduleTest {

	@Test
	public void getFirstConferenceTest() {
		ISchedule schedule = new Schedule();
		IConference conference = new ConferenceModel();
		schedule.setFirstConference(conference);
		assertSame(conference,schedule.getFirstConference());
	}
	
	@Test
	public void getSecondConferenceTest() {
		ISchedule schedule = new Schedule();
		IConference conference = new ConferenceModel();
		schedule.setSecondConference(conference);
		assertSame(conference,schedule.getSecondConference());
	}
	
	@Test
	public void getFirstDivisionTest() {
		ISchedule schedule = new Schedule();
		IDivision division = new DivisionModel();
		schedule.setFirstDivision(division);
		assertSame(division,schedule.getFirstDivision());
	}
	
	@Test
	public void getSecondDivisionTest() {
		ISchedule schedule = new Schedule();
		IDivision division = new DivisionModel();
		schedule.setSecondDivision(division);
		assertSame(division,schedule.getSecondDivision());
	}
	
	@Test
	public void getFirstTeamTest() {
		ISchedule schedule = new Schedule();
		ITeam team = new TeamModel();
		schedule.setFirstTeam(team);
		assertSame(team,schedule.getFirstTeam());
	}
	
	@Test
	public void getSecondTeamTest() {
		ISchedule schedule = new Schedule();
		ITeam team = new TeamModel();
		schedule.setSecondTeam(team);
		assertSame(team,schedule.getSecondTeam());
	}
	
	@Test
	public void getDateTest() {
		ISchedule schedule = new Schedule();
		LocalDate sampleDate = LocalDate.of(2020, Month.SEPTEMBER, 30);
		schedule.setDate(sampleDate);
		assertSame(sampleDate,schedule.getDate());
	}
	
	@Test
	public void getWinningTeamTest() {
		ISchedule schedule = new Schedule();
		ITeam team = new TeamModel();
		schedule.setWinningTeam(team);
		assertSame(team,schedule.getWinningTeam());
	}
	
	@Test
	public void isGamePlayedTest() {
		ISchedule schedule = new Schedule();
		schedule.setIsGamePlayed(true);
		assertTrue(schedule.getIsGamePlayed());
	}
	
	@Test
	public void generateRegularSeasonTest() {
		IInitializeSchedule schedule = new Schedule();
		JsonMockDataDb mock = new JsonMockDataDb();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		mock.league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.generateRegularSeason(mock.league);
		assertNotNull(mock.league.getLeagueSchedule());
	}
	
	@Test
	public void generatePlayOffTest() {
		IInitializeSchedule schedule = new Schedule();
		StandingsMock dummyStandings = new StandingsMock();
		ILeague league = dummyStandings.createDummyLeague();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		ILeagueStanding standings = new LeagueStanding();
		standings.setStandings(dummyStandings.createDummyStandings(league));
		schedule.generatePlayOff(league,standings);
		assertNotNull(league.getLeagueSchedule());
	}
	
}
