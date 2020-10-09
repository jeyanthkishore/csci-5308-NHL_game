package com.dhl.g05.validation;

import java.util.Map;

public class PlayerValidation {
	private Map<String,Object> playerDetails;
	
	public PlayerValidation() {
		setPlayerDetails(null);
	}

	public PlayerValidation(ILeagueModelValidation player) {
		player.loadPlayerData(this);
	}
	
	public PlayerValidation(Map<String, Object> players) {
		this.playerDetails = players;
	}
	
	public void setPlayerDetails(Map<String, Object> player) {
		this.playerDetails = player;
	}
	
	public Map<String, Object> getPlayerDetails() {
		return playerDetails;
	}
	
	public String validatePlayer() {
		if(isPlayerDetailNotInRange()) {
			return "Player Must Have 3 Attributes";
		}
		if(isPlayerDetailsNull()||isPlayerDetailsEmpty()) {
			return "Player Should Not have Empty Value";
		}
		if(!isPlayerPositionValid()) {
			return "Player Position Is Wrong";
		}
		if(isCaptainNotBoolean()) {
			return "Captain Attribute Must Be Boolean";
		}
		return "success";
	}
	
	public boolean isPlayerDetailNotInRange() {
		if(playerDetails.size()==3) {
			return false;
		}
		return true;
	}
	
	public boolean isPlayerDetailListEmpty() {
		if(playerDetails.size()==0) {
			return true;
		}
		return false;
	}
	public boolean isPlayerDetailsNull() {
		for (String key: playerDetails.keySet()) {
			if(playerDetails.get(key)==null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPlayerDetailsEmpty() {
		for (String key: playerDetails.keySet()) {
			if(playerDetails.get(key)=="") {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPlayerPositionValid() {
		if(playerDetails.get("position").equals("forward")
				|| playerDetails.get("position").equals("defense")
				|| playerDetails.get("position").equals("goalie")) {
			return true;
		}
		return false;
	}
	
	public boolean isCaptainNotBoolean() {
		if(playerDetails.get("captain").equals(true) || playerDetails.get("captain").equals(false)) {
			return false;
		}
		return true;
	}
}
