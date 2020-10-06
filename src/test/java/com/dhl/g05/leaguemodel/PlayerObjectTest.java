package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PlayerObjectTest {
	
	
	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerDetailsEmpty();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailListEmpty());
	}
	
	@Test
	public void checkPlayerDetailHighSize() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMoreAttribute();
		PlayerObject validate = new PlayerObject(mock);
		assertFalse(validate.isPlayerDetailInRange());
	}
	
	@Test
	public void checkPlayerDetailLessSize() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeAttribute();
		PlayerObject validate = new PlayerObject(mock);
		assertFalse(validate.isPlayerDetailInRange());
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
	public void captainEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainEmpty();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void captainNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNull();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void captainBooleanTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNonBoolean();
		PlayerObject validate = new PlayerObject(mock);
		assertTrue(validate.isCaptainBoolean());
	}
}