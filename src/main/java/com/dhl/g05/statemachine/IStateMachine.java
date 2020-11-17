package com.dhl.g05.statemachine;

public interface IStateMachine {

	 AbstractState getCurrentState();
	 void setCurrentState(AbstractState abstractState);
	 void enterState();
	 boolean runState();
	 boolean exitState();
}
