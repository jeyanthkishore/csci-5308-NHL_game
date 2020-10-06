package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConferenceObjectTest {
	
	@Test
	public void checkConferenceNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertFalse(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void checkConferenceNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		ConferenceObject validate = new ConferenceObject(mock);
		assertTrue(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void checkConferenceNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameNull();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertTrue(validate.isNameEmptyOrNull());
	}
	
	@Test
	public void isDivisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertFalse(validate.isDivisionListEmpty());
	}
	@Test
	public void divisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeDivision();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}
	@Test
	public void hasEvenDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}
	@Test
	public void hasOddDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneDivision();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertFalse(validate.hasEvenNumberDivision());
	}
}
