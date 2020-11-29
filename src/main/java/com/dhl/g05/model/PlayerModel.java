package com.dhl.g05.model;
import java.time.LocalDate;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.simulation.IAging;
import com.dhl.g05.simulation.IInjury;

public class PlayerModel extends FreeAgentModel implements IPlayerInjury, IPlayer, IPlayerRetirement{

	static final Logger logger = LogManager.getLogger(PlayerModel.class);
	private final int DECREASE_STAT_BY=1;
	private Boolean captain;
	private int injuredForNumberOfDays;
	

	public PlayerModel() {
		setCaptain(null);
		setPlayerName(null);
		setPosition(null);
	}

	public PlayerModel(String playerName, String position, Boolean captain, double skating, double shooting, double checking, double saving, int birthDay, int birthMonth, int birthYear) {
		super(playerName, position, skating, shooting, checking, saving, birthDay, birthMonth, birthYear);
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

	public PlayerModel(IPlayerModel player) {
		player.loadPlayerModelData(this);
	}

	@Override
	public FreeAgentConstant validate() {
		logger.info("Validating player details");
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
		logger.info("Checking player injury");
		return  playerInjured.checkPlayerInjury(player, injury);
	}

	@Override
	public boolean checkPlayerRetirement(IPlayerRetired playerRetired, IPlayer player, IAging aging) {
		logger.info("Checking player retirement");
		return playerRetired.checkPlayerRetirement(aging, player);
	}
	
	@Override
	public void decreaseStatOnBirthday(ILeague league, IAging agingConfig) {
		Random random = new Random();
		for (IConference c : league.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					for (IPlayer p : t.getPlayerList()) {
						if (LocalDate.now().getMonthValue() == p.getBirthMonth() && LocalDate.now().getDayOfMonth() == p.getBirthDay()) {
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setSkating((p.getSkating()) - DECREASE_STAT_BY);
							}
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setShooting((p.getShooting()) - DECREASE_STAT_BY);
							}
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setChecking((p.getChecking()) - DECREASE_STAT_BY);
							}
							if (agingConfig.getStatDecayChance() >= random.nextDouble()) {
								p.setSaving((p.getSaving()) - DECREASE_STAT_BY);
							}
						} else
							continue;
					}
				}
			}
		}
	}

}
