package com.dhl.g05.simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.*;
import com.dhl.g05.simulation.statemachine.AgingConstant;
import com.dhl.g05.simulation.statemachine.GamePlayConfigModel;
import com.dhl.g05.simulation.statemachine.GameResolverConfig;
import com.dhl.g05.simulation.statemachine.GameResolverConstant;
import com.dhl.g05.simulation.statemachine.IAging;
import com.dhl.g05.simulation.statemachine.IGamePlayConfig;
import com.dhl.g05.simulation.statemachine.IGameResolver;
import com.dhl.g05.simulation.statemachine.IInjury;
import com.dhl.g05.simulation.statemachine.ITradingConfig;
import com.dhl.g05.simulation.statemachine.InjuryConstant;
import com.dhl.g05.simulation.statemachine.TradingConfig;
import com.dhl.g05.simulation.statemachine.TradingConstant;
import com.dhl.g05.simulation.statemachine.TrainingConfig;
import com.dhl.g05.simulation.statemachine.TrainingConstant;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.dhl.g05.communication.IPlayerCommunication;
import com.mysql.cj.util.StringUtils;

public class LeagueModelCreatorFromJSON implements ILeagueCreator{

	private FileReader reader;
	private JSONParser parser;
	private IPlayerCommunication playerCommunication;
	private static ModelAbstractFactory modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
	private static SimulationAbstractFactory simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	public LeagueModelCreatorFromJSON(IPlayerCommunication playerCommunication) {
		parser = new JSONParser();
		this.playerCommunication = playerCommunication;
	}

