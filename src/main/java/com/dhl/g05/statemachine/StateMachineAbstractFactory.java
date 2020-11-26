package com.dhl.g05.statemachine;

public abstract class StateMachineAbstractFactory {

	private static StateMachineAbstractFactory abstractStateMachineFactory;

	public static StateMachineAbstractFactory getInstance(StateMachineState state) {
		abstractStateMachineFactory = state.concreteMethod();
		return abstractStateMachineFactory;
	}

	public abstract IStateMachine getStateMachine(AbstractState abstractState);
	public abstract AbstractState getImportState();
	public abstract AbstractState getCreateTeamState();
	public abstract AbstractState getLoadTeamState();
	public abstract AbstractState getPlayerChoiceState();
	public abstract AbstractState getStimulateState(int numberOfSeasons);
	public abstract AbstractState getInitializeSeasonState();
	public abstract AbstractState getAdvancedTimeState();
	public abstract AbstractState getTrainingState();
	public abstract AbstractState getPlayOffState();
	public abstract AbstractState getAgingState();
	public abstract AbstractState getTradeState();
	public abstract AbstractState getStimulateGameState();
	public abstract AbstractState getAdvanceToNextSeasonState();
	public abstract AbstractState getPersistState();
	public abstract AbstractState getPlayerDraftState();

}
