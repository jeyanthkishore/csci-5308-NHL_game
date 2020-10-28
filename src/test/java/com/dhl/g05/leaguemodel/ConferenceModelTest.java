package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.conference.ConferenceConstant;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.operation.DbPersistanceMock;

public class ConferenceModelTest {

	@Test
	public void conferenceConstructorTest() {
		ConferenceModel object = new ConferenceModel();
		assertNull(object.getConferenceName());
		assertNull(object.getDivisionDetails());
	}

	@Test
	public void setDivisionTest() {
		ConferenceModel object = new ConferenceModel();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}

	@Test
	public void getDivisionTest() {
		ConferenceModel object = new ConferenceModel();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}

	@Test
	public void setTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceModel object = new ConferenceModel();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void getTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceModel object = new ConferenceModel();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void divisionParameterConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceModel object = new ConferenceModel(data.conferenceName,data.divisionList);
		assertSame(data.conferenceName,object.getConferenceName());
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void divisionReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		ConferenceModel object = new ConferenceModel(data);
		assertSame(data.conferenceName,object.getConferenceName());
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void checkConferenceNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertFalse(validate.isNameEmptyOrNull());
	}

	@Test
	public void checkConferenceNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		ConferenceModel validate = new ConferenceModel(mock);
		assertTrue(validate.isNameEmptyOrNull());
	}

	@Test
	public void checkConferenceNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameNull();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertTrue(validate.isNameEmptyOrNull());
	}

	@Test
	public void isDivisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertFalse(validate.isDivisionListEmpty());
	}

	@Test
	public void divisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeDivision();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}

	@Test
	public void hasEvenDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertTrue(validate.hasEvenNumberDivision());
	}

	@Test
	public void hasOddDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneDivision();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertFalse(validate.hasEvenNumberDivision());
	}

	@Test
	public void validateConferenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel validate = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,validate.validate());
		mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		validate = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.ConferenceNameEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.removeDivision();
		validate = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.DivisionListEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.removeOneDivision();
		validate = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.NoEvenDivisionCount,validate.validate());
	}

	@Test
	public void saveConferenceObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		ConferenceModel valid = new ConferenceModel(mock);
		assertEquals(1,valid.saveConferenceObject(1,data));
	}

	@Test
	public void loadConferenceObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		ConferenceModel valid = new ConferenceModel(mock);
		assertEquals(1,valid.loadConferenceObject(1,data));
	}
}
