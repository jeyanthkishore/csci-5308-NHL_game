package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TeamObjectTest{
	@Test
	public void TeamObjectConstructorTest() {
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject();
		assertNull(object.getCoachName());
		object.setManagerName("");
		assertTrue(object.getManagerName().isEmpty());
		assertNull(object.getTeamName());
		assertNull(object.getPlayerList());
		object.setPlayerList(data.playerList);
		assertFalse(object.getPlayerList().isEmpty());
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
	public void setCoachNameTest()
	{
		TeamObject object = new TeamObject();
		object.setCoachName("Pele");
		assertTrue(object.getCoachName().equals("Pele"));
	}
	
	@Test
	public void getCoachNameTest()
	{
		TeamObject object = new TeamObject();
		object.setCoachName("Pele");
		assertTrue(object.getCoachName().equals("Pele"));
	}
	@Test
	public void setManagerNameTest()
	{
		TeamObject object = new TeamObject();
		object.setManagerName("Rubinho");
		assertTrue(object.getManagerName().equals("Rubinho"));
	}
	
	@Test
	public void getManagerNameTest()
	{
		TeamObject object = new TeamObject();
		object.setManagerName("Rubinho");
		assertTrue(object.getManagerName().equals("Rubinho"));
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
	public void ConstructorWithParamerterTest() {
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject(data.teamName,data.headCoachName,data.generalManagerName,data.playerList);
		assertSame(data.playerList,object.getPlayerList());
		assertSame(data.teamName,object.getTeamName());
		assertSame(data.headCoachName,object.getCoachName());
		assertSame(data.generalManagerName,object.getManagerName());
	}
	@Test
	public void ConstructorWithReferenceTest() {
		JsonMockDataDb data= new JsonMockDataDb();
		TeamObject object = new TeamObject(data);
		assertSame(data.playerList,object.getPlayerList());
		assertSame(data.teamName,object.getTeamName());
		assertSame(data.headCoachName,object.getCoachName());
		assertSame(data.generalManagerName,object.getManagerName());
	}
}