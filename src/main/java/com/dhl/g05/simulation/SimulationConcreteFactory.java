package com.dhl.g05.simulation;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.leaguesimulation.ILeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;
import com.dhl.g05.simulation.leaguesimulation.LeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.LeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.ScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.StandingModel;
import com.dhl.g05.simulation.statemachine.AbstractState;
import com.dhl.g05.simulation.statemachine.AdvanceTimeState;
import com.dhl.g05.simulation.statemachine.AdvanceToNextSeasonState;
import com.dhl.g05.simulation.statemachine.AgingConfig;
import com.dhl.g05.simulation.statemachine.AgingState;
import com.dhl.g05.simulation.statemachine.CreateTeamState;
import com.dhl.g05.simulation.statemachine.GamePlayConfigModel;
import com.dhl.g05.simulation.statemachine.GameResolverConfig;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.dhl.g05.simulation.statemachine.IGameResolver;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.IStateMachine;
import com.dhl.g05.simulation.statemachine.ITradingConfig;
import com.dhl.g05.simulation.statemachine.ITraining;
import com.dhl.g05.simulation.statemachine.ImportState;
import com.dhl.g05.simulation.statemachine.InitializeSeasonState;
import com.dhl.g05.simulation.statemachine.InjuryCheckState;
import com.dhl.g05.simulation.statemachine.InjuryConfig;
import com.dhl.g05.simulation.statemachine.LoadTeamState;
import com.dhl.g05.simulation.statemachine.PersistState;
import com.dhl.g05.simulation.statemachine.PlayerChoiceState;
import com.dhl.g05.simulation.statemachine.PlayerDraftState;
import com.dhl.g05.simulation.statemachine.PlayoffScheduleState;
import com.dhl.g05.simulation.statemachine.SeasonSimulateState;
import com.dhl.g05.simulation.statemachine.StateMachine;
import com.dhl.g05.simulation.statemachine.SimulateGameState;
import com.dhl.g05.simulation.statemachine.TradeState;
import com.dhl.g05.simulation.statemachine.TradingConfig;
import com.dhl.g05.simulation.statemachine.TrainingConfig;
import com.dhl.g05.simulation.statemachine.TrainingState;

public class SimulationConcreteFactory extends SimulationAbstractFactory{

	private final IPlayerCommunication communication;

	public SimulationConcreteFactory(
			IPlayerCommunication communication) {
		this.communication = communication;
	}

	@Override
	public IStateMachine createStateMachine(AbstractState abstractState) {
		return new StateMachine(abstractState);
	}

	@Override
	public AbstractState createImportState() {
		return new ImportState(communication);
	}

	@Override
	public AbstractState createCreateTeamState() {
		return new CreateTeamState(communication);
	}

	@Override
	public AbstractState createLoadTeamState() {
		return new LoadTeamState(communication);
	}

	@Override
	public AbstractState createPlayerChoiceState() {
		return new PlayerChoiceState(communication);
	}

	@Override
	public AbstractState createStimulateState(int seasons) {
		return new SeasonSimulateState(seasons);
	}

	@Override
	public AbstractState createInitializeSeasonState() {
		return new InitializeSeasonState();
	}


	@Override
	public AbstractState createAdvancedTimeState() {
		return new AdvanceTimeState();
	}


	@Override
	public AbstractState createTrainingState() {
		return new TrainingState();
	}


	@Override
	public AbstractState createPlayOffState() {
		return new PlayoffScheduleState();
	}


	@Override
	public AbstractState createAgingState() {
		return new AgingState();
	}

	@Override
	public AbstractState createInjuryCheckState(ITeam firstTeam, ITeam secondTeam) {
		return new InjuryCheckState(communication,firstTeam,secondTeam);
	}
	
	@Override
	public AbstractState createTradeState() {
		return new TradeState();
	}


	@Override
	public AbstractState createStimulateGameState() {
		return new SimulateGameState();
	}


	@Override
	public AbstractState createAdvanceToNextSeasonState() {
		return new AdvanceToNextSeasonState(communication);
	}


	@Override
	public AbstractState createPersistState() {
		return new PersistState();
	}


	@Override
	public AbstractState createPlayerDraftState() {
		return new PlayerDraftState();
	}


	@Override
	public ILeagueSchedule createLeagueSchedule() {
		return new LeagueSchedule();
	}


	@Override
	public ILeagueStanding createLeagueStanding() {
		return new LeagueStanding();
	}


	@Override
	public IScheduleModel createScheduleModel() {
		return new ScheduleModel();
	}


	@Override
	public IStandingModel createStandingModel() {
		return new StandingModel();
	}

	@Override
	public IAging createAgingConfig() {
		return new AgingConfig();
	}

	@Override
	public IInjury createInjuryConfig() {
		return new InjuryConfig();
	}

	@Override
	public ITradingConfig createTradingConfig() {
		return new TradingConfig();
	}

	@Override
	public ITraining createTrainingConfig() {
		return new TrainingConfig();
	}

	@Override
	public IGameResolver createGameResolverConfig() {
		return new GameResolverConfig();
	}

	@Override
	public IGamePlayConfig createGamePlayConfig() {
		return new GamePlayConfigModel();
	}

}
