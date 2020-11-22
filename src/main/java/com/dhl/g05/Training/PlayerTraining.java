package com.dhl.g05.Training;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayerInjured;
import com.dhl.g05.player.PlayerInjury;
import com.dhl.g05.player.PlayerModel;

public class PlayerTraining implements IPlayerTraining {

	private LeagueModel leagueObject;

	public PlayerTraining() {
	}

	public PlayerModel performTrainingForPlayer(PlayerModel player, CoachModel headCoach,LeagueModel league) {
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

	private Boolean isPlayerInjured(PlayerModel player) {
		IPlayerInjured playerProgress= new PlayerInjury();
		IInjury injury = leagueObject.getGamePlayConfig().getInjuries();
		if(playerProgress.isPlayerInjured(player,injury)) {
			return true;
		}
		return false;
	}

	private Boolean trainingAlgorithm(double coachValue) {
		double randomValue = Math.random();
		if(randomValue < coachValue) {
			return true;
		}
		return false;
	}
}


