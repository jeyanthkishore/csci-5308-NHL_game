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
	
	private static final String  LEAGUE_NAME = "leagueName";
	private static final String CONFERENCES = "conferences";
	private static final String FREE_AGENTS = "freeAgents";
	private static final String COACHES = "coaches";
	private static final String GENERAL_MANAGERS = "generalManagers";
	private static final String GAMEPLAY_CONFIG = "gameplayConfig";
	private static final String LEAGUE_SCHEDULE = "leagueSchedule";
	private static final String LEAGUE_STANDING = "leagueStanding";
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
	private static final String PLAYER_STRENGTH = "playerStrength";
	private static final String CAPTAIN = "captain";
	private static final String SKATING = "skating";
	private static final String SHOOTING = "shooting";
	private static final String CHECKING = "checking";
	private static final String SAVING = "saving";
	private static final String IS_INJURED = "isInjured";
	private static final String IS_RETIRED = "isRetired";
	private static final String BIRTH_DAY = "birthDay";
	private static final String BIRTH_MONTH = "birthMonth";
	private static final String BIRTH_YEAR = "birthYear";
	private static final String NAME = "name";
	private static final String STANDING_LIST = "standingsList";
	private static final String CONFERENCE = "conference";
	private static final String DIVISION = "division";
	private static final String TEAM = "team";
	private static final String NUMBER_OF_LOSS = "numberOfLoss";
	private static final String TOTAL_GAMES_PLAYED = "totalGamesPlayed";
	private static final String NUMBER_OF_WINS = "numberOfWins";
	private static final String TOTAL_POINTS = "totalPoints";
	private static final String REGULAR_SEASON_SCHEDULE = "regularSeasonSchedule";
	private static final String PLAYOFF_SEASON_SCHEDULE = "playOffSeasonSchedule";
	private static final String FIRST_CONFERENCE = "firstConference";
	private static final String FIRST_DIVISION = "firstDivision";
	private static final String FIRST_TEAM = "firstTeam";
	private static final String SECOND_CONFERENCE = "secondConference";
	private static final String SECOND_DIVISION = "secondDivision";
	private static final String SECOND_TEAM = "secondTeam";
	
	
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
		try {
			IAging agingConfig = simulationFactory.createAgingConfig();
			agingConfig.setAverageRetirementAge(((Number) jsonAging.get(AVERAGE_RETIREMENTAGE)).intValue());
			agingConfig.setMaximumAge(((Number) jsonAging.get(MAXIMUM_AGE)).intValue());
			agingConfig.setStatDecayChance(((Number) jsonAging.get(STAT_DECAY_CHANCE)).doubleValue());
			return agingConfig;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private IInjury createInjury(JSONObject jsonInjury) {
		try {
			IInjury injuryConfig = simulationFactory.createInjuryConfig();
			injuryConfig.setRandomInjuryChance(((Number) jsonInjury.get(RANDOM_INJURY_CHANCE)).doubleValue());
			injuryConfig.setInjuryDaysHigh(((Number) jsonInjury.get(INJURY_DAYS_HIGH)).intValue());
			injuryConfig.setInjuryDaysLow(((Number) jsonInjury.get(INJURY_DAYS_LOW)).intValue());
			return injuryConfig;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ITraining createTrainingConfig(JSONObject training) {
		try {
			ITraining trainConfig = simulationFactory.createTrainingConfig();
			trainConfig.setDaysUntilStatIncreaseCheck(((Number) training.get(DAYS_UNTIL_STAT_INCREASE_CHECK)).intValue());
			return trainConfig;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ILeague deserialzieFileData(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ILeague league = modelFactory.createLeagueModel();
		league.setLeagueName((String)leagueData.get(LEAGUE_NAME));
		league.setConferenceDetails(createConferences((JSONArray)leagueData.get(CONFERENCES)));
		league.setFreeAgent(createFreeAgents((JSONArray)leagueData.get(FREE_AGENTS)));
		league.setFreeCoach(createFreeCoaches((JSONArray)leagueData.get(COACHES)));
		league.setManagerList(createFreeManagers((JSONArray)leagueData.get(GENERAL_MANAGERS)));
		league.setGamePlayConfig(setGamePlayConfigsFromFile((JSONObject) leagueData.get(GAMEPLAY_CONFIG)));
		league.setLeagueSchedule(createLeagueSchedule((JSONObject)leagueData.get(LEAGUE_SCHEDULE)));
		league.setLeagueStanding(createLeagueStandings((JSONObject)leagueData.get(LEAGUE_STANDING)));
		return league;
	}

	@SuppressWarnings("unchecked")
	private ILeagueStanding createLeagueStandings(JSONObject jsonStanding) {
		
		ArrayList<IStandingModel> leagueStandingList = new ArrayList<>();
		JSONArray standingList = (JSONArray)jsonStanding.get(STANDING_LIST);
		for(Object standing : standingList) {
			IStandingModel newStanding = new StandingModel();
			JSONArray arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject) ((JSONObject) standing).get(CONFERENCE));
			newStanding.setConference(createConferences((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject) ((JSONObject) standing).get(DIVISION));
			newStanding.setDivision(createDivisions((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject) ((JSONObject) standing).get(TEAM));
			newStanding.setTeam(createTeams((JSONArray)arrayFormation).get(0));
			newStanding.setTotalGamesLost(((Number) ((JSONObject) standing).get(NUMBER_OF_LOSS)).intValue());
			newStanding.setTotalGamesPlayed(((Number) ((JSONObject) standing).get(TOTAL_GAMES_PLAYED)).intValue());
			newStanding.setTotalGamesWon(((Number) ((JSONObject) standing).get(NUMBER_OF_WINS)).intValue());
			newStanding.setTotalPoints(((Number) ((JSONObject) standing).get(TOTAL_POINTS)).intValue());
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
		regularSchedule = generateSchedule((JSONArray) (jsonObject).get(REGULAR_SEASON_SCHEDULE));
		schedule.setRegularSeasonSchedule(regularSchedule);
		playoffSchedule = generateSchedule((JSONArray) (jsonObject).get(PLAYOFF_SEASON_SCHEDULE));
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
			arrayFormation.add((JSONObject) ((JSONObject) schedule).get(FIRST_CONFERENCE));
			currentschedule.setFirstConference(createConferences((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get(FIRST_DIVISION));
			currentschedule.setFirstDivision(createDivisions((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get(FIRST_TEAM));
			currentschedule.setFirstTeam(createTeams((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get(SECOND_CONFERENCE));
			currentschedule.setSecondConference(createConferences((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get(SECOND_DIVISION));
			currentschedule.setSecondDivision(createDivisions((JSONArray)arrayFormation).get(0));
			arrayFormation = new JSONArray();
			arrayFormation.add((JSONObject)((JSONObject) schedule).get(SECOND_TEAM));
			currentschedule.setSecondTeam(createTeams((JSONArray)arrayFormation).get(0));
			scheduleList.add(currentschedule);
		}
		return scheduleList;
	}

	private ArrayList<IConference> createConferences(JSONArray jsonConferences) {
		ArrayList<IConference> conferences = new ArrayList<>();
		for (Object c: jsonConferences) {
			ArrayList<IDivision> divisions = createDivisions((JSONArray)((JSONObject) c).get(DIVISIONS));
			IConference newConference = modelFactory.createConferenceModel();
			newConference.setConferenceName((String)((JSONObject) c).get(CONFERENCE_NAME));
			newConference.setDivisionDetails(divisions);
			conferences.add(newConference);
		}
		return conferences;
	}

	public IGamePlayConfig setGamePlayConfigsFromFile(JSONObject gamePlayConfigs){
		
		try {
			IGamePlayConfig gamePlayconfig = new GamePlayConfigModel();
			gamePlayconfig.setInjuriesConfig(createInjury((JSONObject)gamePlayConfigs.get(INJURIES)));
			gamePlayconfig.setAgingConfig(createAging((JSONObject)gamePlayConfigs.get(AGING)));
			gamePlayconfig.setGameResolverConfig(createGameResolver((JSONObject)gamePlayConfigs.get(GAME_RESOLVER)));
			gamePlayconfig.setTradingConfig(createTradingConfig((JSONObject)gamePlayConfigs.get(TRADING)));
			gamePlayconfig.setTrainingConfig(createTrainingConfig((JSONObject)gamePlayConfigs.get(TRAINING)));
			
			return gamePlayconfig;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private IGameResolver createGameResolver(JSONObject gameResolver) {
		try {
			IGameResolver resolverConfig = simulationFactory.createGameResolverConfig();
			resolverConfig.setRandomWinChance(((Number) gameResolver.get(RANDOM_WIN_CHANCE)).doubleValue());
			return resolverConfig;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ITradingConfig createTradingConfig(JSONObject tradingObject) {
		try {
			ITradingConfig tradeConfig =  simulationFactory.createTradingConfig();
			tradeConfig.setLossPoint(((Number) tradingObject.get(LOSS_POINT)).intValue());
			tradeConfig.setMaxPlayersPerTrade(((Number) tradingObject.get(MAX_PLAYERS_PER_TRADE)).intValue());
			tradeConfig.setRandomAcceptanceChance(((Double) tradingObject.get(RANDOM_ACCEPTANCE_CHANCE)).doubleValue());
			tradeConfig.setRandomTradeOfferChance((Double)((JSONObject) tradingObject).get(RANDOM_TRADE_OFFER_CHANCE));
			return tradeConfig;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<IDivision> createDivisions(JSONArray jsonDivisions) {

		ArrayList<IDivision> divisions = new ArrayList<>();
		for (Object d: jsonDivisions) {
			ArrayList<ITeam> teams = createTeams((JSONArray)((JSONObject) d).get(TEAMS));
			IDivision newDivision = modelFactory.createDivisionModel();
			newDivision.setDivisionName((String)((JSONObject) d).get(DIVISION_NAME));
			newDivision.setTeamDetails(teams);
			divisions.add(newDivision);
		}
		return divisions;
	}

	private ArrayList<ITeam> createTeams(JSONArray jsonTeams) {
		ArrayList<ITeam> teams = new ArrayList<>();
		for (Object t: jsonTeams) {
			ArrayList<IPlayer> players = createPlayers((JSONArray)((JSONObject) t).get(PLAYERS));
			ITeam newTeam = modelFactory.createTeamModel();
			newTeam.setTeamName((String)((JSONObject) t).get(TEAM_NAME));
			newTeam.setGeneralManagerName((String)((JSONObject) t).get(GENERAL_MANAGER));
			newTeam.setCoachDetails(createCoach((JSONObject) ((JSONObject) t).get(HEAD_COACH)));
			newTeam.setPlayerList(players);
			teams.add(newTeam);
		}
		return teams;
	}

	private ArrayList<IPlayer> createPlayers(JSONArray jsonPlayers) {
		try {
			ArrayList<IPlayer> players = new ArrayList<>();
			for (Object player: jsonPlayers) {
				IPlayer newPlayer = modelFactory.createPlayerModel();
				newPlayer.setBirthDay(((Number) ((JSONObject) player).get(BIRTH_DAY)).intValue());
				newPlayer.setBirthMonth(((Number) ((JSONObject) player).get(BIRTH_MONTH)).intValue());
				newPlayer.setBirthYear(((Number) ((JSONObject) player).get(BIRTH_YEAR)).intValue());
				newPlayer.setSkating(((Number) ((JSONObject) player).get(SKATING)).doubleValue());
				newPlayer.setShooting(((Number) ((JSONObject) player).get(SHOOTING)).doubleValue());
				newPlayer.setChecking(((Number) ((JSONObject) player).get(CHECKING)).doubleValue());
				newPlayer.setSaving(((Number) ((JSONObject) player).get(SAVING)).doubleValue());
				newPlayer.setPlayerName((String)((JSONObject) player).get(PLAYER_NAME));
				newPlayer.setPosition((String)((JSONObject) player).get(POSITION));
				newPlayer.setAge(((Number) ((JSONObject) player).get(SAVING)).intValue());
				newPlayer.setPlayerStrength(((Number) ((JSONObject) player).get(PLAYER_STRENGTH)).doubleValue());
				newPlayer.setCaptain((Boolean)((JSONObject) player).get(CAPTAIN));
				newPlayer.setInjuryStatus((Boolean)((JSONObject) player).get(IS_INJURED));
				newPlayer.setRetirementStatus((Boolean)((JSONObject) player).get(IS_RETIRED));
				players.add(newPlayer);
			}
			return players;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<IFreeAgent> createFreeAgents(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		try {
			ArrayList<IFreeAgent> freeAgent = new ArrayList<>();
			for (Object player: jsonPlayers) {
				IFreeAgent newPlayer = modelFactory.createFreeAgentModel();
				newPlayer.setBirthDay(((Number) ((JSONObject) player).get(BIRTH_DAY)).intValue());
				newPlayer.setBirthMonth(((Number) ((JSONObject) player).get(BIRTH_MONTH)).intValue());
				newPlayer.setBirthYear(((Number) ((JSONObject) player).get(BIRTH_YEAR)).intValue());
				newPlayer.setSkating(((Number) ((JSONObject) player).get(SKATING)).doubleValue());
				newPlayer.setShooting(((Number) ((JSONObject) player).get(SHOOTING)).doubleValue());
				newPlayer.setChecking(((Number) ((JSONObject) player).get(CHECKING)).doubleValue());
				newPlayer.setSaving(((Number) ((JSONObject) player).get(SAVING)).doubleValue());
				newPlayer.setPlayerName((String)((JSONObject) player).get(PLAYER_NAME));
				newPlayer.setPosition((String)((JSONObject) player).get(POSITION));
				newPlayer.setAge(((Number) ((JSONObject) player).get(SAVING)).intValue());
				newPlayer.setPlayerStrength(((Number) ((JSONObject) player).get(PLAYER_STRENGTH)).doubleValue());
				newPlayer.setInjuryStatus((Boolean)((JSONObject) player).get(IS_INJURED));
				newPlayer.setRetirementStatus((Boolean)((JSONObject) player).get(IS_RETIRED));
				freeAgent.add(newPlayer);
			}
			return freeAgent;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ICoach createCoach(JSONObject jsonCoachDetails) {
		try{
			ICoach newCoach = modelFactory.createCoachModel();
			newCoach.setName((String) jsonCoachDetails.get(NAME));
			newCoach.setSkating(((Number) ((JSONObject) jsonCoachDetails).get(SKATING)).doubleValue());
			newCoach.setShooting(((Number) ((JSONObject) jsonCoachDetails).get(SHOOTING)).doubleValue());
			newCoach.setChecking(((Number) ((JSONObject) jsonCoachDetails).get(CHECKING)).doubleValue());
			newCoach.setSaving(((Number) ((JSONObject) jsonCoachDetails).get(SAVING)).doubleValue());
			return newCoach;
		} catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
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
