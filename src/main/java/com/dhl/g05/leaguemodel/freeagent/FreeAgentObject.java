package com.dhl.g05.leaguemodel.freeagent;

import com.dhl.g05.leaguemodel.ILeagueModelComplete;
import com.dhl.g05.leaguemodel.ValidateEnumModel;

public class FreeAgentObject implements IFreeAgent{
	private String playerName;
	private String position;
	private double age;
	private double skating;
	private double shooting;
	private double checking;
	private double saving;
	private double playerStrength;
	private boolean hasInjured;

	public FreeAgentObject() {
		setPlayerName(null);
		setPosition(null);
		setHasInjured(false);
	}

	public FreeAgentObject(String playerName, String position, double age, double skating, double shooting, double checking, double saving) {
		this.playerName = playerName;
		this.position = position;
		this.age = age;
		this.skating = skating;
		this.shooting = shooting;
		this.checking = checking;
		this.saving = saving;
	}

	public FreeAgentObject(ILeagueModelComplete player) {
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

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
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

	public boolean getHasInjured() {
		return hasInjured;
	}

	public void setHasInjured(boolean hasInjured) {
		this.hasInjured = hasInjured;
	}

	public double getPlayerStrength() {
		return playerStrength;
	}

	public void setPlayerStrength(double playerStrength) {
		this.playerStrength = playerStrength;
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

	public ValidateEnumModel validate() {
		if(isPlayerDetailsNull()||isPlayerDetailsEmpty()) {
			return ValidateEnumModel.PlayerValueEmpty;
		}
		if(!isPlayerPositionValid()) {
			return ValidateEnumModel.PlayerPositionWrong;
		}
		if(!isPlayerAgeValid()) {
			return ValidateEnumModel.PlayerAgeInvalid;
		}
		if(!isPlayerStatValid()) {
			return ValidateEnumModel.PlayerStateInvalid;
		}
		return ValidateEnumModel.Success;
	}

	public boolean isPlayerDetailsNull() {
		if(playerName == null || position ==null) {
			return true;
		}
		return false;
	}

	public boolean isPlayerDetailsEmpty() {
		if(playerName == "" || position =="") {
				return true;
		}
		return false;
	}
		
	public boolean isPlayerPositionValid() {
			if(position.equals("forward") || position.equals("defense") || position.equals("goalie")) {
				return true;
			}
			return false;
	}

	public boolean isPlayerAgeValid() {
		if (age > 0) {
			return true;
		}
		return  false;
	}

	public boolean isPlayerStatValid() {
		if (validateStat(skating) && validateStat(shooting) && validateStat(checking) && validateStat(saving)) {
			return true;
		}
		return false;
	}

	public boolean validateStat(double stat) {
		if (stat >= 0 && stat <= 20) {
			return true;
		}
		return false;
	}
}
