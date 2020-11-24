package com.dhl.g05.leaguesimulation.leaguestanding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.leaguesimulation.StandingsMock;

public class LeagueStandingTest {

	@Test
	public void setStandingTest() {
		ILeagueStanding standings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		standings.setStandings(mock.createDummyStandings(mock.createDummyLeague()));
		assertSame(20,standings.getStandings().size());
	}
	
	@Test
	public void createStandingsTest() {
		ILeagueStanding standings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		standings.createStandings(mock.createDummyLeague());
		assertSame(20,standings.getStandings().size());
	}
	
	@Test
	public void updateWinningTeamDataTest() {
		ILeagueStanding leagueStandings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		List<IStanding> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStandings.setStandings(standings);
		
		int gamesPlayed = standings.get(0).getGamesPlayed();
		int gamesWon = standings.get(0).getGamesWon();
		int points = standings.get(0).getPoints();
		IStanding standing = standings.get(0);
		leagueStandings.updateWinningTeamData(standing.getConference(), standing.getDivision(), standing.getTeam());
		assertEquals(gamesPlayed+1,standing.getGamesPlayed());
		assertEquals(gamesWon+1,standing.getGamesWon());
		assertEquals(points+2,standing.getPoints());
	}
	
	@Test
	public void updateLosingTeamDataTest() {
		ILeagueStanding leagueStandings = new LeagueStanding();
		StandingsMock mock = new StandingsMock();
		List<IStanding> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStandings.setStandings(standings);
		
		int gamesPlayed = standings.get(0).getGamesPlayed();
		int gamesLost = standings.get(0).getGamesLost();
		IStanding standing = standings.get(0);
		leagueStandings.updateLosingTeamData(standing.getConference(), standing.getDivision(), standing.getTeam());
		assertEquals(gamesPlayed+1,standing.getGamesPlayed());
		assertEquals(gamesLost+1,standing.getGamesLost());
	}
	
    @Test
    public void getStandingsInDivisionTest() {
        ILeagueStanding leagueStanding = new LeagueStanding();
        StandingsMock mock = new StandingsMock();
		List<IStanding> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandings(standings);

        IStanding standing = standings.get(0);
        List<IStanding> divisionStandings = leagueStanding.getStandingsInDivision(standing.getDivision());
        
        boolean compareStandings = ((divisionStandings.get(3).getPoints() > divisionStandings.get(4).getPoints())||
        		(divisionStandings.get(3).getGamesWon() > divisionStandings.get(4).getGamesWon()) ||
        		(divisionStandings.get(3).getGamesPlayed() <= divisionStandings.get(4).getGamesPlayed()));
        assertEquals(5, divisionStandings.size());
        assertTrue(compareStandings);
    }
    
    @Test
    public void getStandingsInConferenceTest() {
    	ILeagueStanding leagueStanding = new LeagueStanding();
        StandingsMock mock = new StandingsMock();
		List<IStanding> standings = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandings(standings);

        IStanding standing = standings.get(0);
        List<IStanding> conferenceStandings = leagueStanding.getStandingsInConference(standing.getConference());

        boolean compareStandings = ((conferenceStandings.get(3).getPoints() > conferenceStandings.get(4).getPoints())||
        		(conferenceStandings.get(3).getGamesWon() > conferenceStandings.get(4).getGamesWon()) ||
        		(conferenceStandings.get(3).getGamesPlayed() <= conferenceStandings.get(4).getGamesPlayed()));
        assertEquals(10, conferenceStandings.size());
        assertTrue(compareStandings);
    }
	
}
