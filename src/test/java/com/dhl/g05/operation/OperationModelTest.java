package com.dhl.g05.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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
		assertNull(model.getLeagueCheck());
	}
	
	@Test
	public void leagueConstructorTest() {
		DbPersistanceMock object = new DbPersistanceMock();
		OperationModel model = new OperationModel(object);
		assertNotNull(model.getLeagueCheck());
	}
	
	@Test
	public void loadConstructorTest() {
		DbPersistanceMock dbObject = new DbPersistanceMock();
		JsonMockDataDb mockData = new JsonMockDataDb();
		OperationModel  model = new OperationModel(mockData.leagueName,dbObject);
		assertNotNull(model.getLeagueObject());
	}
	@Test
	public void saveonstructorTest() {
		DbPersistanceMock dbObject = new DbPersistanceMock();
		JsonMockDataDb mockData = new JsonMockDataDb();
		OperationModel  model = new OperationModel(mockData.league,dbObject);
		assertEquals("success",model.getResult());
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
	public void getleagueCheckTest() {
		JsonMockDataDb object = new JsonMockDataDb();
		OperationModel model = new OperationModel();
		model.setLeagueCheck(object.leagueList);
		assertEquals(object.leagueList,model.getLeagueCheck());
	}
	@Test
	public void setleagueCheckTest() {
		JsonMockDataDb object = new JsonMockDataDb();
		OperationModel model = new OperationModel();
		model.setLeagueCheck(object.leagueList);
		assertEquals(object.leagueList,model.getLeagueCheck());
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
