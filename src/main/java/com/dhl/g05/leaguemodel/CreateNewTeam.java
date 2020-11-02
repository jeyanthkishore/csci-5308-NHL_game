package com.dhl.g05.leaguemodel;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.leaguemodel.coach.CoachConstant;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;
import com.mysql.cj.util.StringUtils;

public class CreateNewTeam implements ICreateTeam {

	private TeamModel newTeam = new TeamModel();
	private LeagueModel leagueObject;
	private IPlayerCommunication communicate;
	List<FreeAgentModel> freeAgentList = new ArrayList<FreeAgentModel>();
	List<CoachModel> coachList = new ArrayList<CoachModel>();
	List<String> managerList = new ArrayList<String>();

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

	public List<String> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<String> managerList) {
		this.managerList = managerList;
	}
	
	public TeamModel getNewTeam() {
		return newTeam;
	}
	
	public boolean teamCreation(String TeamName) {
		List<PlayerModel> playerList = new ArrayList<PlayerModel>();
		freeAgentList = leagueObject.getFreeAgent();
		newTeam.setTeamName(TeamName);
		
		CoachModel coach = pickCoach();
		if(coach.validate().equals(CoachConstant.Success)) {
			newTeam.setCoachDetails(coach);
		}else {
			communicate.sendMessage(CreateTeamConstant.ErrorCoachCreation.getValue());
			return false;
		}
		
		String managerObject = "";
		managerObject = pickManager();
		if(StringUtils.isNullOrEmpty(managerObject)) {
			communicate.sendMessage(CreateTeamConstant.ErrorManagerCreation.getValue());
			return false;
		}else {
			newTeam.setGeneralManagerName(managerObject);
		}
		
		playerList = pickPlayers();
		if(playerList.size()<20 || playerList.size()>20) {
			communicate.sendMessage(CreateTeamConstant.ErrorPlayerCreation.getValue());
			return false;
		}else {
			newTeam.setPlayerList(playerList);
		}
		
		newTeam.setUserTeam(true);
		return true;
	}

	private CoachModel pickCoach() {
		CoachModel selectedCoach = new CoachModel();
		coachList = leagueObject.getFreeCoach();
		Boolean coachNotSelected = true;
		int number;
		
		while(coachNotSelected) {
			communicate.sendMessage(CreateTeamConstant.SelectCoach.getValue());
			communicate.sendCoachMessage(coachList);
			communicate.sendMessage(CreateTeamConstant.AddCoach.getValue());
			try {
				number = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
				communicate.getResponse();
				continue;
			}
			if(number ==0 || number>coachList.size()) {
				communicate.sendMessage(CreateTeamConstant.InvalidNumber.getValue());
				communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
				communicate.getResponse();
				continue;
			}
			selectedCoach = coachList.get(number-1);
			coachList.remove(number-1);
			coachNotSelected = false;
		}
		
		return selectedCoach;
	}

	private String pickManager() {
		managerList = leagueObject.getManagerList();
		Boolean ManagerNotSelected = true;
		String selectedManager ="";
		int number;
		while(ManagerNotSelected) {
			communicate.sendMessage(CreateTeamConstant.SelectManager.getValue());
			communicate.sendManagerMessage(managerList);
			communicate.sendMessage(CreateTeamConstant.AddManager.getValue());
			try {
				number = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
				communicate.getResponse();
				continue;
			}
			if(number == 0 || number>managerList.size()) {
				communicate.sendMessage(CreateTeamConstant.InvalidNumber.getValue());
				communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
				communicate.getResponse();
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
		int responseNumber;
		double skating = 0;
		double shooting = 0;
		double checking = 0;
		double saving = 0;
		String name ="";
		String position="";
		while(playerList.size()<20) {
			String teamCount = CreateTeamConstant.TeamCount.getValue() + playerList.size();
			String skaterscount = CreateTeamConstant.SkaterCount.getValue() +skaters;
			String goaliecount = CreateTeamConstant.GoalieCount.getValue() +goalie;
			communicate.sendMessage(teamCount+CreateTeamConstant.Separator.getValue()
			+skaterscount+CreateTeamConstant.Separator.getValue()+goaliecount);
			communicate.sendMessage(CreateTeamConstant.SelectFreeAgent.getValue());
			communicate.sendMessage(freeAgentList);
			communicate.sendMessage(CreateTeamConstant.AddPlayer.getValue());
			try {
				responseNumber = communicate.getResponseNumber();
			}catch(InputMismatchException e) {
				communicate.sendMessage(CreateTeamConstant.NoNumberResponse.getValue());
				communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
				communicate.getResponse();
				continue;
			}
			
			if(responseNumber == 0 || responseNumber>freeAgentList.size()) {
				communicate.sendMessage(CreateTeamConstant.InvalidNumber.getValue());
				communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
				communicate.getResponse();
				continue;
			}
			if(captainNotAssigned) {
				communicate.sendMessage(CreateTeamConstant.CaptainConfirmation.getValue());
				captainResponse = communicate.getResponse();
				if(captainResponse.equals(CreateTeamConstant.Yes.getValue())) {
					captain = true;
					captainNotAssigned = false;
				}
			}
			if(freeAgentList.get(responseNumber-1).getPosition().equals(CreateTeamConstant.Goalie.getValue())) {
				goalie++;
				if(goalie > 2) {
					goalie--;
					communicate.sendMessage(CreateTeamConstant.MaximumGoalieMessage.getValue());
					communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
					communicate.getResponse();
					continue;
				}
			}else {
				skaters++;
				if(skaters > 18) {
					skaters--;
					communicate.sendMessage(CreateTeamConstant.MaximumSkatersMessage.getValue());
					communicate.sendMessage(CreateTeamConstant.AnyKeyMessage.getValue());
					communicate.getResponse();
					continue;
				}
			}
			name = freeAgentList.get(responseNumber-1).getPlayerName();
			position = freeAgentList.get(responseNumber-1).getPosition();
			age = freeAgentList.get(responseNumber-1).getAge();
			checking = freeAgentList.get(responseNumber-1).getChecking();
			skating = freeAgentList.get(responseNumber-1).getSkating();
			shooting = freeAgentList.get(responseNumber-1).getShooting();
			saving = freeAgentList.get(responseNumber-1).getSaving();
			playerList.add(new PlayerModel(name,position,captain,age,checking,skating,shooting,saving));
			captain = false;
			freeAgentList.remove(responseNumber-1);
		}

		return playerList;
	}

}
