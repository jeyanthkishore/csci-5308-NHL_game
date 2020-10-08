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
	public void enter() {
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
	}

	@Override
	public void performStateTask() {
		// TODO fix object method calls once final league objects are merged
		team = this.getOuterStateMachine().getLeagueModel().createTeam((String)teamDetails.get("teamName"), (String)teamDetails.get("teamManager"), (String)teamDetails.get("teamCoach"), new ArrayList<PlayerObject>());
		getOuterStateMachine().getLeagueModel().addTeam(conferenceName,divisionName,team);
	}

	@Override
	public void exit() {
		// TODO fix league object method calls once final league objects are merged, add check that league was saved
		getOuterStateMachine().getLeagueModel().persistLeague();
		this.transitionState(new PlayerChoiceState(this.getOuterStateMachine()));
		
	}

	public Map<String,Object> getTeamDetails() {
		return teamDetails;
	}
	
	public TeamObject getTeam() {
		return team;
	}
	

}
