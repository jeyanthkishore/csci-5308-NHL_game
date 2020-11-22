package com.dhl.g05.statemachine;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class AgingState extends AbstractState{
	
	private LeagueModel league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		for (ConferenceModel conference : league.getConferenceDetails()) {
            for (DivisionModel division : conference.getDivisionDetails()) {
                for (TeamModel team : division.getTeamDetails()) {
                    for (PlayerModel player : team.getPlayerList()) {
                    	player.incrementPlayerAgeByDay(1);
                    }
                }
            }
        }

        for (FreeAgentModel freeAgent : league.getFreeAgent()) {
            freeAgent.incrementPlayerAgeByDay(1);
        }

//        for (FreeAgentModel retiredFreeAgent : league.getRetiredFreeAgents()) {
//            retiredFreeAgent.incrementPlayerAgeByDay(1);
//        }
//
//        for (PlayerModel retiredTeamPlayer : league.getRetiredTeamPlayers()) {
//            retiredTeamPlayer.incrementPlayerAgeByDay(1);
//        }
        return true;
	}

	@Override
	public boolean exit() {
	       if (league.getLeagueSchedule().isStanleyCupWinnerDetermined()) {
	            this.setNextState(AbstractStateMachineFactory.getFactory().getAdvanceToNextSeasonState());
	        }
	        else {
	            this.setNextState(AbstractStateMachineFactory.getFactory().getPersistState());
	        }
	       return true;
	}

}
