package com.dhl.g05.leaguemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.coach.ICoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.conference.IConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.division.IDivisionModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgent;
import com.dhl.g05.leaguemodel.freeagent.IFreeAgentModel;
import com.dhl.g05.leaguemodel.league.ILeagueModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.manager.IManagerModel;
import com.dhl.g05.leaguemodel.player.IPlayerModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.ITeamModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class JsonMockDataDb implements ILeagueModel,IConferenceModel,IDivisionModel,ITeamModel,IPlayerModel,IFreeAgentModel,ICoachModel,IManagerModel{

	public String leagueName = "HockeyLeague";
	public Map<String,Object> firstPlayerInfo;
	public Map<String,Object> secondPlayerInfo;
	public List<PlayerModel> playerList;
	public List<TeamModel> teamList;
	public List<DivisionModel> divisionList;
	public List<FreeAgentModel> freeAgentList;
	public List<ConferenceModel> conferenceList;
	public List<CoachModel> coachList;
	public CoachModel coachDetails;
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
	public LeagueModel league;
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
	
	public LeagueModel getLeague() {
		return league;
	}

	public void setLeague(LeagueModel league) {
		this.league = league;
	}

	public  JsonMockDataDb () {
		setJsonValues();
	}
	
	private void setJsonValues() {
		divisionList = new ArrayList<DivisionModel>();
		playerList = new ArrayList<PlayerModel>();
		teamList = new ArrayList<TeamModel>();
		freeAgentList = new ArrayList<FreeAgentModel>();
		coachList = new ArrayList<CoachModel>();
		conferenceList = new ArrayList<ConferenceModel>();
		leagueList = new ArrayList<HashMap<String,Object>>();
		leagueMap = new HashMap<String,Object>();
		league = new LeagueModel();
		playerOneName =  "Cristiano Ronaldo";
		positionOne = "forward";
		captainOne = true;
		playerList.add(new PlayerModel(playerOneName,positionOne,captainOne,age,skating,shooting,checking,saving));
		playerTwoName= "Messi";
		positionTwo =  "goalie";
		captainTwo = false;
		coachDetails = new CoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving);
		playerList.add(new PlayerModel(playerTwoName,positionTwo,captainTwo,age,skating,shooting,checking,saving));
		teamList.add(new TeamModel(teamName,coachDetails,generalManagerName,playerList));
		teamList.add(new TeamModel(teamTwoName,coachDetails,generalManagerTwoName,playerList));
		divisionList.add(new DivisionModel(divisionOneName,teamList));
		divisionList.add(new DivisionModel(divisionTwoName,teamList));
		freeAgentList.add(new FreeAgentModel(playerOneName,positionOne,age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel(playerTwoName,positionTwo,age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("SuperMan",positionOne,age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Pit Bull","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("SpiderMan","goalie",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Sachin Tendulkar","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Virat Kohli","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Rondahino","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("James Cameron","goalie",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Silambarasan","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Yuvan Raj","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Great Khali","goalie",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Caper Carloon","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Dwayane Johnson","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("John Cena","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Triple HHH","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Vin Diesel","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Robert Junior","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Mingrann Bose","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Brad Pit","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Kajal Agarwal","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Krithick Roshan","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Salman Butt","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Rajni Kanth","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Karthi Sivakumar","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Keerthi Suresh","defense",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Vijay Joseph","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Maadu Ravi","forward",age,skating,shooting,checking,saving));
		freeAgentList.add(new FreeAgentModel("Kajol","defense",age,skating,shooting,checking,saving));
		conferenceList.add(new ConferenceModel(conferenceName,divisionList));
		conferenceList.add(new ConferenceModel(conferenceTwoName,divisionList));
		coachList.add(new CoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving));
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
			playerList.add(new PlayerModel(playerTwoName,positionTwo,captainTwo,age,skating,shooting,checking,saving));
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

	@Override
	public void loadTeamModelData(TeamModel teamObject) {
		teamObject.setTeamName(teamName);
		teamObject.setCoachDetails(coachDetails);
		teamObject.setGeneralManagerName(generalManagerName);
		teamObject.setPlayerList(playerList);
	}

	@Override
	public void loadPlayerModelData(PlayerModel playerModelObject) {
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
	public void LoadDivisionModelData(DivisionModel divisionModelObject) {
		divisionModelObject.setDivisionName(divisionOneName);
		divisionModelObject.setTeamDetails(teamList);
	}

	@Override
	public void loadLeagueModelData(LeagueModel leagueModelObject) {
		leagueModelObject.setLeagueName(leagueName);
		leagueModelObject.setConferenceDetails(conferenceList);
		leagueModelObject.setFreeAgent(freeAgentList);
		leagueModelObject.setFreeCoach(coachList);
	}

	@Override
	public void loadConferenceModelData(ConferenceModel conferenceModelObject) {
		conferenceModelObject.setConferenceName(conferenceName);
		conferenceModelObject.setDivisionDetails(divisionList);
	}

	@Override
	public void loadPlayerModelData(FreeAgentModel freeAgentObject) {
		freeAgentObject.setPlayerName(playerOneName);
		freeAgentObject.setPosition(positionOne);
		freeAgentObject.setAge(age);
		freeAgentObject.setSkating(skating);
		freeAgentObject.setShooting(shooting);
		freeAgentObject.setChecking(checking);
		freeAgentObject.setSaving(saving);
	}

	@Override
	public void loadCoachModelData(CoachModel coachObject){
		coachObject.setName(headCoachName);
		coachObject.setSkating(coachSkating);
		coachObject.setShooting(coachShooting);
		coachObject.setChecking(coachChecking);
		coachObject.setSaving(coachSaving);

	}

}
