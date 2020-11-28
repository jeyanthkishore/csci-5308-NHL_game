package com.dhl.g05.database;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.gameplayconfig.AgingConfig;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.GameResolverConfig;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.gameplayconfig.IGamePlayConfig;
import com.dhl.g05.gameplayconfig.IGameResolver;
import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.gameplayconfig.InjuryConfig;
import com.dhl.g05.gameplayconfig.TradingConfig;
import com.dhl.g05.gameplayconfig.TrainingConfig;
import com.dhl.g05.model.CoachModel;
import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueModel;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.TeamModel;
import com.dhl.g05.statemachine.ILeagueSchedule;
import com.dhl.g05.statemachine.ILeagueStanding;
import com.dhl.g05.statemachine.IScheduleModel;
import com.dhl.g05.statemachine.IStandingModel;
import com.dhl.g05.statemachine.LeagueSchedule;
import com.dhl.g05.statemachine.LeagueStanding;
import com.dhl.g05.statemachine.ScheduleModel;
import com.dhl.g05.statemachine.StandingModel;

public class DeserializeLeagueModel implements IDeserializeModel {

	@Override
	public ILeague deserializeObjects(String name, ILeague leagueModel) {
		FileReader reader = null;
		DatabaseAbstractFactory databaseFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		IFileOperation file = databaseFactory.createFileOperation();
		String path = file.getFilePath(name);
		try {
			JSONParser parser = new JSONParser();
			reader = new FileReader(path);
			leagueModel = deserialzieFileData((JSONObject)parser.parse(reader));
			reader.close();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		return leagueModel;
	}

	private IAging createAging(JSONObject jsonAging) {
		IAging agingConfig = new AgingConfig();
		agingConfig.setAverageRetirementAge(((Number) jsonAging.get("averageRetirementAge")).intValue());
		agingConfig.setMaximumAge(((Number) jsonAging.get("maximumAge")).intValue());
		agingConfig.setStatDecayChance(((Number) jsonAging.get("statDecayChance")).doubleValue());
		return agingConfig;
	}

	private InjuryConfig createInjury(JSONObject jsonInjury) {
		InjuryConfig injuryConfig = new InjuryConfig();
		injuryConfig.setRandomInjuryChance(((Number) jsonInjury.get("randomInjuryChance")).doubleValue());
		injuryConfig.setInjuryDaysHigh(((Number) jsonInjury.get("injuryDaysHigh")).intValue());
		injuryConfig.setInjuryDaysLow(((Number) jsonInjury.get("injuryDaysLow")).intValue());
		return injuryConfig;
	}

	private TrainingConfig createTrainingConfig(JSONObject training) {
		TrainingConfig trainConfig = new TrainingConfig();
		trainConfig.setDaysUntilStatIncreaseCheck(((Number) training.get("daysUntilStatIncreaseCheck")).intValue());
		return trainConfig;
	}

	private ILeague deserialzieFileData(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ILeague league = new LeagueModel();
		league.setLeagueName((String)leagueData.get("leagueName"));
		league.setConferenceDetails(createConferences((JSONArray)leagueData.get("conferences")));
		league.setFreeAgent(createFreeAgents((JSONArray)leagueData.get("freeAgents")));
		league.setFreeCoach(createFreeCoaches((JSONArray)leagueData.get("coaches")));
		league.setManagerList(createFreeManagers((JSONArray)leagueData.get("generalManagers")));
		league.setGamePlayConfig(setGamePlayConfigsFromFile((JSONObject) leagueData.get("gameplayConfig")));
		league.setLeagueSchedule(createLeagueSchedule((JSONObject)leagueData.get("leagueSchedule")));
		league.setLeagueStanding(createLeagueStandings((JSONObject)leagueData.get("leagueStanding")));
		return league;
	}

	@SuppressWarnings("unchecked")
	private ILeagueStanding createLeagueStandings(JSONObject jsonStanding) {

		ArrayList<IStandingModel> leagueStandingList = new ArrayList<>();
		JSONArray standingList = (JSONArray)jsonStanding.get("standingsList");
		for(Object standing : standingList) {
			IStandingModel newStanding = new StandingModel();
			JSONArray arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject) ((JSONObject) standing).get("conference"));
			newStanding.setConference(createConferences((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject) ((JSONObject) standing).get("division"));
			newStanding.setDivision(createDivisions((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject) ((JSONObject) standing).get("team"));
			newStanding.setTeam(createTeams((JSONArray)arrayFormation).get(0));
			newStanding.setTotalGamesLost(((Number) ((JSONObject) standing).get("numberOfLoss")).intValue());
			newStanding.setTotalGamesPlayed(((Number) ((JSONObject) standing).get("totalGamesPlayed")).intValue());
			newStanding.setTotalGamesWon(((Number) ((JSONObject) standing).get("numberOfWins")).intValue());
			newStanding.setTotalPoints(((Number) ((JSONObject) standing).get("totalPoints")).intValue());
			leagueStandingList.add(newStanding);
		}

		ILeagueStanding newLeagueStanding = new LeagueStanding();
		newLeagueStanding.setStandingList(leagueStandingList);
		return newLeagueStanding;
	}

	private ILeagueSchedule createLeagueSchedule(JSONObject jsonObject) {
		ArrayList<IScheduleModel> regularSchedule = new ArrayList<>();
		ArrayList<IScheduleModel> playoffSchedule = new ArrayList<>();
		ILeagueSchedule schedule = new LeagueSchedule();
		regularSchedule = generateSchedule((JSONArray) (jsonObject).get("regularSeasonSchedule"));
		schedule.setRegularSeasonSchedule(regularSchedule);
		playoffSchedule = generateSchedule((JSONArray) (jsonObject).get("playOffSeasonSchedule"));
		schedule.setRegularSeasonSchedule(playoffSchedule);
		return schedule;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<IScheduleModel> generateSchedule(JSONArray fullSchedule) {
		if(fullSchedule == null) {
			return null;
		}
		ArrayList<IScheduleModel> scheduleList = new ArrayList<>();
		for(Object schedule : fullSchedule) {
			JSONArray arrayFormation = new JSONArray();
			IScheduleModel currentschedule = new ScheduleModel();
			arrayFormation.add((JSONObject) ((JSONObject) schedule).get("firstConference"));
			currentschedule.setFirstConference(createConferences((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get("firstDivision"));
			currentschedule.setFirstDivision(createDivisions((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get("firstTeam"));
			currentschedule.setFirstTeam(createTeams((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get("secondConference"));
			currentschedule.setSecondConference(createConferences((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get("secondDivision"));
			currentschedule.setSecondDivision(createDivisions((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get("secondTeam"));
			currentschedule.setSecondTeam(createTeams((JSONArray)arrayFormation).get(0));
			scheduleList.add(currentschedule);
		}
		return scheduleList;
	}

	private ArrayList<IConference> createConferences(JSONArray jsonConferences) {
		ArrayList<IConference> conferences = new ArrayList<>();
		for (Object c: jsonConferences) {
			ArrayList<IDivision> divisions = createDivisions((JSONArray)((JSONObject) c).get("divisions"));
			IConference newConference = new ConferenceModel();
			newConference.setConferenceName((String)((JSONObject) c).get("conferenceName"));
			newConference.setDivisionDetails(divisions);
			conferences.add(newConference);
		}
		return conferences;
	}

	public IGamePlayConfig setGamePlayConfigsFromFile(JSONObject gamePlayConfigs){
		IGamePlayConfig gamePlayconfig = new GamePlayConfigModel();
		gamePlayconfig.setInjuries(createInjury((JSONObject)gamePlayConfigs.get("injuries")));
		gamePlayconfig.setAging(createAging((JSONObject)gamePlayConfigs.get("aging")));
		gamePlayconfig.setGameResolver(createGameResolver((JSONObject)gamePlayConfigs.get("gameResolver")));
		gamePlayconfig.setTrading(createTradingConfig((JSONObject)gamePlayConfigs.get("trading")));
		gamePlayconfig.setTraining(createTrainingConfig((JSONObject)gamePlayConfigs.get("training")));

		return gamePlayconfig;
	}

	private IGameResolver createGameResolver(JSONObject gameResolver) {
		IGameResolver resolverConfig = new GameResolverConfig();
		resolverConfig.setRandomWinChance(((Number) gameResolver.get("randomWinChance")).doubleValue());
		return resolverConfig;
	}

	private ITradingConfig createTradingConfig(JSONObject tradingObject) {
		ITradingConfig tradeConfig =  new TradingConfig();
		tradeConfig.setLossPoint(((Number) tradingObject.get("lossPoint")).intValue());
		tradeConfig.setMaxPlayersPerTrade(((Number) tradingObject.get("maxPlayersPerTrade")).intValue());
		tradeConfig.setRandomAcceptanceChance(((Double) tradingObject.get("randomAcceptanceChance")).doubleValue());
		tradeConfig.setRandomTradeOfferChance((Double)((JSONObject) tradingObject).get("randomTradeOfferChance"));
		return tradeConfig;
	}

	private ArrayList<IDivision> createDivisions(JSONArray jsonDivisions) {

		ArrayList<IDivision> divisions = new ArrayList<>();
		for (Object d: jsonDivisions) {
			ArrayList<ITeam> teams = createTeams((JSONArray)((JSONObject) d).get("teams"));
			IDivision newDivision = new DivisionModel();
			newDivision.setDivisionName((String)((JSONObject) d).get("divisionName"));
			newDivision.setTeamDetails(teams);
			divisions.add(newDivision);
		}
		return divisions;
	}

	private ArrayList<ITeam> createTeams(JSONArray jsonTeams) {
		ArrayList<ITeam> teams = new ArrayList<>();
		for (Object t: jsonTeams) {
			ArrayList<IPlayer> players = createPlayers((JSONArray)((JSONObject) t).get("players"));
			ITeam newTeam = new TeamModel();
			newTeam.setTeamName((String)((JSONObject) t).get("teamName"));
			newTeam.setGeneralManagerName((String)((JSONObject) t).get("generalManager"));
			newTeam.setCoachDetails(createCoach((JSONObject) ((JSONObject) t).get("headCoach")));
			newTeam.setPlayerList(players);
			teams.add(newTeam);
		}
		return teams;
	}

	private ArrayList<IPlayer> createPlayers(JSONArray jsonPlayers) {
		ArrayList<IPlayer> players = new ArrayList<>();
		for (Object player: jsonPlayers) {
			IPlayer newPlayer = new PlayerModel();
			newPlayer.setBirthDay(((Number) ((JSONObject) player).get("birthDay")).intValue());
			newPlayer.setBirthMonth(((Number) ((JSONObject) player).get("birthMonth")).intValue());
			newPlayer.setBirthYear(((Number) ((JSONObject) player).get("birthYear")).intValue());
			newPlayer.setSkating(((Number) ((JSONObject) player).get("skating")).doubleValue());
			newPlayer.setShooting(((Number) ((JSONObject) player).get("shooting")).doubleValue());
			newPlayer.setChecking(((Number) ((JSONObject) player).get("checking")).doubleValue());
			newPlayer.setSaving(((Number) ((JSONObject) player).get("saving")).doubleValue());
			newPlayer.setPlayerName((String)((JSONObject) player).get("playerName"));
			newPlayer.setPosition((String)((JSONObject) player).get("position"));
			newPlayer.setAge(((Number) ((JSONObject) player).get("saving")).intValue());
			newPlayer.setPlayerStrength(((Number) ((JSONObject) player).get("playerStrength")).doubleValue());
			newPlayer.setCaptain((Boolean)((JSONObject) player).get("position"));
			newPlayer.setInjuryStatus((Boolean)((JSONObject) player).get("isInjured"));
			newPlayer.setRetirementStatus((Boolean)((JSONObject) player).get("isRetired"));
			players.add(newPlayer);
		}
		return players;
	}

	private ArrayList<IFreeAgent> createFreeAgents(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<IFreeAgent> freeAgent = new ArrayList<>();
		for (Object player: jsonPlayers) {
			FreeAgentModel newPlayer = new FreeAgentModel();
			newPlayer.setBirthDay(((Number) ((JSONObject) player).get("birthDay")).intValue());
			newPlayer.setBirthMonth(((Number) ((JSONObject) player).get("birthMonth")).intValue());
			newPlayer.setBirthYear(((Number) ((JSONObject) player).get("birthYear")).intValue());
			newPlayer.setSkating(((Number) ((JSONObject) player).get("skating")).doubleValue());
			newPlayer.setShooting(((Number) ((JSONObject) player).get("shooting")).doubleValue());
			newPlayer.setChecking(((Number) ((JSONObject) player).get("checking")).doubleValue());
			newPlayer.setSaving(((Number) ((JSONObject) player).get("saving")).doubleValue());
			newPlayer.setPlayerName((String)((JSONObject) player).get("playerName"));
			newPlayer.setPosition((String)((JSONObject) player).get("position"));
			newPlayer.setAge(((Number) ((JSONObject) player).get("saving")).intValue());
			newPlayer.setPlayerStrength(((Number) ((JSONObject) player).get("playerStrength")).doubleValue());
			newPlayer.setInjuryStatus((Boolean)((JSONObject) player).get("isInjured"));
			newPlayer.setRetirementStatus((Boolean)((JSONObject) player).get("isRetired"));
			freeAgent.add(newPlayer);
		}
		return freeAgent;
	}

	private ICoach createCoach(JSONObject jsonCoachDetails) {
		ICoach newCoach = new CoachModel();
		newCoach.setName((String) jsonCoachDetails.get("name"));
		newCoach.setSkating(((Number) ((JSONObject) jsonCoachDetails).get("skating")).doubleValue());
		newCoach.setShooting(((Number) ((JSONObject) jsonCoachDetails).get("shooting")).doubleValue());
		newCoach.setChecking(((Number) ((JSONObject) jsonCoachDetails).get("checking")).doubleValue());
		newCoach.setSaving(((Number) ((JSONObject) jsonCoachDetails).get("saving")).doubleValue());
		return newCoach;
	}

	private ArrayList<ICoach> createFreeCoaches(JSONArray jsonCoaches) {
		ArrayList<ICoach> coaches = new ArrayList<>();
		for (Object coach: jsonCoaches) {
			ICoach newCoach = createCoach((JSONObject) coach);
			coaches.add(newCoach);
		}
		return coaches;
	}

	private ArrayList<String> createFreeManagers(JSONArray jsonManagers) {
		ArrayList<String> managers = new ArrayList<>();
		for (int i=0; i<jsonManagers.size(); i++){
			String name = (String) jsonManagers.get(i);
			managers.add(name);
		}
		return managers;
	}

}
