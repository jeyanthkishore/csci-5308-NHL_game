package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.operation.DbPersistanceMock;

public class ConferenceObjectTest {
	@Test
	public void conferenceConstructorTest() {
		ConferenceObject object = new ConferenceObject();
		assertNull(object.getConferenceName());
		assertNull(object.getDivisionDetails());
	}
	@Test
	public void setDivisionTest() {
		ConferenceObject object = new ConferenceObject();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}
	@Test
	public void getDivisionTest() {
		ConferenceObject object = new ConferenceObject();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}
	@Test
	public void setTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceObject object = new ConferenceObject();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}
	@Test
	public void getTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceObject object = new ConferenceObject();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}
	@Test
	public void divisionParameterConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceObject object = new ConferenceObject(data.conferenceName,data.divisionList);
		assertSame(data.conferenceName,object.getConferenceName());
		assertSame(data.divisionList,object.getDivisionDetails());
	}
	@Test
	public void divisionReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceObject object = new ConferenceObject(data);
		assertSame(data.conferenceName,object.getConferenceName());
		assertSame(data.divisionList,object.getDivisionDetails());
	}
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
	@Test
	public void validateConferenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceObject validate = new ConferenceObject(mock); 
		assertEquals("success",validate.validate());
		mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		validate = new ConferenceObject(mock); 
		assertEquals("Conference Name Is Empty",validate.validate());
		mock = new JsonMockDataDb();
		mock.removeDivision();
		validate = new ConferenceObject(mock); 
		assertEquals("Division List Is Empty",validate.validate());
		mock = new JsonMockDataDb();
		mock.removeOneDivision();
		validate = new ConferenceObject(mock); 
		assertEquals("Division Count Must Be Even",validate.validate());
	}
	
	@Test
	public void saveConferenceObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		ConferenceObject valid = new ConferenceObject(mock);
		assertEquals(1,valid.saveConferenceObject(1,data));
	}
	@Test
	public void loadConferenceObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		ConferenceObject valid = new ConferenceObject(mock);
		assertEquals(1,valid.loadConferenceObject(1,data));
	}
}
