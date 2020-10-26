package com.dhl.g05.Training;

import java.util.List;

import com.dhl.g05.leaguemodel.CoachObject;
import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class PlayerTraining {

	private LeagueObject leagueObject;

	public void leagueObject() {
		leagueObject = null;
	}
	
	public LeagueObject getLeagueObject() {
		return leagueObject;
	}
	
	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}

	public void implementTraining() {
		List<ConferenceObject> conferences = leagueObject.getConferenceDetails();
		for (ConferenceObject c: conferences) {
			List<DivisionObject> divisions = c.getDivisionDetails();
			for (DivisionObject d: divisions) {
				List<TeamObject> teams = d.getTeamDetails();
				for(TeamObject t: teams) {
					List<PlayerObject> players = t.getPlayerList();
					CoachObject headCoach = t.getCoachDetails();
					for(PlayerObject p: players) {
//						performTrainingForPlayer();
					}
				}
			}
		}
	}

	
	
	public Boolean trainingAlgorithm(double playerValue, double coachValue) {
		double randomValue = Math.random();
		if(randomValue < coachValue) {
			playerValue = playerValue+1;
			return true;
		}
		return false;
	}
}


