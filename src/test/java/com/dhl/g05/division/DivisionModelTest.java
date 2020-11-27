package com.dhl.g05.division;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class DivisionModelTest {

	@Test
	public void divsionConstructorTest() {
		IDivision object = new DivisionModel();
		assertNull(object.getDivisionName());
		assertNull(object.getTeamDetails());
	}

	@Test
	public void setDivisionTest() {
		IDivision object = new DivisionModel();
		object.setDivisionName("Division");
		assertSame("Division",object.getDivisionName());
	}

	@Test
	public void getDivisionTest() {
		IDivision object = new DivisionModel();
		object.setDivisionName("Division");
		assertSame("Division",object.getDivisionName());
	}

	@Test
	public void setTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		IDivision object = new DivisionModel();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void getTeamListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		IDivision object = new DivisionModel();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void divisionReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		IDivision object = new DivisionModel(data);
		assertSame(data.divisionOneName,object.getDivisionName());
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void checkDivisionNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,division.validate());
	}

	@Test
	public void checkDivisionNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
	}

	@Test
	public void checkDivisionNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setDivisionNameNull();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
	}

	@Test
	public void isTeamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,division.validate());
	}

	@Test
	public void teamListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeTeams();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.TeamListEmpty,division.validate());
	}

	@Test
	public void validateDivisionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,division.validate());
		mock = new JsonMockDataDb();
		mock.setDivisionNameEmpty();
		division = new DivisionModel(mock);
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
		mock = new JsonMockDataDb();
		mock.removeTeams();
		division = new DivisionModel(mock); 
		assertSame(DivisionConstant.TeamListEmpty,division.validate());
	}

}
