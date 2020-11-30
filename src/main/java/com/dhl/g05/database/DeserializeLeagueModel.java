package com.dhl.g05.database;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.ILeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.ILeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;
import com.dhl.g05.simulation.leaguesimulation.LeagueSchedule;
import com.dhl.g05.simulation.leaguesimulation.LeagueStanding;
import com.dhl.g05.simulation.leaguesimulation.ScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.StandingModel;
import com.dhl.g05.simulation.statemachine.GamePlayConfigModel;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.dhl.g05.simulation.statemachine.IGameResolver;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.ITradingConfig;
import com.dhl.g05.simulation.statemachine.ITraining;

public class DeserializeLeagueModel implements IDeserializeModel {
	private static ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	private static SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	
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
		IAging agingConfig = simulationFactory.createAgingConfig();
		agingConfig.setAverageRetirementAge(((Number) jsonAging.get("averageRetirementAge")).intValue());
		agingConfig.setMaximumAge(((Number) jsonAging.get("maximumAge")).intValue());
		agingConfig.setStatDecayChance(((Number) jsonAging.get("statDecayChance")).doubleValue());
		return agingConfig;
	}

	private IInjury createInjury(JSONObject jsonInjury) {
		IInjury injuryConfig = simulationFactory.createInjuryConfig();
		injuryConfig.setRandomInjuryChance(((Number) jsonInjury.get("randomInjuryChance")).doubleValue());
		injuryConfig.setInjuryDaysHigh(((Number) jsonInjury.get("injuryDaysHigh")).intValue());
		injuryConfig.setInjuryDaysLow(((Number) jsonInjury.get("injuryDaysLow")).intValue());
		return injuryConfig;
	}

	private ITraining createTrainingConfig(JSONObject training) {
		ITraining trainConfig = simulationFactory.createTrainingConfig();
		trainConfig.setDaysUntilStatIncreaseCheck(((Number) training.get("daysUntilStatIncreaseCheck")).intValue());
		return trainConfig;
	}

	private ILeague deserialzieFileData(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ILeague league = modelFactory.createLeagueModel();
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
			IConference newConference = modelFactory.createConferenceModel();
			newConference.setConferenceName((String)((JSONObject) c).get("conferenceName"));
			newConference.setDivisionDetails(divisions);
			conferences.add(newConference);
		}
		return conferences;
	}

	public IGamePlayConfig setGamePlayConfigsFromFile(JSONObject gamePlayConfigs){
		IGamePlayConfig gamePlayconfig = new GamePlayConfigModel();
		gamePlayconfig.setInjuriesConfig(createInjury((JSONObject)gamePlayConfigs.get("injuries")));
		gamePlayconfig.setAgingConfig(createAging((JSONObject)gamePlayConfigs.get("aging")));
		gamePlayconfig.setGameResolverConfig(createGameResolver((JSONObject)gamePlayConfigs.get("gameResolver")));
		gamePlayconfig.setTradingConfig(createTradingConfig((JSONObject)gamePlayConfigs.get("trading")));
		gamePlayconfig.setTrainingConfig(createTrainingConfig((JSONObject)gamePlayConfigs.get("training")));

		return gamePlayconfig;
	}

	private IGameResolver createGameResolver(JSONObject gameResolver) {
		IGameResolver resolverConfig = simulationFactory.createGameResolverConfig();
		resolverConfig.setRandomWinChance(((Number) gameResolver.get("randomWinChance")).doubleValue());
		return resolverConfig;
	}

	private ITradingConfig createTradingConfig(JSONObject tradingObject) {
		ITradingConfig tradeConfig =  simulationFactory.createTradingConfig();
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
			IDivision newDivision = modelFactory.createDivisionModel();
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
			ITeam newTeam = modelFactory.createTeamModel();
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
			IPlayer newPlayer = modelFactory.createPlayerModel();
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
			newPlayer.setCaptain((Boolean)((JSONObject) player).get("captain"));
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
			IFreeAgent newPlayer = modelFactory.createFreeAgentModel();
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
		ICoach newCoach = modelFactory.createCoachModel();
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
