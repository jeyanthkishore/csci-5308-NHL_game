package com.dhl.g05.validation;

import java.util.List;

import com.dhl.g05.leaguemodel.PlayerObject;

public class TeamValidation {
	private String teamName;
	private String headCoachName;
	private String generalManagerName;
	private List<PlayerObject> playerList;
	public TeamValidation() {
		setTeamName(null);
		setHeadCoachName(null);
		setGeneralManagerName(null);
		setPlayerList(null);
	}
	public TeamValidation(ILeagueModelValidation teamObject) {
		teamObject.loadTeamData(this);
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
	public void setHeadCoachName(String headCoachName) {
		this.headCoachName = headCoachName;
	}
	public String getGeneralManagerName() {
		return generalManagerName;
	}
	public void setGeneralManagerName(String generalManagerName) {
		this.generalManagerName = generalManagerName;
	}
	public List<PlayerObject> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(List<PlayerObject> playerList) {
		this.playerList = playerList;
	}
	
	public String validateTeam() {
		if(isTeamDetailsEmpty()||isTeamDetailsNull()) {
			return "Team Details are Empty";
		}
		if(isPlayerListEmpty()) {
			return "Player List is empty";
		}
		if(isPlayerListMaximum()) {
			return "There can be only 20 Players in a Team";
		}
		if(!containOneTeamCaptain()) {
			return "Team Must Contain Only One Captain";
		}
		return "success";
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
