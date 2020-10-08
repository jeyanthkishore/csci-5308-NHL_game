package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class PlayerValidationTest {

	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerDetailsEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailListEmpty());
	}
	
	@Test
	public void checkPlayerDetailHighSize() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMoreAttribute();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailNotInRange());
	}
	
	@Test
	public void checkPlayerDetailLessSize() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeAttribute();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailNotInRange());
	}
	
	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerValidation validate = new PlayerValidation(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerPositionValid());
	}
	
	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		PlayerValidation validate = new PlayerValidation(mock);
		assertFalse(validate.isPlayerPositionValid());
	}
	
	@Test
	public void captainEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void captainNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNull();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void captainBooleanTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCaptainNonBoolean();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isCaptainNotBoolean());
	}
	@Test
	public void validatePlayerTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		PlayerValidation validate = new PlayerValidation(mock);
		assertEquals("success",validate.validatePlayer());
		mock.setPlayerPositionEmpty();
		validate = new PlayerValidation(mock);
		assertEquals("Player Should Not have Empty Value",validate.validatePlayer());
		mock = new JsonMockDataDb();
		mock.setPlayerDetailsEmpty();
		validate = new PlayerValidation(mock);
		assertEquals("Player Must Have 3 Attributes",validate.validatePlayer());
		mock = new JsonMockDataDb();
		mock.setCaptainNonBoolean();
		validate = new PlayerValidation(mock);
		assertEquals("Captain attribute must be boolean",validate.validatePlayer());
		mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		validate = new PlayerValidation(mock);
		assertEquals("Player Position is wrong",validate.validatePlayer());
	}
}
