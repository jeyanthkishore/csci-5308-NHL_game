package com.dhl.g05.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.gameplayconfig.AbstractGamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.GamePlayConfigFactory;
import com.dhl.g05.model.AbstractPlayerFactory;
import com.dhl.g05.model.IGenerateNewPlayers;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.PlayerFactory;
import com.dhl.g05.model.PositionConstant;

public class GenerateNewPlayersTest {
	   private static IGenerateNewPlayers generateNewPlayers;
	   private static AbstractPlayerFactory playerFactory;

	    @BeforeClass
	    public static void setup() {
	        AbstractPlayerFactory.setFactory(new PlayerFactory());
	        playerFactory = AbstractPlayerFactory.getFactory();

	    }
	@Test
	public void generateRandomNameTest1() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
		String name = generateNewPlayers.generateRandomName();
		assertNotNull(name);
	}

	@Test
	public void generateRandomNameTest2() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
		String name = generateNewPlayers.generateRandomName();
		assertNotEquals(name, "Lavanya Nili");
	}

	@Test
	public void generatePlayerBirthdateTest1() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
		int[] date = generateNewPlayers.generatePlayerBirthdate();
		assertTrue(date[0] >= 1 && date[0] <= 31);
	}

	@Test
	public void generatePlayerBirthdateTest2() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
		int[] date = generateNewPlayers.generatePlayerBirthdate();
		assertFalse(date[2] < 999 && date[2] > 99999);
	}

	@Test
	public void generatePlayerBirthdateTest3() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
		int[] birthdate = generateNewPlayers.generatePlayerBirthdate();
		int currentYear = LocalDate.now().getYear();
		int generatedYear = birthdate[2];
		assertTrue((currentYear - generatedYear) >= 18 && (currentYear - generatedYear) <= 21);
	}

	@Test
	public void generatePlayersTest1() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
		generateNewPlayers.setNumberOfTeams(10);
		List<IPlayer> newPlayers = generateNewPlayers.generatePlayers();
		assertTrue(newPlayers.size() == 70);
	}

	@Test
	public void generatePlayersTest2() {
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
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
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
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
		generateNewPlayers = playerFactory.getGenerateNewPlayers();
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
