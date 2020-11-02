package com.dhl.g05.freeagent;

import com.mysql.cj.util.StringUtils;

public class FreeAgentModel implements IFreeAgent {
	private final static int MIN_AGE = 0;
	private String playerName;
	private String position;
	private int age;
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

	public FreeAgentModel(String playerName, String position, int age, double skating, double shooting, double checking, double saving) {
		this.playerName = playerName;
		this.position = position;
		this.age = age;
		this.skating = skating;
		this.shooting = shooting;
		this.checking = checking;
		this.saving = saving;
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
	public double calculatePlayerStrength(){
		if(position.equalsIgnoreCase("forward")){
			playerStrength = skating + shooting + (checking/2);
		}
		if(position.equalsIgnoreCase("defense")){
			playerStrength = skating + checking + (shooting/2);
		}
		if(position.equalsIgnoreCase("goalie")){
			playerStrength = skating + saving;
		}
		return playerStrength;
	}

	public FreeAgentConstant validate() {
		if(isPlayerDetailsNullOrEmpty()) {
			return FreeAgentConstant.PlayerValueEmpty;
		}
		if(isPlayerPositionValid()) {
			return FreeAgentConstant.PlayerPositionWrong;
		}
		if(isPlayerAgeNotValid()) {
			return FreeAgentConstant.PlayerAgeInvalid;
		}
		if(isPlayerStatNotValid()) {
			return FreeAgentConstant.PlayerStateInvalid;
		}
		return FreeAgentConstant.Success;
	}

	private boolean isPlayerDetailsNullOrEmpty() {
		if(StringUtils.isNullOrEmpty(playerName)) {
			return true;
		}
		if(StringUtils.isNullOrEmpty(position)) {
			return true;
		}
		return false;
	}

	public boolean isPlayerPositionValid() {
		if(position.equals(PositionConstant.forward.getValue())
				|| position.equals(PositionConstant.defense.getValue()) 
				|| position.equals(PositionConstant.goalie.getValue())) {
			return false;
		}
		return true;
	}

	public boolean isPlayerAgeNotValid() {
		if (age > MIN_AGE) {
			return false;
		}
		return  true;
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

}
