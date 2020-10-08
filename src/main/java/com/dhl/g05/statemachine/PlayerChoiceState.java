package com.dhl.g05.statemachine;

public class PlayerChoiceState extends AbstractState{

	private String choice;
	private String message;
	
	public PlayerChoiceState(StateMachine stateMachine, String message) {
		super(stateMachine);
	}
	
	public PlayerChoiceState(StateMachine stateMachine, String message, AbstractState state) {
		super(stateMachine);
		this.setNextState(state);
	}

	@Override
	public boolean enter() {
		this.getOuterStateMachine().getPlayerCommunication().sendMessage(message);
		return true;
	}

	@Override
	public boolean performStateTask() {
		this.choice = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		return true;
	}

	@Override
	public boolean exit() {
		
		if (choice.equalsIgnoreCase("create")){
			this.setNextState(new CreateTeamState(this.getOuterStateMachine()));
		} else if (choice.equalsIgnoreCase("load")) {
			this.setNextState(new LoadTeamState(this.getOuterStateMachine()));
		} else {
			this.getNextState().setPlayerInput(choice);
		}
		return true;
	}
	
	public String getChoice() {
		return choice;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	public String getMessage() {
		return choice;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
