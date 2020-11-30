package com.dhl.g05.simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.model.CoachConstant;
import com.dhl.g05.model.ConferenceConstant;
import com.dhl.g05.model.DivisionConstant;
import com.dhl.g05.model.FreeAgentConstant;
import com.dhl.g05.model.ICoach;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueConstant;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.TeamConstant;
import com.dhl.g05.simulation.statemachine.AgingConstant;
import com.dhl.g05.simulation.statemachine.GamePlayConfigConstant;
import com.dhl.g05.simulation.statemachine.GameResolverConstant;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.dhl.g05.simulation.statemachine.IGameResolver;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.ITradingConfig;
import com.dhl.g05.simulation.statemachine.ITraining;
import com.dhl.g05.simulation.statemachine.InjuryConstant;
import com.dhl.g05.simulation.statemachine.TradingConstant;
import com.dhl.g05.simulation.statemachine.TrainingConstant;
import com.mysql.cj.util.StringUtils;

public class LeagueModelCreatorFromJSON implements ILeagueCreator{
	static final Logger logger = LogManager.getLogger(LeagueModelCreatorFromJSON.class);
	private static final String  LEAGUE_NAME = "leagueName";
	private static final String CONFERENCES = "conferences";
	private static final String FREE_AGENTS = "freeAgents";
	private static final String COACHES = "coaches";
	private static final String GENERAL_MANAGERS = "generalManagers";
	private static final String GAMEPLAY_CONFIG = "gameplayConfig";
	private static final String INJURIES= "injuries";
	private static final String AGING= "aging";
	private static final String GAME_RESOLVER= "gameResolver";
	private static final String TRADING = "trading";
	private static final String TRAINING = "training";
	private static final String AVERAGE_RETIREMENTAGE = "averageRetirementAge";
	private static final String MAXIMUM_AGE= "maximumAge";
	private static final String STAT_DECAY_CHANCE= "statDecayChance";
	private static final String RANDOM_INJURY_CHANCE = "randomInjuryChance";
	private static final String INJURY_DAYS_HIGH = "injuryDaysHigh";
	private static final String INJURY_DAYS_LOW = "injuryDaysLow";
	private static final String DAYS_UNTIL_STAT_INCREASE_CHECK = "daysUntilStatIncreaseCheck";
	private static final String RANDOM_WIN_CHANCE = "randomWinChance";
	private static final String LOSS_POINT = "lossPoint";
	private static final String MAX_PLAYERS_PER_TRADE = "maxPlayersPerTrade";
	private static final String RANDOM_ACCEPTANCE_CHANCE = "randomAcceptanceChance";
	private static final String  RANDOM_TRADE_OFFER_CHANCE= "randomTradeOfferChance";
	private static final String  DIVISIONS = "divisions";
	private static final String  CONFERENCE_NAME = "conferenceName";
	private static final String  TEAMS= "teams";
	private static final String  DIVISION_NAME = "divisionName";
	private static final String PLAYERS = "players";
	private static final String  TEAM_NAME= "teamName";
	private static final String  GENERAL_MANAGER = "generalManager";
	private static final String  HEAD_COACH = "headCoach";
	private static final String PLAYER_NAME = "playerName";
	private static final String POSITION = "position";
	private static final String CAPTAIN = "captain";
	private static final String SKATING = "skating";
	private static final String SHOOTING = "shooting";
	private static final String CHECKING = "checking";
	private static final String SAVING = "saving";
	private static final String BIRTH_DAY = "birthDay";
	private static final String BIRTH_MONTH = "birthMonth";
	private static final String BIRTH_YEAR = "birthYear";
	private static final String NAME = "name";

	private FileReader reader;
	private JSONParser parser;
	private IPlayerCommunication playerCommunication;
	private static ModelAbstractFactory modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	private static SimulationAbstractFactory simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	public LeagueModelCreatorFromJSON(IPlayerCommunication playerCommunication) {
		parser = new JSONParser();
		this.playerCommunication = playerCommunication;
	}

