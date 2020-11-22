package com.dhl.g05.statemachine;

public abstract class AbstractStateMachineFactory {
	
	 private static AbstractStateMachineFactory abstractStateMachineFactory;

	    public static AbstractStateMachineFactory getFactory() {
	        return abstractStateMachineFactory;
	    }

	    public static void setFactory(AbstractStateMachineFactory stateMachineFactory) {
	        abstractStateMachineFactory = stateMachineFactory;
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
	    
}
