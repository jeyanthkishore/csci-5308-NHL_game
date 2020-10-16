package com.dhl.g05.leaguemodel;

public class PlayerObject extends FreeAgentObject{
	
	private Boolean captain;
	private String result;
	
	public PlayerObject() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}
	
	public PlayerObject(String playerName, String position, Boolean captain) {
		super(playerName,position);
		this.captain = captain;
		result = validate();
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
		result = super.validate();
		if(result.equals("success")) {
			if(isCaptainNull()) {
				return "Captain Cannot be Null"; 
			}
		}
		return result;
	}
	
	public Boolean isCaptainNull() {
		if(captain == null) {
			return true;
		}
		return false;
	}
}
