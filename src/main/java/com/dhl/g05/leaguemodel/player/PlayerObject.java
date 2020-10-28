package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.ILeagueModelComplete;
import com.dhl.g05.leaguemodel.ValidateEnumModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentObject;

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
	
	public int savePlayerObject(int teamId,IPlayerModelPersistence database) {
		return database.savePlayerObject(teamId,this);
	}
	public int loadPlayerObject(int teamId,IPlayerModelPersistence database) {
		return database.loadPlayerObject(teamId,this);
	}

	public Boolean getCaptain() {
		return captain;
	}

	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

	public PlayerObject(ILeagueModelComplete player) {
		player.loadPlayerModelData(this);
	}
	
	public ValidateEnumModel validate() {
		ValidateEnumModel result = super.validate();
		if(result.equals(ValidateEnumModel.Success)) {
			if(isCaptainNull()) {
				return ValidateEnumModel.CaptainNull; 
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
