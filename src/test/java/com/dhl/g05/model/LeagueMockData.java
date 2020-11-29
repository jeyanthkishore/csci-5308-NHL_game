package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.*;

public class LeagueMockData implements ILeagueModel,IConferenceModel,IDivisionModel,ITeamModel,IPlayerModel,IFreeAgentModel{

	Random randomNumber = new Random();
	public String leagueName = "HockeyLeague";
	public Map<String,Object> firstPlayerInfo;
	public Map<String,Object> secondPlayerInfo;
	public List<IPlayer> playerList;
	public List<ITeam> teamList;
	public List<IDivision> divisionList;
	public List<IFreeAgent> freeAgentList;
	public List<IConference> conferenceList;
	public List<ICoach> coachList;
	public List<ICoach> coachListTwo;
	public List<String> managerList;
	public List<IFreeAgent> retiredFreeAgentsList;
	public List<IPlayer> retiredPlayersList;
	public List<String> managerListTwo;
	public ICoach coachDetails;
	public ArrayList<HashMap<String,Object>> leagueList;
	public HashMap<String,Object> leagueMap;
	public IGamePlayConfig gamePlayConfig;
	public ITradingConfig tradeConfig;
	public ITraining training;
	public IGameResolver gameResolver;
	public IInjury injury;
	public IAging aging;
	public String teamName = "Striker Six";
	public String teamTwoName = "Thunder Rockers";
	public String generalManagerName = "Zidanie Zidane";
	public String generalManagerTwoName = "Sachin Tendulkar";
	public String headCoachName = "Diego Marradona";
	public String headCoachTwoName = "Rahul Dravid";
	public String divisionOneName = "Atlantic";
	public String divisionTwoName = "Pacific";
	public String conferenceName = "Western Conference";
	public String conferenceTwoName = "Eastern Conference";
	public ITeam team;
	public IDivision division;
	public IConference conference;
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
	public double randownWinChance = 0.0;
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
	public String position = "";
	public String positionForward = "forward";
	public String freeAgentOne = "freeAgentOne";
	public String positionDefense = "defense";
	public String positionGoalie = "goalie";
	public Boolean captainOne = true;
	public String playerTwoName = "";
	public String positionTwo = "";
	public Boolean captainTwo = false;
	public String result = "success";
	private static ModelAbstractFactory modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	private static SimulationAbstractFactory simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	public LeagueModel getLeague() {
		return league;
	}

	public void setLeague(LeagueModel league) {
		this.league = league;
	}

	public  LeagueMockData () {
		setJsonValues();
	}
	
