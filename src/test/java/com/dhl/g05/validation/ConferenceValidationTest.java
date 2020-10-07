package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConferenceValidationTest {
	@Test
	public void checkConferenceNameEmpty() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertFalse(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void checkConferenceNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setConferenceNameEmpty();
		ConferenceValidation validate = new ConferenceValidation(mock);
		assertTrue(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void checkConferenceNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setConferenceNameNull();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertTrue(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void isDivisonListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertFalse(validate.isDivisionListEmpty());
	}
	@Test
	public void divisonListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeDivision();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}
	@Test
	public void hasEvenDivsionTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}
	@Test
	public void hasOddDivsionTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeOneDivision();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertFalse(validate.hasEvenNumberDivision());
	}
	@Test
	public void validateConferenceTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		ConferenceValidation validate = new ConferenceValidation(mock); 
		assertEquals("success",validate.validateConference());
		mock = new MockLeagueValidationDb();
		mock.setConferenceNameEmpty();
		validate = new ConferenceValidation(mock); 
		assertEquals("Conference name is Empty",validate.validateConference());
		mock = new MockLeagueValidationDb();
		mock.removeDivision();
		validate = new ConferenceValidation(mock); 
		assertEquals("Division List is Empty",validate.validateConference());
		mock = new MockLeagueValidationDb();
		mock.removeOneDivision();
		validate = new ConferenceValidation(mock); 
		assertEquals("Division must be even",validate.validateConference());
	}
}
