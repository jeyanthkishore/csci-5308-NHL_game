package com.dhl.g05.leaguesimulation.leagueschedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.StandingsMock;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class LeagueScheduleTest {

	@Test
	public void getPlayoffScheduleTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		List<ISchedule> scheduleList = new ArrayList<>();
		leagueSchedule.setPlayoffSchedule(scheduleList);
		assertSame(scheduleList,leagueSchedule.getPlayoffSchedule());
	}
	
	@Test
	public void getRegularScheduleTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		List<ISchedule> scheduleList = new ArrayList<>();
		leagueSchedule.setRegularSchedule(scheduleList);
		assertSame(scheduleList,leagueSchedule.getRegularSchedule());
	}
	
	@Test
	public void generateRegularSeasonTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		StandingsMock mock = new StandingsMock();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		ILeague league = mock.createDummyLeague();
		
		ITeam team1 = league.getConferenceDetails().get(0).getDivisionDetails().get(0).getTeamDetails().get(0);
		league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		leagueSchedule.generateRegularSeasonSchedule(league);
		assertNotNull(league.getLeagueSchedule());
		assertEquals(team1,leagueSchedule.getRegularSchedule().get(0).getFirstTeam());
	}
	
	@Test
	public void anyUnplayedGamesOnGivenDateTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		
		List<ISchedule> regularSeasonSchedule = new ArrayList<>();
		ISchedule schedule = new Schedule();
		schedule.setDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.setIsGamePlayed(false);
		regularSeasonSchedule.add(schedule);
		leagueSchedule.setRegularSchedule(regularSeasonSchedule);
		
		assertTrue(leagueSchedule.anyUnplayedGamesOnGivenDate(LocalDate.of(2020, Month.DECEMBER, 07)));
	}
	
	@Test
	public void anyUnplayedGamesOnGivenDateFalseTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		
		List<ISchedule> regularSeasonSchedule = new ArrayList<>();
		ISchedule schedule = new Schedule();
		schedule.setDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.setIsGamePlayed(true);
		regularSeasonSchedule.add(schedule);
		leagueSchedule.setRegularSchedule(regularSeasonSchedule);
		
		assertFalse(leagueSchedule.anyUnplayedGamesOnGivenDate(LocalDate.of(2020, Month.DECEMBER, 07)));
	}
	
	@Test
	public void anyUnplayedPlayOffGamesOnGivenDateTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);
		
		List<ISchedule> playoffSchedule = new ArrayList<>();
		ISchedule schedule = new Schedule();
		schedule.setDate(LocalDate.of(2021, Month.APRIL, 27));
		schedule.setIsGamePlayed(false);
		playoffSchedule.add(schedule);
		leagueSchedule.setPlayoffSchedule(playoffSchedule);
		
		assertTrue(leagueSchedule.anyUnplayedGamesOnGivenDate(LocalDate.of(2021, Month.APRIL, 27)));
	}
	
    @Test
    public void isStanleyCupWinnerDeterminedNoPlayoffTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();
    	assertFalse(leagueSchedule.isStanleyCupWinnerDetermined());
    }
	
    @Test
    public void isStanleyCupWinnerDeterminedNotTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = new Schedule();
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = new Schedule();
        schedule2.setIsGamePlayed(false);
        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        leagueSchedule.setPlayoffSchedule(playoffSchedule);

        assertFalse(leagueSchedule.isStanleyCupWinnerDetermined());
    }
    
    @Test
    public void isStanleyCupWinnerDeterminedTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = new Schedule();
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = new Schedule();
        schedule2.setIsGamePlayed(true);
        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        leagueSchedule.setPlayoffSchedule(playoffSchedule);

        assertTrue(leagueSchedule.isStanleyCupWinnerDetermined());
    }
    
    @Test
    public void getStanleyCupWinnerTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();
		
		IConference conference1 = new ConferenceModel();
		IConference conference2 = new ConferenceModel();
		IDivision division1 = new DivisionModel();
		IDivision division2 = new DivisionModel();
		ITeam team1 = new TeamModel();
		ITeam team2 = new TeamModel();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = new Schedule();
        schedule1.setIsGamePlayed(true);
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(division1);
        schedule1.setFirstTeam(team1);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(division2);
        schedule1.setSecondTeam(team2);
        schedule1.setWinningTeam(team2);
        playoffSchedule.add(schedule1);
        leagueSchedule.setPlayoffSchedule(playoffSchedule);

        assertSame(team2,leagueSchedule.getStanleyCupWinner());
    }
    
    @Test
    public void getScheduledMatchOnThisDateInRegularSeasonTest() {
        ILeagueSchedule scheduleSystem = new LeagueSchedule();
        DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(2020);

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = new Schedule();
        schedule1.setDate(LocalDate.of(2020, Month.DECEMBER, 07));
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = new Schedule();
        schedule2.setDate(LocalDate.of(2020, Month.DECEMBER, 8));
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        ISchedule currentDaySchedule = scheduleSystem.getScheduledMatchOnThisDate(LocalDate.of(2020, Month.DECEMBER, 8));
        assertEquals(LocalDate.of(2020, Month.DECEMBER, 8), currentDaySchedule.getDate());
        assertFalse(currentDaySchedule.getIsGamePlayed());
    }
	
    @Test
    public void getScheduledMatchOnThisDateInPlayOffTest() {
    	ILeagueSchedule scheduleSystem = new LeagueSchedule();
    	DateHandler dateObject  = DateHandler.getInstance();
    	dateObject.performDateAssignment(2020);

    	List<ISchedule> playoffSchedule = new ArrayList<>();
    	ISchedule schedule1 = new Schedule();
    	schedule1.setDate(LocalDate.of(2021, Month.APRIL, 17));
    	schedule1.setIsGamePlayed(true);
    	ISchedule schedule2 = new Schedule();
    	schedule2.setDate(LocalDate.of(2021, Month.APRIL, 28));
    	schedule2.setIsGamePlayed(false);
    	playoffSchedule.add(schedule1);
    	playoffSchedule.add(schedule2);
    	scheduleSystem.setPlayoffSchedule(playoffSchedule);

    	ISchedule currentDaySchedule = scheduleSystem.getScheduledMatchOnThisDate(LocalDate.of(2021, Month.APRIL, 28));
    	assertEquals(LocalDate.of(2021, Month.APRIL, 28), currentDaySchedule.getDate());
    	assertFalse(currentDaySchedule.getIsGamePlayed());
    }
    
    
    @Test
    public void updateScheduleAfterFirstPlayoffGamePlayedTest() {
    	ILeagueSchedule scheduleSystem = new LeagueSchedule();
    	DateHandler dateObject  = DateHandler.getInstance();
    	dateObject.performDateAssignment(2020);

    	IConference conference1 = new ConferenceModel();
        IConference conference2 = new ConferenceModel();
        IConference conference3 = new ConferenceModel();
        IConference conference4 = new ConferenceModel();
        IDivision division1 = new DivisionModel();
        IDivision division2 = new DivisionModel();
        IDivision division3 = new DivisionModel();
        IDivision division4 = new DivisionModel();
        ITeam team1 = new TeamModel();
        ITeam team2 = new TeamModel();
        ITeam team3 = new TeamModel();
        ITeam team4 = new TeamModel();

    	List<ISchedule> playoffSchedule = new ArrayList<>();
    	ISchedule schedule1 = new Schedule();
    	schedule1.setFirstConference(conference1);
    	schedule1.setFirstDivision(division1);
    	schedule1.setFirstTeam(team1);
    	schedule1.setSecondConference(conference2);
    	schedule1.setSecondDivision(division2);
    	schedule1.setSecondTeam(team2);
    	schedule1.setDate(LocalDate.of(2021, Month.APRIL, 28));
    	schedule1.setIsGamePlayed(false);

    	ISchedule schedule2 = new Schedule();
    	schedule1.setFirstConference(conference3);
    	schedule1.setFirstDivision(division3);
    	schedule1.setFirstTeam(team3);
    	schedule1.setSecondConference(conference4);
    	schedule1.setSecondDivision(division4);
    	schedule1.setSecondTeam(team4);
    	schedule1.setIsGamePlayed(true);
    	schedule2.setDate(LocalDate.of(2021, Month.APRIL, 29));

    	playoffSchedule.add(schedule1);
    	playoffSchedule.add(schedule2);
    	scheduleSystem.setPlayoffSchedule(playoffSchedule);

    	schedule1.setWinningTeam(team1);
    	schedule2.setWinningTeam(team4);

    	scheduleSystem.updateScheduleAfterGamePlayed(schedule1);
    	scheduleSystem.updateScheduleAfterGamePlayed(schedule2);
    	List<ISchedule> playoffScheduleList = scheduleSystem.getPlayoffSchedule();

    	assertEquals(3, playoffScheduleList.size());
    	assertTrue(playoffScheduleList.get(0).getIsGamePlayed());
    	assertEquals(team4, playoffScheduleList.get(2).getFirstTeam());
    	assertEquals(LocalDate.of(2021, Month.APRIL, 30), playoffScheduleList.get(2).getDate());
    }
    
    @Test
    public void updateScheduleAfterSecondPlayoffGamePlayedTest() {
    	ILeagueSchedule scheduleSystem = new LeagueSchedule();
    	DateHandler dateObject  = DateHandler.getInstance();
    	dateObject.performDateAssignment(2020);

        IConference conference1 = new ConferenceModel();
        IConference conference2 = new ConferenceModel();
        IConference conference3 = new ConferenceModel();
        IConference conference4 = new ConferenceModel();
        IDivision division1 = new DivisionModel();
        IDivision division2 = new DivisionModel();
        IDivision division3 = new DivisionModel();
        IDivision division4 = new DivisionModel();
        ITeam team1 = new TeamModel();
        ITeam team2 = new TeamModel();
        ITeam team3 = new TeamModel();
        ITeam team4 = new TeamModel();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = new Schedule();
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(division1);
        schedule1.setFirstTeam(team1);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(division2);
        schedule1.setSecondTeam(team2);
        schedule1.setDate(LocalDate.of(2021, Month.APRIL, 20));
        schedule1.setIsGamePlayed(true);
        schedule1.setWinningTeam(team1);

        ISchedule schedule2 = new Schedule();
        schedule2.setFirstConference(conference3);
        schedule2.setFirstDivision(division3);
        schedule2.setFirstTeam(team3);
        schedule2.setSecondConference(conference4);
        schedule2.setSecondDivision(division4);
        schedule2.setSecondTeam(team4);
        schedule2.setDate(LocalDate.of(2021, Month.APRIL, 21));
        schedule2.setIsGamePlayed(false);
        schedule2.setWinningTeam(team3);

        ISchedule schedule3 = new Schedule();
        schedule3.setFirstConference(conference1);
        schedule3.setFirstDivision(division1);
        schedule3.setFirstTeam(team1);
        schedule3.setDate(LocalDate.of(2021, Month.APRIL, 22));

        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        playoffSchedule.add(schedule3);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        scheduleSystem.updateScheduleAfterGamePlayed(schedule1);
        scheduleSystem.updateScheduleAfterGamePlayed(schedule2);
        List<ISchedule> playoffScheduleList = scheduleSystem.getPlayoffSchedule();

        assertEquals(4, playoffScheduleList.size());
        assertTrue(playoffScheduleList.get(1).getIsGamePlayed());
        assertEquals(team1, playoffScheduleList.get(3).getFirstTeam());
        assertEquals(team3, playoffScheduleList.get(3).getSecondTeam());
        assertNull(playoffScheduleList.get(2).getWinningTeam());
        assertEquals(LocalDate.of(2021, Month.APRIL, 22), playoffScheduleList.get(2).getDate());
        assertFalse(playoffScheduleList.get(2).getIsGamePlayed());
    }
    
}
