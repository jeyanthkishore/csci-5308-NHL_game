package com.dhl.g05.player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.PositionConstant;

public class GenerateNewPlayers extends FreeAgentModel implements IGenerateNewPlayers {

	private static final int numberOfDraftRound = 7;
	private static final double percentageOfForwards = 0.5;
	private static final double percentageOfDefenses = 0.4;
	private static final double percentageOfGoalies = 0.1;
	private int numberOfTeams = 2;
	Random random = new Random();

	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public List<PlayerModel> generatePlayers() {

		List<PlayerModel> listOfNewPlayers = new ArrayList<>();
		int totalPlayersNeeded = numberOfDraftRound * getNumberOfTeams();
		int totalForwards = (int) Math.round((percentageOfForwards * totalPlayersNeeded));
		int totalDefenses = (int) Math.round((percentageOfDefenses * totalPlayersNeeded));
		int totalGoalies = (int) Math.round((percentageOfGoalies * totalPlayersNeeded));
		boolean[] booleanValue = { BooleanValue.True.getValue(), BooleanValue.False.getValue() };

		for (int i = 0; i < totalForwards; i++) {
			PlayerModel player = new PlayerModel();
			player.setPlayerName(generateRandomName());
			player.setCaptain(booleanValue[random.nextInt(booleanValue.length)]);
			player.setPosition(PositionConstant.forward.getValue());
			player.setSkating(random.nextInt((20 - 12) + 1) + 12);
			player.setShooting(random.nextInt((20 - 12) + 1) + 12);
			player.setChecking(random.nextInt((18 - 9) + 1) + 9);
			player.setSaving(random.nextInt((7 - 1) + 1) + 1);
			int[] birthdate = generatePlayerBirthdate();
			player.setBirthDay(birthdate[0]);
			player.setBirthMonth(birthdate[1]);
			player.setBirthYear(birthdate[2]);
			if (listOfNewPlayers.contains(player)) {
				totalForwards++;
				continue;
			} else {
				listOfNewPlayers.add(player);
			}
		}
		for (int i = 0; i < totalDefenses; i++) {
			PlayerModel player = new PlayerModel();
			player.setPlayerName(generateRandomName());
			player.setCaptain(booleanValue[random.nextInt(booleanValue.length)]);
			player.setPosition(PositionConstant.defense.getValue());
			player.setSkating(random.nextInt((20 - 12) + 1) + 12);
			player.setShooting(random.nextInt((20 - 12) + 1) + 12);
			player.setChecking(random.nextInt((18 - 9) + 1) + 9);
			player.setSaving(random.nextInt((7 - 1) + 1) + 1);
			int[] birthdate = generatePlayerBirthdate();
			player.setBirthDay(birthdate[0]);
			player.setBirthMonth(birthdate[1]);
			player.setBirthYear(birthdate[2]);
			if (listOfNewPlayers.contains(player)) {
				totalForwards++;
				continue;
			} else {
				listOfNewPlayers.add(player);
			}
		}
		for (int i = 0; i < totalGoalies; i++) {
			PlayerModel player = new PlayerModel();
			player.setPlayerName(generateRandomName());
			player.setCaptain(booleanValue[random.nextInt(booleanValue.length)]);
			player.setPosition(PositionConstant.goalie.getValue());
			player.setSkating(random.nextInt((20 - 12) + 1) + 12);
			player.setShooting(random.nextInt((20 - 12) + 1) + 12);
			player.setChecking(random.nextInt((18 - 9) + 1) + 9);
			player.setSaving(random.nextInt((7 - 1) + 1) + 1);
			int[] birthdate = generatePlayerBirthdate();
			player.setBirthDay(birthdate[0]);
			player.setBirthMonth(birthdate[1]);
			player.setBirthYear(birthdate[2]);
			if (listOfNewPlayers.contains(player)) {
				totalForwards++;
				continue;
			} else {
				listOfNewPlayers.add(player);
			}
		}
		return listOfNewPlayers;
	}

	public int[] generatePlayerBirthdate() {
		int age = (random.nextInt((20 - 18) + 1) + 18);
		int month = (random.nextInt((12 - 1) + 1) + 1);
		int days = (random.nextInt((31 - 1) + 1) + 1);
		int[] birthdate = new int[3];
		LocalDate today = LocalDate.now();
		LocalDate year = today.minusYears(age).minusMonths(month).minusDays(days);
		birthdate[0] = year.getDayOfMonth();
		birthdate[1] = year.getMonthValue();
		birthdate[2] = year.getYear();
		return birthdate;
	}

	public String generateRandomName() {
		String name = "";
		Random randomName = new Random();
		String[] firstName = new String[] { "Dane", "Lamm", "Keltner", "Paules", "Leonardi", "Wicklund", "Reardon",
				"Sherlock", "Bieker", "Nobile", "Vaughan", "Flicker", "Mercad", "Durbin", "Madore", "Olin", "Tirrell",
				"Brink", "Steil", "Mcentyre", "Dockstader", "Mallow", "Hank", "Otto", "Valerius", "Teller", "Pleasants",
				"Zahn", "Calder", "Karner", "Oliveira", "Eisenhauer", "Roof", "Mahone", "Arends", "Fancher", "Leavens",
				"Yamashiro", "Maginnis", "Hamon", "Perron", "Hammers", "Holtman", "Victor", "Walter", "Fred", "Frank",
				"George", "Eleonor", "Sedgwick",  "Heide" ,"Oda" , "Roxie Kehl",  "Stephany", "Broyles",  "Adolfo Boehm",  "Lynda Elsea",  "Loise Arnhold"  ,"Aubrey", "Benites", "Hana",
				 "Kauffman"  ,"Kiara"," Kollar" ," Clifton"};
		String[] lastName = new String[] { "Nolan", "Gullion", "Claude", "Thrower", "Tama", "Kale", "Brook",
				"Brinsfield", "Maryrose", "Rockmore", "Beverlee", "Swann", "Shanell", "Twyman", "Marianela",
				"Mikkelsen", "Kenny", "Kieser", "Chadwick", "Noyola", "Williams", "Swofford", "Francene", "Whitmer",
				"Cassie", "Jacoby", "Ernest", "Servais", "Dwana", "Mcqueen", "Twana", "Alfaro", "Amber", "Wilkes",
				"Isabelle", "Murguia", "Antony", "Gholston", "Larisa", "Morvant", "Annelle", "Timms", "Felicia",
				"Shufelt", "Bertie", "Daluz","Bongard", "Bowers", "Boyd", "Cannon" };
		name = firstName[randomName.nextInt(firstName.length)] + " " + lastName[randomName.nextInt(lastName.length)];
		return name;
	}
}
