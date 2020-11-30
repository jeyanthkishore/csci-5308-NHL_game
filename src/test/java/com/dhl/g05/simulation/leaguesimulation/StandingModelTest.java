package com.dhl.g05.simulation.leaguesimulation;

import static org.junit.Assert.assertSame;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class StandingModelTest {
	private static SimulationAbstractFactory simulationFactory;
	private static ModelAbstractFactory modelFactory;

	@BeforeClass
	public static void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	}
	
	@Test
	public void getConferenceTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		IConference conference = modelFactory.createConferenceModel();
		standing.setConference(conference);
		assertSame(conference,standing.getConference());
	}

	@Test
	public void getDivisionTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		IDivision division = modelFactory.createDivisionModel();
		standing.setDivision(division);
		assertSame(division,standing.getDivision());
	}
	
	@Test
	public void getTeamTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		ITeam team = modelFactory.createTeamModel();
		standing.setTeam(team);
		assertSame(team,standing.getTeam());
	}
	
	@Test
	public void setConferenceTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		IConference conference = modelFactory.createConferenceModel();
		standing.setConference(conference);
		assertSame(conference,standing.getConference());
	}

	@Test
	public void setDivisionTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		IDivision division = modelFactory.createDivisionModel();
		standing.setDivision(division);
		assertSame(division,standing.getDivision());
	}
	
	@Test
	public void setTeamTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		ITeam team = modelFactory.createTeamModel();
		standing.setTeam(team);
		assertSame(team,standing.getTeam());
	}
	
	@Test
	public void setGamesPlayedTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesPlayed(10);
		assertSame(10,standing.getTotalGamesPlayed());
	}
	
	@Test
	public void incrementGamesPlayedTest() {
		IStandingModel standing =  simulationFactory.createStandingModel();
		standing.setTotalGamesPlayed(10);
		standing.incrementGamesPlayed();
		assertSame(11,standing.getTotalGamesPlayed());
	}
	
	@Test
	public void setGamesWonTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesWon(10);
		assertSame(10,standing.getTotalGamesWon());
	}
	
	@Test
	public void setGamesLostTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesLost(12);
		assertSame(12,standing.getTotalGamesLost());
	}
	
	@Test
	public void getGamesWonTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesWon(13);
		assertSame(13,standing.getTotalGamesWon());
	}
	
	@Test
	public void getGamesLostTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesLost(13);
		assertSame(13,standing.getTotalGamesLost());
	}
	
	@Test
	public void incrementGamesWonTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesWon(10);
		standing.incrementGamesWon();
		assertSame(11,standing.getTotalGamesWon());
	}
	
	@Test
	public void incrementGamesLostTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalGamesLost(10);
		standing.incrementGamesLost();
		assertSame(11,standing.getTotalGamesLost());
	}
	
	@Test
	public void getPointsTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalPoints(10);
		assertSame(10,standing.getTotalPoints());
	}
	
	@Test
	public void incrementPointsTest() {
		IStandingModel standing = simulationFactory.createStandingModel();
		standing.setTotalPoints(10);
		standing.incrementPoints();
		assertSame(12,standing.getTotalPoints());
	}
	
}