	private void setJsonValues() {
		divisionList = new ArrayList<>();
		playerList = new ArrayList<>();
		teamList = new ArrayList<>();
		freeAgentList = new ArrayList<>();
		coachList = new ArrayList<>();
		coachListTwo = new ArrayList<>();
		managerList = new ArrayList<>();
		managerListTwo = new ArrayList<>();
		conferenceList = new ArrayList<>();
		retiredFreeAgentsList = new ArrayList<>();
		retiredPlayersList = new ArrayList<>();
		leagueList = new ArrayList<>();
		leagueMap = new HashMap<>();
		league = new LeagueModel();
		playerOneName =  "Cristiano Ronaldo";
		positionOne = "forward";
		captainOne = true;
		playerList.add(modelAbstractFactory.createPlayerModel(playerOneName,positionOne,captainOne,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		playerTwoName= "Messi";
		positionTwo =  "goalie";
		captainTwo = false;
		coachDetails = modelAbstractFactory.createCoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving);
		playerList.add(modelAbstractFactory.createPlayerModel(playerTwoName,positionTwo,captainTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		retiredPlayersList.add(modelAbstractFactory.createPlayerModel(playerTwoName,positionTwo,captainTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
		team = new TeamModel();
		team.setTeamName(teamName);
		team.setCoachDetails(coachDetails);
		team.setGeneralManagerName(generalManagerName);
		team.setPlayerList(playerList);
		teamList.add(team);
		team = new TeamModel();
		team.setTeamName(teamTwoName);
		team.setCoachDetails(coachDetails);
		team.setGeneralManagerName(generalManagerTwoName);
		team.setPlayerList(playerList);
		teamList.add(team);
		division = new DivisionModel();
		division.setDivisionName(divisionOneName);
		division.setTeamDetails(teamList);
		divisionList.add(division);
		division = new DivisionModel();
		division.setDivisionName(divisionTwoName);
		division.setTeamDetails(teamList);
		divisionList.add(division);
		String[] playerNames = {playerOneName,playerTwoName,"SuperMan","SpiderMan","Pit Bull",
		"Goamer","Gamora","Roaster","Brock","Compaien","Taphael","Heinserberg","TossTaylor","Far Cry","Devil","Mike"
		,"Sachin Tendulkar","Virat Kohli","Rondahino","James Cameron","Silambarasan","Yuvan Raj","Great Khali"
		,"Caper Carloon","Dwayane Johnson","John Cena","Triple HHH","Vin Diesel","Robert Junior","Mingrann Bose"
		,"Brad Pit","Kajal Agarwal","Krithick Roshan","Salman Butt","Rajni Kanth","Karthi Sivakumar","Keerthi Suresh"
		,"Vijay Joseph","Maadu Ravi","Kajol"};
		String[] positionValue = {"forward","defense","goalie"};
		int i=3;
		for(int player =0;player<playerNames.length;player++) {
			IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
			freeAgent.setPlayerName(playerNames[player]);
			freeAgent.setPosition(positionValue[ (player) % i]);
			freeAgent.setChecking(checking);
			freeAgent.setSaving(saving);
			freeAgent.setShooting(shooting);
			freeAgent.setSkating(skating);
			freeAgent.setBirthDay(birthDay);
			freeAgent.setBirthMonth(birthMonth);
			freeAgent.setBirthYear(birthYear);
			if(player==15) {
				i--;
			}
			freeAgentList.add(freeAgent);
			retiredFreeAgentsList.add(freeAgent);
		}
		conference = new ConferenceModel();
		conference.setConferenceName(conferenceName);
		conference.setDivisionDetails(divisionList);
		conferenceList.add(conference);
		conference = new ConferenceModel();
		conference.setConferenceName(conferenceTwoName);
		conference.setDivisionDetails(divisionList);
		conferenceList.add(conference);
		coachList.add(modelAbstractFactory.createCoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving));
		coachListTwo.add(modelAbstractFactory.createCoachModel(headCoachName,coachSkating,coachShooting,coachChecking, coachSaving));
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
		league.setRetiredFreeAgentsList(retiredFreeAgentsList);
		league.setRetiredPlayersList(retiredPlayersList);
		leagueMap.put("league_name","HockeyLeague");
		leagueList.add(leagueMap);
		leagueMap.put("league_name","CanadaLeague");
		leagueList.add(leagueMap);
		tradeConfig = simulationAbstractFactory.createTradingConfig();
		tradeConfig.setLossPoint(lossPoint);
		tradeConfig.setMaxPlayersPerTrade(maxPlayerPerTrade);
		tradeConfig.setRandomTradeOfferChance(randomTradeOffer);
		tradeConfig.setRandomAcceptanceChance(randomAcceptanceChance);
		training = simulationAbstractFactory.createTrainingConfig();
		training.setDaysUntilStatIncreaseCheck(daysUntilTraining);
		gameResolver = simulationAbstractFactory.createGameResolverConfig();
		gameResolver.setRandomWinChance(randownWinChance);
		injury = simulationAbstractFactory.createInjuryConfig();
		injury.setInjuryDaysHigh(injuryDaysHigh);
		injury.setInjuryDaysLow(injuryDaysLow);
		injury.setRandomInjuryChance(randomInjuryChance);
		aging = simulationAbstractFactory.createAgingConfig();
		aging.setAverageRetirementAge(averageRetirementAge);
		aging.setMaximumAge(maximumAge);
		aging.setStatDecayChance(statDecayChance);
		gamePlayConfig = simulationAbstractFactory.createGamePlayConfig();
		gamePlayConfig.setAgingConfig(aging);
		gamePlayConfig.setGameResolverConfig(gameResolver);
		gamePlayConfig.setInjuriesConfig(injury);
		gamePlayConfig.setTradingConfig(tradeConfig);
		gamePlayConfig.setTrainingConfig(training);
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

	public void setSkating(double skating) {
		this.skating = skating;
	}

	public void setShooting(double shooting) {
		this.shooting = shooting;
	}

	public void setChecking(double checking) {
		this.checking = checking;
	}

	public void setSaving(double saving) {
		this.saving = saving;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void addMaximumPlayer() {
		for(int count = 0; count<42; count++) {
			playerList.add(modelAbstractFactory.createPlayerModel(playerTwoName,positionTwo,captainTwo,skating,shooting,checking,saving,birthDay,birthMonth,birthYear));
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

	public double calculateTeamStrength(List<IPlayer> playerList){
		for (IPlayer player: playerList) {
			if(player.getInjuryStatus()){
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
	public void LoadDivisionModelData(IDivision divisionModelObject) {
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
		leagueModelObject.setManagerList(managerList);
		leagueModelObject.setRetiredFreeAgentsList(retiredFreeAgentsList);
		leagueModelObject.setRetiredPlayersList(retiredPlayersList);
	}

	@Override
	public void loadConferenceModelData(IConference conferenceModelObject) {
		conferenceModelObject.setConferenceName(conferenceName);
		conferenceModelObject.setDivisionDetails(divisionList);
	}

	@Override
	public void loadPlayerModelData(IFreeAgent freeAgentObject) {
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
}
