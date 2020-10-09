package com.dhl.g05.leaguemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dhl.g05.validation.ConferenceValidation;
import com.dhl.g05.validation.DivisionValidation;
import com.dhl.g05.validation.ILeagueModelValidation;
import com.dhl.g05.validation.LeagueValidation;
import com.dhl.g05.validation.PlayerValidation;
import com.dhl.g05.validation.TeamValidation;

public class JsonMockDataDb implements ILeagueModel,ILeagueModelValidation{

	public String leagueName = "HockeyLeague";
	public Map<String,Object> firstPlayerInfo;
	public Map<String,Object> secondPlayerInfo;
	public List<PlayerObject> playerList;
	public List<TeamObject> teamList;
	public List<DivisionObject> divisionList;
	public List<PlayerObject> freeAgentList;
	public List<ConferenceObject> conferenceList;
	public String teamName = "Striker Six";
	public String teamTwoName = "Thunder Rockers";
	public String generalManagerName = "Zidanie Zidane";
	public String generalManagerTwoName = "Sachin Tendulkar";
	public String headCoachName = "Diego Maradona";
	public String headCoachTwoName = "Rahul Dravid";
	public String divisionOneName = "Atlantic";
	public String divisionTwoName = "Pacific";
	public String conferenceName = "Western Conference";
	public String conferenceTwoName = "Eastern Conference";
	public LeagueObject league;
	String playerOneName = "";
	String positionOne = "";
	Boolean captainOne = true;
	String playerTwoName = "";
	String positionTwo = "";
	Boolean captainTwo = false;
	
	public LeagueObject getLeague() {
		return league;
	}

	public void setLeague(LeagueObject league) {
		this.league = league;
	}

	public  JsonMockDataDb () {
		setJsonValues();
	}
	
	private void setJsonValues() {
		divisionList = new ArrayList<DivisionObject>();
		playerList = new ArrayList<PlayerObject>();
		teamList = new ArrayList<TeamObject>();
		freeAgentList = new ArrayList<PlayerObject>();
		conferenceList = new ArrayList<ConferenceObject>();
		league = new LeagueObject();
		playerOneName =  "Cristiano Ronaldo";
		positionOne = "forward";
		captainOne = true;
		playerList.add(new PlayerObject(playerOneName,positionOne,captainOne));
		playerTwoName= "Messi";
		positionTwo =  "goalie";
		captainTwo = false;
		playerList.add(new PlayerObject(playerTwoName,positionTwo,captainTwo));
		teamList.add(new TeamObject(teamName,headCoachName,generalManagerName,playerList));
		teamList.add(new TeamObject(teamTwoName,headCoachName,generalManagerTwoName,playerList));
		divisionList.add(new DivisionObject(divisionOneName,teamList));
		divisionList.add(new DivisionObject(divisionTwoName,teamList));
		freeAgentList.add(new PlayerObject(playerTwoName,positionTwo,captainTwo));
		freeAgentList.add(new PlayerObject(playerTwoName,positionTwo,captainTwo));
		conferenceList.add(new ConferenceObject(conferenceName,divisionList));
		conferenceList.add(new ConferenceObject(conferenceTwoName,divisionList));
		league.setLeagueName(leagueName);
		league.setConferenceDetails(conferenceList);
		league.setFreeAgent(freeAgentList);
	}
	
	public void setLeagueEmpty() {
		leagueName = "";
	}
	
	public void setLeagueNull() {
		leagueName = null;
	}
	
	public void setPlayerDetailsEmpty() {
		playerOneName = "";
		positionOne = "";
	}

	public void setPlayerNameEmpty() {
		playerOneName = "";
	}

	public void setPlayerNameNull() {
		playerOneName= null;
	}
	
	public void setPlayerPositionEmpty() {
		positionOne = "";
	}

	public void setPlayerPostitionNull() {
		positionOne =  null;
	}

	public void setCaptainNull() {
		captainOne = null;
	}

	public void setPositionDifferent() {
		positionOne = "wing";
	}
	
	public void setPlayerListEmpty() {
		playerList.clear();
	}
	public void addMaximumPlayer() {
		for(int count = 0; count<22; count++) {
			playerList.add(new PlayerObject(playerTwoName,positionTwo,captainTwo));
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
		playerList.get(1).setCaptain(true);
	}
	
	public void removeCaptain() {
		playerList.get(0).setCaptain(false);
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
		freeAgentList.get(0).setPlayerName("");
	}

	public void setFreeAgentNameNull() {
		freeAgentList.get(0).setPlayerName(null);
	}
	
	public void setFreeAgentPositionEmpty() {
		freeAgentList.get(0).setPostition("");
	}

	public void setFreeAgentPositionNull() {
		freeAgentList.get(0).setPostition(null);
	}
	
	public void setFreeAgentCaptainEmpty() {
		freeAgentList.get(0).setCaptain(null);
	}

	public void setFreeAgentCaptainNull() {
		freeAgentList.get(0).setCaptain(null);
	}

	public void setFreeAgentPositionDifferent() {
		freeAgentList.get(0).setPostition("wing");
	}
	public void setFreeAgentCaptainTrueBoolean() {
		freeAgentList.get(0).setCaptain(true);
	}
	@Override
	public void loadTeamModelData(TeamObject teamObject) {
		teamObject.setTeamName(teamName);
		teamObject.setHeadCoachName(headCoachName);
		teamObject.setGeneralManagerName(generalManagerName);
		teamObject.setPlayerList(playerList);
	}

	@Override
	public void loadPlayerModelData(PlayerObject playerModelObject) {
		playerModelObject.setCaptain(captainOne);
		playerModelObject.setPlayerName(playerOneName);
		playerModelObject.setPostition(positionOne);
	}

	@Override
	public void LoadDivisionModelData(DivisionObject divisionModelObject) {
		divisionModelObject.setDivisionName(divisionOneName);
		divisionModelObject.setTeamDetails(teamList);
	}

	@Override
	public void loadLeagueModelData(LeagueObject leagueModelObject) {
		leagueModelObject.setLeagueName(leagueName);
		leagueModelObject.setConferenceDetails(conferenceList);
		leagueModelObject.setFreeAgent(freeAgentList);
	}

	@Override
	public void loadConferenceModelData(ConferenceObject conferenceModelObject) {
		conferenceModelObject.setConferenceName(conferenceName);
		conferenceModelObject.setDivisionDetails(divisionList);
	}

	@Override
	public void loadPlayerData(PlayerValidation playerObject) {
		playerObject.setPlayerName(playerOneName);
		playerObject.setPosition(positionOne);
		playerObject.setCaptain(captainOne);
	}

	@Override
	public void loadTeamData(TeamValidation teamObject) {
		teamObject.setTeamName(teamName);
		teamObject.setHeadCoachName(headCoachName);
		teamObject.setGeneralManagerName(generalManagerName);
		teamObject.setPlayerList(playerList);
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
