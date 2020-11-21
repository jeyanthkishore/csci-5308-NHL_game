package com.dhl.g05.mockdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.coach.ICoachModel;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivisionModel;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.freeagent.IFreeAgentModel;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.GameResolverConfig;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.gameplayconfig.TrainingConfig;
import com.dhl.g05.league.ILeagueModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.league.LeaguePersistenceMock;
import com.dhl.g05.player.IPlayerModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.ITeamModel;
import com.dhl.g05.team.TeamModel;

public class JsonMockDataDb implements ILeagueModel,IConferenceModel,IDivisionModel,ITeamModel,IPlayerModel,IFreeAgentModel,ICoachModel{
	Random randomNumber = new Random();
	public String leagueName = "HockeyLeague";
	public Map<String,Object> firstPlayerInfo;
	public Map<String,Object> secondPlayerInfo;
	public List<PlayerModel> playerList;
	public List<TeamModel> teamList;
	public List<DivisionModel> divisionList;
	public List<FreeAgentModel> freeAgentList;
	public List<ConferenceModel> conferenceList;
	public List<CoachModel> coachList;
	public List<CoachModel> coachListTwo;
	public List<String> managerList;
	public List<IFreeAgent> retiredFreeAgents;
	public List<PlayerModel> retiredTeamPlayers;
	public List<String> managerListTwo;
	public CoachModel coachDetails;
	public ArrayList<HashMap<String,Object>> leagueList;
	public HashMap<String,Object> leagueMap;
	public GamePlayConfigModel gamePlayConfig;
	public TradingModel tradeConfig;
	public TrainingConfig training;
	public GameResolverConfig gameResolver;
	public Injury injury;
	public Aging aging;
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
	public double skating = 10;
	public double shooting = 15;
	public double checking = 10;
	public double saving = 5;
	public int birthDay =20;
	public int birthMonth=10;
	public int birthYear=2000;
	public double coachSkating = 0.5;
	public double coachShooting = 0.5;
	public double coachChecking = 0.5;
	public double coachSaving = 0.5;
	public double playerStrength = 0;
	public double teamStrength = 0;
	public int averageRetirementAge = 35;
	public int maximumAge = 60;
	public double statDecayChance=0.05;
	public double randownWinChance = 0.1;
	public double randomInjuryChance = 0.05;
	public int injuryDaysLow = 1;
	public int injuryDaysHigh = 120;
	public int daysUntilTraining = 100;
	public int lossPoint = 8;
	public double randomTradeOffer = 0.05;
	public double randomAcceptanceChance = 0.05;
	public int maxPlayerPerTrade = 2;
	public LeagueModel league;
	public String playerOneName = "";
	public String positionOne = "";
	public String positionForward = "forward";
	public String freeAgentOne = "freeAgentOne";
	public String positionDefense = "defense";
	public String positionGoalie = "goalie";
	public Boolean captainOne = true;
	public String playerTwoName = "";
	public String positionTwo = "";
	public Boolean captainTwo = false;
	public String result = "success";
	
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
		coachListTwo = new ArrayList<CoachModel>();
		managerList = new ArrayList<String>();
		managerListTwo = new ArrayList<String>();
		conferenceList = new ArrayList<ConferenceModel>();
		retiredFreeAgents = new ArrayList<IFreeAgent>();
		retiredTeamPlayers = new ArrayList<PlayerModel>();
		leagueList = new ArrayList<HashMap<String,Object>>();
		leagueMap = new HashMap<String,Object>();
		league = new LeagueModel();
		playerOneName =  "Cristiano Ronaldo";
		positionOne = "forward";
		captainOne = true;
		playerList.add(new PlayerModel(playerOneName,positionOne,captainOne,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		playerTwoName= "Messi";
		positionTwo =  "goalie";
		captainTwo = false;
		coachDetails = new CoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving);
		playerList.add(new PlayerModel(playerTwoName,positionTwo,captainTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		retiredTeamPlayers.add(new PlayerModel(playerTwoName,positionTwo,captainTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		teamList.add(new TeamModel(teamName,coachDetails,generalManagerName,playerList));
		teamList.add(new TeamModel(teamTwoName,coachDetails,generalManagerTwoName,playerList));
		divisionList.add(new DivisionModel(divisionOneName,teamList));
		divisionList.add(new DivisionModel(divisionTwoName,teamList));
		freeAgentList.add(new FreeAgentModel(playerOneName,positionOne,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel(playerTwoName,positionTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("SuperMan",positionOne,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Pit Bull","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("SpiderMan","goalie",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Sachin Tendulkar","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Virat Kohli","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Rondahino","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("James Cameron","goalie",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Silambarasan","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Yuvan Raj","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Great Khali","goalie",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Caper Carloon","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Dwayane Johnson","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("John Cena","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Triple HHH","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Vin Diesel","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Robert Junior","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Mingrann Bose","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Brad Pit","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Kajal Agarwal","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Krithick Roshan","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Salman Butt","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Rajni Kanth","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Karthi Sivakumar","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Keerthi Suresh","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Vijay Joseph","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Maadu Ravi","forward",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		freeAgentList.add(new FreeAgentModel("Kajol","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		retiredFreeAgents.add(new FreeAgentModel("Kajol","defense",skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		conferenceList.add(new ConferenceModel(conferenceName,divisionList));
		conferenceList.add(new ConferenceModel(conferenceTwoName,divisionList));
		coachList.add(new CoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving));
		coachListTwo.add(new CoachModel(headCoachName,coachSkating,2.2,coachChecking, coachSaving));
		managerList.add(generalManagerName);
		managerList.add(generalManagerName+"Two");
		managerList.add(generalManagerName+"Three");
		managerListTwo.add("");
		managerListTwo.add("");
		league.setLeagueName(leagueName);
		league.setConferenceDetails(conferenceList);
		league.setFreeAgent(freeAgentList);
		league.setFreeCoach(coachList);
		league.setManagerList(managerList);
		leagueMap.put("league_name","HockeyLeague");
		leagueList.add(leagueMap);
		leagueMap.put("league_name","CanadaLeague");
		leagueList.add(leagueMap);
		tradeConfig = new TradingModel(lossPoint, randomTradeOffer, maxPlayerPerTrade, randomAcceptanceChance);
		training = new TrainingConfig(daysUntilTraining);
		gameResolver = new GameResolverConfig(randownWinChance);
		injury = new Injury(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		aging = new Aging(averageRetirementAge, maximumAge,statDecayChance);
		gamePlayConfig = new GamePlayConfigModel(tradeConfig, aging, injury, gameResolver, training);
		league.setGamePlayConfig(gamePlayConfig);
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
			playerList.add(new PlayerModel(playerTwoName,positionTwo,captainTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
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
	
	public void setFreeAgentListNotValid() {
		int size = freeAgentList.size();
		for(int i = size-1; i>=20;i--) {
			freeAgentList.remove(i);
		}
		
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
			if(player.getInjuredStatus()){
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
		playerModelObject.setSkating(skating);
		playerModelObject.setShooting(shooting);
		playerModelObject.setChecking(checking);
		playerModelObject.setSaving(saving);
		playerModelObject.setBirthDay(birthDay);
		playerModelObject.setBirthMonth(birthMonth);
		playerModelObject.setBirthYear(birthYear);
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
		leagueModelObject.setGamePlayConfig(gamePlayConfig);
		leagueModelObject.setDbObject(new LeaguePersistenceMock());
		leagueModelObject.setManagerList(managerList);
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
		freeAgentObject.setSkating(skating);
		freeAgentObject.setShooting(shooting);
		freeAgentObject.setChecking(checking);
		freeAgentObject.setSaving(saving);
		freeAgentObject.setBirthDay(birthDay);
		freeAgentObject.setBirthMonth(birthMonth);
		freeAgentObject.setBirthYear(birthYear);
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
