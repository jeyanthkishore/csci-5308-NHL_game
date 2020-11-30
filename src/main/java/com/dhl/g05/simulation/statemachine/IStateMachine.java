package com.dhl.g05.simulation.statemachine;

public interface IStateMachine {

	 AbstractState getCurrentState();
	 
	 void setCurrentState(AbstractState abstractState);
	 
	 void enterState();
	 
	 boolean runState();
	 
	 boolean exitState();
	 
}
