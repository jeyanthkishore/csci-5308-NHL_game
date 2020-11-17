package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.db.AbstractDataBaseFactory;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.CreateNewTeam;
import com.dhl.g05.team.ICreateTeam;
import com.dhl.g05.team.ITeamModelPersistence;
import com.dhl.g05.team.TeamModel;
import com.mysql.cj.util.StringUtils;

public class CreateTeamState extends AbstractState {

	private TeamModel newTeam;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private LeagueModel league;
	private IPlayerCommunication communicate;
	private ICreateTeam createTeam;

	public CreateTeamState(IPlayerCommunication communication) {
		communicate = communication;
		league = this.getLeague();
	}

	public void setCommunicate(IPlayerCommunication communicate) {
		this.communicate = communicate;
	}

	@Override
	public boolean enter() {
		Boolean teamNotEntered = true;
		communicate.sendMessage("Creating a New Team");
		communicate.sendMessage("Enter conference name:");
		conferenceName = communicate.getResponse();
		communicate.sendMessage("Enter division name:");
		divisionName = communicate.getResponse();
		while(teamNotEntered) {
			communicate.sendMessage("Enter team name:");
			teamName =  communicate.getResponse();
			TeamModel team = new TeamModel();
			ITeamModelPersistence teamDatabase = AbstractDataBaseFactory.getFactory().getTeamDatabase();
			boolean notUnique = team.checkTeamNotUnique(teamName,teamDatabase);
			if(notUnique) {
				communicate.sendMessage("Please Enter Unique Team Name");
				continue;
			}
			teamNotEntered = false;
		}
		return true; 
	}

	public void setLeague(LeagueModel league) {
		this.league = league;
	}

	@Override
	public boolean performStateTask() {
		this.setNextState(AbstractStateMachineFactory.getFactory().getCreateTeamState());

		if (StringUtils.isNullOrEmpty(teamName) || StringUtils.isNullOrEmpty(divisionName)
				|| StringUtils.isNullOrEmpty(conferenceName)){
			communicate.sendMessage("Missing feild, team not created");
			return false;
		}

		if  (isDivisionConferenceNotExists()) {
			communicate.sendMessage("Conference/Division combo does not exist in current league ");
			return false;
		}

		if(createOperation()){
			this.setLeague(league);
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
		this.setNextState(AbstractStateMachineFactory.getFactory().getPlayerChoiceState());
		return true;
	}

}
