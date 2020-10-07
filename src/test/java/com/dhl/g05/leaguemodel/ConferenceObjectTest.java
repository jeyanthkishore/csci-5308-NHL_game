package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

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

}
