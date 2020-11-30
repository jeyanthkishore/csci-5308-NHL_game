package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerRetired;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;

public class AdvanceToNextSeasonState extends AbstractState{
	private IPlayerCommunication communication;
	private ILeague league;

	public AdvanceToNextSeasonState(IPlayerCommunication communication) {
		this.communication = communication;
	}

	@Override
	public boolean enter() {
		league = this.getLeague();
		communication.sendMessage(StateMachineConstant.StanleyCupWinner.getValue());
		ITeam winTeam= league.getLeagueSchedule().getStanleyCupWinner();
		communication.sendMessage(winTeam.getTeamName());
		return true;
	}

	@Override
	public boolean performStateTask() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		LocalDate tempDate = DateHandler.instance().getRegularSeasonStartDate().plusYears(1);
		IPlayerRetired playerRetirement = modelFactory.createPlayerRetirement();
        for (IConference conference : league.getConferenceDetails()) {
            for (IDivision division : conference.getDivisionDetails()) {
                for (ITeam team : division.getTeamDetails()) {
                    for (IPlayer player : team.getPlayerList()) {
                    	player.calculateAge(tempDate);
                        boolean isRetired = playerRetirement.checkPlayerRetirement(league.getGamePlayConfig().getAgingConfig(),player);
                        if (isRetired) {
                        	communication.sendMessage(player.getPlayerName());
//                        	playerRetirement.isPlayerRetired(league, player, team);
                        }
                    }
                }
            }
        }
        
        for (IFreeAgent freeAgent : league.getFreeAgent()) {
            freeAgent.calculateAge(tempDate);
        }

        for (IFreeAgent retiredFreeAgent : league.getRetiredFreeAgentsList()) {
            retiredFreeAgent.calculateAge(tempDate);
        }
        
        for (IPlayer retiredTeamPlayer : league.getRetiredPlayersList()) {
            retiredTeamPlayer.calculateAge(tempDate);
        }
        
        return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(simulationFactory.createPersistState());
		return true;
	}

}
