package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.CreateNewTeam;
import com.dhl.g05.team.ICreateTeam;
import com.dhl.g05.team.TeamModel;

public class CreateTeamState extends AbstractState {

	private TeamModel newTeam;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private LeagueModel league;
	private IPlayerCommunication communicate;
	private ICreateTeam createTeam;

	public LeagueModel getLeague() {
		return league;
	}

	public void setLeague(LeagueModel league) {
		this.league = league;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public CreateTeamState(StateMachine stateMachine) {
		super(stateMachine);
		league = this.getOuterStateMachine().getLeagueModel().getLeague();
		communicate = this.getOuterStateMachine().getPlayerCommunication();
	}

	@Override
	public boolean enter() {
		Boolean teamNotEntered = true;
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Creating a New Team");
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter conference name:");
		conferenceName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter division name:");
		divisionName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		while(teamNotEntered) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team name:");
			teamName =  this.getOuterStateMachine().getPlayerCommunication().getResponse();
			Boolean notUnique = this.getOuterStateMachine().getLeagueModel().checkTeamNotUnique(teamName);
			if(notUnique) {
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Please Enter Unique Team Name");
				continue;
			}
			teamNotEntered = false;
		}
		return true; 
	}

	@Override
	public boolean performStateTask() {
		this.setNextState(new CreateTeamState(this.getOuterStateMachine()));

		if (teamName== null || divisionName == null||conferenceName == null ){
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Missing feild, team not created");
			return false;
		}

		if  (isDivisionConferenceNotExists()) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Conference/Division combo does not exist in current league ");
			return false;
		}

		if(createOperation()){
			this.getOuterStateMachine().getLeagueModel().setLeague(league);
			return true;
		}

		return false;
	}


	private Boolean createOperation() {

		createTeam = new CreateNewTeam(league,communicate);
		if(createTeam.teamCreation(teamName)){
			league.setFreeAgent(createTeam.getFreeAgentList());
			league.setFreeCoach(createTeam.getCoachList());
			league.setManagerList(createTeam.getManagerList());
			newTeam = createTeam.getNewTeam();
			addNewTeamtoLeagueObject();
			return true;
		}
		return false;
	}


	private void addNewTeamtoLeagueObject() {
		List<ConferenceModel> conferences = league.getConferenceDetails();
		for (ConferenceModel c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionModel> divisions = c.getDivisionDetails();
				for (DivisionModel d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						d.getTeamDetails().add(newTeam);
						break;
					}
				}
			}
		}
	}

	private boolean isDivisionConferenceNotExists() {
		List<ConferenceModel> conferences = league.getConferenceDetails();
		for (ConferenceModel c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionModel> divisions = c.getDivisionDetails();
				for (DivisionModel d: divisions) {
					if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean exit() {
		if(getOuterStateMachine().getLeagueModel().persistLeague()) {
			this.setNextState(new PlayerChoiceState(this.getOuterStateMachine(), "Enter number of seasons to simulate", new SimulateState(this.getOuterStateMachine())));
			return true;
		}
		return false;
	}

	public TeamModel getTeam() {
		return newTeam;
	}

}
