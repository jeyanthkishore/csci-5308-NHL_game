package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dhl.g05.leaguemodel.*;

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
		if(!coach.validate().equalsIgnoreCase("success")) {
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

	public String pickCoach() {
		List<CoachObject> coachList = new ArrayList<CoachObject>();
		CoachObject selectedCoach = new CoachObject();
		coachList = league.getFreeCoach();
		String wait = "";
		Boolean coachNotSelected = true;
		while(coachNotSelected) {
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Select a Coach from the below list --");
			this.getOuterStateMachine().getPlayerCommunication().sendCoachMessage(coachList);
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter a Number to add player");
			int number = this.getOuterStateMachine().getPlayerCommunication().getResponseNumber();
			if(number ==0 || number>coachList.size()) {
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Invalid Number.......");
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Press Enter to Continue");
				wait = this.getOuterStateMachine().getPlayerCommunication().getResponse();
				continue;
			}
			selectedCoach = coachList.get(number-1);
			coachList.remove(number-1);
			coachNotSelected = false;
		}
		return "success";
	}

	public String pickPlayers() {
		List<PlayerObject> playerList = new ArrayList<PlayerObject>();
		List<FreeAgentObject> free = new ArrayList<FreeAgentObject>();
		Boolean captainNotAssigned = true;
		String captainResponse ="";
		Boolean captain = false;
		int goalie = 0;
		int skaters = 0;
		double age = 0;
		double skating = 0;
		double shooting = 0;
		double checking = 0;
		double saving = 0;
		String name ="";
		String position="";
		String wait="";
		free = league.getFreeAgent();
		while(playerList.size()<20) {
			String teamCount = "Total Team Strength = " + playerList.size();
			String skaterscount = "Number of Skaters = " +skaters;
			String gaoliecount = "Number of Goalies = " +goalie;
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(teamCount);
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(skaterscount);
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(gaoliecount);
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Select a Free Agent from the below list --");
			this.getOuterStateMachine().getPlayerCommunication().sendMessage(free);
			this.getOuterStateMachine().getPlayerCommunication().sendMessage("Enter a Number to add player");
			int number = this.getOuterStateMachine().getPlayerCommunication().getResponseNumber();
			if(number ==0 || number>free.size()) {
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Invalid Number.......");
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Press Enter to Continue");
				wait = this.getOuterStateMachine().getPlayerCommunication().getResponse();
				continue;
			}
			if(captainNotAssigned) {
				this.getOuterStateMachine().getPlayerCommunication().sendMessage("Do Want him to be captain (Yes/No) : ");
				captainResponse = this.getOuterStateMachine().getPlayerCommunication().getResponse();
				if(captainResponse.equalsIgnoreCase("yes")) {
					captain = true;
					captainNotAssigned = false;
				}
			}
			if(free.get(number-1).getPosition().equalsIgnoreCase("goalie")) {
				goalie++;
				if(goalie > 2) {
					goalie--;
					this.getOuterStateMachine().getPlayerCommunication().sendMessage("Maximum Two Goalie per Team");
					this.getOuterStateMachine().getPlayerCommunication().sendMessage("Press Enter to Continue");
					wait = this.getOuterStateMachine().getPlayerCommunication().getResponse();
					continue;
				}
			}else {
				skaters++;
				if(skaters > 18) {
					skaters--;
					this.getOuterStateMachine().getPlayerCommunication().sendMessage("Maximum Skater Count Reached,Pls Select Goalie");
					this.getOuterStateMachine().getPlayerCommunication().sendMessage("Press Enter to Continue");
					wait = this.getOuterStateMachine().getPlayerCommunication().getResponse();
					continue;
				}
			}
			name = free.get(number-1).getPlayerName();
			position = free.get(number-1).getPosition();
			age = free.get(number-1).getAge();
			checking = free.get(number-1).getChecking();
			skating = free.get(number-1).getSkating();
			shooting = free.get(number-1).getShooting();
			saving = free.get(number-1).getSaving();
			playerList.add(new PlayerObject(name,position,captain,age,checking,skating,shooting,saving));
			captain = false;
			free.remove(number-1);
		}
		return "success";
	}
}
