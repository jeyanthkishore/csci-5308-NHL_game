package com.dhl.g05.Training;

import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.IInjury;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayerProgress;
import com.dhl.g05.player.IRandomGeneratorFactory;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.player.PlayerProgress;
import com.dhl.g05.player.RandomGeneratorFactory;
import com.dhl.g05.team.TeamModel;

public class PlayerTraining implements IPlayerTraining {

	private LeagueModel leagueObject;
	private IRandomGeneratorFactory randomGeneratorFactory;

	public PlayerTraining(IRandomGeneratorFactory randomGeneratorFactory) {
		this.randomGeneratorFactory = randomGeneratorFactory;
	}

	public LeagueModel implementTraining(LeagueModel league) {
		this.leagueObject = league;
		List<ConferenceModel> conferences = leagueObject.getConferenceDetails();
		for (ConferenceModel c: conferences) {
			List<DivisionModel> divisions = c.getDivisionDetails();
			for (DivisionModel d: divisions) {
				List<TeamModel> teams = d.getTeamDetails();
				for(TeamModel t: teams) {
					List<PlayerModel> players = t.getPlayerList();
					CoachModel headCoach = t.getCoachDetails();
					for(PlayerModel p: players) {
						p = performTrainingForPlayer(p,headCoach);
					}
				}
			}
		}
		return leagueObject;
	}

	private PlayerModel performTrainingForPlayer(PlayerModel player, CoachModel headCoach) {

		Boolean playerInjured = false;

		if(trainingAlgorithm(player.getChecking(), headCoach.getChecking())) {
			if(player.getChecking() < 20) {
				player.setChecking((player.getChecking()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(player.getSaving(), headCoach.getSaving())) {
			if(player.getSaving() < 20) {
				player.setSaving((player.getSaving()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(player.getSkating(), headCoach.getSkating())) {
			if(player.getSkating() < 20) {
				player.setSkating((player.getSkating()+1));
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(player.getShooting(), headCoach.getShooting())) {
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
		IPlayerProgress playerProgress= new PlayerProgress(new RandomGeneratorFactory());
		IInjury injury = leagueObject.getGamePlayConfig().getInjuries();
		if(playerProgress.isInjured(player,injury)) {
			return true;
		}
		return false;
	}

	private Boolean trainingAlgorithm(double playerValue, double coachValue) {
		double randomValue = randomGeneratorFactory.getRandomDoubleNumber(0,1);
		if(randomValue < coachValue) {
			return true;
		}
		return false;
	}
}


