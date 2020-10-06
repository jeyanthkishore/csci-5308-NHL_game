package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DivisionObjectTest {
	
	@Test
	public void checkDivisionNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionObject validate = new DivisionObject(mock); 
		assertFalse(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void checkDivisionNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		DivisionObject validate = new DivisionObject(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void checkDivisionNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameNull();
		DivisionObject validate = new DivisionObject(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}
	
	@Test
	public void isTeamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionObject validate = new DivisionObject(mock); 
		assertFalse(validate.isTeamListEmpty());
	}
	@Test
	public void teamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeTeams();
		DivisionObject validate = new DivisionObject(mock); 
		assertTrue(validate.isTeamListEmpty());
	}
}
