package filehandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.coach.CoachPersistenceMock;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.ConferencePersistenceMock;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.DivisionPersistenceMock;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.FreeAgentPersistenceMock;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.GamePlayConfigPersistenceMock;
import com.dhl.g05.gameplayconfig.GameResolverConfig;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.gameplayconfig.TrainingConfig;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.league.LeaguePersistenceMock;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.player.PlayerPersistenceMock;
import com.dhl.g05.team.TeamModel;
import com.dhl.g05.team.TeamPersistenceMock;

public class LeagueModelJsonTest {
	LeagueModelJson leagueModel;
	LeagueModel league;
	TeamModel team;
	TeamModel team2;

	@Before
	public void init() {
		leagueModel = new LeagueModelJson();
		leagueModel.setConferenceDatabase(new ConferencePersistenceMock());
		leagueModel.setLeagueDatabase(new LeaguePersistenceMock());
		leagueModel.setDivisionDatabase(new DivisionPersistenceMock());
		leagueModel.setTeamDatabase(new TeamPersistenceMock());
		leagueModel.setPlayerDatabase(new PlayerPersistenceMock());
		leagueModel.setFreeAgentDatabase(new FreeAgentPersistenceMock());
		leagueModel.setCoachDatabase(new CoachPersistenceMock());
		leagueModel.setGamePlayDatabase(new GamePlayConfigPersistenceMock());

		PlayerModel player = new PlayerModel("Cristiano Ronaldo",null,null,10,15,1,20,22,10,1997);
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
		
		FreeAgentModel freeAgent = new FreeAgentModel("Kane","forward",0.5,0.6,0.7,0.8,22,10,1997);
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

		league = new LeagueModel("HockeyLeague",conferences,freeAgentList,coachList, managerList,game);

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
		assertTrue(leagueModel.loadTeam("Striker Six"));
		assertNotNull(leagueModel.getCurrentTeam());
		assertNotNull(leagueModel.getLeague());
	}

	@Test
	public void testLoadTeamFails() {
		leagueModel.loadTeam("");
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
