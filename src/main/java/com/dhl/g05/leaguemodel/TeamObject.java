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
		setPlayerDetails(null);
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

	public List<PlayerObject> getPlayerDetails() {
		return playerList;
	}

	public void setPlayerDetails(List<PlayerObject> playerDetails) {
		this.playerList = playerDetails;
	}
	
	public boolean isTeamDetailsEmpty() {
		if(teamName == "" || headCoachName ==""
				|| generalManagerName =="") {
			return true;
		}
		return false;
	}
	public boolean isTeamDetailsNull() {
		if(teamName == null || headCoachName ==null
				|| generalManagerName ==null) {
			return true;
		}
		return false;
	}
	public boolean isPlayerListEmpty() {
		return (playerList.isEmpty());
	}
	public boolean isPlayerListMaximum() {
		if(playerList.size() > 20) {
			return true;
		}
		return false;
	}
	public boolean containOneTeamCaptain() {
		long captainCount = playerList.stream().filter(p -> p.getTeamPlayers().get("captain").equals(true)).count();
		if(captainCount > 1 ||captainCount ==0) {
			return false;
		}
		return true;
	}
}
