package com.dhl.g05.filehandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dhl.g05.coach.CoachConstant;
import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.coach.ICoach;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.conference.ConferenceConstant;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionConstant;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentConstant;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.AgingConstant;
import com.dhl.g05.gameplayconfig.GamePlayConfigModel;
import com.dhl.g05.gameplayconfig.GameResolverConfig;
import com.dhl.g05.gameplayconfig.GameResolverConstant;
import com.dhl.g05.gameplayconfig.Injury;
import com.dhl.g05.gameplayconfig.InjuryConstant;
import com.dhl.g05.gameplayconfig.TradingConstant;
import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.gameplayconfig.TrainingConfig;
import com.dhl.g05.gameplayconfig.TrainingConstant;
import com.dhl.g05.league.LeagueConstant;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamConstant;
import com.dhl.g05.team.TeamModel;
import com.mysql.cj.util.StringUtils;

public class LeagueModelCreatorFromJSON implements ILeagueCreator{

	private FileReader reader;
	private JSONParser parser;
	private IPlayerCommunication playerCommunication;

	public LeagueModelCreatorFromJSON(IPlayerCommunication playerCommunication) {
		parser = new JSONParser();
		this.playerCommunication = playerCommunication;
	}

	public boolean isFileValidJson(String fileName) {
		try {
			reader = new FileReader(new File(fileName));
			parser.parse(reader);
			reader.close();
			return true;
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Aging createAging(JSONObject jsonAging) {
		if (jsonAging == null){
			return null;
		}
		int averageRetirementAge = Integer.parseInt(jsonAging.get("averageRetirementAge").toString());
		int maximumAge = Integer.parseInt( jsonAging.get("maximumAge").toString());
		double statDecayChance=Double.parseDouble(jsonAging.get("statDecayChance").toString());
		Aging aging = new Aging(averageRetirementAge, maximumAge,statDecayChance);
		AgingConstant result = aging.validate();
		if(result.equals(AgingConstant.Success)) {
			return aging;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private Injury createInjury(JSONObject jsonInjury) {
		if (jsonInjury == null){
			return null;
		}
		double randomInjuryChance = Double.parseDouble(jsonInjury.get("randomInjuryChance").toString());
		int injuryDaysLow = Integer.parseInt(jsonInjury.get("injuryDaysLow").toString());
		int injuryDaysHigh = Integer.parseInt(jsonInjury.get("injuryDaysHigh").toString());
		Injury injury = new Injury(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		InjuryConstant result = injury.validate();
		if(result.equals(InjuryConstant.Success)) {
			return injury;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private TrainingConfig createTrainingConfig(JSONObject training) {
		if (training == null) {
			return null;
		} 
		Number daysUntilStatIncreaseCheck =  (Number) training.get("daysUntilStatIncreaseCheck");
		TrainingConfig train = new TrainingConfig(daysUntilStatIncreaseCheck.intValue());
		TrainingConstant result = train.Validate();
		if(result.equals(TrainingConstant.Success)) {
			return train;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	public LeagueModel createLeagueFromFile(String fileName) throws FileNotFoundException, IOException, ParseException {
		File file = new File(fileName);
		reader = new FileReader(file);
		LeagueModel league = createLeague((JSONObject)parser.parse(reader));
		return league;
	}
	
	private LeagueModel createLeague(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ArrayList<IConference> conferences = createConferences((JSONArray)leagueData.get("conferences"));
		ArrayList<IFreeAgent> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
		ArrayList<ICoach> freeCoaches = createFreeCoaches((JSONArray)leagueData.get("coaches"));
		ArrayList<String> managers = createFreeManagers((JSONArray)leagueData.get("generalManagers"));
		GamePlayConfigModel gamePlayConfig = setGamePlayConfigsFromFile((JSONObject) leagueData.get("gameplayConfig"));
		String leagueName = (String)leagueData.get("leagueName");
		if (conferences != null && freeAgents != null && freeCoaches != null && managers != null && gamePlayConfig!=null) {
			LeagueModel league = new LeagueModel(leagueName, conferences, freeAgents, freeCoaches, managers,gamePlayConfig);
			LeagueConstant validationResult  =  league.validate();
			if (validationResult.equals(LeagueConstant.Success)) {
				return league;
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
			}
		}
		
		return null;
	}
	
	public GamePlayConfigModel setGamePlayConfigsFromFile(JSONObject gamePlayConfigs){
		if (gamePlayConfigs == null) {
			return null;
		}
		Aging agingObject = createAging((JSONObject)gamePlayConfigs.get("aging"));
		Injury injuryObject = createInjury((JSONObject)gamePlayConfigs.get("injuries"));
		TrainingConfig trainingObject = createTrainingConfig((JSONObject)gamePlayConfigs.get("training"));
		TradingModel tradingObject = createTradingConfig((JSONObject)gamePlayConfigs.get("trading"));
		GameResolverConfig gameResolver = createGameResolver((JSONObject)gamePlayConfigs.get("gameResolver"));
		if (agingObject == null || injuryObject == null || trainingObject == null || tradingObject == null || gameResolver == null) {
			playerCommunication.sendMessage("Error in GamePlay Config");
			return null;
		}
		return new GamePlayConfigModel(tradingObject, agingObject, injuryObject, gameResolver, trainingObject);
}
	
	private GameResolverConfig createGameResolver(JSONObject gameResolver) {
		if (gameResolver == null) {
			return null;
		} 
		double randomWin = Double.parseDouble(gameResolver.get("randomWinChance").toString());
		GameResolverConfig resolver = new GameResolverConfig(randomWin);
		GameResolverConstant result = resolver.Validate();
		if(result.equals(GameResolverConstant.Success)) {
			return resolver;
		}else {
			playerCommunication.sendMessage(result.getValue());
		}
		return null;
	}

	private TradingModel createTradingConfig(JSONObject tradingObject) {
		if (tradingObject == null) {
			return null;
		} 
		double randomTradeOfferChance = Double.parseDouble(tradingObject.get("randomTradeOfferChance").toString());
		int lossPoint = Integer.parseInt(tradingObject.get("lossPoint").toString());
		int maxPlayersPerTrade = Integer.parseInt(tradingObject.get("maxPlayersPerTrade").toString());
		double randomAcceptance =  Double.parseDouble(tradingObject.get("randomAcceptanceChance").toString());
		TradingModel trade =  new TradingModel(lossPoint,randomTradeOfferChance,maxPlayersPerTrade,randomAcceptance);
		TradingConstant result = trade.validate();
		if(result.equals(TradingConstant.Success)) {
			return trade;
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
			String conferenceName = (String)((JSONObject) c).get("conferenceName");
			if (divisions != null) {
				IConference newConference = new ConferenceModel(conferenceName, divisions);
				ConferenceConstant validationResult  = newConference.validate();
				if (validationResult.equals(ConferenceConstant.Success)) {
					conferences.add(newConference);
				} else {
					playerCommunication.sendMessage(validationResult.getValue());
					return null;
				}
			} else {
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
			String divisionName = (String)((JSONObject) d).get("divisionName");
			if (teams != null) {
				IDivision newDivision = new DivisionModel(divisionName,teams);
				DivisionConstant validationResult  = newDivision.validate();
				if (validationResult.equals(DivisionConstant.Success)) {
					divisions.add(newDivision);
				} else {
					playerCommunication.sendMessage(validationResult.getValue());
					return null;
				}
			} else {
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
			String teamName = (String)((JSONObject) t).get("teamName");
			String managerName = (String)((JSONObject) t).get("generalManager");
			JSONObject coach = (JSONObject) ((JSONObject) t).get("headCoach");
			CoachModel coachDetails = createCoach(coach);
			if (players != null && teamName != null && managerName != null && coachDetails != null) {
				ITeam newTeam = new TeamModel(teamName, coachDetails, managerName, players);
				TeamConstant validationResult  = newTeam.validate();
				if (validationResult.equals(TeamConstant.Success)) {
					teams.add(newTeam);
				}  else {
					playerCommunication.sendMessage(validationResult.getValue());
					return null;
				}
			} else
				return null;
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
			IPlayer newPlayer = new PlayerModel(playerName, position, captain,skating, shooting, checking, saving,birthDay,birthMonth,birthYear);
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
			FreeAgentModel newPlayer = new FreeAgentModel(playerName, position,skating, shooting, checking, saving,birthDay,birthMonth,birthYear);
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

	private CoachModel createCoach(JSONObject jsonCoachDetails) {
		if (jsonCoachDetails == null){
			return null;
		}
		String coachName = (String) jsonCoachDetails.get("name");
		double skating = Double.parseDouble(jsonCoachDetails.get("skating").toString());
		double shooting = Double.parseDouble(jsonCoachDetails.get("shooting").toString());
		double checking = Double.parseDouble(jsonCoachDetails.get("checking").toString());
		double saving = Double.parseDouble(jsonCoachDetails.get("saving").toString());
		return new CoachModel(coachName,skating,shooting,checking,saving);
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
			CoachModel newCoach = new CoachModel(coachName,skating,shooting,checking,saving);
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
