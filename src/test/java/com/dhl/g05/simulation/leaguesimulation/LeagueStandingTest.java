package com.dhl.g05.simulation.leaguesimulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.SimulationMockAbstractFactory;

public class LeagueStandingTest {
	private static SimulationAbstractFactory simulationFactory;
	private static SimulationMockAbstractFactory simulationMockFactory;
	
	@BeforeClass
	public static void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState();
	}

	@Test
	public void setStandingTest() {
		ILeagueStanding standings = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		standings.setStandingList(mock.createDummyStandings(mock.createDummyLeague()));
		assertSame(20,standings.getStandingList().size());
	}
	
	@Test
	public void createStandingsTest() {
		ILeagueStanding standings = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		standings.createStandingList(mock.createDummyLeague());
		assertSame(20,standings.getStandingList().size());
	}
	
	@Test
	public void updateStatisticsForWinningTeamTest() {
		ILeagueStanding leagueStandings = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		List<IStandingModel> standingList = mock.createDummyStandings(mock.createDummyLeague());
		leagueStandings.setStandingList(standingList);
		
		int gamesPlayed = standingList.get(0).getTotalGamesPlayed();
		int gamesWon = standingList.get(0).getTotalGamesWon();
		int points = standingList.get(0).getTotalPoints();
		IStandingModel standing = standingList.get(0);
		leagueStandings.updateStatisticsForWinningTeam(standing.getConference(), standing.getDivision(), standing.getTeam());
		assertEquals(gamesPlayed+1,standing.getTotalGamesPlayed());
		assertEquals(gamesWon+1,standing.getTotalGamesWon());
		assertEquals(points+2,standing.getTotalPoints());
	}
	
	@Test
	public void updateStatisticsForLosingTeamTest() {
		ILeagueStanding leagueStandings = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		List<IStandingModel> standingList = mock.createDummyStandings(mock.createDummyLeague());
		leagueStandings.setStandingList(standingList);
		
		int gamesPlayed = standingList.get(0).getTotalGamesPlayed();
		int gamesLost = standingList.get(0).getTotalGamesLost();
		IStandingModel standing = standingList.get(0);
		leagueStandings.updateStatisticsForLosingTeam(standing.getConference(), standing.getDivision(), standing.getTeam());
		assertEquals(gamesPlayed+1,standing.getTotalGamesPlayed());
		assertEquals(gamesLost+1,standing.getTotalGamesLost());
	}
	
    @Test
    public void getRankingAcrossLeagueTest() {
    	ILeagueStanding leagueStanding = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		List<IStandingModel> standingList = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandingList(standingList);

        List<IStandingModel> leagueStandings = leagueStanding.getRankingAcrossLeague();

        boolean compareStandings = ((leagueStandings.get(3).getTotalPoints() >= leagueStandings.get(4).getTotalPoints())||
        		(leagueStandings.get(3).getTotalGamesWon() >= leagueStandings.get(4).getTotalGamesWon()));
        assertEquals(20, leagueStandings.size());
        assertTrue(compareStandings);
    }
    
    @Test
    public void getRankingAcrossDivisionTest() {
    	ILeagueStanding leagueStanding = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		List<IStandingModel> standingList = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandingList(standingList);

        IStandingModel standing = standingList.get(0);
        List<IStandingModel> divisionStandings = leagueStanding.getRankingAcrossDivision(standing.getDivision());
        
        boolean compareStandings = ((divisionStandings.get(3).getTotalPoints() >= divisionStandings.get(4).getTotalPoints())||
        		(divisionStandings.get(3).getTotalGamesWon() >= divisionStandings.get(4).getTotalGamesWon()));
        assertEquals(5, divisionStandings.size());
        assertTrue(compareStandings);
    }
    
    @Test
    public void getRankingAcrossConferenceTest() {
    	ILeagueStanding leagueStanding = simulationFactory.createLeagueStanding();
		StandingMockData mock = simulationMockFactory.createStandingMock();
		List<IStandingModel> standingList = mock.createDummyStandings(mock.createDummyLeague());
		leagueStanding.setStandingList(standingList);

        IStandingModel standing = standingList.get(0);
        List<IStandingModel> conferenceStandings = leagueStanding.getRankingAcrossConference(standing.getConference());

        boolean compareStandings = ((conferenceStandings.get(3).getTotalPoints() >= conferenceStandings.get(4).getTotalPoints())||
        		(conferenceStandings.get(3).getTotalGamesWon() >= conferenceStandings.get(4).getTotalGamesWon()));
        assertEquals(10, conferenceStandings.size());
        assertTrue(compareStandings);
    }
	
}
