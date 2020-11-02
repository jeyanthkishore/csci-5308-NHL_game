package com.dhl.g05.Training;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.gameplayconfig.IInjury;
import com.dhl.g05.leaguemodel.gameplayconfig.Injury;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.*;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class PlayerTraining implements IPlayerTraining {

	private LeagueModel leagueObject;
	
	public PlayerTraining(LeagueModel league) {
		this.leagueObject = league;
	}
	
	public LeagueModel implementTraining() {
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
			player.setChecking((player.getChecking()+1));
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(player.getSaving(), headCoach.getSaving())) {
			player.setSaving((player.getSaving()+1));
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(player.getSkating(), headCoach.getSkating())) {
			player.setSkating((player.getSkating()+1));
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(player.getShooting(), headCoach.getShooting())) {
			player.setShooting((player.getShooting()+1));
		}else {
			playerInjured = isPlayerInjured(player);
		}
		player.setPlayerStrength(player.calculatePlayerStrength());
		player.setInjuredStatus(playerInjured);
		return player;
	}

	private Boolean isPlayerInjured(PlayerModel player) {
		IPlayerInjury playerInjury = new PlayerModel();
		IPlayerProgress playerProgress= new PlayerProgress(new RandomGeneratorFactory());
		IInjury injury = leagueObject.getGamePlayConfig().getInjuries();
		if(playerInjury.isInjured(playerProgress,player,injury)) {
			return true;
		}
		return false;
	}
	
	private Boolean trainingAlgorithm(double playerValue, double coachValue) {
		double randomValue = Math.random();
		if(randomValue < coachValue) {
			return true;
		}
		return false;
	}
}


