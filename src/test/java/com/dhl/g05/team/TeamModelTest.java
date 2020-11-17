package com.dhl.g05.team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

import java.util.List;

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

	@Test
	public void saveTeamObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamPersistenceMock data = new TeamPersistenceMock();
		TeamModel valid = new TeamModel(mock);
		assertEquals(1,valid.saveTeamObject(1,data));
	}

	@Test
	public void loadTeamObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamPersistenceMock data = new TeamPersistenceMock();
		TeamModel valid = new TeamModel(mock);
		assertEquals(1,valid.loadTeamObject(1,data));
	}
	
}