package com.dhl.g05.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.model.ConferenceConstant;
import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.IConference;

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
		LeagueMockData data = new LeagueMockData();
		IConference object = new ConferenceModel();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void getTeamListTest() {
		LeagueMockData data = new LeagueMockData();
		IConference object = new ConferenceModel();
		object.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,object.getDivisionDetails());
	}

	@Test
	public void divisionReferenceConstructor() {
		LeagueMockData data = new LeagueMockData();
		IConference conference = new ConferenceModel(data);
		assertSame(data.conferenceName,conference.getConferenceName());
		assertSame(data.divisionList,conference.getDivisionDetails());
	}

	@Test
	public void checkConferenceNameEmpty() {
		LeagueMockData mock = new LeagueMockData();
		IConference conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void checkConferenceNameEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.setConferenceNameEmpty();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void checkConferenceNameNullTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.setConferenceNameNull();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void isDivisonListEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void divisonListEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.removeDivision();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
	}

	@Test
	public void hasEvenDivsionTest() {
		LeagueMockData mock = new LeagueMockData();
		IConference conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void hasOddDivsionTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.removeOneDivision();
		IConference conference = new ConferenceModel(mock);
		assertSame(ConferenceConstant.NoEvenDivisionCount,conference.validate());
	}

	@Test
	public void validateConferenceTest() {
		LeagueMockData mock = new LeagueMockData();
		IConference conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.Success,conference.validate());
		mock = new LeagueMockData();
		mock.setConferenceNameEmpty();
		conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
		mock = new LeagueMockData();
		mock.removeDivision();
		conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
		mock = new LeagueMockData();
		mock.removeOneDivision();
		conference = new ConferenceModel(mock); 
		assertSame(ConferenceConstant.NoEvenDivisionCount,conference.validate());
	}

}