	private IAging createAging(JSONObject jsonAging) {
		if (jsonAging == null){
			return null;
		}
		IAging agingConfig = simulationAbstractFactory.createAgingConfig();
		agingConfig.setAverageRetirementAge(((Number) jsonAging.get("averageRetirementAge")).intValue());
		agingConfig.setMaximumAge(((Number) jsonAging.get("maximumAge")).intValue());
		agingConfig.setStatDecayChance(((Number) jsonAging.get("statDecayChance")).doubleValue());
		AgingConstant result = agingConfig.validate();
		if(result.equals(AgingConstant.Success)) {
			return agingConfig;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private IInjury createInjury(JSONObject jsonInjury) {
		if (jsonInjury == null){
			return null;
		}
		IInjury injuryConfig = simulationAbstractFactory.createInjuryConfig();
		injuryConfig.setRandomInjuryChance(((Number) jsonInjury.get("randomInjuryChance")).doubleValue());
		injuryConfig.setInjuryDaysHigh(((Number) jsonInjury.get("injuryDaysHigh")).intValue());
		injuryConfig.setInjuryDaysLow(((Number) jsonInjury.get("injuryDaysLow")).intValue());
		InjuryConstant result = injuryConfig.validate();
		if(result.equals(InjuryConstant.Success)) {
			return injuryConfig;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private TrainingConfig createTrainingConfig(JSONObject training) {
		if (training == null) {
			return null;
		} 
		TrainingConfig trainConfig = new TrainingConfig();
		trainConfig.setDaysUntilStatIncreaseCheck(((Number) training.get("daysUntilStatIncreaseCheck")).intValue());
		TrainingConstant result = trainConfig.Validate();
		if(result.equals(TrainingConstant.Success)) {
			return trainConfig;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	public ILeague createLeagueFromFile(String fileName) {
		ILeague league = null;
		try {
			File file = new File(fileName);
			reader = new FileReader(file);
			league = createLeague((JSONObject)parser.parse(reader));
			reader.close();
		} catch (FileNotFoundException e) {
			playerCommunication.sendMessage("File not found\n");
		} catch (IOException e) {
			playerCommunication.sendMessage(e.toString());
		} catch (ParseException e) {
			playerCommunication.sendMessage(e.toString());
		}
		return league;
	}
	
	private ILeague createLeague(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setLeagueName((String)leagueData.get("leagueName"));
		league.setConferenceDetails(createConferences((JSONArray)leagueData.get("conferences")));
		league.setFreeAgent(createFreeAgents((JSONArray)leagueData.get("freeAgents")));
		league.setFreeCoach(createFreeCoaches((JSONArray)leagueData.get("coaches")));
		league.setManagerList(createFreeManagers((JSONArray)leagueData.get("generalManagers")));
		league.setGamePlayConfig(setGamePlayConfigsFromFile((JSONObject) leagueData.get("gameplayConfig")));
		LeagueConstant validationResult  =  league.validate();
		if (validationResult.equals(LeagueConstant.Success)) {
			return league;
		} else {
			playerCommunication.sendMessage(validationResult.getValue());
		}

		return null;
	}
	
	public IGamePlayConfig setGamePlayConfigsFromFile(JSONObject gamePlayConfigs){
		if (gamePlayConfigs == null) {
			return null;
		}
		
		IGamePlayConfig gamePlayconfig = new GamePlayConfigModel();
		gamePlayconfig.setInjuriesConfig(createInjury((JSONObject)gamePlayConfigs.get("injuries")));
		gamePlayconfig.setAgingConfig(createAging((JSONObject)gamePlayConfigs.get("aging")));
		gamePlayconfig.setGameResolverConfig(createGameResolver((JSONObject)gamePlayConfigs.get("gameResolver")));
		gamePlayconfig.setTradingConfig(createTradingConfig((JSONObject)gamePlayConfigs.get("trading")));
		gamePlayconfig.setTrainingConfig(createTrainingConfig((JSONObject)gamePlayConfigs.get("training")));
		
		return gamePlayconfig;
}
	
	private IGameResolver createGameResolver(JSONObject gameResolver) {
		if (gameResolver == null) {
			return null;
		} 
		IGameResolver resolverConfig = new GameResolverConfig();
		resolverConfig.setRandomWinChance(((Number) gameResolver.get("randomWinChance")).doubleValue());
		GameResolverConstant result = resolverConfig.Validate();
		if(result.equals(GameResolverConstant.Success)) {
			return resolverConfig;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private ITradingConfig createTradingConfig(JSONObject tradingObject) {
		if (tradingObject == null) {
			return null;
		} 
		ITradingConfig tradeConfig =  new TradingConfig();
		tradeConfig.setLossPoint(((Number) tradingObject.get("lossPoint")).intValue());
		tradeConfig.setMaxPlayersPerTrade(((Number) tradingObject.get("maxPlayersPerTrade")).intValue());
		tradeConfig.setRandomAcceptanceChance(((Double) tradingObject.get("randomAcceptanceChance")).doubleValue());
		tradeConfig.setRandomTradeOfferChance((Double)((JSONObject) tradingObject).get("randomTradeOfferChance"));
		TradingConstant result = tradeConfig.validate();
		if(result.equals(TradingConstant.Success)) {
			return tradeConfig;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private ArrayList<IConference> createConferences(JSONArray jsonConferences) {
		if (jsonConferences == null) {
			return null;
		}
		ArrayList<IConference> conferences = new ArrayList<>();
		for (Object c: jsonConferences) {
			ArrayList<IDivision> divisions = createDivisions((JSONArray)((JSONObject) c).get("divisions"));
			if(divisions == null ) {
				return null;
			}
			IConference newConference = new ConferenceModel();
			newConference.setConferenceName((String)((JSONObject) c).get("conferenceName"));
			newConference.setDivisionDetails(divisions);
			ConferenceConstant validationResult  = newConference.validate();
			if (validationResult.equals(ConferenceConstant.Success)) {
				conferences.add(newConference);
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return conferences;
	}

	private ArrayList<IDivision> createDivisions(JSONArray jsonDivisions) {
		if (jsonDivisions == null) {
			return null;
		}

		ArrayList<IDivision> divisions = new ArrayList<>();
		for (Object d: jsonDivisions) {
			ArrayList<ITeam> teams = createTeams((JSONArray)((JSONObject) d).get("teams"));
			if(teams == null) {
				return null;
			}
			IDivision newDivision = new DivisionModel();
			newDivision.setDivisionName((String)((JSONObject) d).get("divisionName"));
			newDivision.setTeamDetails(teams);
			DivisionConstant validationResult  = newDivision.validate();
			if (validationResult.equals(DivisionConstant.Success)) {
				divisions.add(newDivision);
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return divisions;
	}

	private ArrayList<ITeam> createTeams(JSONArray jsonTeams) {
		if (jsonTeams == null) {
			return null;
		}
		ArrayList<ITeam> teams = new ArrayList<>();
		for (Object t: jsonTeams) {
			ArrayList<IPlayer> players = createPlayers((JSONArray)((JSONObject) t).get("players"));
			if(players == null) {
				return null;
			}
			ITeam newTeam = new TeamModel();
			newTeam.setTeamName((String)((JSONObject) t).get("teamName"));
			newTeam.setGeneralManagerName((String)((JSONObject) t).get("generalManager"));
			newTeam.setCoachDetails(createCoach((JSONObject) ((JSONObject) t).get("headCoach")));
			newTeam.setPlayerList(players);
			newTeam.setTeamStrength(newTeam.calculateTeamStrength(players));
			TeamConstant validationResult  = newTeam.validate();
			if (validationResult.equals(TeamConstant.Success)) {
				teams.add(newTeam);
			}  else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return teams;
	}

	private ArrayList<IPlayer> createPlayers(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<IPlayer> players = new ArrayList<>();
		for (Object p: jsonPlayers) {
			String playerName = (String)((JSONObject) p).get("playerName");
			String position = (String)((JSONObject) p).get("position");
			Boolean captain = (Boolean)((JSONObject) p).get("captain");
			double skating = Double.parseDouble(((JSONObject) p).get("skating").toString());
			double shooting = Double.parseDouble(((JSONObject) p).get("shooting").toString());
			double checking = Double.parseDouble(((JSONObject) p).get("checking").toString());
			double saving = Double.parseDouble(((JSONObject) p).get("saving").toString());
			int birthDay=Integer.parseInt(((JSONObject) p).get("birthDay").toString());
			int birthMonth=Integer.parseInt(((JSONObject) p).get("birthMonth").toString());
			int birthYear=Integer.parseInt(((JSONObject) p).get("birthYear").toString());
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
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return players;
	}

	private ArrayList<IFreeAgent> createFreeAgents(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<IFreeAgent> freeAgent = new ArrayList<>();
		for (Object p: jsonPlayers) {
			String playerName = (String)((JSONObject) p).get("playerName");
			String position = (String)((JSONObject) p).get("position");
			double skating = Double.parseDouble(((JSONObject) p).get("skating").toString());
			double shooting = Double.parseDouble(((JSONObject) p).get("shooting").toString());
			double checking = Double.parseDouble(((JSONObject) p).get("checking").toString());
			double saving = Double.parseDouble(((JSONObject) p).get("saving").toString());
			int birthDay=Integer.parseInt(((JSONObject) p).get("birthDay").toString());
			int birthMonth=Integer.parseInt(((JSONObject) p).get("birthMonth").toString());
			int birthYear=Integer.parseInt(((JSONObject) p).get("birthYear").toString());
			if (playerName == null ||position == null || birthDay < 0 || birthMonth < 0|| birthYear < 0|| skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			IFreeAgent newPlayer = modelAbstractFactory.createFreeAgentModel(playerName, position,skating, shooting, checking, saving,birthDay,birthMonth,birthYear);
			FreeAgentConstant validationResult  = newPlayer.validate();
			if (validationResult.equals(FreeAgentConstant.Success)) {
				freeAgent.add(newPlayer);
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return freeAgent;
	}

	private ICoach createCoach(JSONObject jsonCoachDetails) {
		if (jsonCoachDetails == null){
			return null;
		}
		String coachName = (String) jsonCoachDetails.get("name");
		double skating = Double.parseDouble(jsonCoachDetails.get("skating").toString());
		double shooting = Double.parseDouble(jsonCoachDetails.get("shooting").toString());
		double checking = Double.parseDouble(jsonCoachDetails.get("checking").toString());
		double saving = Double.parseDouble(jsonCoachDetails.get("saving").toString());
		return modelAbstractFactory.createCoachModel(coachName,skating,shooting,checking,saving);
	}

	private ArrayList<ICoach> createFreeCoaches(JSONArray jsonCoaches) {
		if (jsonCoaches == null){
			return null;
		}
		ArrayList<ICoach> coaches = new ArrayList<>();
		for (Object p: jsonCoaches) {
			String coachName = (String)((JSONObject) p).get("name");
			double skating = Double.parseDouble(((JSONObject) p).get("skating").toString());
			double shooting = Double.parseDouble(((JSONObject) p).get("shooting").toString());
			double checking = Double.parseDouble(((JSONObject) p).get("checking").toString());
			double saving = Double.parseDouble(((JSONObject) p).get("saving").toString());
			if (coachName == null || skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			ICoach newCoach = modelAbstractFactory.createCoachModel(coachName,skating,shooting,checking,saving);
			CoachConstant validationResult  = newCoach.validate();
			if (validationResult.equals(CoachConstant.Success)) {
				coaches.add(newCoach);
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return coaches;
	}

	private ArrayList<String> createFreeManagers(JSONArray jsonManagers) {
		if (jsonManagers == null){
			return null;
		}
		ArrayList<String> managers = new ArrayList<>();
		for (int i=0; i<jsonManagers.size(); i++){
			String name = (String) jsonManagers.get(i);
			if (StringUtils.isNullOrEmpty(name)) {
				playerCommunication.sendMessage(("Manager name is empty"));
			}
				managers.add(name);
		}
		return managers;
	}

}