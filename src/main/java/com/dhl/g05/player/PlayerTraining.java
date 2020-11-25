package com.dhl.g05.player;

import com.dhl.g05.coach.ICoach;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.league.ILeague;

public class PlayerTraining implements IPlayerTraining {

	private ILeague leagueObject;
	private IRandomNumberFactory randomGeneratorFactory;

	public PlayerTraining(IRandomNumberFactory randomGeneratorFactory) {
		this.randomGeneratorFactory = randomGeneratorFactory;
	}

	public IPlayer performTrainingForPlayer(IPlayer player, ICoach headCoach,ILeague league) {
		this.leagueObject = league;

		Boolean playerInjured = false;

		if(trainingAlgorithm(headCoach.getChecking())) {
			if(player.getChecking() < 20) {
				player.setChecking((player.getChecking()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(headCoach.getSaving())) {
			if(player.getSaving() < 20) {
				player.setSaving((player.getSaving()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(headCoach.getSkating())) {
			if(player.getSkating() < 20) {
				player.setSkating((player.getSkating()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(headCoach.getShooting())) {
			if(player.getShooting() < 20) {
				player.setShooting((player.getShooting()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}
		player.setPlayerStrength(player.calculatePlayerStrength());
		player.setInjuredStatus(playerInjured);
		return player;
	}

	private Boolean isPlayerInjured(IPlayer player) {
		IPlayerInjured playerProgress= new PlayerInjury();
		IInjury injury = leagueObject.getGamePlayConfig().getInjuries();
		if(playerProgress.checkPlayerInjury(player,injury)) {
			return true;
		}
		return false;
	}

	private Boolean trainingAlgorithm(double coachValue) {
		double randomValue = randomGeneratorFactory.generateRandomDoubleNumber(0,1);
		if(randomValue < coachValue) {
			return true;
		}
		return false;

	}
}
