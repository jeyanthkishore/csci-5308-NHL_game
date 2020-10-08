package com.dhl.g05.statemachine;

public class SimulateState extends AbstractState {

	
	public SimulateState(StateMachine stateMachine) {
		super(stateMachine);
		this.setInnerStateMachine(new StateMachine());
	}

	@Override
	public boolean enter() {
		this.getInnerStateMachine().setCurrentState(new StateMock(this.getInnerStateMachine()));
		return true;
	}

	@Override
	public boolean performStateTask() {
		for (int i = 0; i<Integer.parseInt(this.getPlayerInput()); i++){
			this.getInnerStateMachine().enterState();
			this.getInnerStateMachine().setCurrentState(new StateMock(this.getInnerStateMachine()));
		}
		return true;
	}

	@Override
	public boolean exit() {
		this.setNextState(null);
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("simulation complete");
		return true;
	}

}
