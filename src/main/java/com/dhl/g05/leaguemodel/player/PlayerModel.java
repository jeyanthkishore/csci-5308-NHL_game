package com.dhl.g05.leaguemodel.player;

import com.dhl.g05.leaguemodel.Injury;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;

import java.util.Random;

public class PlayerModel extends FreeAgentModel{

	private Boolean captain;
	private int injuredForNumberOfDays;

	public PlayerModel() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerModel(String playerName, String position, Boolean captain, double age, double skating, double shooting, double checking, double saving) {
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

	public Boolean isCaptainNull() {
		if(captain == null) {
			return true;
		}
		return false;
	}

	public boolean checkPlayerInjury(PlayerModel playerObject){
		Injury injury = new Injury();
		if (playerObject.getHasInjured()){
			return true;
		}
		else {
			double randomDecimal = randomDecimalNumberGenerator();
			if (randomDecimal < injury.getRandomInjuryChance()){
				injuredForNumberOfDays = randomIntegerNumberGenerator(injury.getInjuryDaysLow(),injury.getInjuryDaysHigh());
				playerObject.setInjuredForNumberOfDays(injuredForNumberOfDays);
				playerObject.setHasInjured(true);
				return true;
			}
			return false;
		}
	}

	public double randomDecimalNumberGenerator(){
		Random random = new Random();
		double randomNumber = random.nextDouble();
		String formatNumber = String.format("%1.2f", randomNumber);
		return Double.valueOf(formatNumber);
	}

	public int randomIntegerNumberGenerator(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + max;
	}
}
