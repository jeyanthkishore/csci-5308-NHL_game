package com.dhl.g05.statemachine;

public class PlayerChoiceState extends AbstractState{
	
	private StateMachine outerStateMachine;
	private StateMachine InnerStateMachine;
	private String playerChoice;
	

	public PlayerChoiceState(StateMachine stateMachine) {
		
	}
	
	@Override
	public void enter() {
		// TODO Auto-generated method stub
		//ask for input
		
	}

	@Override
	public void performStateTask() {
		// TODO Auto-generated method stub
		//get input
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		//transition to appropriate state
		
	}
	
	public String getChoice() {
		return playerChoice;
	}
	
	public void setChoice(String choice) {
		playerChoice = choice;
	}

}
