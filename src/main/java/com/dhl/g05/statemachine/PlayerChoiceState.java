package com.dhl.g05.statemachine;

public class PlayerChoiceState extends AbstractState{

	private String playerChoice;
	
	public PlayerChoiceState(StateMachine stateMachine) {
		super(stateMachine);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public boolean enter() {
		// TODO Auto-generated method stub
		//ask for input
		return false;
		
	}

	@Override
	public boolean performStateTask() {
		// TODO Auto-generated method stub
		//get input
		return false;
	}

	@Override
	public boolean exit() {
		// TODO Auto-generated method stub
		//transition to appropriate state
		return false;
	}
	
	public String getChoice() {
		return playerChoice;
	}
	
	public void setChoice(String choice) {
		playerChoice = choice;
	}

}
