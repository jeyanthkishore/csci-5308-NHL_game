package com.dhl.g05.statemachine;

import java.util.HashMap;
import java.util.Map;

import com.dhl.g05.leaguemodel.*;

public class LoadTeamState extends AbstractState{

	private Map<String,Object> teamDetails;
	private TeamObject team;
	private String teamName;
	private String leagueName;
	private String conferenceName;
	private String divisionName;
	
	public LoadTeamState(StateMachine stateMachine) {
		super(stateMachine);
	}
	
	@Override
	public boolean enter() {
		
		teamDetails = new HashMap<String,Object>();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team name:");
		teamName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		teamDetails.put("teamName", teamName);
	
		return true;
	}

	@Override
	public boolean performStateTask() {
		if (this.getOuterStateMachine().getLeagueModel().loadTeam(leagueName, conferenceName, divisionName, teamName)) {
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
