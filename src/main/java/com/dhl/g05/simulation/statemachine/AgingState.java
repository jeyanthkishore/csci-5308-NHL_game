package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class AgingState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(AgingState.class);
	private ILeague league;

	@Override
	public boolean enter() {
		logger.info("Entering into Aging State");
		
		league = this.getLeague();
		return true;
	}

	@Override
	public boolean performStateTask() {
		LocalDate currentDate = league.getLeagueCurrentDate();
		IAging agingConfig = league.getGamePlayConfig().getAgingConfig();
		
		for (IConference conference : league.getConferenceDetails()) {
            for (IDivision division : conference.getDivisionDetails()) {
                for (ITeam team : division.getTeamDetails()) {
                    for (IPlayer player : team.getPlayerList()) {
                    	player.calculateAge(currentDate);
                    	player.decreasePlayerStatOnBirthday(player,agingConfig);
                    }
                }
            }
        }

        for (IFreeAgent freeAgent : league.getFreeAgent()) {
            freeAgent.calculateAge(currentDate);
            freeAgent.decreaseFreeAgentStatOnBirthday(freeAgent, agingConfig);
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
		logger.info("Exiting Aging State");
		
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		LocalDate currentDate = league.getLeagueCurrentDate();
		if (league.getLeagueSchedule().isStanleyCupWinnerDetermined()) {
			if(DateHandler.instance().isTodayPlayerDraftDate(currentDate)) {
					this.setNextState(stateFactory.createPlayerDraftState());
			}else {
				this.setNextState(stateFactory.createAdvancedTimeState());
			}
		}
		else {
			this.setNextState(stateFactory.createPersistState());
		}
		return true;
	}

}
