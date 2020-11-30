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
		LocalDate currentDate = league.getLeagueCurrentDate();
		for (IConference conference : league.getConferenceDetails()) {
            for (IDivision division : conference.getDivisionDetails()) {
                for (ITeam team : division.getTeamDetails()) {
                    for (IPlayer player : team.getPlayerList()) {
                    	player.calculateAge(currentDate);
                    	//decreaseStats
                    }
                }
            }
        }

        for (IFreeAgent freeAgent : league.getFreeAgent()) {
            freeAgent.calculateAge(currentDate);
        }

        for (IFreeAgent retiredFreeAgent : league.getRetiredFreeAgentsList()) {
            retiredFreeAgent.calculateAge(currentDate);
        }
        
        for (IPlayer retiredTeamPlayer : league.getRetiredPlayersList()) {
            retiredTeamPlayer.calculateAge(currentDate);
        }
        
        return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		LocalDate currentDate = league.getLeagueCurrentDate();
		System.out.println(currentDate);
		if (league.getLeagueSchedule().isStanleyCupWinnerDetermined() && DateHandler.instance().isTodayPlayerDraftDate(currentDate)) {
			this.setNextState(stateFactory.createPlayerDraftState());
		}
		else {
			this.setNextState(stateFactory.createPersistState());
		}
		return true;
	}

}
