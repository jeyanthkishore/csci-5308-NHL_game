package com.dhl.g05.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.model.DivisionConstant;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.IDivision;

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
		LeagueMockData data = new LeagueMockData();
		IDivision object = new DivisionModel();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void getTeamListTest() {
		LeagueMockData data = new LeagueMockData();
		IDivision object = new DivisionModel();
		object.setTeamDetails(data.teamList);;
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void divisionReferenceConstructor() {
		LeagueMockData data = new LeagueMockData();
		IDivision object = new DivisionModel(data);
		assertSame(data.divisionOneName,object.getDivisionName());
		assertSame(data.teamList,object.getTeamDetails());
	}

	@Test
	public void checkDivisionNameEmpty() {
		LeagueMockData mock = new LeagueMockData();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,division.validate());
	}

	@Test
	public void checkDivisionNameEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.setDivisionNameEmpty();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
	}

	@Test
	public void checkDivisionNameNullTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.setDivisionNameNull();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
	}

	@Test
	public void isTeamListEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,division.validate());
	}

	@Test
	public void teamListEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.removeTeams();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.TeamListEmpty,division.validate());
	}

	@Test
	public void validateDivisionTest() {
		LeagueMockData mock = new LeagueMockData();
		IDivision division = new DivisionModel(mock); 
		assertSame(DivisionConstant.Success,division.validate());
		mock = new LeagueMockData();
		mock.setDivisionNameEmpty();
		division = new DivisionModel(mock);
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
		mock = new LeagueMockData();
		mock.removeTeams();
		division = new DivisionModel(mock); 
		assertSame(DivisionConstant.TeamListEmpty,division.validate());
	}

}
