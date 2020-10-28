package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.team.TeamModel;
import com.dhl.g05.operation.DbPersistanceMock;

public class TeamObjectTest{
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
		assertTrue(object.isCoachDetailsEmptyOrNull());
	}

	@Test
	public void isCoachDetailsNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		TeamModel object = new TeamModel(mock);
		assertTrue(object.isCoachDetailsEmptyOrNull());
	}

	@Test
	public void checkPlayerListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel validate = new TeamModel(mock);
		assertFalse(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		TeamModel validate = new TeamModel(mock);
		assertTrue(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListMaxTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		TeamModel validate = new TeamModel(mock);
		assertTrue(validate.isPlayerListMaximum());
	}
	
	@Test
	public void teamNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameEmpty();
		TeamModel validate = new TeamModel(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void teamNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		TeamModel validate = new TeamModel(mock);
		assertTrue(validate.isTeamDetailsNull());
	}

	@Test
	public void managerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameEmpty();
		TeamModel validate = new TeamModel(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void managerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameNull();
		TeamModel validate = new TeamModel(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void oneTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel validate = new TeamModel(mock);
		assertEquals(1,validate.containOneTeamCaptain());
	}
	@Test
	public void twoTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		TeamModel validate = new TeamModel(mock);
		assertEquals(2,validate.containOneTeamCaptain());
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
		assertEquals(0,validate.containOneTeamCaptain());
	}



	@Test
	public void validateTeamTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamModel validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.Success,validate.validate());
		mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.PlayerListEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.TeamDetailsEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.MaxPlayerCountExceed,validate.validate());
		mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.MoreTeamCaptain,validate.validate());
		mock = new JsonMockDataDb();
		mock.removeCaptain();
		validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.NoTeamCaptain,validate.validate());
		mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		validate = new TeamModel(mock);
		assertSame(ValidateEnumModel.CoachDetailsEmpty,validate.validate());
	}
	
	@Test
	public void saveTeamObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		TeamModel valid = new TeamModel(mock);
		assertEquals(1,valid.saveTeamObject(1,data));
	}
	
	@Test
	public void loadTeamObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		TeamModel valid = new TeamModel(mock);
		assertEquals(1,valid.loadTeamObject(1,data));
	}
}