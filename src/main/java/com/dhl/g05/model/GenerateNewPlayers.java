package com.dhl.g05.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateNewPlayers implements IGenerateNewPlayers {

	private static final int NUMBER_OF_ROUNDS = 7;
	private static final double PERCENTAGE__OF_FORWARDS = 0.5;
	private static final double PERCENTAGE_OF_DEFENSE = 0.4;
	private static final double PERCENTAGE_OF_GOALIES = 0.1;
	private static final int DEFENSE_SKATER_MAX=19; 
	private static final int DEFENSE_SKATER_MIN=10; 
	private static final int FORWARD_SKATER_MAX=20;
	private static final int FORWARD_SKATER_MIN=12;
	private static final int GOALIE_SKATER_MAX=15;
	private static final int GOALIE_SKATER_MIN=8;
	private static final int DEFENSE_SHOOTER_MAX=18; 
	private static final int DEFENSE_SHOOTER_MIN=9; 
	private static final int FORWARD_SHOOTER_MAX=20;
	private static final int FORWARD_SHOOTER_MIN=12;
	private static final int GOALIE_SHOOTER_MAX=10;
	private static final int GOALIE_SHOOTER_MIN=1;
	private static final int DEFENSE_CHECKING_MAX=20; 
	private static final int DEFENSE_CHECKING_MIN=12; 
	private static final int FORWARD_CHECKING_MAX=18;
	private static final int FORWARD_CHECKING_MIN=9;
	private static final int GOALIE_CHECKING_MAX=12;
	private static final int GOALIE_CHECKING_MIN=1;
	private static final int DEFENSE_SAVING_MAX=12; 
	private static final int DEFENSE_SAVING_MIN=1; 
	private static final int FORWARD_SAVING_MAX=7;
	private static final int FORWARD_SAVING_MIN=1;
	private static final int GOALIE_SAVING_MAX=20;
	private static final int GOALIE_SAVING_MIN=12;
	private static final int AGE_MAX=20;
	private static final int AGE_MIN=18;
	private static final int MONTH_MAX=12;
	private static final int MONTH_MIN=1;
	private static final int DAYS_MAX=31;
	private static final int DAYS_MIN=1;
	private int numberOfTeams;
	
	Random random = new Random();
	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public List<IPlayer> generatePlayers() {

		List<IPlayer> listOfNewPlayers = new ArrayList<>();
		int totalPlayersNeeded = NUMBER_OF_ROUNDS * getNumberOfTeams();
		int totalForwards = (int) Math.round((PERCENTAGE__OF_FORWARDS * totalPlayersNeeded));
		int totalDefenses = (int) Math.round((PERCENTAGE_OF_DEFENSE * totalPlayersNeeded));
		int totalGoalies = (int) Math.round((PERCENTAGE_OF_GOALIES * totalPlayersNeeded));
		boolean[] booleanValue = { BooleanValue.True.getValue(), BooleanValue.False.getValue() };

		for (int i = 0; i < totalForwards; i++) {
			PlayerModel player = new PlayerModel();
			player.setPlayerName(generateRandomName());
			player.setCaptain(booleanValue[random.nextInt(booleanValue.length)]);
			player.setPosition(PositionConstant.forward.getValue());
			player.setSkating(random.nextInt((FORWARD_SKATER_MAX - FORWARD_SKATER_MIN) + 1) + FORWARD_SKATER_MIN);
			player.setShooting(random.nextInt((FORWARD_SHOOTER_MAX - FORWARD_SHOOTER_MIN) + 1) + FORWARD_SHOOTER_MIN);
			player.setChecking(random.nextInt((FORWARD_CHECKING_MAX - FORWARD_CHECKING_MIN) + 1) + FORWARD_CHECKING_MIN);
			player.setSaving(random.nextInt((FORWARD_SAVING_MAX - FORWARD_SAVING_MIN) + FORWARD_SAVING_MIN) + FORWARD_SAVING_MIN);
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
			IPlayer player = new PlayerModel();
			((FreeAgentModel) player).setPlayerName(generateRandomName());
			player.setCaptain(booleanValue[random.nextInt(booleanValue.length)]);
			player.setPosition(PositionConstant.defense.getValue());
			player.setSkating(random.nextInt((DEFENSE_SKATER_MAX - DEFENSE_SKATER_MIN) + 1) + DEFENSE_SKATER_MIN);
			player.setShooting(random.nextInt((DEFENSE_SHOOTER_MAX - DEFENSE_SHOOTER_MIN) + 1) + DEFENSE_SHOOTER_MIN);
			player.setChecking(random.nextInt((DEFENSE_CHECKING_MAX - DEFENSE_CHECKING_MIN) + 1) + DEFENSE_CHECKING_MIN);
			player.setSaving(random.nextInt((DEFENSE_SAVING_MAX - DEFENSE_SAVING_MIN) + 1) + DEFENSE_SAVING_MIN);
			int[] birthdate = generatePlayerBirthdate();
			((FreeAgentModel) player).setBirthDay(birthdate[0]);
			((FreeAgentModel) player).setBirthMonth(birthdate[1]);
			((FreeAgentModel) player).setBirthYear(birthdate[2]);
			if (listOfNewPlayers.contains(player)) {
				totalForwards++;
				continue;
			} else {
				listOfNewPlayers.add(player);
			}
		}
		for (int i = 0; i < totalGoalies; i++) {
			IPlayer player = new PlayerModel();
			((FreeAgentModel) player).setPlayerName(generateRandomName());
			player.setCaptain(booleanValue[random.nextInt(booleanValue.length)]);
			player.setPosition(PositionConstant.goalie.getValue());
			player.setSkating(random.nextInt((GOALIE_SKATER_MAX - GOALIE_SKATER_MIN) + 1) + GOALIE_SKATER_MIN);
			player.setShooting(random.nextInt((GOALIE_SHOOTER_MAX - GOALIE_SHOOTER_MIN) + GOALIE_SHOOTER_MIN) + GOALIE_SHOOTER_MIN);
			player.setChecking(random.nextInt((GOALIE_CHECKING_MAX - GOALIE_CHECKING_MIN) + 1) + GOALIE_CHECKING_MIN);
			player.setSaving(random.nextInt((GOALIE_SAVING_MAX - GOALIE_SAVING_MIN) + 1) + GOALIE_SAVING_MIN);
			int[] birthdate = generatePlayerBirthdate();
			((FreeAgentModel) player).setBirthDay(birthdate[0]);
			((FreeAgentModel) player).setBirthMonth(birthdate[1]);
			((FreeAgentModel) player).setBirthYear(birthdate[2]);
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
		int age = (random.nextInt((AGE_MAX - AGE_MIN) + 1) + AGE_MIN);
		int month = (random.nextInt((MONTH_MAX - MONTH_MIN) + 1) + MONTH_MIN);
		int days = (random.nextInt((DAYS_MAX - DAYS_MIN) + 1) + DAYS_MIN);
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
				"George", "Eleonor", "Sedgwick", "Heide", "Oda", "Roxie Kehl", "Stephany", "Broyles", "Adolfo Boehm",
				"Lynda Elsea", "Loise Arnhold", "Aubrey", "Benites", "Hana", "Kauffman", "Kiara", " Kollar",
				" Clifton" };
		String[] lastName = new String[] { "Nolan", "Gullion", "Claude", "Thrower", "Tama", "Kale", "Brook",
				"Brinsfield", "Maryrose", "Rockmore", "Beverlee", "Swann", "Shanell", "Twyman", "Marianela",
				"Mikkelsen", "Kenny", "Kieser", "Chadwick", "Noyola", "Williams", "Swofford", "Francene", "Whitmer",
				"Cassie", "Jacoby", "Ernest", "Servais", "Dwana", "Mcqueen", "Twana", "Alfaro", "Amber", "Wilkes",
				"Isabelle", "Murguia", "Antony", "Gholston", "Larisa", "Morvant", "Annelle", "Timms", "Felicia",
				"Shufelt", "Bertie", "Daluz", "Bongard", "Bowers", "Boyd", "Cannon" };
		name = firstName[randomName.nextInt(firstName.length)] + " " + lastName[randomName.nextInt(lastName.length)];
		return name;
	}
}
