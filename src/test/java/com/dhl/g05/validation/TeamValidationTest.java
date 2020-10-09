package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class TeamValidationTest {
	@Test
	public void checkPlayerListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamValidation validate = new TeamValidation(mock);
		assertFalse(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListMaxTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isPlayerListMaximum());
	}
	
	@Test
	public void teamNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void teamNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void coachNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachNameEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void coachNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachNameNull();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsNull());
	}

	@Test
	public void managerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void managerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameNull();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void oneTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.containOneTeamCaptain());
	}
	@Test
	public void twoTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		TeamValidation validate = new TeamValidation(mock);
		assertFalse(validate.containOneTeamCaptain());
	}
	
	@Test
	public void noTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeCaptain();
		TeamValidation validate = new TeamValidation(mock);
		assertFalse(validate.containOneTeamCaptain());
	}
	@Test
	public void validateTeamTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamValidation validate = new TeamValidation(mock);
		assertEquals("success",validate.validateTeam());
		mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		validate = new TeamValidation(mock);
		assertEquals("Player List Is Empty",validate.validateTeam());
		mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		validate = new TeamValidation(mock);
		assertEquals("Team Details Are Empty",validate.validateTeam());
		mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		validate = new TeamValidation(mock);
		assertEquals("Maximum Player Limit Is 20",validate.validateTeam());
		mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		validate = new TeamValidation(mock);
		assertEquals("Team Must Contain Only One Captain",validate.validateTeam());
	}
}
