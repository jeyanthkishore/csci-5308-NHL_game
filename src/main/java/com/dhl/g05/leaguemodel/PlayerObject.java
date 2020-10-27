package com.dhl.g05.leaguemodel;

import com.dhl.g05.operation.IDataBasePersistence;

public class PlayerObject extends FreeAgentObject{
	
	private Boolean captain;
	
	public PlayerObject() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerObject(String playerName, String position, Boolean captain, double age, double skating, double shooting, double checking, double saving) {
		super(playerName,position, age, skating, shooting, checking, saving);
		this.captain = captain;
	}
	
	public int savePlayerObject(int teamId,IDataBasePersistence database) {
		return database.savePlayerObject(teamId,this);
	}
	public int loadPlayerObject(int teamId,IDataBasePersistence database) {
		return database.loadPlayerObject(teamId,this);
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
		String result = super.validate();
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
