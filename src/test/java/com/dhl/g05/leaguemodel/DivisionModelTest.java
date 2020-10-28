package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.division.DivisionConstant;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.operation.DbPersistanceMock;

public class DivisionModelTest {

	@Test
	public void divsionConstructorTest() {
		DivisionModel object = new DivisionModel();
		assertNull(object.getDivisionName());
		assertNull(object.getTeamDetails());
	}

	@Test
	public void setDivisionTest() {
		DivisionModel object = new DivisionModel();
		object.setDivisionName("Division");
		assertSame("Division",object.getDivisionName());
	}

	@Test
	public void getDivisionTest() {
		DivisionModel object = new DivisionModel();
		object.setDivisionName("Division");
		assertSame("Division",object.getDivisionName());
	}

	@Test
	public void setTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionModel object = new DivisionModel();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void getTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionModel object = new DivisionModel();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void divisionParameterConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionModel object = new DivisionModel(data.divisionOneName,data.teamList);
		assertSame(data.divisionOneName,object.getDivisionName());
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void divisionReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		DivisionModel object = new DivisionModel(data);
		assertSame(data.divisionOneName,object.getDivisionName());
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void checkDivisionNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionModel validate = new DivisionModel(mock); 
		assertFalse(validate.isDivisionNameEmptyorNull());
	}

	@Test
	public void checkDivisionNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		DivisionModel validate = new DivisionModel(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}

	@Test
	public void checkDivisionNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameNull();
		DivisionModel validate = new DivisionModel(mock); 
		assertTrue(validate.isDivisionNameEmptyorNull());
	}

	@Test
	public void isTeamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionModel validate = new DivisionModel(mock); 
		assertFalse(validate.isTeamListEmpty());
	}

	@Test
	public void teamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeTeams();
		DivisionModel validate = new DivisionModel(mock); 
		assertTrue(validate.isTeamListEmpty());
	}

	@Test
	public void validateDivisionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DivisionModel validate = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,validate.validate());
		mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		validate = new DivisionModel(mock);
		assertSame(DivisionConstant.DivisionNameEmpty,validate.validate());
		mock = new JsonMockDataDb();
		mock.removeTeams();
		validate = new DivisionModel(mock); 
		assertSame(DivisionConstant.TeamListEmpty,validate.validate());
	}

	@Test
	public void saveDivisionObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		DivisionModel valid = new DivisionModel(mock);
		assertEquals(1,valid.saveDivisionObject(1,data));
	}
	@Test
	public void loadDivisionObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		DivisionModel valid = new DivisionModel(mock);
		assertEquals(1,valid.loadDivisionObject(1,data));
	}

}
