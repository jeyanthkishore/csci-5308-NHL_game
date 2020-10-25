package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.operation.DbPersistanceMock;

public class TeamObjectTest{
	@Test
	public void TeamObjectConstructorTest() {
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject();
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
		DbPersistanceMock db = new DbPersistanceMock();
		TeamObject object = new TeamObject(data.teamName,data.coachDetails,data.generalManagerName,data.playerList);
		assertSame(data.teamName,object.getTeamName());
		assertSame(data.coachDetails,object.getCoachDetails());
		assertSame(data.generalManagerName,object.getGeneralManagerName());
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void teamReferenceConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		TeamObject object = new TeamObject(data);
		assertSame(data.teamName,object.getTeamName());
		assertSame(data.coachDetails,object.getCoachDetails());
		assertSame(data.generalManagerName,object.getGeneralManagerName());
		assertSame(data.playerList, object.getPlayerList());
	}

	@Test
	public void setTeamNameTest()
	{
		TeamObject object = new TeamObject();
		object.setTeamName("Strikers");
		assertTrue(object.getTeamName().equals("Strikers"));
	}
	
	@Test
	public void getTeamNameTest()
	{
		TeamObject object = new TeamObject();
		object.setTeamName("Strikers");
		assertTrue(object.getTeamName().equals("Strikers"));
	}

	@Test
	public void getResultTest() {
		TeamObject object = new TeamObject();
		object.setResult("success");
		assertEquals("success",object.getResult());
	}
	@Test
	public void setResultTest() {
		TeamObject object = new TeamObject();
		object.setResult("success");
		assertEquals("success",object.getResult());
	}

	@Test
	public void setManagerNameTest()
	{
		TeamObject object = new TeamObject();
		object.setGeneralManagerName("Rubinho");
		assertTrue(object.getGeneralManagerName().equals("Rubinho"));
	}
	
	@Test
	public void getManagerNameTest()
	{
		TeamObject object = new TeamObject();
		object.setGeneralManagerName("Rubinho");
		assertTrue(object.getGeneralManagerName().equals("Rubinho"));
	}

	@Test
	public void setPlayerListTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject();
		object.setPlayerList(data.playerList);
		assertSame(data.playerList,object.getPlayerList());
	}
	
	@Test
	public void getPlayerListTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject();
		object.setPlayerList(data.playerList);
		assertSame(data.playerList,object.getPlayerList());
	}

	@Test
	public void setCoachDetailsTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject();
		object.setCoachDetails(data.coachDetails);
		assertSame(data.coachDetails, object.getCoachDetails());
	}

	@Test
	public void getCoachDetailsTest()
	{
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject();
		object.setCoachDetails(data.coachDetails);
		assertSame(data.coachDetails, object.getCoachDetails());
	}

	@Test
	public void setTeamStrengthTest() {
		TeamObject object = new TeamObject();
		object.setTeamStrength(25);
		assertEquals(object.getTeamStrength(),25,0);
	}

	@Test
	public void getTeamStrengthTest() {
		TeamObject object = new TeamObject();
		object.setTeamStrength(25);
		assertEquals(object.getTeamStrength(),25,0);
	}

	@Test
	public void isCoachDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		TeamObject object = new TeamObject(mock);
		assertTrue(object.isCoachDetailsEmptyOrNull());
	}

	@Test
	public void isCoachDetailsNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		TeamObject object = new TeamObject(mock);
		assertTrue(object.isCoachDetailsEmptyOrNull());
	}

	@Test
	public void checkPlayerListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamObject validate = new TeamObject(mock);
		assertFalse(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListMaxTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isPlayerListMaximum());
	}
	
	@Test
	public void teamNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void teamNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsNull());
	}

	@Test
	public void managerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void managerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameNull();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void oneTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamObject validate = new TeamObject(mock);
		assertEquals(1,validate.containOneTeamCaptain());
	}
	@Test
	public void twoTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		TeamObject validate = new TeamObject(mock);
		assertEquals(2,validate.containOneTeamCaptain());
	}

	@Test
	public void calculateTeamStrengthTest(){
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamObject object = new TeamObject(mock);
		assertEquals(object.calculateTeamStrength(mock.playerList),mock.calculateTeamStrength(mock.playerList),0);
	}
	@Test
	public void noTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeCaptain();
		TeamObject validate = new TeamObject(mock);
		assertEquals(0,validate.containOneTeamCaptain());
	}



	@Test
	public void validateTeamTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamObject validate = new TeamObject(mock);
		assertEquals("success",validate.validate());
		mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		validate = new TeamObject(mock);
		assertEquals("Player List Is Empty",validate.validate());
		mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		validate = new TeamObject(mock);
		assertEquals("Team Details Are Empty",validate.validate());
		mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		validate = new TeamObject(mock);
		assertEquals("Maximum Player Limit Is 20",validate.validate());
		mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		validate = new TeamObject(mock);
		assertEquals("Team Must Contain Only One Captain",validate.validate());
		mock = new JsonMockDataDb();
		mock.removeCaptain();
		validate = new TeamObject(mock);
		assertEquals("Team Must Contain Atleast One Captain",validate.validate());
		mock = new JsonMockDataDb();
		mock.setCoachDetailsNull();
		validate = new TeamObject(mock);
		assertEquals("Coach has missing values",validate.validate());
	}
	
	@Test
	public void saveTeamObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		TeamObject valid = new TeamObject(mock);
		assertEquals(1,valid.saveTeamObject(1,data));
	}
	
	@Test
	public void loadTeamObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		TeamObject valid = new TeamObject(mock);
		assertEquals(1,valid.loadTeamObject(1,data));
	}
}