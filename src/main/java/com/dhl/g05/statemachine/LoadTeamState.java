package com.dhl.g05.statemachine;

import java.util.Map;

import com.dhl.g05.leagueobjects.LeagueObject;

public class LoadTeamState extends AbstractState{
	
	private StateMachine outerStateMachine;
	private StateMachine InnerStateMachine;
	private Map<String,Object> teamDetails;
	private LeagueObject league;
	private boolean wasTeamValid;

	
	public LoadTeamState(StateMachine stateMachine) {
		
	}
	
	@Override
	public void enter() {
		//prompts user and gets input

	}

	@Override
	public void performStateTask() {
		//loads data		
	}

	@Override
	public void exit() {
		//instantiate league model;
	}
	
	
	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}
	
	public LeagueObject getLeague() {
		return league;
	}

	public void setTeamDetails(Map<String,Object> teamDetails) {
		// TODO Auto-generated method stub
		
	}

}
