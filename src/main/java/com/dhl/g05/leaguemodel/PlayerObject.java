package com.dhl.g05.leaguemodel;

import java.util.Map;

public class PlayerObject {

	
	private Map<String,Object> playerDetails;
	
	public PlayerObject() {
		setPlayerDetails(null);
	}

	public PlayerObject(ILeagueModel player) {
		player.loadPlayerModelData(this);
	}
	
	public PlayerObject(Map<String, Object> players) {
		this.playerDetails = players;
	}
	
	public void setPlayerDetails(Map<String, Object> player) {
		this.playerDetails = player;
	}
	
	public Map<String, Object> getPlayerDetails() {
		return playerDetails;
	}
}
