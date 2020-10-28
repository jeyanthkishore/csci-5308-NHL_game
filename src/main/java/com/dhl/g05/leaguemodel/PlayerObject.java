package com.dhl.g05.leaguemodel;

import com.dhl.g05.operation.IDataBasePersistence;
import java.util.Random;

public class PlayerObject extends FreeAgentObject{
	
	private Boolean captain;
	private int injuredForNumberOfDays;

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

	public int getInjuredForNumberOfDays() {
		return injuredForNumberOfDays;
	}

	public void setInjuredForNumberOfDays(int injuredForNumberOfDays) {
		this.injuredForNumberOfDays = injuredForNumberOfDays;
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
	
	public ValidateEnumModel validate() {
		ValidateEnumModel result = super.validate();
		if(result.equals(ValidateEnumModel.Success)) {
			if(isCaptainNull()) {
				return ValidateEnumModel.CaptainNull; 
			}
		}
		return result;
	}

	public boolean checkPlayerInjury(PlayerObject playerObject){
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

	public Boolean isCaptainNull() {
		if(captain == null) {
			return true;
		}
		return false;
	}
}
