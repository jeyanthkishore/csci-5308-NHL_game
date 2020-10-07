package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class PlayerObjectTest {
	
	@Test
	public void constructorTest() {
		PlayerObject object = new PlayerObject();
		assertNull(object.getTeamPlayers());
	}
	@Test
	public void setPlayerTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerObject object = new PlayerObject();
		object.setTeamPlayers(data.firstPlayerInfo);
		assertSame(object.getTeamPlayers(),data.firstPlayerInfo);
	}
	@Test
	public void getPlayerTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerObject object = new PlayerObject();
		object.setTeamPlayers(data.firstPlayerInfo);
		assertSame(object.getTeamPlayers(),data.firstPlayerInfo);
	}
	@Test
	public void playerParameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerObject object = new PlayerObject(data.firstPlayerInfo);
		assertSame(object.getTeamPlayers(),data.firstPlayerInfo);
	}
	@Test
	public void playerReferenceConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerObject object = new PlayerObject(data);
		assertSame(object.getTeamPlayers(),data.firstPlayerInfo);
	}
}