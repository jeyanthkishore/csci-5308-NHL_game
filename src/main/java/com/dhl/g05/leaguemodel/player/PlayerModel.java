package com.dhl.g05.leaguemodel.player;

import java.util.Random;

import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.gameplayconfig.Aging;
import com.dhl.g05.leaguemodel.gameplayconfig.IInjury;
import com.dhl.g05.leaguemodel.gameplayconfig.Injury;

public class PlayerModel extends FreeAgentModel implements IPlayerInjury{

	private Boolean captain;
	private int injuredForNumberOfDays;

	public PlayerModel() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerModel(String playerName, String position, Boolean captain, int age, double skating, double shooting, double checking, double saving) {
		super(playerName,position, age, skating, shooting, checking, saving);
		this.captain = captain;
	}

	public Boolean getCaptain() {
		return captain;
	}
	
	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

	public int getInjuredForNumberOfDays() {
		return injuredForNumberOfDays;
	}

	public void setInjuredForNumberOfDays(int injuredForNumberOfDays) {
		this.injuredForNumberOfDays = injuredForNumberOfDays;
	}

	public int savePlayerObject(int teamId,IPlayerModelPersistence database) {
		return database.savePlayerObject(teamId,this);
	}
	
	public int loadPlayerObject(int teamId,IPlayerModelPersistence database) {
		return database.loadPlayerObject(teamId,this);
	}

	public PlayerModel(IPlayerModel player) {
		player.loadPlayerModelData(this);
	}

	public FreeAgentConstant validate() {
		FreeAgentConstant result = super.validate();
		if(result.equals(FreeAgentConstant.Success)) {
			if(isCaptainNull()) {
				return FreeAgentConstant.CaptainNull; 
			}
		}
		return result;
	}

	private Boolean isCaptainNull() {
		if(captain == null) {
			return true;
		}
		return false;
	}


	@Override
	public boolean isInjured(IPlayerProgress playerProgress, PlayerModel playerModel , IInjury injury) {
		return  playerProgress.isInjured(playerModel, injury);
	}

	/*public boolean playerRetirement(Aging aging) {
		PlayerRetirement p = new PlayerRetirement();
		return p.checkPlayerRetirement(aging,this);
	}*/
}
