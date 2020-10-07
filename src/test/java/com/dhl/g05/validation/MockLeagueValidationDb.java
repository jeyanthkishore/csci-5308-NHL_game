package com.dhl.g05.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.PlayerObject;
import com.dhl.g05.leaguemodel.TeamObject;

public class MockLeagueValidationDb implements ILeagueModelValidation{



	public String leagueName = "HockeyLeague";
	public Map<String,Object> firstPlayerInfo;
	public Map<String,Object> secondPlayerInfo;
	public List<PlayerObject> playerList;
	public List<TeamObject> teamList;
	public List<DivisionObject> divisionList;
	public List<PlayerObject> freeAgentList;
	public List<ConferenceObject> conferenceList;
	public String teamName = "Striker XI";
	public String generalManagerName = "Zidanie Zidane";
	public String headCoachName = "Diego Maradona";
	public String divisionOneName = "Atlantic";
	public String divisionTwoName = "Pacific";
	public String conferenceName = "Western Conference";
	
	public  MockLeagueValidationDb () {
		setJsonValues();
	}
	
	private void setJsonValues() {
		divisionList = new ArrayList<DivisionObject>();
		playerList = new ArrayList<PlayerObject>();
		firstPlayerInfo = new HashMap<String,Object>();
		teamList = new ArrayList<TeamObject>();
		freeAgentList = new ArrayList<PlayerObject>();
		conferenceList = new ArrayList<ConferenceObject>();
		firstPlayerInfo.put("playerName", "Cristiano Ronaldo");
		firstPlayerInfo.put("position", "forward");
		firstPlayerInfo.put("captain", true);
		playerList.add(new PlayerObject(firstPlayerInfo));
		secondPlayerInfo = new HashMap<String,Object>();
		secondPlayerInfo.put("playerName", "Messi");
		secondPlayerInfo.put("position", "goalie");
		secondPlayerInfo.put("captain", false);
		playerList.add(new PlayerObject(secondPlayerInfo));
		teamList.add(new TeamObject(teamName,headCoachName,generalManagerName,playerList));
		teamList.add(new TeamObject(teamName,headCoachName,generalManagerName,playerList));
		divisionList.add(new DivisionObject(divisionOneName,teamList));
		divisionList.add(new DivisionObject(divisionTwoName,teamList));
		freeAgentList.add(new PlayerObject(secondPlayerInfo));
		freeAgentList.add(new PlayerObject(secondPlayerInfo));
		conferenceList.add(new ConferenceObject(conferenceName,divisionList));
		conferenceList.add(new ConferenceObject(conferenceName,divisionList));
	}
	
	public void setLeagueEmpty() {
		leagueName = "";
	}
	
	public void setLeagueNull() {
		leagueName = null;
	}
	
	public void setPlayerDetailsEmpty() {
		firstPlayerInfo.clear();
	}

	public void setPlayerNameEmpty() {
		firstPlayerInfo.replace("playerName", "");
	}

	public void setPlayerNameNull() {
		firstPlayerInfo.replace("playerName", null);
	}
	
	public void setPlayerPositionEmpty() {
		firstPlayerInfo.replace("position", "");
	}

	public void setPlayerPostitionNull() {
		firstPlayerInfo.replace("position", null);
	}
	
	public void setCaptainEmpty() {
		firstPlayerInfo.replace("captain", "");
	}

	public void setCaptainNull() {
		firstPlayerInfo.replace("captain", null);
	}

	public void setPositionDifferent() {
		firstPlayerInfo.replace("position", "wing");
	}

	public void setCaptainNonBoolean() {
		firstPlayerInfo.replace("captain", "true");
	}
	
	public void addMoreAttribute() {
		firstPlayerInfo.put("Awards", "10");
	}
	public void removeAttribute() {
		firstPlayerInfo.remove("position");
	}
	public void setPlayerListEmpty() {
		playerList.clear();
	}
	public void addMaximumPlayer() {
		for(int count = 0; count<22; count++) {
			playerList.add(new PlayerObject(secondPlayerInfo));
		}
	}
	public void setTeamNameEmpty() {
		teamName = "";
	}

	public void setTeamNameNull() {
		teamName = null;
	}
	
	public void setCoachNameEmpty() {
		headCoachName = "";
	}

	public void setCoachNameNull() {
		headCoachName = null;
	}
	
	public void setManagerNameEmpty() {
		generalManagerName = "";
	}

	public void setManagerNameNull() {
		generalManagerName = null;
	}
	
	public void setSecondCaptain() {
		secondPlayerInfo.put("captain",true) ;
	}
	
	public void removeCaptain() {
		firstPlayerInfo.put("captain",false) ;
	}
	
	public void setDivisionNameEmpty() {
		divisionOneName = "";
	}

	public void setDivisionNameNull() {
		divisionOneName = null;
	}
	
	public void removeTeams() {
		teamList.clear();
	}
	
	public void setConferenceNameEmpty() {
		conferenceName = "";
	}

	public void setConferenceNameNull() {
		conferenceName = null;
	}
	
	public void removeDivision() {
		divisionList.clear();
	}
	public void removeOneDivision() {
		divisionList.remove(0);
	}
	public void removeConference() {
		conferenceList.clear();
	}
	public void removeOneConference() {
		conferenceList.remove(0);
	}
	public void setFreeAgentListEmpty() {
		freeAgentList.clear();
	}
	public void setFreeAgentNameEmpty() {
		firstPlayerInfo.replace("playerName", "");
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}

	public void setFreeAgentNameNull() {
		firstPlayerInfo.replace("playerName", null);
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}
	
	public void setFreeAgentPositionEmpty() {
		firstPlayerInfo.replace("position", "");
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}

	public void setFreeAgentPositionNull() {
		firstPlayerInfo.replace("position", null);
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}
	
	public void setFreeAgentCaptainEmpty() {
		firstPlayerInfo.replace("captain", "");
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}

	public void setFreeAgentCaptainNull() {
		firstPlayerInfo.replace("captain", null);
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}

	public void setFreeAgentPositionDifferent() {
		firstPlayerInfo.replace("position", "wing");
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}
	public void setFreeAgentCaptainNonBoolean() {
		firstPlayerInfo.replace("captain", "false");
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}
	public void setFreeAgentCaptainTrueBoolean() {
		firstPlayerInfo.replace("captain", true);
		freeAgentList.add(new PlayerObject(firstPlayerInfo));
	}
	@Override
	public void loadTeamData(TeamValidation teamObject) {
		teamObject.setTeamName(teamName);
		teamObject.setHeadCoachName(generalManagerName);
		teamObject.setGeneralManagerName(headCoachName);
		teamObject.setPlayerList(playerList);
	}

	@Override
	public void loadPlayerData(PlayerValidation playerOject) {
		playerOject.setplayerDetails(firstPlayerInfo);
	}

	@Override
	public void LoadDivisionData(DivisionValidation divisionObject) {
		divisionObject.setDivisionName(divisionOneName);
		divisionObject.setTeamDetails(teamList);
	}

	@Override
	public void loadLeagueData(LeagueValidation leagueObject) {
		leagueObject.setLeagueName(leagueName);
		leagueObject.setConferenceDetails(conferenceList);
		leagueObject.setFreeAgent(freeAgentList);
	}

	@Override
	public void loadConferenceData(ConferenceValidation conferenceObject) {
		conferenceObject.setConferenceName(conferenceName);
		conferenceObject.setDivisionDetails(divisionList);
	}

}
