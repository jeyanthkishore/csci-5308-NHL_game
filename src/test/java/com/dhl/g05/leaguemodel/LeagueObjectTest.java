package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class LeagueObjectTest {

	@Test
	public void setLeagueNameTest() {
		LeagueObject imp = new LeagueObject();
		imp.setLeagueName("Dalhousie League");
		assertEquals("Dalhousie League", imp.getLeagueName());
	}
	
	@Test
	public void getLeagueNameTest() {
		LeagueObject imp = new LeagueObject();
		imp.setLeagueName("Dalhousie");
		assertEquals("Dalhousie", imp.getLeagueName());
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
		LeagueObject valid = new LeagueObject(mock);
		assertSame(1,valid.checkLeaguePresent());
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
	public void isAgentCaptainNonBooleanTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.isAgentCaptainNotFalseBoolean());
	}
	@Test
	public void agentCaptainNonBooleanTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainNonBoolean();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isAgentCaptainNotFalseBoolean());
	}
	@Test
	public void agentCaptainTrueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainTrueBoolean();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isAgentCaptainNotFalseBoolean());
	}
	
}
