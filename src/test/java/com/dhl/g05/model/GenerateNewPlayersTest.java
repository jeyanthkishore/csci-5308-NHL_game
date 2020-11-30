package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class GenerateNewPlayersTest {
	 private IGenerateNewPlayers generateNewPlayers; 
	 private static ModelAbstractFactory modelAbstractFactory;

	    @BeforeClass
	    public static void init() {
	        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	    }
	    
	@Test
	public void generateRandomNameTest1() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		String name = generateNewPlayers.generateRandomName();
		assertNotNull(name);
	}

	@Test
	public void generateRandomNameTest2() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		String name = generateNewPlayers.generateRandomName();
		assertNotEquals(name, "Lavanya Nili");
	}

	@Test
	public void generatePlayerBirthdateTest1() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		int[] date = generateNewPlayers.generatePlayerBirthdate();
		assertTrue(date[0] >= 1 && date[0] <= 31);
	}

	@Test
	public void generatePlayerBirthdateTest2() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		int[] date = generateNewPlayers.generatePlayerBirthdate();
		assertFalse(date[2] < 999 && date[2] > 99999);
	}

	@Test
	public void generatePlayerBirthdateTest3() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		int[] birthdate = generateNewPlayers.generatePlayerBirthdate();
		int currentYear = LocalDate.now().getYear();
		int generatedYear = birthdate[2];
		assertTrue((currentYear - generatedYear) >= 18 && (currentYear - generatedYear) <= 21);
	}

	@Test
	public void generatePlayersTest1() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		generateNewPlayers.setNumberOfTeams(10);
		List<IPlayer> newPlayers = generateNewPlayers.generatePlayers();
		assertTrue(newPlayers.size() == 70);
	}
	
	@Test
	public void generatePlayersTest5() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		generateNewPlayers.setNumberOfTeams(70);
		List<IPlayer> newPlayers = generateNewPlayers.generatePlayers();
		assertEquals(newPlayers.size(),490);
	}

	@Test
	public void generatePlayersTest2() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		generateNewPlayers.setNumberOfTeams(4);
		int numberOfDefense = 0;
		List<IPlayer> newPlayers = generateNewPlayers.generatePlayers();
		for (IPlayer player : newPlayers) {
			if (player.getPosition().equals(PositionConstant.defense.getValue())) {
				numberOfDefense++;
			}
		}
		assertTrue(numberOfDefense == 11);
	}

	@Test
	public void generatePlayersTest3() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		generateNewPlayers.setNumberOfTeams(4);
		int numberOfForwards = 0;
		List<IPlayer> newPlayers = generateNewPlayers.generatePlayers();
		for (IPlayer player : newPlayers) {
			if (player.getPosition().equals(PositionConstant.forward.getValue())) {
				numberOfForwards++;
			}
		}
		assertTrue(numberOfForwards == 14);
	}

	@Test
	public void generatePlayersTest4() {
		generateNewPlayers = modelAbstractFactory.createNewPlayers();
		generateNewPlayers.setNumberOfTeams(4);
		int numberOfGoalies = 0;
		List<IPlayer> newPlayers = generateNewPlayers.generatePlayers();
		for (IPlayer player : newPlayers) {
			if (player.getPosition().equals(PositionConstant.goalie.getValue())) {
				numberOfGoalies++;
			}
		}
		assertTrue(numberOfGoalies == 3);
	}

}
