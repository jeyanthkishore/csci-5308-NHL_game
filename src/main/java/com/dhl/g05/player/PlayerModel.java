package com.dhl.g05.player;
import com.dhl.g05.freeagent.FreeAgentConstant;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;

public class PlayerModel extends FreeAgentModel implements IPlayerInjury,IPlayer, IPlayerRetirement{

	private Boolean captain;
	private int injuredForNumberOfDays;

	public PlayerModel() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerModel(String playerName, String position, Boolean captain,double skating, double shooting, double checking, double saving, int birthDay,int birthMonth,int birthYear) {
		super(playerName,position,skating, shooting, checking, saving, birthDay,birthMonth,birthYear);
		this.captain = captain;
	}

	@Override
	public Boolean getCaptain() {
		return captain;
	}

	@Override
	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

	@Override
	public int getInjuredForNumberOfDays() {
		return injuredForNumberOfDays;
	}

	@Override
	public void setInjuredForNumberOfDays(int injuredForNumberOfDays) {
		this.injuredForNumberOfDays = injuredForNumberOfDays;
	}

	public int savePlayerObject(int teamId, IPlayerModelPersistence database) {
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
	public boolean checkPlayerInjury(IPlayerInjured playerInjured, IPlayer player, IInjury injury) {
		return  playerInjured.checkPlayerInjury(player, injury);
	}

	@Override
	public boolean checkPlayerRetirement(IPlayerRetired playerRetired, IFreeAgent player, IAging aging) {
		return playerRetired.checkPlayerRetirement(aging, player);
	}

}
