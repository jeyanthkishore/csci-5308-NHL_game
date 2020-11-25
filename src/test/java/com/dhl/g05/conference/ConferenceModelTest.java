package com.dhl.g05.conference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class ConferenceModelTest {

	@Test
	public void conferenceConstructorTest() {
		IConference object = new ConferenceModel();
		assertNull(object.getConferenceName());
		assertNull(object.getDivisionDetails());
	}

	@Test
	public void setDivisionTest() {
		IConference object = new ConferenceModel();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}

	@Test
	public void getDivisionTest() {
		IConference object = new ConferenceModel();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}

	@Test
	public void setTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		IConference object = new ConferenceModel();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void getTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		IConference object = new ConferenceModel();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void divisionParameterConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		IConference object = new ConferenceModel(data.conferenceName,data.divisionList);
		assertSame(data.conferenceName,object.getConferenceName());
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void divisionReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		IConference conference = new ConferenceModel(data);
		assertSame(data.conferenceName,conference.getConferenceName());
		assertSame(data.divisionList,conference.getDivisionDetails());
	}

	@Test
	public void checkConferenceNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IConference conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void checkConferenceNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameEmpty();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void checkConferenceNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setConferenceNameNull();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void isDivisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void divisonListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeDivision();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
	}

	@Test
	public void hasEvenDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IConference conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void hasOddDivsionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneDivision();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.NoEvenDivisionCount,conference.validate());
	}

	@Test
	public void validateConferenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IConference conference = new ConferenceModel(mock); 
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

}
