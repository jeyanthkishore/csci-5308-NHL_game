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
}
