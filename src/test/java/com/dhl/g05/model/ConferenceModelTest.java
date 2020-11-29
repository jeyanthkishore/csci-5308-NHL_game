package com.dhl.g05.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;

public class ConferenceModelTest {
	private static ModelAbstractFactory modelAbstractFactory;
	private static ModelMockAbstractFactory modelMockFactory;
	
	@BeforeClass
	public static void init() {
		modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
	}

	@Test
	public void conferenceConstructorTest() {
		IConference object = modelAbstractFactory.createConferenceModel();
		assertNull(object.getConferenceName());
		assertNull(object.getDivisionDetails());
	}

	@Test
	public void setDivisionTest() {
		IConference object = modelAbstractFactory.createConferenceModel();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}

	@Test
	public void getDivisionTest() {
		IConference object = modelAbstractFactory.createConferenceModel();
		object.setConferenceName("conference");
		assertSame("conference",object.getConferenceName());
	}

	@Test
	public void setTeamListTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,conference.getDivisionDetails());
	}

	@Test
	public void getTeamListTest() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setDivisionDetails(data.divisionList);
		assertSame(data.divisionList,conference.getDivisionDetails());
	}

	@Test
	public void checkConferenceNameEmpty() {
		LeagueMockData data = modelMockFactory.createLeagueMockData();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(data.conferenceName);
		conference.setDivisionDetails(data.divisionList);
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void checkConferenceNameEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setConferenceNameEmpty();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(mock.divisionList);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void checkConferenceNameNullTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setConferenceNameNull();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(mock.divisionList);
		assertSame(ConferenceConstant.ConferenceNameEmpty,conference.validate());
	}

	@Test
	public void divisonListEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.removeDivision();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(mock.divisionList);
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
	}
	
	@Test
	public void divisonListNullTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(null);
		assertSame(ConferenceConstant.DivisionListEmpty,conference.validate());
	}

	@Test
	public void hasEvenDivsionTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(mock.divisionList); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

	@Test
	public void hasOddDivsionTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.removeOneDivision();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(mock.divisionList); 
		assertSame(ConferenceConstant.NoEvenDivisionCount,conference.validate());
	}

	@Test
	public void validateConferenceTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IConference conference = modelAbstractFactory.createConferenceModel();
		conference.setConferenceName(mock.conferenceName);
		conference.setDivisionDetails(mock.divisionList); 
		assertSame(ConferenceConstant.Success,conference.validate());
	}

}
