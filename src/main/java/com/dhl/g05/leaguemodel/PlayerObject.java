package com.dhl.g05.leaguemodel;

public class PlayerObject {

	private String playerName;
	private String position;
	private Boolean captain;
	private String result;
	
	public PlayerObject() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}
	
	public PlayerObject(String playerName, String position, Boolean captain) {
		this.playerName = playerName;
		this.position = position;
		this.captain = captain;
		result = validate();
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String postition) {
		this.position = postition;
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
	
	public String validate() {

		if(isPlayerDetailsNull()||isPlayerDetailsEmpty()) {
			return "Player Should Not have Empty Value";
		}
		if(!isPlayerPositionValid()) {
			return "Player Position Is Wrong";
		}
		return "success";
	
	}
	public boolean isPlayerDetailsNull() {
		if(playerName == null || position ==null||captain == null) {
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
			if(position.equals("forward")
					|| position.equals("defense")
					|| position.equals("goalie")) {
				return true;
			}
			return false;
		}

}
