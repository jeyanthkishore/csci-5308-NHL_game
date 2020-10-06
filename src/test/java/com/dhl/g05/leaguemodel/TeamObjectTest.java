package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TeamObjectTest{
	
	@Test
	public void checkPlayerListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamObject validate = new TeamObject(mock);
		assertFalse(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerListEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isPlayerListEmpty());
	}
	
	@Test
	public void checkPlayerListMaxTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.addMaximumPlayer();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isPlayerListMaximum());
	}
	
	@Test
	public void teamNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void teamNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setTeamNameNull();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void coachNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachNameEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void coachNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachNameNull();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsNull());
	}

	@Test
	public void managerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameEmpty();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsEmpty());
	}
	
	@Test
	public void managerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setManagerNameNull();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.isTeamDetailsNull());
	}
	
	@Test
	public void oneTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		TeamObject validate = new TeamObject(mock);
		assertTrue(validate.containOneTeamCaptain());
	}
	@Test
	public void twoTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setSecondCaptain();
		TeamObject validate = new TeamObject(mock);
		assertFalse(validate.containOneTeamCaptain());
	}
	
	@Test
	public void noTeamCaptainTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeCaptain();
		TeamObject validate = new TeamObject(mock);
		assertFalse(validate.containOneTeamCaptain());
	}
}