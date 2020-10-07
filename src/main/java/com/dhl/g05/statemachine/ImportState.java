package com.dhl.g05.statemachine;

public class ImportState extends AbstractState{
	
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	private String fileName;

	//private league
	
	public ImportState(StateMachine stateMachine) {
		outerStateMachine = stateMachine;
	}
	
	@Override
	public void enter() {
		//create league modelCreator
		//check if file is valid json file
		// TODO Auto-generated method stub
	}

	@Override
	public void performStateTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	public String getFileName() {
		return fileName;
	}

}
