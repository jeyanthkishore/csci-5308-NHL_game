package com.dhl.g05.simulation;

import java.time.LocalDate;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerInjured;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;

public class InjuryCheckState extends AbstractState{
	private ILeague league;
	private IPlayerCommunication communication;
	private LocalDate currentDate;

	public InjuryCheckState(IPlayerCommunication communication) {
		this.communication = communication;
	}

	@Override
	public boolean enter() {
		league = this.getLeague();
		currentDate = league.getLeagueCurrentDate();
		return true;
	}

	@Override
	public boolean performStateTask() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		IScheduleModel schedule = league.getLeagueSchedule().getMatchOnCurrentDate(currentDate);
		ITeam teamA = schedule.getFirstTeam();
		ITeam teamB = schedule.getSecondTeam();
		IPlayerInjured playerInjury = modelFactory.createPlayerInjury();
		IInjury injuryConfig = league.getGamePlayConfig().getInjuriesConfig();
		for (IPlayer player: teamA.getPlayerList()) {
			if (player.checkPlayerInjury(playerInjury, player, injuryConfig)) {
				communication.sendMessage(player.getPlayerName() +" is injured in match for days :" 
						+player.getInjuredForNumberOfDays());
			}
		}

		for (IPlayer player: teamB.getPlayerList()) {
			if (player.checkPlayerInjury(playerInjury, player, injuryConfig)) {
				communication.sendMessage(player.getPlayerName() +" is injured in match for days :" 
						+player.getInjuredForNumberOfDays());
			}
		}
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		if (league.getLeagueSchedule().isGamesUnplayedOnCurrentDay(currentDate)) {
            this.setNextState(stateFactory.createStimulateGameState());
        }
        else if (DateHandler.instance().isTradeDeadlinePassed(currentDate)) {
        	this.setNextState(stateFactory.createAgingState());
        }
        else {
        	this.setNextState(stateFactory.createTradeState());
        }
		return true;
	}

}
