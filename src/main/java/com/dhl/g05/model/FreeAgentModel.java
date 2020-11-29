package com.dhl.g05.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.simulation.IAging;
import com.mysql.cj.util.StringUtils;

public class FreeAgentModel implements IFreeAgent {

	static final Logger logger = LogManager.getLogger(FreeAgentModel.class);
	private final static int MIN_AGE = 0;
	private String playerName;
	private String position;
	private int age;
	private int birthDay;
	private int birthMonth;
	private int birthYear;
	private double skating;
	private double shooting;
	private double checking;
	private double saving;
	private double playerStrength;
	private boolean isInjured;
	private boolean isRetired;

	public FreeAgentModel() {
		setPlayerName(null);
		setPosition(null);
		setInjuryStatus(false);
		setRetirementStatus(false);
	}

	public FreeAgentModel(String playerName, String position, double skating, double shooting, double checking,
			double saving, int birthDay, int birthMonth, int birthYear) {
		this.playerName = playerName;
		this.position = position;
		this.skating = skating;
		this.shooting = shooting;
		this.checking = checking;
		this.saving = saving;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
	}

	@Override
	public int getBirthDay() {
		return birthDay;
	}

	@Override
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public int getBirthMonth() {
		return birthMonth;
	}

	@Override
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	@Override
	public int getBirthYear() {
		return birthYear;
	}

	@Override
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public FreeAgentModel(IFreeAgentModel player) {
		player.loadPlayerModelData(this);
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String player) {
		playerName = player;
	}

	@Override
	public String getPosition() {
		return position;
	}

	@Override
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public double getSkating() {
		return skating;
	}

	@Override
	public void setSkating(double skating) {
		this.skating = skating;
	}

	@Override
	public double getShooting() {
		return shooting;
	}

	@Override
	public void setShooting(double shooting) {
		this.shooting = shooting;
	}

	@Override
	public double getChecking() {
		return checking;
	}

	@Override
	public void setChecking(double checking) {
		this.checking = checking;
	}

	@Override
	public double getSaving() {
		return saving;
	}

	@Override
	public void setSaving(double saving) {
		this.saving = saving;
	}

	@Override
	public double getPlayerStrength() {
		return playerStrength;
	}

	@Override
	public void setPlayerStrength(double playerStrength) {
		this.playerStrength = playerStrength;
	}

	@Override
	public boolean getInjuryStatus() {
		return isInjured;
	}

	@Override
	public void setInjuryStatus(boolean isInjured) {
		this.isInjured = isInjured;
	}

	@Override
	public boolean getRetirementStatus() {
		return isRetired;
	}

	@Override
	public void setRetirementStatus(boolean retired) {
		isRetired = retired;
	}

	@Override
	public double calculatePlayerStrength() {
		logger.info("Calculating player strength");
		if (position.equalsIgnoreCase(PositionConstant.forward.getValue())) {
			playerStrength = skating + shooting + (checking / 2);
		}
		if (position.equalsIgnoreCase(PositionConstant.defense.getValue())) {
			playerStrength = skating + checking + (shooting / 2);
		}
		if (position.equalsIgnoreCase(PositionConstant.goalie.getValue())) {
			playerStrength = skating + saving;
		}
		return playerStrength;
	}

	public FreeAgentConstant validate() {
		logger.info("Validating freeAgent details - name, birthdate, statistics");
		if (isPlayerDetailsNullOrEmpty()) {
			return FreeAgentConstant.PlayerValueEmpty;
		}
		if (isPlayerPositionNotValid()) {
			return FreeAgentConstant.PlayerPositionWrong;
		}
		if (isPlayerStatNotValid()) {
			return FreeAgentConstant.PlayerStateInvalid;
		}
		if (isBirthDateNotValid()) {
			return FreeAgentConstant.PlayerBirthdateInvalid;
		}
		return FreeAgentConstant.Success;
	}

