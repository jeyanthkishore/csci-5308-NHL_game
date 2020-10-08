package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.operation.DbPersistanceMock;

public class LeagueValidationTest {
	@Test
	public void setLeagueNameTest() {
		LeagueValidation imp = new LeagueValidation();
		imp.setLeagueName("Dalhousie League");
		assertEquals("Dalhousie League", imp.getLeagueName());
	}
	
	@Test
	public void getLeagueNameTest() {
		LeagueValidation imp = new LeagueValidation();
		imp.setLeagueName("Dalhousie");
		assertEquals("Dalhousie", imp.getLeagueName());
	}
	
	@Test
	public void checkLeagueName() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertEquals("HockeyLeague", valid.getLeagueName());
	}
	
	@Test
	public void isLeagueNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}
	@Test
	public void isLeagueNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}
	@Test
	public void checkLeagueExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock dbMock = new DbPersistanceMock();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.checkLeaguePresent(dbMock));
	}
	
	@Test
	public void checkEmptyConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeConference();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isConferenceListEmpty());
	}
	
	@Test
	public void checkEvenConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.hasEvenNumberConference());
	}
	
	@Test
	public void checkOddConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneConference();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.hasEvenNumberConference());
	}
	
	@Test
	public void isFreeAgentListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void freeAgentListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentListEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentListEmpty());
	}
	@Test
	public void isFreeAgentAttributeEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void isFreeAgentNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentNameeNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentPositionEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentPositionNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentCaptainEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentCaptainNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	@Test
	public void checkFreeAgentWrongPositionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isFreeAgentPositionWrong());
	}
	@Test
	public void checkFreeAgentWrongPositionChange() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentPositionWrong());
	}
	@Test
	public void isAgentCaptainNonBooleanTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isCaptainNotFalseBoolean());
	}
	@Test
	public void agentCaptainNonBooleanTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainNonBoolean();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isCaptainNotFalseBoolean());
	}
	@Test
	public void agentCaptainTrueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainTrueBoolean();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isAgentCaptainTrue());
	}
	@Test
	public void validateLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertEquals("success",valid.validateLeague());
		mock.setLeagueEmpty();
		valid = new LeagueValidation(mock);
		assertEquals("League Name is empty",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.removeConference();
		valid = new LeagueValidation(mock);
		assertEquals("Conference List is empty",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.removeOneConference();
		valid = new LeagueValidation(mock);
		assertEquals("Conference must be evenly sized",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.setFreeAgentListEmpty();
		valid = new LeagueValidation(mock);
		assertEquals("Free Agent List is empty",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		valid = new LeagueValidation(mock);
		assertEquals("Free Agent Attribue is empty",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		valid = new LeagueValidation(mock);
		assertEquals("Position of the player cannot be different",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainTrueBoolean();
		valid = new LeagueValidation(mock);
		assertEquals("Free Agent Cannot be Captain",valid.validateLeague());
		mock = new JsonMockDataDb();
		mock.setFreeAgentCaptainNonBoolean();
		valid = new LeagueValidation(mock);
		assertEquals("Captain Should be a False Boolean",valid.validateLeague());
	}
}
