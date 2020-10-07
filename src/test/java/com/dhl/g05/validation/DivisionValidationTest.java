package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DivisionValidationTest {
	@Test
	public void checkDivisionNameEmpty() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertFalse(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void checkDivisionNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setDivisionNameEmpty();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void checkDivisionNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setDivisionNameNull();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void isTeamListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertFalse(validate.isTeamListEmpty());
	}
	@Test
	public void teamListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeTeams();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertTrue(validate.isTeamListEmpty());
	}
	@Test
	public void validateDivisionTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertEquals("success",validate.validateDivision());
		mock = new MockLeagueValidationDb();
		mock.setDivisionNameEmpty();
		validate = new DivisionValidation(mock);
		assertEquals("DivisionName Cannot be empty",validate.validateDivision());
		mock = new MockLeagueValidationDb();
		mock.removeTeams();
		validate = new DivisionValidation(mock); 
		assertEquals("TeamList is empty",validate.validateDivision());
	}
}
