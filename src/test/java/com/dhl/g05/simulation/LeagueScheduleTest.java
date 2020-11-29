package com.dhl.g05.simulation;

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

import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.TeamModel;

public class LeagueScheduleTest {

	@Test
	public void getPlayoffScheduleTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		List<IScheduleModel> scheduleList = new ArrayList<>();
		leagueSchedule.setPlayoffSeasonSchedule(scheduleList);
		assertSame(scheduleList,leagueSchedule.getPlayoffSeasonSchedule());
	}
	
	@Test
	public void getRegularScheduleTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		List<IScheduleModel> scheduleList = new ArrayList<>();
		leagueSchedule.setRegularSeasonSchedule(scheduleList);
		assertSame(scheduleList,leagueSchedule.getRegularSeasonSchedule());
	}
	
	@Test
	public void generateRegularSeasonTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		StandingMockData mock = new StandingMockData();
		DateHandler.instance().performDateAssignment(2020);
		ILeague league = mock.createDummyLeague();
		
		ITeam team1 = league.getConferenceDetails().get(0).getDivisionDetails().get(0).getTeamDetails().get(0);
		league.setLeagueCurrentDate(LocalDate.of(2020, Month.DECEMBER, 07));
		leagueSchedule.generateRegularSeasonSchedule(league);
		assertNotNull(league.getLeagueSchedule());
		assertEquals(team1,leagueSchedule.getRegularSeasonSchedule().get(0).getFirstTeam());
	}
	
	@Test
	public void generatePlayoffScheduleTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		StandingMockData mock = new StandingMockData();
		DateHandler.instance().performDateAssignment(2020);
		ILeague league = mock.createDummyLeague();
		league.getLeagueStanding().createStandingList(league);
		
		ITeam team1 = league.getConferenceDetails().get(0).getDivisionDetails().get(0).getTeamDetails().get(0);
		league.setLeagueCurrentDate(LocalDate.of(2021, Month.APRIL, 07));
		leagueSchedule.generatePlayoffSchedule(league,league.getLeagueStanding());
		assertNotNull(league.getLeagueSchedule());
		assertEquals(team1,leagueSchedule.getPlayoffSeasonSchedule().get(2).getFirstTeam());
	}
	
	
	@Test
	public void anyUnplayedGamesOnGivenDateTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		DateHandler.instance().performDateAssignment(2020);
		
		List<IScheduleModel> regularSeasonSchedule = new ArrayList<>();
		IScheduleModel schedule = new ScheduleModel();
		schedule.setScheduleDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.setIsGameCompleted(false);
		regularSeasonSchedule.add(schedule);
		leagueSchedule.setRegularSeasonSchedule(regularSeasonSchedule);
		
		assertTrue(leagueSchedule.isGamesUnplayedOnCurrentDay(LocalDate.of(2020, Month.DECEMBER, 07)));
	}
	
	@Test
	public void anyUnplayedGamesOnGivenDateFalseTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		DateHandler.instance().performDateAssignment(2020);
		
		List<IScheduleModel> regularSeasonSchedule = new ArrayList<>();
		IScheduleModel schedule = new ScheduleModel();
		schedule.setScheduleDate(LocalDate.of(2020, Month.DECEMBER, 07));
		schedule.setIsGameCompleted(true);
		regularSeasonSchedule.add(schedule);
		leagueSchedule.setRegularSeasonSchedule(regularSeasonSchedule);
		
		assertFalse(leagueSchedule.isGamesUnplayedOnCurrentDay(LocalDate.of(2020, Month.DECEMBER, 07)));
	}
	
	@Test
	public void anyUnplayedPlayOffGamesOnGivenDateTest() {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		DateHandler.instance().performDateAssignment(2020);
		
		List<IScheduleModel> playoffSchedule = new ArrayList<>();
		IScheduleModel schedule = new ScheduleModel();
		schedule.setScheduleDate(LocalDate.of(2021, Month.APRIL, 27));
		schedule.setIsGameCompleted(false);
		playoffSchedule.add(schedule);
		leagueSchedule.setPlayoffSeasonSchedule(playoffSchedule);
		
		assertTrue(leagueSchedule.isGamesUnplayedOnCurrentDay(LocalDate.of(2021, Month.APRIL, 27)));
	}
	
    @Test
    public void isStanleyCupWinnerDeterminedNoPlayoffTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();
    	assertFalse(leagueSchedule.isStanleyCupWinnerDetermined());
    }
	
    @Test
    public void isStanleyCupWinnerDeterminedNotTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();

        List<IScheduleModel> playoffSchedule = new ArrayList<>();
        IScheduleModel schedule1 = new ScheduleModel();
        schedule1.setIsGameCompleted(true);
        IScheduleModel schedule2 = new ScheduleModel();
        schedule2.setIsGameCompleted(false);
        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        leagueSchedule.setPlayoffSeasonSchedule(playoffSchedule);

        assertFalse(leagueSchedule.isStanleyCupWinnerDetermined());
    }
    
    @Test
    public void isStanleyCupWinnerDeterminedTest() {
    	ILeagueSchedule leagueSchedule = new LeagueSchedule();

        List<IScheduleModel> playoffSchedule = new ArrayList<>();
        IScheduleModel schedule1 = new ScheduleModel();
        schedule1.setIsGameCompleted(true);
        IScheduleModel schedule2 = new ScheduleModel();
        schedule2.setIsGameCompleted(true);
        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        leagueSchedule.setPlayoffSeasonSchedule(playoffSchedule);

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

        List<IScheduleModel> playoffSchedule = new ArrayList<>();
        IScheduleModel schedule1 = new ScheduleModel();
        schedule1.setIsGameCompleted(true);
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(division1);
        schedule1.setFirstTeam(team1);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(division2);
        schedule1.setSecondTeam(team2);
        schedule1.setWinningTeam(team2);
        playoffSchedule.add(schedule1);
        leagueSchedule.setPlayoffSeasonSchedule(playoffSchedule);

        assertSame(team2,leagueSchedule.getStanleyCupWinner());
    }
    
    @Test
    public void getScheduledMatchOnThisDateInRegularSeasonTest() {
        ILeagueSchedule scheduleSystem = new LeagueSchedule();
        DateHandler.instance().performDateAssignment(2020);

        List<IScheduleModel> regularSeasonSchedule = new ArrayList<>();
        IScheduleModel schedule1 = new ScheduleModel();
        schedule1.setScheduleDate(LocalDate.of(2020, Month.DECEMBER, 07));
        schedule1.setIsGameCompleted(true);
        IScheduleModel schedule2 = new ScheduleModel();
        schedule2.setScheduleDate(LocalDate.of(2020, Month.DECEMBER, 8));
        schedule2.setIsGameCompleted(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSeasonSchedule(regularSeasonSchedule);

        IScheduleModel currentDaySchedule = scheduleSystem.getMatchOnCurrentDate(LocalDate.of(2020, Month.DECEMBER, 8));
        assertEquals(LocalDate.of(2020, Month.DECEMBER, 8), currentDaySchedule.getScheduleDate());
        assertFalse(currentDaySchedule.getIsGameCompleted());
    }
	
    @Test
    public void getScheduledMatchOnThisDateInPlayOffTest() {
    	ILeagueSchedule scheduleSystem = new LeagueSchedule();
    	DateHandler.instance().performDateAssignment(2020);

    	List<IScheduleModel> playoffSchedule = new ArrayList<>();
    	IScheduleModel schedule1 = new ScheduleModel();
    	schedule1.setScheduleDate(LocalDate.of(2021, Month.APRIL, 17));
    	schedule1.setIsGameCompleted(true);
    	IScheduleModel schedule2 = new ScheduleModel();
    	schedule2.setScheduleDate(LocalDate.of(2021, Month.APRIL, 28));
    	schedule2.setIsGameCompleted(false);
    	playoffSchedule.add(schedule1);
    	playoffSchedule.add(schedule2);
    	scheduleSystem.setPlayoffSeasonSchedule(playoffSchedule);

    	IScheduleModel currentDaySchedule = scheduleSystem.getMatchOnCurrentDate(LocalDate.of(2021, Month.APRIL, 28));
    	assertEquals(LocalDate.of(2021, Month.APRIL, 28), currentDaySchedule.getScheduleDate());
    	assertFalse(currentDaySchedule.getIsGameCompleted());
    }
    
    
    @Test
    public void updateScheduleAfterPlayoffGameOneTest() {
    	ILeagueSchedule scheduleSystem = new LeagueSchedule();
    	DateHandler.instance().performDateAssignment(2020);

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

    	List<IScheduleModel> playoffSchedule = new ArrayList<>();
    	IScheduleModel schedule1 = new ScheduleModel();
    	schedule1.setFirstConference(conference1);
    	schedule1.setFirstDivision(division1);
    	schedule1.setFirstTeam(team1);
    	schedule1.setSecondConference(conference2);
    	schedule1.setSecondDivision(division2);
    	schedule1.setSecondTeam(team2);
    	schedule1.setScheduleDate(LocalDate.of(2021, Month.APRIL, 28));
    	schedule1.setIsGameCompleted(false);

    	IScheduleModel schedule2 = new ScheduleModel();
    	schedule1.setFirstConference(conference3);
    	schedule1.setFirstDivision(division3);
    	schedule1.setFirstTeam(team3);
    	schedule1.setSecondConference(conference4);
    	schedule1.setSecondDivision(division4);
    	schedule1.setSecondTeam(team4);
    	schedule1.setIsGameCompleted(true);
    	schedule2.setScheduleDate(LocalDate.of(2021, Month.APRIL, 29));

    	playoffSchedule.add(schedule1);
    	playoffSchedule.add(schedule2);
    	scheduleSystem.setPlayoffSeasonSchedule(playoffSchedule);

    	schedule1.setWinningTeam(team1);
    	schedule2.setWinningTeam(team4);

    	scheduleSystem.updateScheduleAfterGame(schedule1);
    	scheduleSystem.updateScheduleAfterGame(schedule2);
    	List<IScheduleModel> playoffScheduleList = scheduleSystem.getPlayoffSeasonSchedule();

    	assertEquals(3, playoffScheduleList.size());
    	assertTrue(playoffScheduleList.get(0).getIsGameCompleted());
    	assertEquals(team4, playoffScheduleList.get(2).getFirstTeam());
    	assertEquals(LocalDate.of(2021, Month.APRIL, 30), playoffScheduleList.get(2).getScheduleDate());
    }
    
    @Test
    public void updateScheduleAfterPlayoffGameTwoTest() {
    	ILeagueSchedule scheduleSystem = new LeagueSchedule();
    	DateHandler.instance().performDateAssignment(2020);

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

        List<IScheduleModel> playoffSchedule = new ArrayList<>();
        IScheduleModel schedule1 = new ScheduleModel();
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(division1);
        schedule1.setFirstTeam(team1);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(division2);
        schedule1.setSecondTeam(team2);
        schedule1.setScheduleDate(LocalDate.of(2021, Month.APRIL, 20));
        schedule1.setIsGameCompleted(true);
        schedule1.setWinningTeam(team1);

        IScheduleModel schedule2 = new ScheduleModel();
        schedule2.setFirstConference(conference3);
        schedule2.setFirstDivision(division3);
        schedule2.setFirstTeam(team3);
        schedule2.setSecondConference(conference4);
        schedule2.setSecondDivision(division4);
        schedule2.setSecondTeam(team4);
        schedule2.setScheduleDate(LocalDate.of(2021, Month.APRIL, 21));
        schedule2.setIsGameCompleted(false);
        schedule2.setWinningTeam(team3);

        IScheduleModel schedule3 = new ScheduleModel();
        schedule3.setFirstConference(conference1);
        schedule3.setFirstDivision(division1);
        schedule3.setFirstTeam(team1);
        schedule3.setScheduleDate(LocalDate.of(2021, Month.APRIL, 22));

        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        playoffSchedule.add(schedule3);
        scheduleSystem.setPlayoffSeasonSchedule(playoffSchedule);

        scheduleSystem.updateScheduleAfterGame(schedule1);
        scheduleSystem.updateScheduleAfterGame(schedule2);
        List<IScheduleModel> playoffScheduleList = scheduleSystem.getPlayoffSeasonSchedule();

        assertEquals(4, playoffScheduleList.size());
        assertTrue(playoffScheduleList.get(1).getIsGameCompleted());
        assertEquals(team1, playoffScheduleList.get(3).getFirstTeam());
        assertEquals(team3, playoffScheduleList.get(3).getSecondTeam());
        assertNull(playoffScheduleList.get(2).getWinningTeam());
        assertEquals(LocalDate.of(2021, Month.APRIL, 22), playoffScheduleList.get(2).getScheduleDate());
        assertFalse(playoffScheduleList.get(2).getIsGameCompleted());
    }
    
}
