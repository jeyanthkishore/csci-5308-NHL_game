package com.dhl.g05.statemachine;

public abstract class StateMachineAbstractFactory {

	private static StateMachineAbstractFactory abstractStateMachineFactory;

	public static StateMachineAbstractFactory getInstance(StateMachineState state) {
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

}
