package com.dhl.g05.leaguemodel;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.leaguemodel.coach.CoachConstant;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.manager.ManagerModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class CreateNewTeam implements ICreateTeam {

	private TeamModel newTeam;
	private LeagueModel leagueObject;
	private IPlayerCommunication communicate;
	List<FreeAgentModel> freeAgentList = new ArrayList<FreeAgentModel>();
	List<CoachModel> coachList = new ArrayList<CoachModel>();
	List<ManagerModel> managerList = new ArrayList<ManagerModel>();

	public CreateNewTeam() {
		this.leagueObject = null;
		this.communicate = null;
	}

	public CreateNewTeam(LeagueModel league, IPlayerCommunication communicate) {
		this.leagueObject = league;
		this.communicate = communicate;
	}

	public List<FreeAgentModel> getFreeAgentList() {
		return freeAgentList;
	}

	public LeagueModel getLeagueObject() {
		return leagueObject;
	}

	public IPlayerCommunication getCommunicate() {
		return communicate;
	}

	public void setFreeAgentList(List<FreeAgentModel> freeAgentList) {
		this.freeAgentList = freeAgentList;
	}
	public List<CoachModel> getCoachList() {
		return coachList;
	}

	public void setCoachList(List<CoachModel> coachList) {
		this.coachList = coachList;
	}

	public List<ManagerModel> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<ManagerModel> managerList) {
		this.managerList = managerList;
	}
	
	public TeamModel getNewTeam() {
		return newTeam;
	}
	
	public boolean teamCreation(String TeamName) {
		newTeam = new TeamModel();
		List<PlayerModel> playerList = new ArrayList<PlayerModel>();
		newTeam.setTeamName(TeamName);
		CoachModel coach = pickCoach();
		if(coach.validate().equals(CoachConstant.Success)) {
			newTeam.setCoachDetails(coach);
		}else {
			communicate.sendMessage("Error Creating Coach for the team");
			return false;
		}
		playerList = pickPlayers();
		if(playerList.size()<20 && playerList.size()>20) {
			communicate.sendMessage("Error Creating players for the team");
			return false;
		}
		ManagerModel managerObject = new ManagerModel();
		managerObject = pickManager();
		newTeam.setGeneralManagerName(managerObject.getName());
		newTeam.setPlayerList(playerList);
		newTeam.setUserTeam(true);
		return true;
	}

	private CoachModel pickCoach() {
		CoachModel selectedCoach = new CoachModel();
		coachList = leagueObject.getFreeCoach();
		String wait = "";
		Boolean coachNotSelected = true;
		while(coachNotSelected) {
			communicate.sendMessage("Select a Coach from the below list --");
			communicate.sendCoachMessage(coachList);
			communicate.sendMessage("Enter a Number to add coach");
			int number = communicate.getResponseNumber();
			if(number ==0 || number>coachList.size()) {
				communicate.sendMessage("Invalid Number.......");
				communicate.sendMessage("Press Enter to Continue");
				wait = communicate.getResponse();
				continue;
			}
			selectedCoach = coachList.get(number-1);
			coachList.remove(number-1);
			coachNotSelected = false;
		}
		return selectedCoach;
	}

	private ManagerModel pickManager() {
		managerList = leagueObject.getManagerList();
		Boolean ManagerNotSelected = true;
		ManagerModel selectedManager = new ManagerModel();
		String wait = "";
		while(ManagerNotSelected) {
			communicate.sendMessage("Select a Manager from the below lits --");
			communicate.sendManagerMessage(managerList);
			int number = communicate.getResponseNumber();
			if(number ==0 || number>managerList.size()) {
				communicate.sendMessage("Invalid Number.......");
				communicate.sendMessage("Press Enter to Continue");
				wait = communicate.getResponse();
				continue;
			}
			selectedManager = managerList.get(number-1);
			managerList.remove(number-1);
			ManagerNotSelected = false;
		}
		return selectedManager;
	}

	private List<PlayerModel> pickPlayers() {
		List<PlayerModel> playerList = new ArrayList<PlayerModel>();
		Boolean captainNotAssigned = true;
		String captainResponse ="";
		Boolean captain = false;
		int goalie = 0;
		int skaters = 0;
		int age = 0;
		double skating = 0;
		double shooting = 0;
		double checking = 0;
		double saving = 0;
		String name ="";
		String position="";
		String wait="";

		freeAgentList = leagueObject.getFreeAgent();
		while(playerList.size()<20) {
			String teamCount = "Total Team Strength = " + playerList.size();
			String skaterscount = "Number of Skaters = " +skaters;
			String gaoliecount = "Number of Goalies = " +goalie;
			communicate.sendMessage(teamCount+" | "+skaterscount+" | "+gaoliecount);
			communicate.sendMessage("Select a Free Agent from the below list --");
			communicate.sendMessage(freeAgentList);
			communicate.sendMessage("Enter a Number to add player");
			int number = communicate.getResponseNumber();
			if(number ==0 || number>freeAgentList.size()) {
				communicate.sendMessage("Invalid Number.......");
				communicate.sendMessage("Press Enter to Continue");
				wait = communicate.getResponse();
				continue;
			}
			if(captainNotAssigned) {
				communicate.sendMessage("Do Want him to be captain (Yes/No) : ");
				captainResponse = communicate.getResponse();
				if(captainResponse.equalsIgnoreCase("yes")) {
					captain = true;
					captainNotAssigned = false;
				}
			}
			if(freeAgentList.get(number-1).getPosition().equalsIgnoreCase("goalie")) {
				goalie++;
				if(goalie > 2) {
					goalie--;
					communicate.sendMessage("Maximum Two Goalie per Team");
					communicate.sendMessage("Press Enter to Continue");
					wait = communicate.getResponse();
					continue;
				}
			}else {
				skaters++;
				if(skaters > 18) {
					skaters--;
					communicate.sendMessage("Maximum Skater Count Reached,Pls Select Goalie");
					communicate.sendMessage("Press Enter to Continue");
					wait = communicate.getResponse();
					continue;
				}
			}
			name = freeAgentList.get(number-1).getPlayerName();
			position = freeAgentList.get(number-1).getPosition();
			age = freeAgentList.get(number-1).getAge();
			checking = freeAgentList.get(number-1).getChecking();
			skating = freeAgentList.get(number-1).getSkating();
			shooting = freeAgentList.get(number-1).getShooting();
			saving = freeAgentList.get(number-1).getSaving();
			playerList.add(new PlayerModel(name,position,captain,age,checking,skating,shooting,saving));
			captain = false;
			freeAgentList.remove(number-1);
		}

		return playerList;
	}

}
