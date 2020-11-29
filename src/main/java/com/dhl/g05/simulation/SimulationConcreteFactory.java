package com.dhl.g05.simulation;

import com.dhl.g05.communication.IPlayerCommunication;

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
	public AbstractState createTradeState() {
		return new TradeState();
	}


	@Override
	public AbstractState createStimulateGameState() {
		return new StimulateGameState();
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
