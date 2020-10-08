package com.dhl.g05.operation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.LeagueObject;

public class SaveConferenceObjectTest {
	@Test
	public void constructorTest() {
		SaveConferenceObject fullData = new SaveConferenceObject();
		assertNull(fullData.getLeagueObject());
	}
	@Test
	public void setLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveConferenceObject data = new SaveConferenceObject();
		data.setLeagueObject(object);
		assertSame(object,data.getLeagueObject());
	}
	@Test
	public void getLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveConferenceObject data = new SaveConferenceObject();
		data.setLeagueObject(object);
		assertSame(object,data.getLeagueObject());
	}
	@Test
	public void saveConferenceDetailTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveConferenceObject data = new SaveConferenceObject();
		data.setLeagueObject(object);
		assertTrue(data.saveConferenceDetail());
	}
}
