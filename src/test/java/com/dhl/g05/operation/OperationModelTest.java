package com.dhl.g05.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class OperationModelTest {

	@Test
	public void constructorTest() {
		OperationModel model = new OperationModel();
		assertNull(model.getConferenceName());
		assertNull(model.getLeagueName());
		assertNull(model.getLeagueName());
		assertNull(model.getDivisionName());
		assertFalse(model.isLeagueCheck());
	}
	
	@Test
	public void leagueConstructorTest() {
		DbPersistanceMock object = new DbPersistanceMock();
		OperationModel model = new OperationModel("HockeyLeague",false,object);
		assertTrue(model.isLeagueCheck());
	}
	
	@Test
	public void leagueConstructorFailTest() {
		DbPersistanceMock object = new DbPersistanceMock();
		OperationModel model = new OperationModel("Canada League",false,object);
		assertFalse(model.isLeagueCheck());
	}
//	@Test
//	public void loadConstructorTest() {
//		DbPersistanceMock dbObject = new DbPersistanceMock();
//		DatabaseClass dbObject = new DatabaseClass();
//		JsonMockDataDb mockData = new JsonMockDataDb();
//		OperationModel  model = new OperationModel(mockData.teamName,dbObject);
//		assertNotNull(model.getLeagueObject());
//	}
	@Test
	public void loadNewConstructorTest() {
		DbPersistanceMock dbObject = new DbPersistanceMock();
//		DatabaseClass dbObject = new DatabaseClass();
		OperationModel  model = new OperationModel(dbObject);
		assertNotNull(model.getNewTeamList());
	}
	@Test
	public void saveonstructorTest() {
		DbPersistanceMock dbObject = new DbPersistanceMock();
//		DatabaseClass dbObject = new DatabaseClass();
		JsonMockDataDb mockData = new JsonMockDataDb();
//		OperationModel  model = new OperationModel(mockData.league,dbObject);
//		assertEquals("success",model.getResult());
	}
	@Test
	 public void getLeagueNameTest() {
		OperationModel model = new OperationModel();
		model.setLeagueName("Canada");
		assertEquals("Canada",model.getLeagueName());
	 }
	
	@Test
	 public void setLeagueNameTest() {
		OperationModel model = new OperationModel();
		model.setLeagueName("America");
		assertEquals("America",model.getLeagueName());
	 }
	@Test
	 public void getDivisionNameTest() {
		OperationModel model = new OperationModel();
		model.setDivisionName("America");
		assertEquals("America",model.getDivisionName());
	 }
	
	@Test
	 public void setDivisionNameTest() {
		OperationModel model = new OperationModel();
		model.setDivisionName("Atlantic");
		assertEquals("Atlantic",model.getDivisionName());
	 }
	@Test
	 public void getConferenceNameTest() {
		OperationModel model = new OperationModel();
		model.setConferenceName("Western");
		assertEquals("Western",model.getConferenceName());
	 }
	
	@Test
	 public void setConferenceNameTest() {
		OperationModel model = new OperationModel();
		model.setConferenceName("Eastern");
		assertEquals("Eastern",model.getConferenceName());
	 }
	@Test
	 public void getTeamNameTest() {
		OperationModel model = new OperationModel();
		model.setTeamName("Striker XI");
		assertEquals("Striker XI",model.getTeamName());
	 }
	
	@Test
	 public void setTeamNameTest() {
		OperationModel model = new OperationModel();
		model.setTeamName("Striker XI");
		assertEquals("Striker XI",model.getTeamName());
	 }
	@Test
	public void isleagueCheckTest() {
		OperationModel model = new OperationModel();
		model.setLeagueCheck(false);
		assertFalse(model.isLeagueCheck());
	}
	@Test
	public void setleagueCheckTest() {
		OperationModel model = new OperationModel();
		model.setLeagueCheck(true);
		assertTrue(model.isLeagueCheck());
	}
	@Test
	public void getLeagueObjectTest() {
		JsonMockDataDb object = new JsonMockDataDb();
		OperationModel model = new OperationModel();
		model.setLeagueObject(object.league);
		assertSame(object.league,model.getLeagueObject());
	}
	@Test
	public void setLeagueObjectTest() {
		JsonMockDataDb object = new JsonMockDataDb();
		OperationModel model = new OperationModel();
		model.setLeagueObject(object.league);
		assertSame(object.league,model.getLeagueObject());
	}
}
