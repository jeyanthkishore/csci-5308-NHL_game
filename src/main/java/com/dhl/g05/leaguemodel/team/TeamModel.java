package com.dhl.g05.leaguemodel.team;

import java.util.List;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgent;
import com.dhl.g05.leaguemodel.player.PlayerModel;

public class TeamModel {
	
	private String teamName;
	private CoachModel headCoach;
	private String generalManager;
	private List<PlayerModel> players;
	private double teamStrength;
	private Boolean userTeam;
	
	public TeamModel() {
		setGeneralManagerName(null);
		setTeamName(null);
		setPlayerList(null);
		setCoachDetails(null);
		setUserTeam(false);
	}


	public TeamModel(ITeamModel teamObject) {
		teamObject.loadTeamModelData(this);
	}
	
	public TeamModel(String team, CoachModel coachDetails, String manager, List<PlayerModel> players) {
		this.teamName = team;
		this.headCoach = coachDetails;
		this.generalManager = manager;
		this.players = players;
		this.userTeam = false;
	}

	public int saveTeamObject(int divisionId,ITeamModelPersistence database) {
		return database.saveTeamObject(divisionId,this, headCoach);
	}

	public int loadTeamObject(int divisionId,ITeamModelPersistence database) {
		return database.loadTeamObject(divisionId,this, headCoach);
	}

	public double calculateTeamStrength(List<PlayerModel> playerList){
		for (IFreeAgent player: playerList) {
			if(player.getHasInjured()){
				teamStrength +=	player.calculatePlayerStrength()/2;
			}
			else{
				teamStrength += player.calculatePlayerStrength();
			}
		}
		return  teamStrength;
	}

	public Boolean getUserTeam() {
		return userTeam;
	}
	
	public void setUserTeam(Boolean userTeam) {
		this.userTeam = userTeam;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getGeneralManagerName() {
		return generalManager;
	}

	public void setGeneralManagerName(String managerName) {
		this.generalManager = managerName;
	}

	public List<PlayerModel> getPlayerList() {
		return players;
	}

	public void setPlayerList(List<PlayerModel> playerList) {
		this.players = playerList;
	}

	public CoachModel getCoachDetails() {
		return headCoach;
	}

	public void setCoachDetails(CoachModel coachDetails) {
		this.headCoach = coachDetails;
	}

	public double getTeamStrength() {
		return teamStrength;
	}

	public void setTeamStrength(double teamStrength) {
		this.teamStrength = teamStrength;
	}

	public TeamConstant validate() {
		if(isTeamDetailsEmpty()||isTeamDetailsNull()) {
			return TeamConstant.TeamDetailsEmpty;
		}
		if(isPlayerListEmpty()) {
			return TeamConstant.PlayerListEmpty;
		}
		if(isPlayerListMaximum()) {
			return TeamConstant.MaxPlayerCountExceed;
		}
		if(containOneTeamCaptain()==0) {
			return TeamConstant.NoTeamCaptain;
		}
		if(containOneTeamCaptain()>1) {
			return TeamConstant.MoreTeamCaptain;
		}
		if(isCoachDetailsEmptyOrNull()){
			return TeamConstant.CoachDetailsEmpty;
		}
		return TeamConstant.Success;
	}

	public boolean isTeamDetailsEmpty() {
		if(teamName == "" || generalManager =="") {
			return true;
		}
		return false;
	}

	public boolean isTeamDetailsNull() {
		if(teamName == null || generalManager ==null) {
			return true;
		}
		return false;
	}

	public boolean isPlayerListEmpty() {
		return (players == null || players.isEmpty());
	}

	public boolean isPlayerListMaximum() {
		if(players.size() > 20) {
			return true;
		}
		return false;
	}

	public long containOneTeamCaptain() {
		long captainCount = players.stream().filter(p -> p.getCaptain().equals(true)).count();
		return captainCount;
	}

	public boolean isCoachDetailsEmptyOrNull() {
		if(headCoach == null){
			return true;
		}
		return false;
	}
}
