package com.dhl.g05.statemachine;

import java.util.HashMap;
import java.util.Map;

import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.TeamObject;

public class LoadTeamState extends AbstractState{

	private Map<String,Object> teamDetails;
	private TeamObject team;
	private String conferenceName;
	private String divisionName;
	
	public LoadTeamState(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public void enter() {
		teamDetails = new HashMap<String,Object>();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter conference name:");
		conferenceName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter division name:");
		divisionName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team name:");
		teamDetails.put("teamName", this.getOuterStateMachine().getPlayerCommunication().getResponse());
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team manager:");
		teamDetails.put("teamManager", this.getOuterStateMachine().getPlayerCommunication().getResponse());
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team coach:");
		teamDetails.put("teamCoach", this.getOuterStateMachine().getPlayerCommunication().getResponse());
	}

	@Override
	public void performStateTask() {
		//TODO: get league from db
	//	this.getOuterStateMachine().setLeague(loadTeam(teamdeltails)));	
	}

	@Override
	public void exit() {
		this.transitionState(new PlayerChoiceState(this.getOuterStateMachine()));
		this.getOuterStateMachine().enterState();
	}
	
	
	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}

	public void setTeamDetails(Map<String,Object> teamDetails) {
		this.teamDetails = teamDetails;
		
	}

}
