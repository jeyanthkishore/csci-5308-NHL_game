package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.FreeAgentObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;
import com.dhl.g05.operation.DbPersistanceMock;

public class LeagueModelTest {
	LeagueModel leagueModel;
	LeagueObject league;
	TeamObject team;
	TeamObject team2;

	@Before
	public void init() {
		leagueModel = new LeagueModel(new DbPersistanceMock());
		
		PlayerObject player = new PlayerObject("Cristiano Ronaldo",null,null,10,15,1,20,15);
		ArrayList<PlayerObject> players = new ArrayList<>();
		players.add(player);
		
		team = new TeamObject("Striker Six",null,null,players);
		team2 = new TeamObject("t",null,null,players);
		ArrayList<TeamObject> teams = new ArrayList<>();
		teams.add(team);
		
		DivisionObject division = new DivisionObject("Atlantic",teams);
		ArrayList<DivisionObject> divisions = new ArrayList<>();
		divisions.add(division);
		
		ConferenceObject conference = new ConferenceObject("Western Conference",divisions);
		ArrayList<ConferenceObject> conferences = new ArrayList<>();
		conferences.add(conference);

		league = new LeagueObject("HockeyLeague",conferences,null,new DbPersistanceMock());
		
	}

	@Test
	public void testCreateLeague() {
		LeagueObject newleague = leagueModel.createLeague("testLeague", new ArrayList<ConferenceObject>(), new ArrayList<FreeAgentObject>());   
		assertNotNull(newleague);
		assertTrue(newleague.getLeagueName().equals("testLeague"));
	}

	@Test
	public void testGetLeague() {
		leagueModel.setLeague(league);
		assertNotNull(leagueModel.getLeague());
		assertEquals(league,leagueModel.getLeague());
	}

	@Test
	public void testSetLeague() {
		leagueModel.setLeague(league);
		assertNotNull(leagueModel.getLeague());
		assertEquals(league,leagueModel.getLeague());
	}

	@Test
	public void testPersistLeague() {
		leagueModel.setLeague(league);
		assertTrue(leagueModel.persistLeague());
		
	}

	@Test
	public void testAddTeamToCurrentLeague() {
		leagueModel.setLeague(league);
		leagueModel.addTeamToCurrentLeague("Western Conference", "Atlantic", team2);
		assertEquals(team2, leagueModel.getTeamFromLeagueObject(league, "Western Conference", "Atlantic", "t"));
	}
	
	@Test
	public void testAddTeamToCurrentLeagueFails() {
		TeamObject team = new TeamObject("t",null,null,null);
		leagueModel.setLeague(league);
		leagueModel.addTeamToCurrentLeague("a", "b", team);
		assertNull(leagueModel.getTeamFromLeagueObject(league, "a", "b", "t"));
	}

	@Test
	public void testLoadTeam() {
		assertTrue(leagueModel.loadTeam("HockeyLeague", "Western Conference", "Atlantic", "Striker Six"));
		assertNotNull(leagueModel.getCurrentTeam());
		assertNotNull(leagueModel.getLeague());
	}
	
	@Test
	public void testLoadTeamFails() {
		leagueModel.loadTeam("HockeyLeague", "", "", "");
		assertNull(leagueModel.getCurrentTeam());
	}
	
	@Test
	public void testGetCurrentTeam() {
		assertNull(leagueModel.getCurrentTeam());
		leagueModel.setLeague(league);
		leagueModel.addTeamToCurrentLeague("Western Conference", "Atlantic", team2);
		assertEquals(team2,leagueModel.getCurrentTeam());
	}

}
