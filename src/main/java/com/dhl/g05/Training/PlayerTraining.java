package com.dhl.g05.Training;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.IPlayerInjury;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class PlayerTraining implements IPlayerTraining {

	private LeagueModel leagueObject;
	private IPlayerInjury playerInjury = new PlayerModel();
	
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

	public PlayerModel performTrainingForPlayer(PlayerModel player, CoachModel headCoach) {
		
		Boolean playerInjured = false;

		if(trainingAlgorithm(player.getChecking(), headCoach.getChecking())) {
			player.setChecking((player.getChecking()+1));
		}else {
			/*if(playerInjury.checkPlayerInjury(player)) {
				playerInjured = true;
			}*/
		}

		if(trainingAlgorithm(player.getSaving(), headCoach.getSaving())) {
			player.setSaving((player.getSaving()+1));
		}else {
			/*if(playerInjury.checkPlayerInjury(player)) {
				playerInjured = true;
			}*/
		}

		if(trainingAlgorithm(player.getSkating(), headCoach.getSkating())) {
			player.setSkating((player.getSkating()+1));
		}else {
			/*if(playerInjury.checkPlayerInjury(player)) {
				playerInjured = true;
			}*/
		}

		if(trainingAlgorithm(player.getShooting(), headCoach.getShooting())) {
			player.setShooting((player.getShooting()+1));
		}else {
			/*if(playerInjury.checkPlayerInjury(player)) {
				playerInjured = true;
			}*/
		}
		player.setPlayerStrength(player.calculatePlayerStrength());
		player.setHasInjured(playerInjured);
		return player;
	}


	public Boolean trainingAlgorithm(double playerValue, double coachValue) {
		double randomValue = Math.random();
		if(randomValue < coachValue) {
			return true;
		}
		return false;
	}
}


