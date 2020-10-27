package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.operation.DbPersistanceMock;

public class PlayerObjectTest {
	
	@Test
	public void constructorTest() {
		PlayerObject object = new PlayerObject();
		assertNull(object.getPlayerName());
		assertNull(object.getPosition());
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
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}
	@Test
	public void getPositionTest() {
		PlayerObject object = new PlayerObject();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
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
	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerObject validate = new PlayerObject(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerObject validate = new PlayerObject(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerPositionValid());
	}
	
	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		PlayerObject validate = new PlayerObject(mock);
		assertFalse(validate.isPlayerPositionValid());
	}
	
	@Test
	public void captainNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNull();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isCaptainNull());
	}
	@Test
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerObject validate = new PlayerObject(mock);
		assertEquals("success",validate.validate());
		mock.setPlayerPositionEmpty();
		validate = new PlayerObject(mock);
		assertEquals("Player Should Not have Empty Value",validate.validate());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		validate = new PlayerObject(mock);
		assertEquals("Player Position Is Wrong",validate.validate());
		mock = new JsonMockDataDb();
		mock.setCaptainNull();
		validate = new PlayerObject(mock);
		assertEquals("Captain Cannot be Null",validate.validate());
	}
	
	@Test
	public void savePlayerObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		PlayerObject valid = new PlayerObject(mock);
		assertEquals(1,valid.savePlayerObject(1,data));
	}
	@Test
	public void loadPlayerObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		PlayerObject valid = new PlayerObject(mock);
		assertEquals(1,valid.loadPlayerObject(1,data));
	}
}