package com.dhl.g05.leaguemodel;

import java.util.List;
import com.dhl.g05.operation.IDataBasePersistence;

public class TeamObject {
	
	private String teamName;
	private CoachObject coachDetails;
	private String generalManagerName;
	private List<PlayerObject> playerList;
	private String result;
	private double teamStrength;
	
	public TeamObject() {
		setGeneralManagerName(null);
		setTeamName(null);
		setPlayerList(null);
		setCoachDetails(null);
	}

	public TeamObject(ILeagueModel teamObject) {
		teamObject.loadTeamModelData(this);
	}
	
	public TeamObject(String team, CoachObject coachDetails, String manager, List<PlayerObject> players) {
		this.teamName = team;
		this.coachDetails = coachDetails;
		this.generalManagerName = manager;
		this.playerList = players;
		result = validate();
	}

	public int saveTeamObject(int divisionId,IDataBasePersistence database) {
		return database.saveTeamObject(divisionId,this, coachDetails);
	}

	public int loadTeamObject(int divisionId,IDataBasePersistence database) {
		return database.loadTeamObject(divisionId,this, coachDetails);
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	public CoachObject getCoachDetails() {
		return coachDetails;
	}

	public void setCoachDetails(CoachObject coachDetails) {
		this.coachDetails = coachDetails;
	}

	public double getTeamStrength() {
		return teamStrength;
	}

	public void setTeamStrength(double teamStrength) {
		this.teamStrength = teamStrength;
	}

	public String validate() {
		if(isTeamDetailsEmpty()||isTeamDetailsNull()) {
			return "Team Details Are Empty";
		}
		if(isPlayerListEmpty()) {
			return "Player List Is Empty";
		}
		if(isPlayerListMaximum()) {
			return "Maximum Player Limit Is 20";
		}
		if(containOneTeamCaptain()==0) {
			return "Team Must Contain Atleast One Captain";
		}
		if(containOneTeamCaptain()>1) {
			return "Team Must Contain Only One Captain";
		}
		if(isCoachDetailsEmptyOrNull()){
			return "Coach has missing values";
		}
		return "success";
	}

	public boolean isTeamDetailsEmpty() {
		if(teamName == "" || generalManagerName =="") {
			return true;
		}
		return false;
	}

	public boolean isTeamDetailsNull() {
		if(teamName == null || generalManagerName ==null) {
			return true;
		}
		return false;
	}

	public boolean isPlayerListEmpty() {
		return (playerList == null || playerList.isEmpty());
	}

	public boolean isPlayerListMaximum() {
		if(playerList.size() > 20) {
			return true;
		}
		return false;
	}

	public long containOneTeamCaptain() {
		long captainCount = playerList.stream().filter(p -> p.getCaptain().equals(true)).count();
		return captainCount;
	}

	public boolean isCoachDetailsEmptyOrNull() {
		if(coachDetails == null){
			return true;
		}
		return false;
	}
}
