package com.dhl.g05.leaguemodel;

import com.dhl.g05.operation.IDataBasePersistence;

public class PlayerObject extends FreeAgentObject{
	
	private Boolean captain;

	private String result;
	
	public PlayerObject() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerObject(String playerName, String position, Boolean captain, int age, int skating, int shooting, int checking, int saving) {

		super(playerName,position, age, skating, shooting, checking, saving);

		this.captain = captain;

		result = validate();
	}
	
	public int savePlayerObject(int teamId,IDataBasePersistence database) {
		return database.savePlayerObject(teamId,this);
	}
	public int loadPlayerObject(int teamId,IDataBasePersistence database) {
		return database.loadPlayerObject(teamId,this);
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
