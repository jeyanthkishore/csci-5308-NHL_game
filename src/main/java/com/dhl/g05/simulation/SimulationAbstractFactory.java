package com.dhl.g05.simulation;

import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.leaguesimulation.ILeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;
import com.dhl.g05.simulation.statemachine.AbstractState;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.dhl.g05.simulation.statemachine.IGameResolver;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.IStateMachine;
import com.dhl.g05.simulation.statemachine.ITradingConfig;
import com.dhl.g05.simulation.statemachine.ITraining;

public abstract class SimulationAbstractFactory {

	private static SimulationAbstractFactory abstractStateMachineFactory;

	public static SimulationAbstractFactory getInstance(SimulationState state) {
		abstractStateMachineFactory = state.concreteMethod();
		return abstractStateMachineFactory;
	}

	public abstract IStateMachine createStateMachine(AbstractState abstractState);
	public abstract AbstractState createImportState();
	public abstract AbstractState createCreateTeamState();
	public abstract AbstractState createLoadTeamState();
	public abstract AbstractState createPlayerChoiceState();
	public abstract AbstractState createStimulateState(int numberOfSeasons);
	public abstract AbstractState createInitializeSeasonState();
	public abstract AbstractState createAdvancedTimeState();
	public abstract AbstractState createTrainingState();
	public abstract AbstractState createPlayOffState();
	public abstract AbstractState createInjuryCheckState(ITeam firstTeam, ITeam secondTeam);
	public abstract AbstractState createAgingState();
	public abstract AbstractState createTradeState();
	public abstract AbstractState createStimulateGameState();
	public abstract AbstractState createAdvanceToNextSeasonState();
	public abstract AbstractState createPersistState();
	public abstract AbstractState createPlayerDraftState();
	public abstract ILeagueSchedule createLeagueSchedule();
	public abstract ILeagueStanding createLeagueStanding();
	public abstract IScheduleModel createScheduleModel();
	public abstract IStandingModel createStandingModel();
	public abstract IAging createAgingConfig();
	public abstract IInjury createInjuryConfig();
    public abstract ITradingConfig createTradingConfig();
    public abstract ITraining createTrainingConfig();
    public abstract IGameResolver createGameResolverConfig();
    public abstract IGamePlayConfig createGamePlayConfig();
}
