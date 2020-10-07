package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerValidationTest {

	@Test
	public void playerListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPlayerDetailsEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailListEmpty());
	}
	
	@Test
	public void checkPlayerDetailHighSize() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.addMoreAttribute();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailNotInRange());
	}
	
	@Test
	public void checkPlayerDetailLessSize() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeAttribute();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailNotInRange());
	}
	
	@Test
	public void checkPlayerDetailsEmpty() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		PlayerValidation validate = new PlayerValidation(mock);
		assertFalse(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPlayerNameEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPlayerNameNull();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}

	@Test
	public void playerPositionEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPlayerPositionEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void playerPositionNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPlayerPostitionNull();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void playerPositionValidTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerPositionValid());
	}
	
	@Test
	public void playerPositionCheckTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPositionDifferent();
		PlayerValidation validate = new PlayerValidation(mock);
		assertFalse(validate.isPlayerPositionValid());
	}
	
	@Test
	public void captainEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setCaptainEmpty();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsEmpty());
	}
	
	@Test
	public void captainNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setCaptainNull();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isPlayerDetailsNull());
	}
	
	@Test
	public void captainBooleanTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setCaptainNonBoolean();
		PlayerValidation validate = new PlayerValidation(mock);
		assertTrue(validate.isCaptainNotBoolean());
	}
	@Test
	public void validatePlayerTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		PlayerValidation validate = new PlayerValidation(mock);
		assertEquals("success",validate.validatePlayer());
		mock.setPlayerPositionEmpty();
		validate = new PlayerValidation(mock);
		assertEquals("Player Should Not have Empty Value",validate.validatePlayer());
		mock = new MockLeagueValidationDb();
		mock.setPlayerDetailsEmpty();
		validate = new PlayerValidation(mock);
		assertEquals("Player Must Have 3 Attributes",validate.validatePlayer());
		mock = new MockLeagueValidationDb();
		mock.setCaptainNonBoolean();
		validate = new PlayerValidation(mock);
		assertEquals("Captain attribute must be boolean",validate.validatePlayer());
		mock = new MockLeagueValidationDb();
		mock.setPositionDifferent();
		validate = new PlayerValidation(mock);
		assertEquals("Player Position is wrong",validate.validatePlayer());
	}
}
