package com.dhl.g05.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertEquals("HockeyLeague", valid.getLeagueName());
	}
	
	@Test
	public void isLeagueNameEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setLeagueEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}
	@Test
	public void isLeagueNameNullTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setLeagueNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}
	@Test
	public void checkLeagueExistenceTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertSame(1,valid.checkLeaguePresent());
	}
	
	@Test
	public void checkEmptyConference() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeConference();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isConferenceListEmpty());
	}
	
	@Test
	public void checkEvenConference() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.hasEvenNumberConference());
	}
	
	@Test
	public void checkOddConference() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.removeOneConference();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.hasEvenNumberConference());
	}
	
	@Test
	public void isFreeAgentListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void freeAgentListEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentListEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentListEmpty());
	}
	@Test
	public void isFreeAgentAttributeEmptyTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void isFreeAgentNameEmpty() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentNameEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentNameeNull() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentNameNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentPositionEmpty() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentPositionEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentPositionNull() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentPositionNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentCaptainEmpty() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentCaptainEmpty();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	
	@Test
	public void checkFreeAgentCaptainNull() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentCaptainNull();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}
	@Test
	public void checkFreeAgentWrongPositionTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isFreeAgentPositionWrong());
	}
	@Test
	public void checkFreeAgentWrongPositionChange() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentPositionDifferent();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isFreeAgentPositionWrong());
	}
	@Test
	public void isAgentCaptainNonBooleanTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertFalse(valid.isCaptainNotFalseBoolean());
	}
	@Test
	public void agentCaptainNonBooleanTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentCaptainNonBoolean();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isCaptainNotFalseBoolean());
	}
	@Test
	public void agentCaptainTrueTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		mock.setFreeAgentCaptainTrueBoolean();
		LeagueValidation valid = new LeagueValidation(mock);
		assertTrue(valid.isAgentCaptainTrue());
	}
	@Test
	public void validateLeagueTest() {
		MockLeagueValidationDb mock = new MockLeagueValidationDb();
		LeagueValidation valid = new LeagueValidation(mock);
		assertEquals("success",valid.validateLeague());
		mock.setLeagueEmpty();
		valid = new LeagueValidation(mock);
		assertEquals("League Name is empty",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.removeConference();
		valid = new LeagueValidation(mock);
		assertEquals("Conference List is empty",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.removeOneConference();
		valid = new LeagueValidation(mock);
		assertEquals("Conference must be evenly sized",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.setFreeAgentListEmpty();
		valid = new LeagueValidation(mock);
		assertEquals("Free Agent List is empty",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.setFreeAgentNameEmpty();
		valid = new LeagueValidation(mock);
		assertEquals("Free Agent Attribue is empty",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.setFreeAgentPositionDifferent();
		valid = new LeagueValidation(mock);
		assertEquals("Position of the player cannot be different",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.setFreeAgentCaptainTrueBoolean();
		valid = new LeagueValidation(mock);
		assertEquals("Free Agent Cannot be Captain",valid.validateLeague());
		mock = new MockLeagueValidationDb();
		mock.setFreeAgentCaptainNonBoolean();
		valid = new LeagueValidation(mock);
		assertEquals("Captain Should be a False Boolean",valid.validateLeague());
	}
}
