package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachConstant;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class CreateTeamState extends AbstractState {

	private TeamModel newTeam;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private LeagueModel league;
	private IPlayerCommunication communicate;
	private EnchancedTeamCreation newTeamObject;

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

		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Creating a New Team");
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter conference name:");
		conferenceName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter division name:");
		divisionName = this.getOuterStateMachine().getPlayerCommunication().getResponse();
		this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter team name:");
		teamName =  this.getOuterStateMachine().getPlayerCommunication().getResponse();
		return true; 
	}

	@Override
	public boolean performStateTask() {
		this.setNextState(new CreateTeamState(this.getOuterStateMachine()));

		newTeamObject = new EnchancedTeamCreation(league,communicate);
		if (teamName== null || divisionName == null||conferenceName == null ){
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Missing feild, team not created");
			return false;
		}

		if  (isDivisionConferenceNotExists()) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Conference/Division combo does not exist in current league ");
			return false;
		}
		if(performOperation()){
			return true;
		}
		return false;
	}

	private boolean performOperation() {
		CoachModel coach = new CoachModel();
		List<PlayerModel> playerList = new ArrayList<PlayerModel>();
		newTeam = new TeamModel();
		newTeam.setTeamName(teamName);
		coach = newTeamObject.pickCoach();
		if(!coach.validate().equals(CoachConstant.Success)) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Error Creating Coach for the team");
			return false;
		}
		newTeam.setCoachDetails(coach);
		playerList = newTeamObject.pickPlayers();
		if(playerList.size()<20 && playerList.size()>20) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Error Creating players for the team");
			return false;
		}
		newTeam.setPlayerList(playerList);
		newTeam.setUserTeam(true);
		return true;
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
		//		if(getOuterStateMachine().getLeagueModel().persistLeague()) {
		//			this.setNextState(new PlayerChoiceState(this.getOuterStateMachine(), "Enter number of seasons to simulate", new SimulateState(this.getOuterStateMachine())));
		//			return true;
		//		}
		return true;
	}

	public TeamModel getTeam() {
		return newTeam;
	}

}
