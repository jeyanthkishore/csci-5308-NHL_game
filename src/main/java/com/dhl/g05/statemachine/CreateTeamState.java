package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;

public class CreateTeamState extends AbstractState {
	
	private Map<String,Object> teamDetails;
	private TeamObject team;
	private String conferenceName;
	private String divisionName;
	
	
	public CreateTeamState(StateMachine stateMachine) {
		super(stateMachine);
	}

	@Override
	public boolean enter() {
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Create a new team:");
		teamDetails = new HashMap<String, Object>();
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
		return true; 
	}

	@Override
	public boolean performStateTask() {
		team = new TeamObject((String)teamDetails.get("teamName"), (String)teamDetails.get("teamManager"), (String)teamDetails.get("teamCoach"), new ArrayList<PlayerObject>());
		
		if (teamDetails.get("teamName") == null || teamDetails.get("teamManager") == null||teamDetails.get("teamCoach") == null ){
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Missing feild, team not created");
			return false;
		}
	
		if  (this.getOuterStateMachine().getLeagueModel().addTeamToCurrentLeague(conferenceName,divisionName,team)) {
			return true;
		} else {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Conference/Division combo does not exist in current league ");
			return false;
		}
			
	}

	@Override
	public boolean exit() {
		if(getOuterStateMachine().getLeagueModel().persistLeague()) {
			this.setNextState(new PlayerChoiceState(this.getOuterStateMachine(), "Enter number of seasons to simulate", new SimulateState(this.getOuterStateMachine())));
			return true;
		}
		
		 return false;
	}

	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}
	
	public void setTeamDetails(Map<String,Object> details) {
		teamDetails = details;
	}
	
	public TeamObject getTeam() {
		return team;
	}
	

}
