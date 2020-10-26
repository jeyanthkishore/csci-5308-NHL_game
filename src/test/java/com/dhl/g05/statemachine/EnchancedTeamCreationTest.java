package com.dhl.g05.statemachine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class EnchancedTeamCreationTest {

	@Test
	public void enchancedConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		EnchancedTeamCreation team = new EnchancedTeamCreation(data.league,communicate);
		assertNotNull(team.getLeagueObject());
		assertNotNull(team.getCommunicate());
	}
	
	@Test
	public void setLeagueObjectTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		EnchancedTeamCreation team = new EnchancedTeamCreation();
		team.setLeagueObject(data.league);
		assertSame(team.getLeagueObject(),data.league);
	}
	
	@Test
	public void getLeagueObjectTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		EnchancedTeamCreation team = new EnchancedTeamCreation();
		team.setLeagueObject(data.league);
		assertSame(team.getLeagueObject(),data.league);
	}
	
	@Test
	public void setCommunicateTest() {
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		EnchancedTeamCreation team = new EnchancedTeamCreation();
		team.setCommunicate(communicate);
		assertSame(team.getCommunicate(),communicate);
	}
	
	@Test
	public void getCommunicateTest() {
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		EnchancedTeamCreation team = new EnchancedTeamCreation();
		team.setCommunicate(communicate);
		assertSame(team.getCommunicate(),communicate);
	}
	
	
	
	@Test
	public void pickPlayersTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		MockPlayerCommunication communicate = new MockPlayerCommunication();
		EnchancedTeamCreation team = new EnchancedTeamCreation(data.league,communicate);
		assertNotNull(team.pickPlayers());
	}
	
//	@Test
//	public void pickCoachTest() {
//		JsonMockDataDb data = new JsonMockDataDb();
//		MockPlayerCommunication communicate = new MockPlayerCommunication();
//		EnchancedTeamCreation team = new EnchancedTeamCreation(data.league,communicate);
//		assertNotNull(team.pickPlayers());
//	}
	
}
