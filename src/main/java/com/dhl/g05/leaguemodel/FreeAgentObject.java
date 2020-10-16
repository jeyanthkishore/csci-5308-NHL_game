package com.dhl.g05.leaguemodel;

public class FreeAgentObject {
	
	private String playerName;
	private String position;
	private String result;
	
	public FreeAgentObject() {
		setPlayerName(null);
		setPosition(null);
	}
	
	public FreeAgentObject(String playerName, String position) {
		this.playerName = playerName;
		this.position = position;
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

	public void setPlayerName(String player) {
		playerName = player;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String postition) {
		this.position = postition;
	}

	
	public FreeAgentObject(ILeagueModel player) {
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
			if(position.equals("forward")
					|| position.equals("defense")
					|| position.equals("goalie")) {
				return true;
			}
			return false;
		}

}
