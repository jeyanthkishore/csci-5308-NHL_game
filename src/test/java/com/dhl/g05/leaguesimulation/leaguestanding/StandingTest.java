package com.dhl.g05.leaguesimulation.leaguestanding;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class StandingTest {
	
	@Test
	public void getConferenceTest() {
		IStanding standing = new Standing();
		IConference conference = new ConferenceModel();
		standing.setConference(conference);
		assertSame(conference,standing.getConference());
	}

	@Test
	public void getDivisionTest() {
		IStanding standing = new Standing();
		IDivision division = new DivisionModel();
		standing.setDivision(division);
		assertSame(division,standing.getDivision());
	}
	
	@Test
	public void getTeamTest() {
		IStanding standing = new Standing();
		ITeam team = new TeamModel();
		standing.setTeam(team);
		assertSame(team,standing.getTeam());
	}
	
	@Test
	public void setGamesPlayedTest() {
		IStanding standing = new Standing();
		standing.setGamesPlayed(10);
		assertSame(10,standing.getGamesPlayed());
	}
	
	@Test
	public void incrementGamesPlayedTest() {
		IStanding standing = new Standing();
		standing.setGamesPlayed(10);
		standing.incrementGamesPlayed();
		assertSame(11,standing.getGamesPlayed());
	}
	
	@Test
	public void setGamesWonTest() {
		IStanding standing = new Standing();
		standing.setGamesWon(10);
		assertSame(10,standing.getGamesWon());
	}
	
	@Test
	public void setGamesLostTest() {
		IStanding standing = new Standing();
		standing.setGamesLost(10);
		assertSame(10,standing.getGamesLost());
	}
	
	@Test
	public void incrementGamesWonTest() {
		IStanding standing = new Standing();
		standing.setGamesWon(10);
		standing.incrementGamesWon();
		assertSame(11,standing.getGamesWon());
	}
	
	@Test
	public void incrementGamesLostTest() {
		IStanding standing = new Standing();
		standing.setGamesLost(10);
		standing.incrementGamesLost();
		assertSame(11,standing.getGamesLost());
	}
	
	@Test
	public void getPointsTest() {
		IStanding standing = new Standing();
		standing.setPoints(10);
		assertSame(10,standing.getPoints());
	}
	
	@Test
	public void incrementPointsTest() {
		IStanding standing = new Standing();
		standing.setPoints(10);
		standing.incrementPoints();
		assertSame(12,standing.getPoints());
	}
	
}
