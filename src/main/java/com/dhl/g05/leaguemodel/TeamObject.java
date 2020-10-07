package com.dhl.g05.leaguemodel;

import java.util.List;

public class TeamObject {
	
	private String teamName;
	private String headCoachName;
	private String generalManagerName;
	private List<PlayerObject> playerList;
	
	public TeamObject() {
		setCoachName(null);
		setManagerName(null);
		setTeamName(null);
		setPlayerList(null);
	}

	public TeamObject(ILeagueValidation teamObject) {
		teamObject.loadTeamData(this);
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

	public String getCoachName() {
		return headCoachName;
	}

	public void setCoachName(String coachName) {
		this.headCoachName = coachName;
	}

	public String getManagerName() {
		return generalManagerName;
	}

	public void setManagerName(String managerName) {
		this.generalManagerName = managerName;
	}
	public List<PlayerObject> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<PlayerObject> playerList) {
		this.playerList = playerList;
	}
}
