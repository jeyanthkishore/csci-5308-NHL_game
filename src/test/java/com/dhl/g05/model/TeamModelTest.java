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
	public void teamObjectConstructorTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel();
		assertNull(team.getCoachDetails());
		team.setGeneralManagerName("");
		assertTrue(team.getGeneralManagerName().isEmpty());
		assertNull(team.getTeamName());
		assertNull(team.getPlayerList());
		team.setPlayerList(data.playerList);
		assertFalse(team.getPlayerList().isEmpty());
	}

	@Test
	public void teamReferenceConstructorTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel(data);
		assertSame(data.teamName, team.getTeamName());
		assertSame(data.coachDetails, team.getCoachDetails());
		assertSame(data.generalManagerName, team.getGeneralManagerName());
		assertSame(data.playerList, team.getPlayerList());
	}

	@Test
	public void setTeamNameTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setTeamName("Strikers");
		assertTrue(team.getTeamName().equals("Strikers"));
	}

	@Test
	public void getTeamNameTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setTeamName("Strikers");
		assertTrue(team.getTeamName().equals("Strikers"));
	}

	@Test
	public void setGeneralManagerNameTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setGeneralManagerName("Rubinho");
		assertTrue(team.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void getGeneralManagerNameTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setGeneralManagerName("Rubinho");
		assertTrue(team.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void setUserTeamTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setUserTeam(false);
		assertFalse(team.getUserTeam());
	}

	@Test
	public void getUserTeamTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setUserTeam(true);
		assertTrue(team.getUserTeam());
	}

	@Test
	public void setPlayerListTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setPlayerList(data.playerList);
		assertSame(data.playerList, team.getPlayerList());
	}

	@Test
	public void getPlayerListTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setPlayerList(data.playerList);
		assertSame(data.playerList, team.getPlayerList());
	}

	@Test
	public void setCoachDetailsTest() {
		LeagueMockData leagueMock = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setCoachDetails(leagueMock.coachDetails);
		assertSame(leagueMock.coachDetails, team.getCoachDetails());
	}

	@Test
	public void getCoachDetailsTest() {
		LeagueMockData leagueMock = modelMockFactory.createLeagueMockData();
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setCoachDetails(leagueMock.coachDetails);
		assertSame(leagueMock.coachDetails, team.getCoachDetails());
	}

	@Test
	public void setTeamStrengthTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setTeamStrength(25);
		assertEquals(team.getTeamStrength(), 25, 0);
	}

	@Test
	public void getTeamStrengthTest() {
		ITeam team = modelAbstractFactory.createTeamModel();
		team.setTeamStrength(25);
		assertEquals(team.getTeamStrength(), 25, 0);
	}

	@Test
	public void isCoachDetailsEmpty() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setCoachDetailsNull();
		ITeam team = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty, team.validate());
	}

	@Test
	public void isCoachDetailsNull() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setCoachDetailsNull();
		ITeam team = modelAbstractFactory.createTeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty, team.validate());
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

//	@Test
//	public void checkPlayerListMaxTest() {
//		LeagueMockData mock = modelMockFactory.createLeagueMockData();
//		mock.addMaximumPlayer();
//		ITeam validate = modelAbstractFactory.createTeamModel(mock);
//		assertSame(TeamConstant.PlayerCountMismatch, validate.validate());
//	}

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
		ITeam team = modelAbstractFactory.createTeamModel(mock);
		assertEquals(60, team.calculateTeamStrength(mock.playerList),0);
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
	
	@Test
	public void setActiveRosterPlayerTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		newPlayers.setNumberOfTeams(5);
		ITeam team = modelAbstractFactory.createTeamModel();
		List<IPlayer> players = newPlayers.generatePlayers();
		team.setPlayerList(players);
		List<IPlayer> activePlayers=team.setActiveRoster(team);
		assertEquals(activePlayers.get(1).getIsActive(),true);
	}
	
	@Test
	public void setInActiveRosterTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		ITeam team = modelAbstractFactory.createTeamModel();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		team.setPlayerList(players);
		List<IPlayer> inActivePlayers=team.setInActiveRoster(team);
		assertEquals(inActivePlayers.size(),10);
	}
	
	@Test
	public void setInActiveRosterPlayerTest() {
		IGenerateNewPlayers newPlayers = modelAbstractFactory.createNewPlayers();
		ITeam team = modelAbstractFactory.createTeamModel();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		team.setPlayerList(players);
		List<IPlayer> inActivePlayers=team.setInActiveRoster(team);
		assertEquals(inActivePlayers.get(1).getIsActive(),false);
	}
	
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
	public void validateTest() {
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
//		mock = modelMockFactory.createLeagueMockData();
//		mock.addMaximumPlayer();
//		validate = modelAbstractFactory.createTeamModel(mock);
//		assertSame(TeamConstant.PlayerCountMismatch, validate.validate());
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