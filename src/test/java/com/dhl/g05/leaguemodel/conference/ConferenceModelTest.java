package com.dhl.g05.leaguemodel.conference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
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
		ConferenceModel conference = new ConferenceModel(data);
		assertSame(data.conferenceName,conference.getConferenceName());
		assertSame(data.divisionList,conference.getDivisionDetails());
	}

	@Test
	public void checkConferenceNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void checkConferenceNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		ConferenceModel conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void checkConferenceNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameNull();
		ConferenceModel conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void isDivisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void divisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeDivision();
		ConferenceModel conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
	}

	@Test
	public void hasEvenDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void hasOddDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneDivision();
		ConferenceModel conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.NoEvenDivisionCount,conference.validate());
	}

	@Test
	public void validateConferenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ConferenceModel conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
		mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
		mock = new JsonMockDataDb();
		mock.removeDivision();
		conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
		mock = new JsonMockDataDb();
		mock.removeOneDivision();
		conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.NoEvenDivisionCount,conference.validate());
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
