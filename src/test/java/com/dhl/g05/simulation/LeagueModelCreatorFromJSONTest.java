package com.dhl.g05.simulation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;

public class LeagueModelCreatorFromJSONTest {
	ILeagueCreator leagueModelCreator;

	@Before
	public void init() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		leagueModelCreator = simulationFactory.createLeagueCreator();
	}

	@Test
	public void testCreateLeagueFromFileGoodFile() {
		ILeague result = null;
		result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json");
		assertNotNull(result);
	}

	@Test
	public void badTeamTest() {
		ILeague result = null;
		result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadTeamInfo.json");
		assertNull(result);
	}

	@Test
	public void badPlayerTest() {
		ILeague result = null;
		result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadPlayerInfo.json");
		assertNull(result);
	}

	@Test
	public void badFreeAgentTest() {
		ILeague result = null;
		result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadFreeAngentInfo.json");
		assertNull(result);
	}

	@Test
	public void badDivisionTest() {
		ILeague result = null;
		result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadDivisionInfo.json");
		assertNull(result);
	}
}


