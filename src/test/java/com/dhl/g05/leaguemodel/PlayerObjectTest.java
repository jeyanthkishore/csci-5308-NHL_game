package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.operation.DbPersistanceMock;

public class PlayerObjectTest {
	
	@Test
	public void constructorTest() {
		PlayerModel object = new PlayerModel();
		assertNull(object.getPlayerName());
		assertNull(object.getPosition());
		assertNull(object.getCaptain());
	}

	@Test
	public void setPlayerNameTest() {
		PlayerModel object = new PlayerModel();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}
	@Test
	public void getPlayerNameTest() {
		PlayerModel object = new PlayerModel();
		object.setPlayerName("Ronaldo");
		assertSame(object.getPlayerName(),"Ronaldo");
	}
	@Test
	public void setPositionTest() {
		PlayerModel object = new PlayerModel();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}
	@Test
	public void getPositionTest() {
		PlayerModel object = new PlayerModel();
		object.setPosition("forward");
		assertSame(object.getPosition(),"forward");
	}
	@Test
	public void setCaptainTest() {
		PlayerModel object = new PlayerModel();
		object.setCaptain(true);
		assertTrue(object.getCaptain());
	}
	@Test
	public void getCaptainTest() {
		PlayerModel object = new PlayerModel();
		object.setCaptain(true);
		assertTrue(object.getCaptain());
	}
	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel validate = new PlayerModel(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel validate = new PlayerModel(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		PlayerModel validate = new PlayerModel(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		PlayerModel validate = new PlayerModel(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		PlayerModel validate = new PlayerModel(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		PlayerModel validate = new PlayerModel(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel validate = new PlayerModel(mock);
		assertTrue(validate.isPlayerPositionValid());
	}
	
	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		PlayerModel validate = new PlayerModel(mock);
		assertFalse(validate.isPlayerPositionValid());
	}
	
	@Test
	public void captainNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNull();
		PlayerModel validate = new PlayerModel(mock);
		assertTrue(validate.isCaptainNull());
	}
	@Test
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerModel validate = new PlayerModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
		mock.setPlayerPositionEmpty();
		validate = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		validate = new PlayerModel(mock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,validate.validate());
		mock = new JsonMockDataDb();
		mock.setCaptainNull();
		validate = new PlayerModel(mock);
		assertSame(FreeAgentConstant.CaptainNull,validate.validate());
	}
	
	@Test
	public void savePlayerObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		PlayerModel valid = new PlayerModel(mock);
		assertEquals(1,valid.savePlayerObject(1,data));
	}
	@Test
	public void loadPlayerObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		PlayerModel valid = new PlayerModel(mock);
		assertEquals(1,valid.loadPlayerObject(1,data));
	}
}