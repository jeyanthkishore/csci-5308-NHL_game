package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class ConferenceValidationTest {
	@Test
	public void checkConferenceNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertFalse(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void checkConferenceNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		ConferenceValidation validate = new ConferenceValidation(mock);
		assertTrue(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void checkConferenceNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameNull();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertTrue(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void isDivisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertFalse(validate.isDivisionListEmpty());
	}
	@Test
	public void divisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeDivision();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}
	@Test
	public void hasEvenDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}
	@Test
	public void hasOddDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneDivision();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertFalse(validate.hasEvenNumberDivision());
	}
	@Test
	public void validateConferenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertEquals("success",validate.validateConference());
		mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		validate = new ConferenceValidation(mock); 
		assertEquals("Conference Name Is Empty",validate.validateConference());
		mock = new JsonMockDataDb();
		mock.removeDivision();
		validate = new ConferenceValidation(mock); 
		assertEquals("Division List Is Empty",validate.validateConference());
		mock = new JsonMockDataDb();
		mock.removeOneDivision();
		validate = new ConferenceValidation(mock); 
		assertEquals("Division Count Must Be Even",validate.validateConference());
	}
}
