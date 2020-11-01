package com.dhl.g05.leaguemodel.freeagent;

import com.mysql.cj.util.StringUtils;

public class FreeAgentModel implements IFreeAgent {
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String player) {
		playerName = player;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String postition) {
		this.position = postition;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSkating() {
		return skating;
	}

	public void setSkating(double skating) {
		this.skating = skating;
	}

	public double getShooting() {
		return shooting;
	}

	public void setShooting(double shooting) {
		this.shooting = shooting;
	}

	public double getChecking() {
		return checking;
	}

	public void setChecking(double checking) {
		this.checking = checking;
	}

	public double getSaving() {
		return saving;
	}

	public void setSaving(double saving) {
		this.saving = saving;
	}

	public boolean getInjuredStatus() {
		return isInjured;
	}

	public void setInjuredStatus(boolean isInjured) {
		this.isInjured = isInjured;
	}

	public boolean getRetiredStatus() {
		return isRetired;
	}

	public void setRetiredStatus(boolean retired) {
		isRetired = retired;
	}

	public double getPlayerStrength() {
		return playerStrength;
	}

	public void setPlayerStrength(double playerStrength) {
		this.playerStrength = playerStrength;
	}
	
	public int saveFreeAgentObject(int leagueId,IFreeAgentPersistence database) {
		return database.saveFreeAgentObject(leagueId,this);
	}

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

	public enum Position{
		goalie,
		defense,
		forward
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
		if(position.equals("forward") || position.equals("defense") || position.equals("goalie")) {
			return false;
		}
		return true;
	}

	public boolean isPlayerAgeNotValid() {
		if (age > 0) {
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
