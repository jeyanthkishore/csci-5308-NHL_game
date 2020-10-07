package com.dhl.g05.statemachine;

import java.util.Map;

import com.dhl.g05.leagueobjects.TeamObject;

public class CreateTeamState extends AbstractState {
	
	private Map<String,Object> teamDetails;
	private TeamObject team;
	
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

	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}
	
	public TeamObject getTeam() {
		return team;
	}
	
	
	private void makeTeam() {
		
	}
	
	private void persistTeam() {
		
	}

}
