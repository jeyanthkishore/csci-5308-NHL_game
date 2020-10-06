package com.dhl.g05.leaguemodel;

import java.util.Map;

public class PlayerObject {

	
	private Map<String,Object> teamPlayers;
	
	public PlayerObject() {
		setTeamPlayers(null);
	}

	public PlayerObject(ILeagueValidation player) {
		player.loadPlayerData(this);
	}
	
	public PlayerObject(Map<String, Object> players) {
		this.teamPlayers = players;
	}
	
	public void setTeamPlayers(Map<String, Object> player) {
		this.teamPlayers = player;
	}
	
	public Map<String, Object> getTeamPlayers() {
		return teamPlayers;
	}

	public boolean isPlayerDetailInRange() {
		if(teamPlayers.size()==3) {
			return true;
		}
		return false;
	}
	
	public boolean isPlayerDetailListEmpty() {
		if(teamPlayers.size()==0) {
			return true;
		}
		return false;
	}
	public boolean isPlayerDetailsNull() {
		for (String key: teamPlayers.keySet()) {
			if(teamPlayers.get(key)==null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPlayerDetailsEmpty() {
		for (String key: teamPlayers.keySet()) {
			if(teamPlayers.get(key)=="") {
				return true;
			}
		}
		return false;
	}
	
	public boolean isPlayerPositionValid() {
		if(teamPlayers.get("position").equals("forward")
				|| teamPlayers.get("position").equals("defense")
				|| teamPlayers.get("position").equals("goalie")) {
			return true;
		}
		return false;
	}
	
	public boolean isCaptainBoolean() {
		if(teamPlayers.get("captain").equals(true) ||teamPlayers.get("captain").equals(true)) {
			return false;
		}
		return true;
	}
}