	public ILeague createLeagueFromFile(String fileName) {
		logger.info("Creating League for the import file");
		
		ILeague league = null;
		try {
			File file = new File(fileName);
			reader = new FileReader(file);
			league = createLeague((JSONObject)parser.parse(reader));
			reader.close();
		} catch (FileNotFoundException e) {
			playerCommunication.sendMessage(LeagueCreatorConstant.FileNotFound.getValue());
		} catch (IOException e) {
			playerCommunication.sendMessage(e.toString());
		} catch (ParseException e) {
			playerCommunication.sendMessage(e.toString());
		}
		return league;
	}

	private ILeague createLeague(JSONObject leagueData) {
		logger.info("Creating League Objects");
		
		if (leagueData == null) {
			return null;
		}
		
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setLeagueName((String)leagueData.get(LEAGUE_NAME));
		league.setConferenceDetails(createConferences((JSONArray)leagueData.get(CONFERENCES)));
		league.setFreeAgent(createFreeAgents((JSONArray)leagueData.get(FREE_AGENTS)));
		league.setFreeCoach(createFreeCoaches((JSONArray)leagueData.get(COACHES)));
		league.setManagerList(createFreeManagers((JSONArray)leagueData.get(GENERAL_MANAGERS)));
		league.setGamePlayConfig(setGamePlayConfigsFromFile((JSONObject) leagueData.get(GAMEPLAY_CONFIG)));
		LeagueConstant validationResult  =  league.validate();
		if (validationResult.equals(LeagueConstant.Success)) {
			return league;
		} else {
			logger.info(validationResult.getValue());
			playerCommunication.sendMessage(validationResult.getValue());
		}

		return null;
	}

	public IGamePlayConfig setGamePlayConfigsFromFile(JSONObject gamePlayConfigs) {
		logger.info("Creating GamePlay Configuration Objects");
		
		if (gamePlayConfigs == null) {
			return null;
		}
		
		try {
			IGamePlayConfig gamePlayconfig = simulationAbstractFactory.createGamePlayConfig();
			gamePlayconfig.setInjuriesConfig(createInjury((JSONObject)gamePlayConfigs.get(INJURIES)));
			gamePlayconfig.setAgingConfig(createAging((JSONObject)gamePlayConfigs.get(AGING)));
			gamePlayconfig.setGameResolverConfig(createGameResolver((JSONObject)gamePlayConfigs.get(GAME_RESOLVER)));
			gamePlayconfig.setTradingConfig(createTradingConfig((JSONObject)gamePlayConfigs.get(TRADING)));
			gamePlayconfig.setTrainingConfig(createTrainingConfig((JSONObject)gamePlayConfigs.get(TRAINING)));
			GamePlayConfigConstant result = gamePlayconfig.validate();
			if(result.equals(GamePlayConfigConstant.Success)) {
				return gamePlayconfig;
			}else {
				logger.info(result.getValue());
				playerCommunication.sendMessage(result.getValue());
			}
			return null;
		} catch (NullPointerException e) {
			logger.error("GamePlay Config contains null values");
			playerCommunication.sendMessage(LeagueCreatorConstant.GameplayConfigNull.getValue());
			return null;
		}
	}

	private IAging createAging(JSONObject jsonAging) {
		logger.info("Creating Aging Configuration Objects");
		
		if (jsonAging == null){
			return null;
		}
		
		try {
			IAging agingConfig = simulationAbstractFactory.createAgingConfig();
			agingConfig.setAverageRetirementAge(((Number) jsonAging.get(AVERAGE_RETIREMENTAGE)).intValue());
			agingConfig.setMaximumAge(((Number) jsonAging.get(MAXIMUM_AGE)).intValue());
			agingConfig.setStatDecayChance(((Number) jsonAging.get(STAT_DECAY_CHANCE)).doubleValue());
			AgingConstant result = agingConfig.validate();
			if(result.equals(AgingConstant.Success)) {
				return agingConfig;
			}else {
				logger.info(result.getValue());
				playerCommunication.sendMessage(result.getValue());
			}
			return null;
		} catch (NullPointerException e) {
			logger.error("Aging Config contains null values");
			playerCommunication.sendMessage(LeagueCreatorConstant.AgingConfigNull.getValue());
			return null;
		}
	}

