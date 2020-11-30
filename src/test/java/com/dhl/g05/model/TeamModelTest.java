package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;

public class TeamModelTest {

	private static ModelAbstractFactory modelAbstractFactory;
	private static ModelMockAbstractFactory modelMockFactory;

	@BeforeClass
	public static void init() {
		modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
	}

	@Test
	public void TeamObjectConstructorTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel();
		assertNull(object.getCoachDetails());
		object.setGeneralManagerName("");
		assertTrue(object.getGeneralManagerName().isEmpty());
		assertNull(object.getTeamName());
		assertNull(object.getPlayerList());
		object.setPlayerList(data.playerList);
		assertFalse(object.getPlayerList().isEmpty());
	}

	@Test
	public void teamReferenceConstructorTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel(data);
		assertSame(data.teamName, object.getTeamName());
		assertSame(data.coachDetails, object.getCoachDetails());
		assertSame(data.generalManagerName, object.getGeneralManagerName());
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void setTeamNameTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setTeamName("Strikers");
		assertTrue(object.getTeamName().equals("Strikers"));
	}

	@Test
	public void getTeamNameTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setTeamName("Strikers");
		assertTrue(object.getTeamName().equals("Strikers"));
	}

	@Test
	public void setManagerNameTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setGeneralManagerName("Rubinho");
		assertTrue(object.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void removeRetiredPlayerFromTeamTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel(mock);
		List<IPlayer> players = mock.playerList;
		team.removeRetiredPlayerFromTeam(players.get(0));
		assertEquals(mock.playerList.size() - 1, team.getPlayerList().size() - 1);
	}

	@Test
	public void getManagerNameTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setGeneralManagerName("Rubinho");
		assertTrue(object.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void setUserTeamTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setUserTeam(false);
		assertFalse(object.getUserTeam());
	}

	@Test
	public void getUserTeamTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setUserTeam(true);
		assertTrue(object.getUserTeam());
	}

	@Test
	public void setPlayerListTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setPlayerList(data.playerList);
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void getPlayerListTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setPlayerList(data.playerList);
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void setCoachDetailsTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setCoachDetails(data.coachDetails);
		assertSame(data.coachDetails, object.getCoachDetails());
	}

	@Test
	public void getCoachDetailsTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setCoachDetails(data.coachDetails);
		assertSame(data.coachDetails, object.getCoachDetails());
	}

	@Test
	public void setTeamStrengthTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setTeamStrength(25);
		assertEquals(object.getTeamStrength(), 25, 0);
	}

	@Test
	public void getTeamStrengthTest() {
		ITeam object = modelAbstractFactory.createTeamModel();
		object.setTeamStrength(25);
		assertEquals(object.getTeamStrength(), 25, 0);
	}

	@Test
	public void isCoachDetailsEmpty() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setCoachDetailsNull();
		ITeam object = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty, object.validate());
	}

	@Test
	public void isCoachDetailsNull() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setCoachDetailsNull();
		ITeam object = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty, object.validate());
	}

	@Test
	public void checkPlayerListTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.Success, validate.validate());
	}

	@Test
	public void checkPlayerListEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setPlayerListEmpty();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.PlayerListEmpty, validate.validate());
	}

	@Test
	public void checkPlayerListMaxTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.addMaximumPlayer();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.PlayerCountMismatch, validate.validate());
	}

	@Test
	public void teamNameEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setTeamNameEmpty();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty, validate.validate());
	}

	@Test
	public void teamNameNullTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setTeamNameNull();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty, validate.validate());
	}

	@Test
	public void managerNameEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setManagerNameEmpty();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty, validate.validate());
	}

	@Test
	public void managerNameNullTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setManagerNameNull();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty, validate.validate());
	}

	@Test
	public void oneTeamCaptainTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.Success, validate.validate());
	}

	@Test
	public void twoTeamCaptainTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setSecondCaptain();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.MoreTeamCaptain, validate.validate());
	}

	@Test
	public void calculateTeamStrengthTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ITeam object = modelAbstractFactory.createTeamModel(mock);
		assertEquals(object.calculateTeamStrength(mock.playerList), mock.calculateTeamStrength(mock.playerList), 0);
	}

	@Test
	public void noTeamCaptainTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.removeCaptain();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.NoTeamCaptain, validate.validate());
	}
	
	@Test
	public void setActiveRosterTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		newPlayers.setNumberOfTeams(5);
		ITeam team = modelAbstractFactory.createTeamModel();
		List<IPlayer> players = newPlayers.generatePlayers();
		team.setPlayerList(players);
		List<IPlayer> activePlayers=team.setActiveRoster(team);
		assertEquals(activePlayers.size(),20);
	}
	
