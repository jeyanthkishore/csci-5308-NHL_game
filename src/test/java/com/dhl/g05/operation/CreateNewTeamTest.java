package com.dhl.g05.operation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.LeagueObject;

public class CreateNewTeamTest {

	@Test
	public void constructorTest() {
		CreateNewTeam fullData = new CreateNewTeam();
		assertNull(fullData.getLeagueObject());
	}
	@Test
	public void createConstructorTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertSame(object,newData.getLeagueObject());
	}
	@Test
	public void setLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		CreateNewTeam newData = new CreateNewTeam();
		newData.setLeagueObject(object);
		assertSame(object,newData.getLeagueObject());
	}
	@Test
	public void getLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		CreateNewTeam newData = new CreateNewTeam();
		newData.setLeagueObject(object);
		assertSame(object,newData.getLeagueObject());
	}
	@Test
	public void isConferenceExistTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertTrue(newData.isConferenceExist(mock.conferenceName));
	}
	@Test
	public void isConferenceNotExistTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertFalse(newData.isConferenceExist("dummyData"));
	}
	@Test
	public void isDivisionExistTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertTrue(newData.isDivisionExist(mock.divisionOneName));
	}
	@Test
	public void isDivisionNotExistTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertFalse(newData.isDivisionExist("dummyData"));
	}
	@Test
	public void isDivisionExistTestAgain() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertTrue(newData.isDivisionExist("Atlantic"));
	}
	@Test
	public void isTeamExistTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertTrue(newData.isTeamExist(mock.teamName));
	}
	@Test
	public void isTeamNotExistTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertFalse(newData.isTeamExist("dummyData"));
	}
	@Test
	public void isTeamExistTestAgain() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertFalse(newData.isTeamExist("StrikerSix"));
	}
	@Test
	public void isTeamTestAgain() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(mock);
		CreateNewTeam newData = new CreateNewTeam(object);
		assertTrue(newData.isTeamExist("Striker Six"));
	}
}
