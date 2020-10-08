package com.dhl.g05.statemachine;

public class StateMock extends AbstractState {

	public StateMock(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public boolean enter() {
		return true;
	}

	@Override
	public boolean performStateTask() {
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Simulating season");
		return true;
	}

	@Override
	public boolean exit() {
		return true;
	}


}
