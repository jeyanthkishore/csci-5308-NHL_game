package com.dhl.g05.statemachine;

public class CreateTeamState implements IState {
	
	private StateMachine outerStateMachine;
	private StateMachine innerStateMachine;
	
	private String conference;
	private String division;
	private String generalManager;
	private String headCoach;
	
	//private Team newTeam;
	
	
	public CreateTeamState(StateMachine stateMachine) {
		
	}

	@Override
	public void enter() {
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

	@Override
	public void transitionState(IState state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StateMachine getInnerStateMachine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInnerStateMachine(StateMachine stateMachine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runInnerStateMachine() {
		// TODO Auto-generated method stub
		
	}

	private void getTeamData() {
		
	}
	
	private void makeTeam() {
		
	}
	
	private void persistTeam() {
		
	}

	@Override
	public boolean didEnterState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean didRunState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean didExitState() {
		// TODO Auto-generated method stub
		return false;
	}

}
