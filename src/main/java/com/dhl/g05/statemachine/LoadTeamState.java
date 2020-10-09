package com.dhl.g05.statemachine;

import java.util.HashMap;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;

public class LoadTeamState extends AbstractState{

	private Map<String,Object> teamDetails;
	private TeamObject team;
	private String conferenceName;
	private String divisionName;
	
	public LoadTeamState(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public boolean enter() {
		
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Load a saved team");
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
		return true;
	}

	@Override
	public boolean performStateTask() {
		if (this.getOuterStateMachine().getLeagueModel().loadTeam(teamDetails)) {
			return true;
		} else {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Team does not exist");
			return false;
		}
	}

	@Override
	public boolean exit() {
		this.setNextState(new PlayerChoiceState(this.getOuterStateMachine(),"Enter number of seasons to simulate", new SimulateState(this.getOuterStateMachine())));
		return true;
	}
	
	
	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}

	public void setTeamDetails(Map<String,Object> teamDetails) {
		this.teamDetails = teamDetails;
		
	}

}
