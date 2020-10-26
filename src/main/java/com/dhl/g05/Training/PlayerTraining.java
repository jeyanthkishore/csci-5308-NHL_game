package com.dhl.g05.Training;

import java.util.List;

import com.dhl.g05.leaguemodel.CoachObject;
import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class PlayerTraining implements IPlayerTraining {

	private LeagueObject leagueObject;


	public LeagueObject implementTraining(LeagueObject league) {
		this.leagueObject = league;
		List<ConferenceObject> conferences = leagueObject.getConferenceDetails();
		for (ConferenceObject c: conferences) {
			List<DivisionObject> divisions = c.getDivisionDetails();
			for (DivisionObject d: divisions) {
				List<TeamObject> teams = d.getTeamDetails();
				for(TeamObject t: teams) {
					List<PlayerObject> players = t.getPlayerList();
					CoachObject headCoach = t.getCoachDetails();
					for(PlayerObject p: players) {
						p = performTrainingForPlayer(p,headCoach);
					}
				}
			}
		}
		return leagueObject;
	}

	public PlayerObject performTrainingForPlayer(PlayerObject player, CoachObject headCoach) {

		if(trainingAlgorithm(player.getChecking(), headCoach.getChecking())) {
			player.setChecking((player.getChecking()+1));
		}else {
			//call injury check
		}

		if(trainingAlgorithm(player.getSaving(), headCoach.getSaving())) {
			player.setSaving((player.getSaving()+1));
		}else {
			//call injury check
		}

		if(trainingAlgorithm(player.getSkating(), headCoach.getSkating())) {
			player.setSkating((player.getSkating()+1));
		}else {
			//call injury check
		}

		if(trainingAlgorithm(player.getShooting(), headCoach.getShooting())) {
			player.setShooting((player.getShooting()+1));
		}else {
			//call injury check
		}
		player.setPlayerStrength(player.calculatePlayerStrength());
		
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


