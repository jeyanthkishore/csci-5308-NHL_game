package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TeamValidationTest {
	@Test
	public void checkPlayerListTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		TeamValidation validate = new TeamValidation(mock);
		assertFalse(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setPlayerListEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListMaxTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.addMaximumPlayer();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isPlayerListMaximum());
	}
	
	@Test
	public void teamNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setTeamNameEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void teamNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setTeamNameNull();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void coachNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setCoachNameEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void coachNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setCoachNameNull();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsNull());
	}

	@Test
	public void managerNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setManagerNameEmpty();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void managerNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setManagerNameNull();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void oneTeamCaptainTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		TeamValidation validate = new TeamValidation(mock);
		assertTrue(validate.containOneTeamCaptain());
	}
	@Test
	public void twoTeamCaptainTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setSecondCaptain();
		TeamValidation validate = new TeamValidation(mock);
		assertFalse(validate.containOneTeamCaptain());
	}
	
	@Test
	public void noTeamCaptainTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeCaptain();
		TeamValidation validate = new TeamValidation(mock);
		assertFalse(validate.containOneTeamCaptain());
	}
	@Test
	public void validateTeamTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		TeamValidation validate = new TeamValidation(mock);
		assertEquals("success",validate.validateTeam());
		mock = new MockLeagueValidationDb();
		mock.setPlayerListEmpty();
		validate = new TeamValidation(mock);
		assertEquals("Player List is empty",validate.validateTeam());
		mock = new MockLeagueValidationDb();
		mock.setTeamNameNull();
		validate = new TeamValidation(mock);
		assertEquals("Team Details are Empty",validate.validateTeam());
		mock = new MockLeagueValidationDb();
		mock.addMaximumPlayer();
		validate = new TeamValidation(mock);
		assertEquals("There can be only 20 Players in a Team",validate.validateTeam());
		mock = new MockLeagueValidationDb();
		mock.setSecondCaptain();
		validate = new TeamValidation(mock);
		assertEquals("Team Must Contain Only One Captain",validate.validateTeam());
	}
}