//	@Test
//	public void setActiveRosterPlayerTest() {
//		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
//		newPlayers.setNumberOfTeams(5);
//		ITeam team = modelAbstractFactory.createTeamModel();
//		List<IPlayer> players = newPlayers.generatePlayers();
//		team.setPlayerList(players);
//		List<IPlayer> activePlayers=team.setActiveRoster(team);
//		assertEquals(activePlayers.get(1).getIsActive(),true);
//	}
	
	@Test
	public void setInActiveRosterTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		ITeam team = modelAbstractFactory.createTeamModel();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		team.setPlayerList(players);
		List<IPlayer> inActivePlayers=team.setInActiveRoster(players,team);
		assertEquals(inActivePlayers.size(),10);
	}
	
//	@Test
//	public void setInActiveRosterPlayerTest() {
//		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
//		ITeam team = modelAbstractFactory.createTeamModel();
//		newPlayers.setNumberOfTeams(6);
//		List<IPlayer> players = newPlayers.generatePlayers();
//		team.setPlayerList(players);
//		List<IPlayer> inActivePlayers=team.setInActiveRoster(players,team);
//		assertEquals(inActivePlayers.get(1).getIsActive(),false);
//	}
	
	@Test
	public void adjustTeamRoasterAfterDraftSizeTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setPlayerList(players);
		team.adjustTeamRoasterAfterDraft(team);
		assertTrue(team.getPlayerList().size() == 30);
	}

	@Test
	public void adjustTeamRoasterAfterDrafPositiontCountTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setPlayerList(players);
		team.adjustTeamRoasterAfterDraft(team);
		int numberOfForwards = 0, numberOfDefense = 0, numberOfGoalies = 0;
		for (IPlayer player : team.getPlayerList()) {
			if (player.getPosition().equals(PositionConstant.defense.getValue())) {
				numberOfDefense++;
			}
			if (player.getPosition().equals(PositionConstant.forward.getValue())) {
				numberOfForwards++;
			}
			if (player.getPosition().equals(PositionConstant.goalie.getValue())) {

				numberOfGoalies++;
			}
		}
		assertTrue(numberOfDefense == 10);
		assertTrue(numberOfForwards == 16);
		assertTrue(numberOfGoalies == 4);
	}

	@Test
	public void validateTeamTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		ITeam validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.Success, validate.validate());
		mock = modelMockFactory.createLeagueMockData();
		mock.setPlayerListEmpty();
		validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.PlayerListEmpty, validate.validate());
		mock = modelMockFactory.createLeagueMockData();
		mock.setTeamNameNull();
		validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty, validate.validate());
		mock = modelMockFactory.createLeagueMockData();
		mock.addMaximumPlayer();
		validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.PlayerCountMismatch, validate.validate());
		mock = modelMockFactory.createLeagueMockData();
		mock.setSecondCaptain();
		validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.MoreTeamCaptain, validate.validate());
		mock = modelMockFactory.createLeagueMockData();
		mock.removeCaptain();
		validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.NoTeamCaptain, validate.validate());
		mock = modelMockFactory.createLeagueMockData();
		mock.setCoachDetailsNull();
		validate = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty, validate.validate());
	}

}