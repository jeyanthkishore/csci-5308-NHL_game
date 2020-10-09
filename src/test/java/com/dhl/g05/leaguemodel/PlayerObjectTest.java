package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerObjectTest {
	
	@Test
	public void constructorTest() {
		PlayerObject object = new PlayerObject();
		assertNull(object.getPlayerName());
		assertNull(object.getPostition());
		assertNull(object.getCaptain());
	}
	@Test
	public void setPlayerNameTest() {
		PlayerObject object = new PlayerObject();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}
	@Test
	public void getPlayerNameTest() {
		PlayerObject object = new PlayerObject();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}
	@Test
	public void setPositionTest() {
		PlayerObject object = new PlayerObject();
		object.setPlayerName("forward");
		assertSame(object.getPlayerName(),"forward");
	}
	@Test
	public void getPositionTest() {
		PlayerObject object = new PlayerObject();
		object.setPlayerName("goalie");
		assertSame(object.getPlayerName(),"goalie");
	}
	@Test
	public void setCaptainTest() {
		PlayerObject object = new PlayerObject();
		object.setCaptain(true);
		assertTrue(object.getCaptain());
	}
	@Test
	public void getCaptainTest() {
		PlayerObject object = new PlayerObject();
		object.setCaptain(true);
		assertTrue(object.getCaptain());
	}
}