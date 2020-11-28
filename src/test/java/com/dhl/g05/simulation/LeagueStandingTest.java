package com.dhl.g05.simulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.simulation.ILeagueStanding;
import com.dhl.g05.simulation.IStandingModel;
import com.dhl.g05.simulation.LeagueStanding;

public class LeagueStandingTest {

	@Test
	public void setStandingTest() {
		ILeagueStanding standings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		standings.setStandingList(mock.createDummyStandings(mock.createDummyLeague()));
		assertSame(20,standings.getStandingList().size());
	}
	
	@Test
	public void createStandingsTest() {
		ILeagueStanding standings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		standings.createStandingList(mock.createDummyLeague());
		assertSame(20,standings.getStandingList().size());
	}
	
	@Test
	public void updateStatisticsForWinningTeamTest() {
		ILeagueStanding leagueStandings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		List<IStandingModel> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStandings.setStandingList(standings);
		
		int gamesPlayed = standings.get(0).getTotalGamesPlayed();
		int gamesWon = standings.get(0).getTotalGamesWon();
		int points = standings.get(0).getTotalPoints();
		IStandingModel standing = standings.get(0);
		leagueStandings.updateStatisticsForWinningTeam(standing.getConference(), standing.getDivision(), standing.getTeam());
		assertEquals(gamesPlayed+1,standing.getTotalGamesPlayed());
		assertEquals(gamesWon+1,standing.getTotalGamesWon());
		assertEquals(points+2,standing.getTotalPoints());
	}
	
	@Test
	public void updateStatisticsForLosingTeamTest() {
		ILeagueStanding leagueStandings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		List<IStandingModel> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStandings.setStandingList(standings);
		
		int gamesPlayed = standings.get(0).getTotalGamesPlayed();
		int gamesLost = standings.get(0).getTotalGamesLost();
		IStandingModel standing = standings.get(0);
		leagueStandings.updateStatisticsForLosingTeam(standing.getConference(), standing.getDivision(), standing.getTeam());
		assertEquals(gamesPlayed+1,standing.getTotalGamesPlayed());
		assertEquals(gamesLost+1,standing.getTotalGamesLost());
	}
	
    @Test
    public void getRankingAcrossLeagueTest() {
    	ILeagueStanding leagueStanding = new LeagueStanding();
        StandingsMock mock = new StandingsMock();
		List<IStandingModel> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandingList(standings);

        List<IStandingModel> leagueStandings = leagueStanding.getRankingAcrossLeague();

        boolean compareStandings = ((leagueStandings.get(3).getTotalPoints() >= leagueStandings.get(4).getTotalPoints())||
        		(leagueStandings.get(3).getTotalGamesWon() >= leagueStandings.get(4).getTotalGamesWon()));
        assertEquals(20, leagueStandings.size());
        assertTrue(compareStandings);
    }
    
    @Test
    public void getRankingAcrossDivisionTest() {
        ILeagueStanding leagueStanding = new LeagueStanding();
        StandingsMock mock = new StandingsMock();
		List<IStandingModel> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandingList(standings);

        IStandingModel standing = standings.get(0);
        List<IStandingModel> divisionStandings = leagueStanding.getRankingAcrossDivision(standing.getDivision());
        
        boolean compareStandings = ((divisionStandings.get(3).getTotalPoints() >= divisionStandings.get(4).getTotalPoints())||
        		(divisionStandings.get(3).getTotalGamesWon() >= divisionStandings.get(4).getTotalGamesWon()));
        assertEquals(5, divisionStandings.size());
        assertTrue(compareStandings);
    }
    
    @Test
    public void getRankingAcrossConferenceTest() {
    	ILeagueStanding leagueStanding = new LeagueStanding();
        StandingsMock mock = new StandingsMock();
		List<IStandingModel> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandingList(standings);

        IStandingModel standing = standings.get(0);
        List<IStandingModel> conferenceStandings = leagueStanding.getRankingAcrossConference(standing.getConference());

        boolean compareStandings = ((conferenceStandings.get(3).getTotalPoints() >= conferenceStandings.get(4).getTotalPoints())||
        		(conferenceStandings.get(3).getTotalGamesWon() >= conferenceStandings.get(4).getTotalGamesWon()));
        assertEquals(10, conferenceStandings.size());
        assertTrue(compareStandings);
    }
	
}
