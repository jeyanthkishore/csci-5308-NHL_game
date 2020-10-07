package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;


public class LeagueObjectTest {

	@Test
	public void leagueConstructorTest() {
		LeagueObject object = new LeagueObject();
		assertNull(object.getLeagueName());
		assertNull(object.getConferenceDetails());
		assertNull(object.getFreeAgent());
	}
	@Test
	public void setLeagueNameTest() {
		LeagueObject object = new LeagueObject();
		object.setLeagueName("League");
		assertSame("League",object.getLeagueName());
	}
	@Test
	public void getLeagueNameTestt() {
		LeagueObject object = new LeagueObject();
		object.setLeagueName("League");
		assertSame("League",object.getLeagueName());
	}
	@Test
	public void setFreeAgentTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setFreeAgent(data.freeAgentList);
		assertSame(data.freeAgentList,object.getFreeAgent());
	}
	@Test
	public void getFreeAgentTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setFreeAgent(data.freeAgentList);
		assertSame(data.freeAgentList,object.getFreeAgent());
	}
	@Test
	public void setConferenceDetailsTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setConferenceDetails(data.conferenceList);
		assertSame(data.conferenceList,object.getConferenceDetails());
	}
	@Test
	public void getConferenceDetailsTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setConferenceDetails(data.conferenceList);
		assertSame(data.conferenceList,object.getConferenceDetails());
	}
	@Test
	public void leagueParameterConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(data.leagueName,data.conferenceList,data.freeAgentList);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
	}
	@Test
	public void leagueReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(data);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
	}
}
