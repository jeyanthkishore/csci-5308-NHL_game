package com.dhl.g05.statemachine;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.team.ITeam;

public class AgingState extends AbstractState{
	
	private ILeague league;

	@Override
	public boolean enter() {
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		for (IConference conference : league.getConferenceDetails()) {
            for (IDivision division : conference.getDivisionDetails()) {
                for (ITeam team : division.getTeamDetails()) {
                    for (IPlayer player : team.getPlayerList()) {
                    	player.calculateAge();
                    }
                }
            }
        }

        for (IFreeAgent freeAgent : league.getFreeAgent()) {
            freeAgent.calculateAge();
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
