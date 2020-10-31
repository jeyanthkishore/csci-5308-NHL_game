package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class CreateNewTeamTest {

	@Test
	public void enchancedConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		CreateNewTeam team = new CreateNewTeam(data.league,communicate);
		assertNotNull(team.getLeagueObject());
		assertNotNull(team.getCommunicate());
	}
	
	@Test
	public void getObjectTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		CreateNewTeam team = new CreateNewTeam(data.league,communicate);
		assertSame(team.getLeagueObject(),data.league);
		assertSame(team.getCommunicate(),communicate);
	}
	
	@Test
	public void objectNullTest() {
		CreateNewTeam team = new CreateNewTeam();
		assertNull(team.getLeagueObject());
		assertNull(team.getCommunicate());
	}
	
	@Test
	public void setCoachListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CreateNewTeam team = new CreateNewTeam();
		team.setCoachList(data.coachList);
		assertSame(team.getCoachList(),data.coachList);
	}

	@Test
	public void getManagerListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CreateNewTeam team = new CreateNewTeam();
		team.setManagerList(data.managerList);
		assertSame(team.getManagerList(),data.managerList);
	}
	
	@Test
	public void setManagerListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CreateNewTeam team = new CreateNewTeam();
		team.setManagerList(data.managerList);
		assertSame(team.getManagerList(),data.managerList);
	}

	@Test
	public void getCoachListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CreateNewTeam team = new CreateNewTeam();
		team.setCoachList(data.coachList);
		assertSame(team.getCoachList(),data.coachList);
	}
	
	@Test
	public void setFreeAgentListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CreateNewTeam team = new CreateNewTeam();
		team.setFreeAgentList(data.freeAgentList);
		assertSame(team.getFreeAgentList(),data.freeAgentList);
	}

	@Test
	public void getFreeAgentListTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		CreateNewTeam team = new CreateNewTeam();
		team.setFreeAgentList(data.freeAgentList);
		assertSame(team.getFreeAgentList(),data.freeAgentList);
	}


	@Test
	public void teamCreationTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		CreateNewTeam team = new CreateNewTeam(data.league,communicate);
		assertNotNull(team.teamCreation("Rocker"));
		assertNotNull(team.getNewTeam());
		assertSame(20,team.getNewTeam().getPlayerList().size());
	}
}