	private IInjury createInjury(JSONObject jsonInjury) {
		logger.info("Creating Injury Configuration Objects");
		
		if (jsonInjury == null){
			return null;
		}
		
		try {
			IInjury injuryConfig = simulationAbstractFactory.createInjuryConfig();
			injuryConfig.setRandomInjuryChance(((Number) jsonInjury.get(RANDOM_INJURY_CHANCE)).doubleValue());
			injuryConfig.setInjuryDaysHigh(((Number) jsonInjury.get(INJURY_DAYS_HIGH)).intValue());
			injuryConfig.setInjuryDaysLow(((Number) jsonInjury.get(INJURY_DAYS_LOW)).intValue());
			InjuryConstant result = injuryConfig.validate();
			if(result.equals(InjuryConstant.Success)) {
				return injuryConfig;
			}else {
				logger.info(result.getValue());
				playerCommunication.sendMessage(result.getValue());
			}
			return null;
		} catch (NullPointerException e) {
			logger.error("Injury Config contains null values");
			playerCommunication.sendMessage(LeagueCreatorConstant.InjuryConfigNull.getValue());
			return null;
		}
	}

	private ITraining createTrainingConfig(JSONObject training) {
		logger.info("Creating Training Configuration Objects");
		
		if (training == null) {
			return null;
		} 
		
		try {
			ITraining trainConfig = simulationAbstractFactory.createTrainingConfig();
			trainConfig.setDaysUntilStatIncreaseCheck(((Number) training.get(DAYS_UNTIL_STAT_INCREASE_CHECK)).intValue());
			TrainingConstant result = trainConfig.Validate();
			if(result.equals(TrainingConstant.Success)) {
				return trainConfig;
			}else {
				logger.info(result.getValue());
				playerCommunication.sendMessage(result.getValue());
			}
			return null;
		} catch (NullPointerException e) {
			logger.error("GameResolver Config contains null values");
			playerCommunication.sendMessage(LeagueCreatorConstant.TrainingConfigNull.getValue());
			return null;
		}
	}

	private IGameResolver createGameResolver(JSONObject gameResolver) {
		logger.info("Creating GameResolver configuration Objects");
		
		if (gameResolver == null) {
			return null;
		} 
		
		try {
			IGameResolver resolverConfig = simulationAbstractFactory.createGameResolverConfig();
			resolverConfig.setRandomWinChance(((Number) gameResolver.get(RANDOM_WIN_CHANCE)).doubleValue());
			GameResolverConstant result = resolverConfig.Validate();
			if(result.equals(GameResolverConstant.Success)) {
				return resolverConfig;
			}else {
				logger.info(result.getValue());
				playerCommunication.sendMessage(result.getValue());
			}
			return null;
		} catch (NullPointerException e){
			logger.error("GameResolver Config contains null values");
			playerCommunication.sendMessage(LeagueCreatorConstant.GameResolverConfigNull.getValue());
			return null;
		}
	}

	private ITradingConfig createTradingConfig(JSONObject tradingObject) {
		logger.info("Creating Trading configuration Objects");
		
		if (tradingObject == null) {
			return null;
		} 
		
		try {
			ITradingConfig tradeConfig =  simulationAbstractFactory.createTradingConfig();
			tradeConfig.setLossPoint(((Number) tradingObject.get(LOSS_POINT)).intValue());
			tradeConfig.setMaxPlayersPerTrade(((Number) tradingObject.get(MAX_PLAYERS_PER_TRADE)).intValue());
			tradeConfig.setRandomAcceptanceChance(((Double) tradingObject.get(RANDOM_ACCEPTANCE_CHANCE)).doubleValue());
			tradeConfig.setRandomTradeOfferChance((Double)((JSONObject) tradingObject).get(RANDOM_TRADE_OFFER_CHANCE));
			TradingConstant result = tradeConfig.validate();
			if(result.equals(TradingConstant.Success)) {
				return tradeConfig;
			}else {
				logger.info(result.getValue());
				playerCommunication.sendMessage(result.getValue());
			}
			return null;
		} catch (NullPointerException e){
			logger.error("Trading Config contains null values");
			playerCommunication.sendMessage(LeagueCreatorConstant.TradingConfigNull.getValue());
			return null;
		}
	}

