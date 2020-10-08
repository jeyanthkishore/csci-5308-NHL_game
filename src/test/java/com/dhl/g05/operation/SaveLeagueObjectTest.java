package com.dhl.g05.operation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.LeagueObject;

public class SaveLeagueObjectTest {
	@Test
	public void constructorTest() {
		SaveLeagueObject fullData = new SaveLeagueObject();
		assertNull(fullData.getLeagueObject());
	}
	@Test
	public void setLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveLeagueObject newData = new SaveLeagueObject();
		newData.setLeagueObject(object);
		assertSame(object,newData.getLeagueObject());
	}
	@Test
	public void getLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveLeagueObject newData = new SaveLeagueObject();
		newData.setLeagueObject(object);
		assertSame(object,newData.getLeagueObject());
	}
	@Test
	public void saveLeagueDetailTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveLeagueObject newData = new SaveLeagueObject();
		newData.setLeagueObject(object);
		assertTrue(newData.saveLeagueDetail());
	}
	@Test
	public void saveConferenceNameTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveLeagueObject newData = new SaveLeagueObject();
		newData.setLeagueObject(object);
		assertTrue(newData.saveLeagueName());
	}
	@Test
	public void saveFreeAgentTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveLeagueObject newData = new SaveLeagueObject();
		newData.setLeagueObject(object);
		assertTrue(newData.saveLeagueName());
	}
}
