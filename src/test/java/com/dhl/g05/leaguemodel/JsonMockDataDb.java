package com.dhl.g05.leaguemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.coach.CoachObject;
import com.dhl.g05.leaguemodel.conference.ConferenceObject;
import com.dhl.g05.leaguemodel.division.DivisionObject;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentObject;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgent;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.leaguemodel.player.PlayerObject;
import com.dhl.g05.leaguemodel.team.TeamObject;

public class JsonMockDataDb implements ILeagueModelComplete{

	public String leagueName = "HockeyLeague";
	public Map<String,Object> firstPlayerInfo;
	public Map<String,Object> secondPlayerInfo;
	public List<PlayerObject> playerList;
	public List<TeamObject> teamList;
	public List<DivisionObject> divisionList;
	public List<FreeAgentObject> freeAgentList;
	public List<ConferenceObject> conferenceList;
	public List<CoachObject> coachList;
	public CoachObject coachDetails;
	public ArrayList<HashMap<String,Object>> leagueList;
	public HashMap<String,Object> leagueMap;
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
	public double age = 10;
	public double skating = 10;
	public double shooting = 15;
	public double checking = 10;
	public double saving = 5;
	public double coachSkating = 0.5;
	public double coachShooting = 0.5;
	public double coachChecking = 0.5;
	public double coachSaving = 0.5;
	public double playerStrength = 0;
	public double teamStrength = 0;
	public LeagueObject league;
	String playerOneName = "";
	String positionOne = "";
	String positionForward = "forward";
	String positionDefense = "defense";
	String positionGoalie = "goalie";
	Boolean captainOne = true;
	String playerTwoName = "";
	String positionTwo = "";
	Boolean captainTwo = false;
	String result = "success";
	
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
		freeAgentList = new ArrayList<FreeAgentObject>();
		coachList = new ArrayList<CoachObject>();
		conferenceList = new ArrayList<ConferenceObject>();
		leagueList = new ArrayList<HashMap<String,Object>>();
		leagueMap = new HashMap<String,Object>();
		league = new LeagueObject();
		playerOneName =  "Cristiano Ronaldo";
		positionOne = "forward";
		captainOne = true;
		playerList.add(new PlayerObject(playerOneName,positionOne,captainOne,age,skating,shooting,checking,saving));
		playerTwoName= "Messi";
		positionTwo =  "goalie";
		captainTwo = false;
		coachDetails = new CoachObject(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving);
		playerList.add(new PlayerObject(playerTwoName,positionTwo,captainTwo,age,skating,shooting,checking,saving));
		teamList.add(new TeamObject(teamName,coachDetails,generalManagerName,playerList));
		teamList.add(new TeamObject(teamTwoName,coachDetails,generalManagerTwoName,playerList));
		divisionList.add(new DivisionObject(divisionOneName,teamList));
		divisionList.add(new DivisionObject(divisionTwoName,teamList));
		freeAgentList.add(new FreeAgentObject(playerOneName,positionOne,age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject(playerTwoName,positionTwo,age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("SuperMan",positionOne,age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Pit Bull","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("SpiderMan","goalie",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Sachin Tendulkar","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Virat Kohli","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Rondahino","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("James Cameron","goalie",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Silambarasan","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Yuvan Raj","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Great Khali","goalie",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Caper Carloon","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Dwayane Johnson","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("John Cena","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Triple HHH","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Vin Diesel","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Robert Junior","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Mingrann Bose","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Brad Pit","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Kajal Agarwal","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Krithick Roshan","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Salman Butt","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Rajni Kanth","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Karthi Sivakumar","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Keerthi Suresh","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Vijay Joseph","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Maadu Ravi","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentObject("Kajol","defense",age,skating,shooting,checking,saving));
		conferenceList.add(new ConferenceObject(conferenceName,divisionList));
		conferenceList.add(new ConferenceObject(conferenceTwoName,divisionList));
		coachList.add(new CoachObject(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving));
		league.setLeagueName(leagueName);
		league.setConferenceDetails(conferenceList);
		league.setFreeAgent(freeAgentList);
		league.setFreeCoach(coachList);
		leagueMap.put("league_name","HockeyLeague");
		leagueList.add(leagueMap);
		leagueMap.put("league_name","CanadaLeague");
		leagueList.add(leagueMap);
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
			playerList.add(new PlayerObject(playerTwoName,positionTwo,captainTwo,age,skating,shooting,checking,saving));
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
		freeAgentList.get(0).setPosition("");
	}

	public void setFreeAgentPositionNull() {
		freeAgentList.get(0).setPosition(null);
	}

	public void setFreeAgentPositionDifferent() {
		freeAgentList.get(0).setPosition("wing");
	}

	public void setCoachListEmpty() {
		coachList.clear();
	}

	public void setCoachDetailsNull() {
		coachDetails = null;
	}

	public double calculatePlayerStrength(String position){
		if(position.equalsIgnoreCase("forward")){
			playerStrength = skating + shooting + (checking/2);
		}
		if(position.equalsIgnoreCase("defense")){
			playerStrength = skating + checking + (shooting/2);
		}
		if(position.equalsIgnoreCase("goalie")){
			playerStrength = skating + saving;
		}
		return playerStrength;
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

	@Override
	public void loadTeamModelData(TeamObject teamObject) {
		teamObject.setTeamName(teamName);
		teamObject.setCoachDetails(coachDetails);
		teamObject.setGeneralManagerName(generalManagerName);
		teamObject.setPlayerList(playerList);
	}

	@Override
	public void loadPlayerModelData(PlayerObject playerModelObject) {
		playerModelObject.setCaptain(captainOne);
		playerModelObject.setPlayerName(playerOneName);
		playerModelObject.setPosition(positionOne);
		playerModelObject.setAge(age);
		playerModelObject.setSkating(skating);
		playerModelObject.setShooting(shooting);
		playerModelObject.setChecking(checking);
		playerModelObject.setSaving(saving);
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
		leagueModelObject.setFreeCoach(coachList);
	}

	@Override
	public void loadConferenceModelData(ConferenceObject conferenceModelObject) {
		conferenceModelObject.setConferenceName(conferenceName);
		conferenceModelObject.setDivisionDetails(divisionList);
	}

	@Override
	public void loadPlayerModelData(FreeAgentObject freeAgentObject) {
		freeAgentObject.setPlayerName(playerOneName);
		freeAgentObject.setPosition(positionOne);
		freeAgentObject.setAge(age);
		freeAgentObject.setSkating(skating);
		freeAgentObject.setShooting(shooting);
		freeAgentObject.setChecking(checking);
		freeAgentObject.setSaving(saving);
	}

	@Override
	public void loadCoachModelData(CoachObject coachObject){
		coachObject.setName(headCoachName);
		coachObject.setSkating(coachSkating);
		coachObject.setShooting(coachShooting);
		coachObject.setChecking(coachChecking);
		coachObject.setSaving(coachSaving);

	}

}
