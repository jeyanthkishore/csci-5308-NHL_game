package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class DivisionValidationTest {
	@Test
	public void checkDivisionNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertFalse(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void checkDivisionNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void checkDivisionNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameNull();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void isTeamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertFalse(validate.isTeamListEmpty());
	}
	@Test
	public void teamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeTeams();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertTrue(validate.isTeamListEmpty());
	}
	@Test
	public void validateDivisionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionValidation validate = new DivisionValidation(mock); 
		assertEquals("success",validate.validateDivision());
		mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		validate = new DivisionValidation(mock);
		assertEquals("DivisionName Cannot be empty",validate.validateDivision());
		mock = new JsonMockDataDb();
		mock.removeTeams();
		validate = new DivisionValidation(mock); 
		assertEquals("TeamList is empty",validate.validateDivision());
	}
}
