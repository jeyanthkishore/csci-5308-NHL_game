package com.dhl.g05.player;
import com.dhl.g05.freeagent.FreeAgentConstant;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IInjury;

import java.time.LocalDate;

public class PlayerModel extends FreeAgentModel implements IPlayerInjury,IPlayerRetirement,IPlayer{

	private Boolean captain;
	private int injuredForNumberOfDays;
	private final static int DAYS_IN_YEAR = 365;
	private int elapsedDaysSinceLastBDay;
	private LocalDate injuryDate;

	public PlayerModel() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerModel(String playerName, String position, Boolean captain, int age, double skating, double shooting, double checking, double saving) {
		super(playerName,position, age, skating, shooting, checking, saving);
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

	@Override
	public int getElapsedDaysSinceLastBDay() {
		return elapsedDaysSinceLastBDay;
	}

	@Override
	public void setElapsedDaysSinceLastBDay(int elapsedDaysSinceLastBDay) {
		this.elapsedDaysSinceLastBDay = elapsedDaysSinceLastBDay;
	}

	public LocalDate getInjuryDate() {
		return injuryDate;
	}

	public void setInjuryDate(LocalDate injuryDate) {
		this.injuryDate = injuryDate;
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
	public boolean isRecovered(IPlayerProgress playerCareerProgression, LocalDate currentDate) {
		return playerCareerProgression.isRecovered(this, currentDate);
	}

	@Override
	public void calculatePlayerAgeByDays(int days) {
		if (days > 0) {
			elapsedDaysSinceLastBDay += days;
			handlePlayerAge();
		}
	}

	private void handlePlayerAge() {
		if (elapsedDaysSinceLastBDay >= DAYS_IN_YEAR) {
			int daysAfterYear = elapsedDaysSinceLastBDay % DAYS_IN_YEAR;

			if (daysAfterYear == 0) {
				setAge(getAge() + 1);
				elapsedDaysSinceLastBDay = 0;
			}
			else {
				setAge( getAge() + (elapsedDaysSinceLastBDay/DAYS_IN_YEAR));
				elapsedDaysSinceLastBDay = daysAfterYear;
			}
		}
	}

	@Override
	public boolean isInjured(IPlayerProgress playerProgress, PlayerModel playerModel , IInjury injury) {
		return  playerProgress.isInjured(playerModel, injury);
	}

	@Override
	public boolean isRetired(IPlayerProgress playerProgress, PlayerModel player, IAging aging){
		return  playerProgress.isRetired(player,aging);
	}
}
