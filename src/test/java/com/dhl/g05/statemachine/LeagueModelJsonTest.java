package com.dhl.g05.statemachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.gameplayconfig.Aging;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.leaguemodel.gameplayconfig.GameResolverConfig;
import com.dhl.g05.leaguemodel.gameplayconfig.Injury;
import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;
import com.dhl.g05.leaguemodel.gameplayconfig.TrainingConfig;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;
import com.dhl.g05.operation.DbPersistanceMock;

public class LeagueModelJsonTest {
	LeagueModelJson leagueModel;
	LeagueModel league;
	TeamModel team;
	TeamModel team2;

	@Before
	public void init() {
		leagueModel = new LeagueModelJson();
		leagueModel.setConferenceDatabase(new DbPersistanceMock());
		leagueModel.setLeagueDatabase(new DbPersistanceMock());
		leagueModel.setDivisionDatabase(new DbPersistanceMock());
		leagueModel.setTeamDatabase(new DbPersistanceMock());
		leagueModel.setPlayerDatabase(new DbPersistanceMock());
		leagueModel.setDateDatabase(new DbPersistanceMock());
		leagueModel.setFreeAgentDatabase(new DbPersistanceMock());
		leagueModel.setCoachDatabase(new DbPersistanceMock());
		leagueModel.setGamePlayDatabase(new DbPersistanceMock());

		PlayerModel player = new PlayerModel("Cristiano Ronaldo",null,null,10,15,1,20,15);
		ArrayList<PlayerModel> players = new ArrayList<>();
		players.add(player);

		team = new TeamModel("Striker Six",null,null,players);
		team2 = new TeamModel("t",null,null,players);
		ArrayList<TeamModel> teams = new ArrayList<>();
		teams.add(team);

		DivisionModel division = new DivisionModel("Atlantic",teams);
		ArrayList<DivisionModel> divisions = new ArrayList<>();
		divisions.add(division);

		ConferenceModel conference = new ConferenceModel("Western Conference",divisions);
		ArrayList<ConferenceModel> conferences = new ArrayList<>();
		conferences.add(conference);
		
		FreeAgentModel freeAgent = new FreeAgentModel("Kane","forward",30,0.5,0.6,0.7,0.8);
		ArrayList<FreeAgentModel>freeAgentList = new ArrayList<FreeAgentModel>();
		freeAgentList.add(freeAgent);

		CoachModel coach = new CoachModel("Smith",0.5,0.6,0.7,0.8);
		ArrayList<CoachModel> coachList = new ArrayList<>();
		coachList.add(coach);

		ArrayList<String> managerList = new ArrayList<>();
		managerList.add("Smith");
		
		TradingModel trade = new TradingModel(8, 0.05, 2, 0.07);
		TrainingConfig train = new TrainingConfig(100);
		GameResolverConfig resolver = new GameResolverConfig(0.2);
		Injury injury = new Injury(2, 1, 260);
		Aging age = new Aging(28, 60);
		GamePlayConfigModel game = new GamePlayConfigModel(trade, age, injury, resolver, train);

		league = new LeagueModel("HockeyLeague",conferences,freeAgentList,coachList, managerList,game,new DbPersistanceMock());

	}

	@Test
	public void testCreateLeague() {
		LeagueModel newleague = leagueModel.createLeague("testLeague", new ArrayList<ConferenceModel>(), new ArrayList<FreeAgentModel>(), new ArrayList<CoachModel>(), new ArrayList<String>(),new GamePlayConfigModel(null,null,null,null,null));
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
		TeamModel team = new TeamModel("t",null,null,null);
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
