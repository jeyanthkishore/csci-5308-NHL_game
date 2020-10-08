package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;
import com.dhl.g05.leagueobjects.TeamObject;

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
		team = this.getOuterStateMachine().getLeagueModel().createTeam((String)teamDetails.get("teamName"), (String)teamDetails.get("teamManager"), (String)teamDetails.get("teamCoach"), new ArrayList<PlayerObject>());
		if (this.getOuterStateMachine().getLeagueModel().validateTeam(team) 
				&& this.getOuterStateMachine().getLeagueModel().addTeam(conferenceName,divisionName,team)) {
			
			return true;
		}
		//TODO: error reporting
		return false;
	}

	@Override
	public boolean exit() {
		if(getOuterStateMachine().getLeagueModel().persistLeague()) {
			this.setNextState(new PlayerChoiceState(this.getOuterStateMachine()));
			this.markStateCompleted();
			return true;
		}
		//TODO: error reporting
		 return false;
	}

	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}
	
	public TeamObject getTeam() {
		return team;
	}
	

}
