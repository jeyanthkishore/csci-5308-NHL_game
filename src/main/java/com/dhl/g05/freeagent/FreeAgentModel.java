package com.dhl.g05.freeagent;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import com.mysql.cj.util.StringUtils;

public class FreeAgentModel implements IFreeAgent {
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
		setInjuredStatus(false);
		setInjuredStatus(false);
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

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		return birthYear;
	}

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
	public void setPosition(String postition) {
		this.position = postition;
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
	public boolean getInjuredStatus() {
		return isInjured;
	}

	@Override
	public void setInjuredStatus(boolean isInjured) {
		this.isInjured = isInjured;
	}

	@Override
	public boolean getRetiredStatus() {
		return isRetired;
	}

	@Override
	public void setRetiredStatus(boolean retired) {
		isRetired = retired;
	}

	@Override
	public double getPlayerStrength() {
		return playerStrength;
	}

	@Override
	public void setPlayerStrength(double playerStrength) {
		this.playerStrength = playerStrength;
	}

	public int saveFreeAgentObject(int leagueId,IFreeAgentPersistence database) {
		return database.saveFreeAgentObject(leagueId,this);
	}
	
	@Override
	public void incrementPlayerAgeByDay(int day) {
		
	}

	@Override
	public double calculatePlayerStrength() {
		if (position.equalsIgnoreCase("forward")) {
			playerStrength = skating + shooting + (checking / 2);
		}
		if (position.equalsIgnoreCase("defense")) {
			playerStrength = skating + checking + (shooting / 2);
		}
		if (position.equalsIgnoreCase("goalie")) {
			playerStrength = skating + saving;
		}
		return playerStrength;
	}

	public FreeAgentConstant validate() {
		if (isPlayerDetailsNullOrEmpty()) {
			return FreeAgentConstant.PlayerValueEmpty;
		}
		if (isPlayerPositionValid()) {
			return FreeAgentConstant.PlayerPositionWrong;
		}
		if (isBirthDateValid()==false) {
			return FreeAgentConstant.PlayerBirthdateInvalid;
		}
		if (isPlayerStatNotValid()) {
			return FreeAgentConstant.PlayerStateInvalid;
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

	public boolean isPlayerPositionValid() {
		if (position.equals(PositionConstant.forward.getValue()) || position.equals(PositionConstant.defense.getValue())
				|| position.equals(PositionConstant.goalie.getValue())) {
			return false;
		}
		return true;
	}

	public boolean isPlayerAgeNotValid() {
		if (age > MIN_AGE) {
			return false;
		}
		return true;
	}

	public boolean isPlayerStatNotValid() {
		if (validateStat(skating) && validateStat(shooting) && validateStat(checking) && validateStat(saving)) {
			return false;
		}
		return true;
	}

	public boolean validateStat(double stat) {
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

	public boolean isBirthDateValid() {
	int birthDay=getBirthDay(); 
	int birthMonth=getBirthMonth();
	int birthYear=getBirthYear();
		int monthlist1[] = { 1, 3, 5, 7, 8, 10, 12 };
		int monthlist2[] = { 4, 6, 9, 11 };
		int monthlist3[] = { 2 };
		boolean valid = false;
		for (int month : monthlist1) {
			if (birthMonth == month) {
				if (birthDay > 1 && birthDay <= 31) {
					valid = true;
				}

			}
		}
		for (int month : monthlist2) {
			if (birthMonth == month) {
				if (birthDay > 1 && birthDay <= 30) {
					valid = true;
				}

			}
		}
		if (birthMonth == monthlist3[0]) {
			if ((birthYear % 4 == 0)) {
				if (birthDay > 1 && birthDay <= 29) {
					valid = true;
				}
			} else {
				if (birthDay > 1 && birthDay <= 28) {
					valid = true;
				}
			}
		}

		return valid;
	}

	public int calculateAge() {
		int age = 0;
		LocalDate currentDate = LocalDate.now();
		if (isBirthDateValid()) {
			LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
			age = Period.between(birthDate, currentDate).getYears();
		}
		setAge(age);
		return age;
	}

}
