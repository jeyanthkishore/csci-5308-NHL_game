package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.operation.DbPersistanceMock;


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
	public void getResultTest() {
		LeagueObject object = new LeagueObject();
		object.setResult("success");
		assertEquals("success",object.getResult());
	}
	@Test
	public void setResultTest() {
		LeagueObject object = new LeagueObject();
		object.setResult("success");
		assertEquals("success",object.getResult());
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
		assertSame(data.result,object.getResult());
	}
	@Test
	public void leagueReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(data);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
	}
	@Test
	public void checkLeagueName() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertEquals("HockeyLeague", valid.getLeagueName());
	}
	
	@Test
	public void isLeagueNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}
	@Test
	public void isLeagueNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}
	@Test
	public void checkLeagueExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock dbMock = new DbPersistanceMock();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.checkLeaguePresent(dbMock));
	}
	
	@Test
	public void checkEmptyConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeConference();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isConferenceListEmpty());
	}
	
	@Test
	public void checkEvenConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.hasEvenNumberConference());
	}
	
	@Test
	public void checkOddConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneConference();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.hasEvenNumberConference());
	}
	
	@Test
	public void isFreeAgentListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void freeAgentListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentListEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentListEmpty());
	}
	@Test
	public void isFreeAgentAttributeEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void isFreeAgentNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentNameeNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentPositionEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentPositionNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentCaptainEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentCaptainNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	@Test
	public void checkFreeAgentWrongPositionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.isFreeAgentPositionWrong());
	}
	@Test
	public void checkFreeAgentWrongPositionChange() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentPositionWrong());
	}
	@Test
	public void agentCaptainTrueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainTrueBoolean();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isAgentCaptainTrue());
	}
	@Test
	public void validateLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertEquals("success",valid.validate());
		mock.setLeagueEmpty();
		valid = new LeagueObject(mock);
		assertEquals("League Name Is Empty",valid.validate());
		mock = new JsonMockDataDb();
		mock.removeConference();
		valid = new LeagueObject(mock);
		assertEquals("Conference List Is Empty",valid.validate());
		mock = new JsonMockDataDb();
		mock.removeOneConference();
		valid = new LeagueObject(mock);
		assertEquals("Conference Count Must Be Even",valid.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentListEmpty();
		valid = new LeagueObject(mock);
		assertEquals("Free Agent List Is Empty",valid.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		valid = new LeagueObject(mock);
		assertEquals("Free Agent Attribue Is Empty",valid.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		valid = new LeagueObject(mock);
		assertEquals("Position Of The Player Cannot Be Different",valid.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainTrueBoolean();
		valid = new LeagueObject(mock);
		assertEquals("Free Agent Cannot Be Captain",valid.validate());
	}
}
