package com.dhl.g05.leaguesimulation;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.leaguesimulation.IStandingModel;
import com.dhl.g05.leaguesimulation.StandingModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class StandingModelTest {
	
	@Test
	public void getConferenceTest() {
		IStandingModel standing = new StandingModel();
		IConference conference = new ConferenceModel();
		standing.setConference(conference);
		assertSame(conference,standing.getConference());
	}

	@Test
	public void getDivisionTest() {
		IStandingModel standing = new StandingModel();
		IDivision division = new DivisionModel();
		standing.setDivision(division);
		assertSame(division,standing.getDivision());
	}
	
	@Test
	public void getTeamTest() {
		IStandingModel standing = new StandingModel();
		ITeam team = new TeamModel();
		standing.setTeam(team);
		assertSame(team,standing.getTeam());
	}
	
	@Test
	public void setGamesPlayedTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalGamesPlayed(10);
		assertSame(10,standing.getTotalGamesPlayed());
	}
	
	@Test
	public void incrementGamesPlayedTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalGamesPlayed(10);
		standing.incrementGamesPlayed();
		assertSame(11,standing.getTotalGamesPlayed());
	}
	
	@Test
	public void setGamesWonTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalGamesWon(10);
		assertSame(10,standing.getTotalGamesWon());
	}
	
	@Test
	public void setGamesLostTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalGamesLost(10);
		assertSame(10,standing.getTotalGamesLost());
	}
	
	@Test
	public void incrementGamesWonTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalGamesWon(10);
		standing.incrementGamesWon();
		assertSame(11,standing.getTotalGamesWon());
	}
	
	@Test
	public void incrementGamesLostTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalGamesLost(10);
		standing.incrementGamesLost();
		assertSame(11,standing.getTotalGamesLost());
	}
	
	@Test
	public void getPointsTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalPoints(10);
		assertSame(10,standing.getTotalPoints());
	}
	
	@Test
	public void incrementPointsTest() {
		IStandingModel standing = new StandingModel();
		standing.setTotalPoints(10);
		standing.incrementPoints();
		assertSame(12,standing.getTotalPoints());
	}
	
}