	private boolean isPlayerDetailsNullOrEmpty() {
		if (StringUtils.isNullOrEmpty(playerName)) {
			return true;
		}
		if (StringUtils.isNullOrEmpty(position)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isPlayerPositionNotValid() {
		if (position.equals(PositionConstant.forward.getValue()) || position.equals(PositionConstant.defense.getValue())
				|| position.equals(PositionConstant.goalie.getValue())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPlayerAgeNotValid() {
		if (age > MIN_AGE) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPlayerStatNotValid() {
		logger.info("validating freeAgent's statistics");
		if (validateStat(skating) && validateStat(shooting) && validateStat(checking) && validateStat(saving)) {
			return false;
		}
		return true;
	}

	private boolean validateStat(double stat) {
		if (stat >= 0 && stat <= 20) {
			return true;
		}
		return false;
	}

	public boolean isbirthDayValid() {
		if (birthDay > 0 && birthDay <= 31) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isbirthMonthValid() {
		if (birthMonth > 0 && birthMonth <= 12) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isbirthYearValid() {
		if (birthYear > 999 && birthYear <= 9999) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBirthDateNotValid() {
	int birthDay=getBirthDay(); 
	int birthMonth=getBirthMonth();
	int birthYear=getBirthYear();
		int monthlist1[] = { 1, 3, 5, 7, 8, 10, 12 };
		int monthlist2[] = { 4, 6, 9, 11 };
		int monthlist3[] = { 2 };
		boolean notValid = true;
		for (int month : monthlist1) {
			if (birthMonth == month) {
				if (birthDay >= 1 && birthDay <= 31) {
					notValid = false;
				}

			}
		}
		for (int month : monthlist2) {
			if (birthMonth == month) {
				if (birthDay >= 1 && birthDay <= 30) {
					notValid = false;
				}

			}
		}
		if (birthMonth == monthlist3[0]) {
			if ((birthYear % 4 == 0)) {
				if (birthDay >= 1 && birthDay <= 29) {
					notValid = false;
				}
			} else {
				if (birthDay >= 1 && birthDay <= 28) {
					notValid = false;
				}
			}
		}

		if(notValid) {
			System.out.println(notValid);
		}
		return notValid;
	}

	public void calculateAge() {
		int age = 0;
		LocalDate currentDate = LocalDate.now();
		if (isBirthDateNotValid()) {
			setAge(age);
		}else {
			LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
			age = Period.between(birthDate, currentDate).getYears();
			setAge(age);
		}
	}

	public void decreaseStatOnBirthday(ILeague league, IAging agingConfig) {
		Random random = new Random();
		for (IFreeAgent p : league.getFreeAgent()) {
			if (LocalDate.now().getMonthValue() == p.getBirthMonth() && LocalDate.now().getDayOfMonth() == p.getBirthDay()) {
				if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
					p.setSkating((p.getSkating()) - 1);
				}
				if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
					p.setShooting((p.getShooting()) - 1);
				}
				if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
					p.setChecking((p.getChecking()) - 1);
				}
				if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
					p.setSaving((p.getSaving()) - 1);
				}
			} else
				continue;
		}
	}
	
	public List<IFreeAgent> ConvertPlayerToFreeAgent(List<IPlayer> excessPlayers) {
		List<IFreeAgent> freeAgents = new ArrayList<>();
		double skating = 0;
		double shooting = 0;
		double checking = 0;
		double saving = 0;
		int birthDay = 0;
		int birthMonth = 0;
		int birthYear = 0;
		String name = "";
		String position = "";
		for (IPlayer player : excessPlayers) {
			name = ((FreeAgentModel) player).getPlayerName();
			position = player.getPosition();
			skating = player.getSkating();
			shooting = player.getShooting();
			checking = player.getChecking();
			saving = player.getSaving();
			birthDay = player.getBirthDay();
			birthMonth = player.getBirthMonth();
			birthYear = player.getBirthYear();
			freeAgents.add(new FreeAgentModel(name, position, skating, shooting, checking, saving, birthDay, birthMonth,
					birthYear));
		}
		return freeAgents;
	}
	
}
