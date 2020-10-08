package com.dhl.g05.operation;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.LeagueObject;

public class SaveTeamInformationTest {
	@Test
	public void constructorTest() {
		SaveTeamInformation fullData = new SaveTeamInformation();
		assertNull(fullData.getLeagueObject());
	}
	@Test
	public void setLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveTeamInformation data = new SaveTeamInformation();
		data.setLeagueObject(object);
		assertSame(object,data.getLeagueObject());
	}
	@Test
	public void getLeagueObjectTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveTeamInformation data = new SaveTeamInformation();
		data.setLeagueObject(object);
		assertSame(object,data.getLeagueObject());
	}
	@Test
	public void saveTeamDetailTest() {
		LeagueObject object = new LeagueObject(new JsonMockDataDb());
		SaveTeamInformation data = new SaveTeamInformation();
		data.setLeagueObject(object);
		assertTrue(data.saveTeamDetail());
	}
}
