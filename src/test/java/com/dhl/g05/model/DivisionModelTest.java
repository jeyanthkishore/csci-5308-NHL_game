package com.dhl.g05.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;

public class DivisionModelTest {
	
	private static ModelAbstractFactory modelAbstractFactory;
	private static ModelMockAbstractFactory modelMockFactory;
	
	@BeforeClass
	public static void init() {
		modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
	}

	@Test
	public void divsionConstructorTest() {
		IDivision division = modelAbstractFactory.createDivisionModel();
		assertNull(division.getDivisionName());
		assertNull(division.getTeamDetails());
	}

	@Test
	public void setDivisionTest() {
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName("Division");
		assertSame("Division",division.getDivisionName());
	}

	@Test
	public void getDivisionTest() {
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName("Division");
		assertSame("Division",division.getDivisionName());
	}

	@Test
	public void setTeamListTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setTeamDetails(mock.teamList);
		assertSame(mock.teamList,division.getTeamDetails());
	}

	@Test
	public void getTeamListTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setTeamDetails(mock.teamList);;
		assertSame(mock.teamList,division.getTeamDetails());
	}

	@Test
	public void checkDivisionNameEmpty() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(mock.teamList);
		assertSame(DivisionConstant.Success,division.validate());
	}

	@Test
	public void checkDivisionNameEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setDivisionNameEmpty();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(mock.teamList);
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
	}

	@Test
	public void checkDivisionNameNullTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.setDivisionNameNull();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(mock.teamList);
		assertSame(DivisionConstant.DivisionNameEmpty,division.validate());
	}

	@Test
	public void isTeamListEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(mock.teamList);
		assertSame(DivisionConstant.Success,division.validate());
	}

	@Test
	public void teamListEmptyTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		mock.removeTeams();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(mock.teamList);
		assertSame(DivisionConstant.TeamListEmpty,division.validate());
	}
	
	@Test
	public void teamListNullTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(null);
		assertSame(DivisionConstant.TeamListEmpty,division.validate());
	}

	@Test
	public void validateDivisionTest() {
		LeagueMockData mock = modelMockFactory.createLeagueMockData();
		IDivision division = modelAbstractFactory.createDivisionModel();
		division.setDivisionName(mock.divisionOneName);
		division.setTeamDetails(mock.teamList);
		assertSame(DivisionConstant.Success,division.validate());
	}

}