	private ArrayList<IConference> createConferences(JSONArray jsonConferences) {
		logger.info("Creating Conference Objects");
		
		if (jsonConferences == null) {
			return null;
		}
		
		ArrayList<IConference> conferences = new ArrayList<>();
		for (Object c: jsonConferences) {
			ArrayList<IDivision> divisions = createDivisions((JSONArray)((JSONObject) c).get(DIVISIONS));
			if(divisions == null ) {
				return null;
			}
			IConference newConference = modelAbstractFactory.createConferenceModel();
			newConference.setConferenceName((String)((JSONObject) c).get(CONFERENCE_NAME));
			newConference.setDivisionDetails(divisions);
			ConferenceConstant validationResult  = newConference.validate();
			if (validationResult.equals(ConferenceConstant.Success)) {
				conferences.add(newConference);
			} else {
				logger.info(validationResult.getValue());
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		
		return conferences;
	}

	private ArrayList<IDivision> createDivisions(JSONArray jsonDivisions) {
		logger.info("Creating Division Objects");
		
		if (jsonDivisions == null) {
			return null;
		}

		ArrayList<IDivision> divisions = new ArrayList<>();
		for (Object d: jsonDivisions) {
			ArrayList<ITeam> teams = createTeams((JSONArray)((JSONObject) d).get(TEAMS));
			if(teams == null) {
				return null;
			}
			IDivision newDivision = modelAbstractFactory.createDivisionModel();
			newDivision.setDivisionName((String)((JSONObject) d).get(DIVISION_NAME));
			newDivision.setTeamDetails(teams);
			DivisionConstant validationResult  = newDivision.validate();
			if (validationResult.equals(DivisionConstant.Success)) {
				divisions.add(newDivision);
			} else {
				logger.info(validationResult.getValue());
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		
		return divisions;
	}

	private ArrayList<ITeam> createTeams(JSONArray jsonTeams) {
		logger.info("Creating Team Objects");
		
		if (jsonTeams == null) {
			return null;
		}
		
		ArrayList<ITeam> teams = new ArrayList<>();
		for (Object t: jsonTeams) {
			ArrayList<IPlayer> players = createPlayers((JSONArray)((JSONObject) t).get(PLAYERS));
			if(players == null) {
				return null;
			}
			ITeam newTeam = modelAbstractFactory.createTeamModel();
			newTeam.setTeamName((String)((JSONObject) t).get(TEAM_NAME));
			newTeam.setGeneralManagerName((String)((JSONObject) t).get(GENERAL_MANAGER));
			newTeam.setCoachDetails(createCoach((JSONObject) ((JSONObject) t).get(HEAD_COACH)));
			newTeam.setPlayerList(players);
			newTeam.setTeamStrength(newTeam.calculateTeamStrength(players));
			TeamConstant validationResult  = newTeam.validate();
			if (validationResult.equals(TeamConstant.Success)) {
				teams.add(newTeam);
			}  else {
				logger.info(validationResult.getValue());
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		
		return teams;
	}

	private ArrayList<IPlayer> createPlayers(JSONArray jsonPlayers) {
		logger.info("Creating Player Objects");
		
		if (jsonPlayers == null) {
			return null;
		}
		
		ArrayList<IPlayer> players = new ArrayList<>();
		for (Object p: jsonPlayers) {
			String playerName = (String)((JSONObject) p).get(PLAYER_NAME);
			String position = (String)((JSONObject) p).get(POSITION);
			Boolean captain = (Boolean)((JSONObject) p).get(CAPTAIN);
			double skating = Double.parseDouble(((JSONObject) p).get(SKATING).toString());
			double shooting = Double.parseDouble(((JSONObject) p).get(SHOOTING).toString());
			double checking = Double.parseDouble(((JSONObject) p).get(CHECKING).toString());
			double saving = Double.parseDouble(((JSONObject) p).get(SAVING).toString());
			int birthDay=Integer.parseInt(((JSONObject) p).get(BIRTH_DAY).toString());
			int birthMonth=Integer.parseInt(((JSONObject) p).get(BIRTH_MONTH).toString());
			int birthYear=Integer.parseInt(((JSONObject) p).get(BIRTH_YEAR).toString());
			if (playerName == null ||position == null || captain == null || birthDay < 0 || birthMonth < 0|| birthYear < 0|| skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			IPlayer newPlayer = modelAbstractFactory.createPlayerModel(playerName, position, captain,skating, shooting, checking, saving,birthDay,birthMonth,birthYear);
			newPlayer.setPlayerStrength(newPlayer.calculatePlayerStrength());
			FreeAgentConstant validationResult  = newPlayer.validate();
			if (validationResult.equals(FreeAgentConstant.Success)) {
				players.add(newPlayer);
			} else {
				logger.info(validationResult.getValue());
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		
		return players;
	}

	private ArrayList<IFreeAgent> createFreeAgents(JSONArray jsonPlayers) {
		logger.info("Creating FreeAgent Objects");
		
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<IFreeAgent> freeAgent = new ArrayList<>();
		for (Object p: jsonPlayers) {
			String playerName = (String)((JSONObject) p).get(PLAYER_NAME);
			String position = (String)((JSONObject) p).get(POSITION);
			double skating = Double.parseDouble(((JSONObject) p).get(SKATING).toString());
			double shooting = Double.parseDouble(((JSONObject) p).get(SHOOTING).toString());
			double checking = Double.parseDouble(((JSONObject) p).get(CHECKING).toString());
			double saving = Double.parseDouble(((JSONObject) p).get(SAVING).toString());
			int birthDay=Integer.parseInt(((JSONObject) p).get(BIRTH_DAY).toString());
			int birthMonth=Integer.parseInt(((JSONObject) p).get(BIRTH_MONTH).toString());
			int birthYear=Integer.parseInt(((JSONObject) p).get(BIRTH_YEAR).toString());
			if (playerName == null ||position == null || birthDay < 0 || birthMonth < 0|| birthYear < 0|| skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			IFreeAgent newPlayer = modelAbstractFactory.createFreeAgentModel(playerName, position,skating, shooting, checking, saving,birthDay,birthMonth,birthYear);
			FreeAgentConstant validationResult  = newPlayer.validate();
			if (validationResult.equals(FreeAgentConstant.Success)) {
				freeAgent.add(newPlayer);
			} else {
				logger.info(validationResult.getValue());
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return freeAgent;
	}

	private ICoach createCoach(JSONObject jsonCoachDetails) {
		logger.info("Creating Coach Objects");
		
		if (jsonCoachDetails == null){
			return null;
		}
		String coachName = (String) jsonCoachDetails.get(NAME);
		double skating = Double.parseDouble(jsonCoachDetails.get(SKATING).toString());
		double shooting = Double.parseDouble(jsonCoachDetails.get(SHOOTING).toString());
		double checking = Double.parseDouble(jsonCoachDetails.get(CHECKING).toString());
		double saving = Double.parseDouble(jsonCoachDetails.get(SAVING).toString());
		return modelAbstractFactory.createCoachModel(coachName,skating,shooting,checking,saving);
	}

	private ArrayList<ICoach> createFreeCoaches(JSONArray jsonCoaches) {
		logger.info("Creating Free Coach Objects");
		
		if (jsonCoaches == null){
			return null;
		}
		
		ArrayList<ICoach> coaches = new ArrayList<>();
		for (Object p: jsonCoaches) {
			String coachName = (String)((JSONObject) p).get(NAME);
			double skating = Double.parseDouble(((JSONObject) p).get(SKATING).toString());
			double shooting = Double.parseDouble(((JSONObject) p).get(SHOOTING).toString());
			double checking = Double.parseDouble(((JSONObject) p).get(CHECKING).toString());
			double saving = Double.parseDouble(((JSONObject) p).get(SAVING).toString());
			if (coachName == null || skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			ICoach newCoach = modelAbstractFactory.createCoachModel(coachName,skating,shooting,checking,saving);
			CoachConstant validationResult  = newCoach.validate();
			if (validationResult.equals(CoachConstant.Success)) {
				coaches.add(newCoach);
			} else {
				logger.info(validationResult.getValue());
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return coaches;
	}

	private ArrayList<String> createFreeManagers(JSONArray jsonManagers) {
		logger.info("Creating Manager Objects");
		
		if (jsonManagers == null){
			return null;
		}
		ArrayList<String> managers = new ArrayList<>();
		for (int i=0; i<jsonManagers.size(); i++){
			String name = (String) jsonManagers.get(i);
			if (StringUtils.isNullOrEmpty(name)) {
				logger.info("Manager name is empty");
				playerCommunication.sendMessage(("Manager name is empty"));
			}
			managers.add(name);
		}
		return managers;
	}

}
