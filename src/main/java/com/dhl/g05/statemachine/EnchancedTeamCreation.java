package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.leaguemodel.CoachObject;
import com.dhl.g05.leaguemodel.FreeAgentObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.PlayerObject;

public class EnchancedTeamCreation {

	private LeagueObject leagueObject;
	private IPlayerCommunication communicate;
	List<FreeAgentObject> freeAgentList = new ArrayList<FreeAgentObject>();
	
	
	public EnchancedTeamCreation() {
		this.leagueObject = null;
		this.communicate = null;
	}

	public EnchancedTeamCreation(LeagueObject league, IPlayerCommunication communicate) {
		this.leagueObject = league;
		this.communicate = communicate;
	}

	public LeagueObject getLeagueObject() {
		return leagueObject;
	}

	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}

	public IPlayerCommunication getCommunicate() {
		return communicate;
	}

	public void setCommunicate(IPlayerCommunication communicate) {
		this.communicate = communicate;
	}

	public List<FreeAgentObject> getFreeAgentList() {
		return freeAgentList;
	}

	public void setFreeAgentList(List<FreeAgentObject> freeAgentList) {
		this.freeAgentList = freeAgentList;
	}

	public CoachObject pickCoach() {
		List<CoachObject> coachList = new ArrayList<CoachObject>();
		CoachObject selectedCoach = new CoachObject();
		coachList = leagueObject.getFreeCoach();
		String wait = "";
		Boolean coachNotSelected = true;
		while(coachNotSelected) {
			communicate.sendMessage("Select a Coach from the below list --");
			communicate.sendCoachMessage(coachList);
			communicate.sendMessage("Enter a Number to add player");
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

	public List<PlayerObject> pickPlayers() {
		List<PlayerObject> playerList = new ArrayList<PlayerObject>();
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
		freeAgentList = leagueObject.getFreeAgent();
		while(playerList.size()<20) {
			String teamCount = "Total Team Strength = " + playerList.size();
			String skaterscount = "Number of Skaters = " +skaters;
			String gaoliecount = "Number of Goalies = " +goalie;
			communicate.sendMessage(teamCount);
			communicate.sendMessage(skaterscount);
			communicate.sendMessage(gaoliecount);
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
			playerList.add(new PlayerObject(name,position,captain,age,checking,skating,shooting,saving));
			captain = false;
			freeAgentList.remove(number-1);
		}
		return playerList;
	}

}
