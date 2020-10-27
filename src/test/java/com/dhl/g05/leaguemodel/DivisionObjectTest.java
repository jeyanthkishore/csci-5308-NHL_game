package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.operation.DbPersistanceMock;

public class DivisionObjectTest {
	
	@Test
	public void divsionConstructorTest() {
		DivisionObject object = new DivisionObject();
		assertNull(object.getDivisionName());
		assertNull(object.getTeamDetails());
	}
	@Test
	public void setDivisionTest() {
		DivisionObject object = new DivisionObject();
		object.setDivisionName("Division");
		assertSame("Division",object.getDivisionName());
	}
	@Test
	public void getDivisionTest() {
		DivisionObject object = new DivisionObject();
		object.setDivisionName("Division");
		assertSame("Division",object.getDivisionName());
	}
	@Test
	public void setTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionObject object = new DivisionObject();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}
	@Test
	public void getTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionObject object = new DivisionObject();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}
	@Test
	public void divisionParameterConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionObject object = new DivisionObject(data.divisionOneName,data.teamList);
		assertSame(data.divisionOneName,object.getDivisionName());
		assertSame(data.teamList,object.getTeamDetails());
	}
	@Test
	public void divisionReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionObject object = new DivisionObject(data);
		assertSame(data.divisionOneName,object.getDivisionName());
		assertSame(data.teamList,object.getTeamDetails());
	}
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
	@Test
	public void validateDivisionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionObject validate = new DivisionObject(mock); 
		assertEquals("success",validate.validate());
		mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		validate = new DivisionObject(mock);
		assertEquals("DivisionName Cannot Be Empty",validate.validate());
		mock = new JsonMockDataDb();
		mock.removeTeams();
		validate = new DivisionObject(mock); 
		assertEquals("TeamList Is Empty",validate.validate());
	}
	
	@Test
	public void saveDivisionObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		DivisionObject valid = new DivisionObject(mock);
		assertEquals(1,valid.saveDivisionObject(1,data));
	}
	@Test
	public void loadDivisionObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		DivisionObject valid = new DivisionObject(mock);
		assertEquals(1,valid.loadDivisionObject(1,data));
	}
}
