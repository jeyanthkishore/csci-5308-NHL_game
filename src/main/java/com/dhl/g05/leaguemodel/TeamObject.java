package com.dhl.g05.leaguemodel;

import java.util.List;

public class TeamObject {
	
	private String teamName;
	private String headCoachName;
	private String generalManagerName;
	private List<PlayerObject> playerList;
	
	public TeamObject() {
		setHeadCoachName(null);
		setGeneralManagerName(null);
		setTeamName(null);
		setPlayerList(null);
	}

	public TeamObject(ILeagueValidation teamObject) {
		teamObject.loadTeamModelData(this);
	}
	
	public TeamObject(String team, String coach, String manager, List<PlayerObject> players) {
		this.teamName = team;
		this.headCoachName = coach;
		this.generalManagerName = manager;
		this.playerList = players;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getHeadCoachName() {
		return headCoachName;
	}

	public void setHeadCoachName(String coachName) {
		this.headCoachName = coachName;
	}

	public String getGeneralManagerName() {
		return generalManagerName;
	}

	public void setGeneralManagerName(String managerName) {
		this.generalManagerName = managerName;
	}
	public List<PlayerObject> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<PlayerObject> playerList) {
		this.playerList = playerList;
	}
}
