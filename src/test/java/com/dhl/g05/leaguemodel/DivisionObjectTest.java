package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

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
}
