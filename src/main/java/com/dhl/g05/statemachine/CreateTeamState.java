package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.leaguemodel.ValidateEnumModel;
import com.dhl.g05.leaguemodel.coach.CoachObject;
import com.dhl.g05.leaguemodel.conference.ConferenceObject;
import com.dhl.g05.leaguemodel.division.DivisionObject;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.leaguemodel.player.PlayerObject;
import com.dhl.g05.leaguemodel.team.TeamObject;

public class CreateTeamState extends AbstractState {

	private TeamObject newTeam;
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private LeagueObject league;
	private IPlayerCommunication communicate;
	private EnchancedTeamCreation newTeamObject;

	public LeagueObject getLeague() {
		return league;
	}

	public void setLeague(LeagueObject league) {
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
		CoachObject coach = new CoachObject();
		List<PlayerObject> playerList = new ArrayList<PlayerObject>();
		newTeam = new TeamObject();
		newTeam.setTeamName(teamName);
		coach = newTeamObject.pickCoach();
		if(!coach.validate().equals(ValidateEnumModel.Success)) {
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
		List<ConferenceObject> conferences = league.getConferenceDetails();
		for (ConferenceObject c: conferences) {
			if (c.getConferenceName().equalsIgnoreCase(conferenceName)) {
				List<DivisionObject> divisions = c.getDivisionDetails();
				for (DivisionObject d: divisions) {
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

	public TeamObject getTeam() {
		return newTeam;
	}

}
