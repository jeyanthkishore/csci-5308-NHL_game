package com.dhl.g05.leaguemodel;

public class PlayerObject {

	private String playerName;
	private String postition;
	private Boolean captain;
	
	public PlayerObject() {
		setCaptain(null);
		setPlayerName(null);
		setPostition(null);
	}
	
	public PlayerObject(String playerName, String position, Boolean captain) {
		this.playerName = playerName;
		this.postition = position;
		this.captain = captain;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPostition() {
		return postition;
	}

	public void setPostition(String postition) {
		this.postition = postition;
	}

	public Boolean getCaptain() {
		return captain;
	}

	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

	public PlayerObject(ILeagueModel player) {
		player.loadPlayerModelData(this);
	}
	
}
