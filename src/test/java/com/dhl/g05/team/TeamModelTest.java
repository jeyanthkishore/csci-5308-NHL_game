package com.dhl.g05.team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.freeagent.PositionConstant;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.player.GenerateNewPlayers;
import com.dhl.g05.player.IGenerateNewPlayers;
import com.dhl.g05.player.IPlayer;

public class TeamModelTest{

	@Test
	public void TeamObjectConstructorTest() {
		JsonMockDataDb data= new JsonMockDataDb();
		TeamModel object = new TeamModel();
		assertNull(object.getCoachDetails());
		object.setGeneralManagerName("");
		assertTrue(object.getGeneralManagerName().isEmpty());
		assertNull(object.getTeamName());
		assertNull(object.getPlayerList());
		object.setPlayerList(data.playerList);
		assertFalse(object.getPlayerList().isEmpty());
	}

	@Test
	public void teamObjectParameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		TeamModel object = new TeamModel(data.teamName,data.coachDetails,data.generalManagerName,data.playerList);
		assertSame(data.teamName,object.getTeamName());
		assertSame(data.coachDetails,object.getCoachDetails());
		assertSame(data.generalManagerName,object.getGeneralManagerName());
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void teamReferenceConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		TeamModel object = new TeamModel(data);
		assertSame(data.teamName,object.getTeamName());
		assertSame(data.coachDetails,object.getCoachDetails());
		assertSame(data.generalManagerName,object.getGeneralManagerName());
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void setTeamNameTest()
	{
		TeamModel object = new TeamModel();
		object.setTeamName("Strikers");
		assertTrue(object.getTeamName().equals("Strikers"));
	}

	@Test
	public void getTeamNameTest()
	{
		TeamModel object = new TeamModel();
		object.setTeamName("Strikers");
		assertTrue(object.getTeamName().equals("Strikers"));
	}

	@Test
	public void setManagerNameTest()
	{
		TeamModel object = new TeamModel();
		object.setGeneralManagerName("Rubinho");
		assertTrue(object.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void removeRetiredPlayerFromTeamTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ITeam team = new TeamModel(mock);
		List<IPlayer> players = mock.playerList;
		team.removeRetiredPlayerFromTeam(players.get(0));
		assertEquals(mock.playerList.size()-1,team.getPlayerList().size()-1);
	}

	@Test
	public void getManagerNameTest()
	{
		TeamModel object = new TeamModel();
		object.setGeneralManagerName("Rubinho");
		assertTrue(object.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void setUserTeamTest()
	{
		TeamModel object = new TeamModel();
		object.setUserTeam(false);
		assertFalse(object.getUserTeam());
	}

	@Test
	public void getUserTeamTest()
	{
		TeamModel object = new TeamModel();
		object.setUserTeam(true);
		assertTrue(object.getUserTeam());
	}

	@Test
	public void setPlayerListTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamModel object = new TeamModel();
		object.setPlayerList(data.playerList);
		assertSame(data.playerList,object.getPlayerList());
	}

	@Test
	public void getPlayerListTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamModel object = new TeamModel();
		object.setPlayerList(data.playerList);
		assertSame(data.playerList,object.getPlayerList());
	}

	@Test
	public void setCoachDetailsTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamModel object = new TeamModel();
		object.setCoachDetails(data.coachDetails);
		assertSame(data.coachDetails, object.getCoachDetails());
	}

	@Test
	public void getCoachDetailsTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamModel object = new TeamModel();
		object.setCoachDetails(data.coachDetails);
		assertSame(data.coachDetails, object.getCoachDetails());
	}

	@Test
	public void setTeamStrengthTest() {
		TeamModel object = new TeamModel();
		object.setTeamStrength(25);
		assertEquals(object.getTeamStrength(),25,0);
	}

	@Test
	public void getTeamStrengthTest() {
		TeamModel object = new TeamModel();
		object.setTeamStrength(25);
		assertEquals(object.getTeamStrength(),25,0);
	}

	@Test
	public void isCoachDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		TeamModel object = new TeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty,object.validate());
	}

	@Test
	public void isCoachDetailsNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		TeamModel object = new TeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty,object.validate());
	}

	@Test
	public void checkPlayerListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.Success,validate.validate());
	}

	@Test
	public void checkPlayerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.PlayerListEmpty,validate.validate());
	}

	@Test
	public void checkPlayerListMaxTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.PlayerCountMismatch,validate.validate());
	}

	@Test
	public void teamNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameEmpty();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty,validate.validate());
	}

	@Test
	public void teamNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty,validate.validate());
	}

	@Test
	public void managerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameEmpty();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty,validate.validate());
	}

	@Test
	public void managerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameNull();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty,validate.validate());
	}

	@Test
	public void oneTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.Success,validate.validate());
	}
	@Test
	public void twoTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.MoreTeamCaptain,validate.validate());
	}

	@Test
	public void calculateTeamStrengthTest(){
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel object = new TeamModel(mock);
		assertEquals(object.calculateTeamStrength(mock.playerList),mock.calculateTeamStrength(mock.playerList),0);
	}
	@Test
	public void noTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeCaptain();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.NoTeamCaptain,validate.validate());
	}
	
	@Test
	public void adjustTeamRoasterAfterDraftSizeTest()
	{
		IGenerateNewPlayers newPlayers = new GenerateNewPlayers();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		ITeam team= new TeamModel();
		team.setPlayerList(players);
		team.adjustTeamRoasterAfterDraft(team);
		assertTrue(team.getPlayerList().size()== 30);
	}
	
	@Test
	public void adjustTeamRoasterAfterDrafPositiontCountTest()
	{
		IGenerateNewPlayers newPlayers = new GenerateNewPlayers();
		newPlayers.setNumberOfTeams(6);
		List<IPlayer> players = newPlayers.generatePlayers();
		ITeam team= new TeamModel();
		team.setPlayerList(players);
		team.adjustTeamRoasterAfterDraft(team);
		int numberOfForwards=0,numberOfDefense=0,numberOfGoalies=0;
		for (IPlayer player : team.getPlayerList()) {
			if (player.getPosition().equals(PositionConstant.defense.getValue())) {
				 numberOfDefense++;
				
			}
			if(player.getPosition().equals(PositionConstant.forward.getValue())) {
				numberOfForwards++;
			}
			if (player.getPosition().equals(PositionConstant.goalie.getValue())) {
				
				numberOfGoalies++;
			}
		}
		assertTrue(numberOfDefense== 10);
		assertTrue(numberOfForwards== 16);
		assertTrue(numberOfGoalies== 4);
	}

	@Test
	public void validateTeamTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel validate = new TeamModel(mock);
		assertSame(TeamConstant.Success,validate.validate());
		mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		validate = new TeamModel(mock);
		assertSame(TeamConstant.PlayerListEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		validate = new TeamModel(mock);
		assertSame(TeamConstant.TeamDetailsEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		validate = new TeamModel(mock);
		assertSame(TeamConstant.PlayerCountMismatch,validate.validate());
		mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		validate = new TeamModel(mock);
		assertSame(TeamConstant.MoreTeamCaptain,validate.validate());
		mock = new JsonMockDataDb();
		mock.removeCaptain();
		validate = new TeamModel(mock);
		assertSame(TeamConstant.NoTeamCaptain,validate.validate());
		mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		validate = new TeamModel(mock);
		assertSame(TeamConstant.CoachDetailsEmpty,validate.validate());
	}

}