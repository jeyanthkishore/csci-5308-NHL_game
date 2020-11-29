package com.dhl.g05.simulation;

import java.time.LocalDate;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;

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
                    	//decreaseStats
                    }
                }
            }
        }

        for (IFreeAgent freeAgent : league.getFreeAgent()) {
            freeAgent.calculateAge();
        }

        for (IFreeAgent retiredFreeAgent : league.getRetiredFreeAgentsList()) {
            retiredFreeAgent.calculateAge();
        }
        
        for (IPlayer retiredTeamPlayer : league.getRetiredPlayersList()) {
            retiredTeamPlayer.calculateAge();
        }
        
        return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		LocalDate currentDate = league.getLeagueCurrentDate();
		if (league.getLeagueSchedule().isStanleyCupWinnerDetermined() && DateHandler.instance().isTodayPlayerDraftDate(currentDate)) {
			this.setNextState(stateFactory.createPlayerDraftState());
		}
		else {
			this.setNextState(stateFactory.createPersistState());
		}
		return true;
	}

}
