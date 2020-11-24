package com.dhl.g05.player;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.freeagent.PositionConstant;

public class GenerateNewPlayersTest {

	@Test
	public void generateRandomNameTest1() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		String name = generate.generateRandomName();
		assertNotNull(name);
	}

	@Test
	public void generateRandomNameTest2() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		String name = generate.generateRandomName();
		assertNotEquals(name, "Lavanya Nili");
	}

	@Test
	public void generatePlayerBirthdateTest1() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		int[] date = generate.generatePlayerBirthdate();
		assertTrue(date[0] >= 1 && date[0] <= 31);
	}

	@Test
	public void generatePlayerBirthdateTest2() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		int[] date = generate.generatePlayerBirthdate();
		assertFalse(date[2] < 999 && date[2] > 99999);
	}

	@Test
	public void generatePlayerBirthdateTest3() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		int[] birthdate = generate.generatePlayerBirthdate();
		int currentYear = LocalDate.now().getYear();
		int generatedYear = birthdate[2];
		assertTrue((currentYear - generatedYear) >= 18 && (currentYear - generatedYear) <= 21);
	}

	@Test
	public void generatePlayersTest1() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		generate.setNumberOfTeams(10);
		List<IPlayer> newPlayers = generate.generatePlayers();
		assertTrue(newPlayers.size() == 70);
	}

	@Test
	public void generatePlayersTest2() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		generate.setNumberOfTeams(4);
		int numberOfDefense = 0;
		List<IPlayer> newPlayers = generate.generatePlayers();
		for (IPlayer player : newPlayers) {
			if (player.getPosition().equals(PositionConstant.defense.getValue())) {
				numberOfDefense++;
			}
		}
		assertTrue(numberOfDefense == 11);
	}

	@Test
	public void generatePlayersTest3() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		generate.setNumberOfTeams(4);
		int numberOfForwards = 0;
		List<IPlayer> newPlayers = generate.generatePlayers();
		for (IPlayer player : newPlayers) {
			if (player.getPosition().equals(PositionConstant.forward.getValue())) {
				numberOfForwards++;
			}
		}
		assertTrue(numberOfForwards == 14);
	}

	@Test
	public void generatePlayersTest4() {
		IGenerateNewPlayers generate = new GenerateNewPlayers();
		generate.setNumberOfTeams(4);
		int numberOfGoalies = 0;
		List<IPlayer> newPlayers = generate.generatePlayers();
		for (IPlayer player : newPlayers) {
			if (player.getPosition().equals(PositionConstant.goalie.getValue())) {
				numberOfGoalies++;
			}
		}
		assertTrue(numberOfGoalies == 3);
	}

}
