package com.dhl.g05.leaguemodel;

import java.util.List;
import com.dhl.g05.operation.IDataBasePersistence;

public class TeamObject {
	
	private String teamName;
	private CoachObject headCoach;
	private String generalManager;
	private List<PlayerObject> players;
	private double teamStrength;
	private Boolean userTeam;
	
	public TeamObject() {
		setGeneralManagerName(null);
		setTeamName(null);
		setPlayerList(null);
		setCoachDetails(null);
		setUserTeam(false);
	}


	public TeamObject(ILeagueModel teamObject) {
		teamObject.loadTeamModelData(this);
	}
	
	public TeamObject(String team, CoachObject coachDetails, String manager, List<PlayerObject> players) {
		this.teamName = team;
		this.headCoach = coachDetails;
		this.generalManager = manager;
		this.players = players;
		this.userTeam = false;
	}

	public int saveTeamObject(int divisionId,IDataBasePersistence database) {
		return database.saveTeamObject(divisionId,this, headCoach);
	}

	public int loadTeamObject(int divisionId,IDataBasePersistence database) {
		return database.loadTeamObject(divisionId,this, headCoach);
	}

	public double calculateTeamStrength(List<PlayerObject> playerList){
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

	public List<PlayerObject> getPlayerList() {
		return players;
	}

	public void setPlayerList(List<PlayerObject> playerList) {
		this.players = playerList;
	}

	public CoachObject getCoachDetails() {
		return headCoach;
	}

	public void setCoachDetails(CoachObject coachDetails) {
		this.headCoach = coachDetails;
	}

	public double getTeamStrength() {
		return teamStrength;
	}

	public void setTeamStrength(double teamStrength) {
		this.teamStrength = teamStrength;
	}

	public ValidateEnumModel validate() {
		if(isTeamDetailsEmpty()||isTeamDetailsNull()) {
			return ValidateEnumModel.TeamDetailsEmpty;
		}
		if(isPlayerListEmpty()) {
			return ValidateEnumModel.PlayerListEmpty;
		}
		if(isPlayerListMaximum()) {
			return ValidateEnumModel.MaxPlayerCountExceed;
		}
		if(containOneTeamCaptain()==0) {
			return ValidateEnumModel.NoTeamCaptain;
		}
		if(containOneTeamCaptain()>1) {
			return ValidateEnumModel.MoreTeamCaptain;
		}
		if(isCoachDetailsEmptyOrNull()){
			return ValidateEnumModel.CoachDetailsEmpty;
		}
		return ValidateEnumModel.Success;
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
