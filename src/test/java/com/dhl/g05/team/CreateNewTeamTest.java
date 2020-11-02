package com.dhl.g05.team;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.MockData.JsonMockDataDb;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;
import com.dhl.g05.team.CreateNewTeam;

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
		CreateNewTeam teamCreation = new CreateNewTeam(data.league,communicate);
		int FreeAgentSize = data.league.getFreeAgent().size();
		int managerSize = data.league.getManagerList().size();
		int coachListSize = data.league.getFreeCoach().size();
		assertNotNull(teamCreation.teamCreation("Rocker"));
		assertNotNull(teamCreation.getNewTeam());
		assertSame(20,teamCreation.getNewTeam().getPlayerList().size());
		int lastFreeAgentSize = FreeAgentSize-20;
		assertSame(lastFreeAgentSize,teamCreation.getFreeAgentList().size());
		assertSame(managerSize-1,teamCreation.getManagerList().size());
		assertSame(coachListSize-1,teamCreation.getCoachList().size());
	}
	
	@Test
	public void managerNameEmptyTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		data.league.setManagerList(data.managerListTwo);
		CreateNewTeam creation = new CreateNewTeam(data.getLeague(),communicate);
		assertFalse(creation.teamCreation("Rocker"));
	}
}
