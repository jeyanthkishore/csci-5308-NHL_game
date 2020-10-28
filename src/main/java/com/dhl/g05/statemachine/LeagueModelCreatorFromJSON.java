package com.dhl.g05.statemachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dhl.g05.leaguemodel.coach.CoachConstant;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.conference.ConferenceConstant;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionConstant;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentConstant;
import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;
import com.dhl.g05.leaguemodel.league.LeagueConstant;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.manager.ManagerConstant;
import com.dhl.g05.leaguemodel.manager.ManagerModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamConstant;
import com.dhl.g05.leaguemodel.team.TeamModel;
import com.dhl.g05.simulation.Date;

public class LeagueModelCreatorFromJSON {
	
	private FileReader reader;
	private JSONParser parser;
	private ILeagueModelJson leagueModel;
	private IPlayerCommunication playerCommunication;

	public LeagueModelCreatorFromJSON(ILeagueModelJson leagueModel, IPlayerCommunication playerCommunication) {
		parser = new JSONParser();
		this.leagueModel = leagueModel;
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
	
	public boolean setGamePlayConfigsFromFile(String fileName) throws IOException, ParseException {
		File file = new File(fileName);
		reader = new FileReader(file);
		JSONObject leagueData = (JSONObject)parser.parse(reader);
		if (leagueData == null) {
			return false;
		} else {
			JSONObject gamePlayConfigs = (JSONObject) leagueData.get("gameplayConfig");

			System.out.println(gamePlayConfigs);
			if (gamePlayConfigs == null) {
				return false;
			} else {
				JSONObject training = (JSONObject)gamePlayConfigs.get("training");
				JSONObject aging = (JSONObject)gamePlayConfigs.get("aging");
				JSONObject injuries = (JSONObject)gamePlayConfigs.get("injuries");
				System.out.println("Aging"+aging);
				System.out.println("Injuries"+injuries);
				if (setTrainingConfig(training) == false) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean setTrainingConfig(JSONObject training) {
		if (training == null) {
			return false;
		} else {
			Number daysUntilStatIncreaseCheck =  (Number) training.get("daysUntilStatIncreaseCheck");
			Date.getInstance().setDaysUntilStatIncreaseCheck(daysUntilStatIncreaseCheck.intValue());
			return true;
		}
	}

	public boolean createLeagueFromFile(String fileName) throws FileNotFoundException, IOException, ParseException {
		setGamePlayConfigsFromFile(fileName);
		File file = new File(fileName);
		reader = new FileReader(file);
		LeagueModel league = createLeague((JSONObject)parser.parse(reader));
		leagueModel.setLeague(league);
		return (leagueModel.getLeague() != null);
	}
	
	private LeagueModel createLeague(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ArrayList<ConferenceModel> conferences = createConferences((JSONArray)leagueData.get("conferences"));
		ArrayList<FreeAgentModel> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
		ArrayList<CoachModel> freeCoaches = createFreeCoaches((JSONArray)leagueData.get("coaches"));
		ArrayList<ManagerModel> managers = createFreeManagers((JSONArray)leagueData.get("generalManagers"));
		ArrayList<ManagerModel> managerList = new ArrayList<>();
		String leagueName = (String)leagueData.get("leagueName");
		if (conferences != null && freeAgents != null && freeCoaches != null) {
			LeagueModel league = leagueModel.createLeague(leagueName,conferences,freeAgents, freeCoaches);
			LeagueConstant validationResult  =  leagueModel.validateLeague(league);
			if (validationResult.equals(LeagueConstant.Success)) {
				if ( managers != null){
					System.out.println(managers);
					ManagerModel managerObject = new ManagerModel(managers);
					managerList.add(managerObject);
				}
				return league;
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
			}
		}
		return null;
	}

	private ArrayList<ConferenceModel> createConferences(JSONArray jsonConferences) {
		if (jsonConferences == null) {
			return null;
		}
		ArrayList<ConferenceModel> conferences = new ArrayList<>();
		for (Object c: jsonConferences) {
			ArrayList<DivisionModel> divisions = createDivisions((JSONArray)((JSONObject) c).get("divisions"));
			String conferenceName = (String)((JSONObject) c).get("conferenceName");
			if (divisions != null) {
				ConferenceModel newConference = new ConferenceModel(conferenceName, divisions);
				ConferenceConstant validationResult  = leagueModel.validateConference(newConference);
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

	private ArrayList<DivisionModel> createDivisions(JSONArray jsonDivisions) {
		if (jsonDivisions == null) {
			return null;
		}
		ArrayList<DivisionModel> divisions = new ArrayList<>();
		for (Object d: jsonDivisions) {
			ArrayList<TeamModel> teams = createTeams((JSONArray)((JSONObject) d).get("teams"));
			String divisionName = (String)((JSONObject) d).get("divisionName");
			if (teams != null) {
				DivisionModel newDivision = new DivisionModel(divisionName,teams);
				DivisionConstant validationResult  = leagueModel.validateDivision(newDivision);
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

	private ArrayList<TeamModel> createTeams(JSONArray jsonTeams) {
		if (jsonTeams == null) {
			return null;
		}
		ArrayList<TeamModel> teams = new ArrayList<>();
		for (Object t: jsonTeams) {
			ArrayList<PlayerModel> players = createPlayers((JSONArray)((JSONObject) t).get("players"));
			String teamName = (String)((JSONObject) t).get("teamName");
			String managerName = (String)((JSONObject) t).get("generalManager");
			JSONObject coach = (JSONObject) ((JSONObject) t).get("headCoach");
			CoachModel coachDetails = createCoach(coach);
			if (players != null && teamName != null && managerName != null && coachDetails != null) {
				TeamModel newTeam = new TeamModel(teamName, coachDetails, managerName, players);
				TeamConstant validationResult  = leagueModel.validateTeam(newTeam);
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

	private ArrayList<PlayerModel> createPlayers(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<PlayerModel> players = new ArrayList<>();
		for (Object p: jsonPlayers) {
			String playerName = (String)((JSONObject) p).get("playerName");
			String position = (String)((JSONObject) p).get("position");
			Boolean captain = (Boolean)((JSONObject) p).get("captain");
			double age = Double.parseDouble(((JSONObject) p).get("age").toString());
			double skating = Double.parseDouble(((JSONObject) p).get("skating").toString());
			double shooting = Double.parseDouble(((JSONObject) p).get("shooting").toString());
			double checking = Double.parseDouble(((JSONObject) p).get("checking").toString());
			double saving = Double.parseDouble(((JSONObject) p).get("saving").toString());
			if (playerName == null ||position == null || captain == null || age < 0 || skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			PlayerModel newPlayer = new PlayerModel(playerName, position, captain, age, skating, shooting, checking, saving);
			FreeAgentConstant validationResult  = leagueModel.validatePlayer(newPlayer);
			if (validationResult.equals(FreeAgentConstant.Success)) {
				players.add(newPlayer);
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return players;
	}

	private ArrayList<FreeAgentModel> createFreeAgents(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<FreeAgentModel> players = new ArrayList<>();
		for (Object p: jsonPlayers) {
			String playerName = (String)((JSONObject) p).get("playerName");
			String position = (String)((JSONObject) p).get("position");
			double age = Double.parseDouble(((JSONObject) p).get("age").toString());
			double skating = Double.parseDouble(((JSONObject) p).get("skating").toString());
			double shooting = Double.parseDouble(((JSONObject) p).get("shooting").toString());
			double checking = Double.parseDouble(((JSONObject) p).get("checking").toString());
			double saving = Double.parseDouble(((JSONObject) p).get("saving").toString());
			if (playerName == null ||position == null || age < 0 || skating < 0 || shooting < 0 || checking < 0 || saving < 0) {
				playerCommunication.sendMessage("player missing field");
				return null;
			}
			FreeAgentModel newPlayer = new FreeAgentModel(playerName, position, age, skating, shooting, checking, saving);
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

	private ArrayList<CoachModel> createFreeCoaches(JSONArray jsonCoaches) {
		if (jsonCoaches == null){
			return null;
		}
		ArrayList<CoachModel> coaches = new ArrayList<>();
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

	private ArrayList<ManagerModel> createFreeManagers(JSONArray jsonManagers) {
		if (jsonManagers == null){
			return null;
		}
		ArrayList<ManagerModel> managers = new ArrayList<>();
		for (int i=0; i<jsonManagers.size(); i++){
			String name = (String) jsonManagers.get(i);
			if (name.isEmpty()) {
				playerCommunication.sendMessage(("Manager name is empty"));
			}
			ManagerModel managerObject = new ManagerModel();
			managerObject.setName(name);
			ManagerConstant validationResult  = managerObject.validate();
			if (validationResult.equals(ManagerConstant.Success)) {
				managers.add(managerObject);
			} else {
				playerCommunication.sendMessage(validationResult.getValue());
				return null;
			}
		}
		return managers;
	}

}
