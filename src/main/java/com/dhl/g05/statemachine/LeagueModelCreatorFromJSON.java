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

import com.dhl.g05.leaguemodel.*;
import com.dhl.g05.simulation.Date;

public class LeagueModelCreatorFromJSON {
	
	private FileReader reader;
	private JSONParser parser;
	private ILeagueModel leagueModel;
	private IPlayerCommunication playerCommunication;

	public LeagueModelCreatorFromJSON(ILeagueModel leagueModel, IPlayerCommunication playerCommunication) {
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
			if (gamePlayConfigs == null) {
				return false;
			} else {
				JSONObject training = (JSONObject)gamePlayConfigs.get("training");
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
		LeagueObject league = createLeague((JSONObject)parser.parse(reader));
		leagueModel.setLeague(league);
		return (leagueModel.getLeague() != null);
	}
	
	private LeagueObject createLeague(JSONObject leagueData) {
		if (leagueData == null) {
			return null;
		}
		ArrayList<ConferenceObject> conferences = createConferences((JSONArray)leagueData.get("conferences"));
		ArrayList<FreeAgentObject> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
		ArrayList<CoachObject> freeCoaches = createFreeCoaches((JSONArray)leagueData.get("coaches"));
		ArrayList<ManagerObject> managers = createFreeManagers((JSONArray)leagueData.get("generalManagers"));
		ArrayList<ManagerObject> managerList = new ArrayList<>();
		String leagueName = (String)leagueData.get("leagueName");
		if (conferences != null && freeAgents != null && freeCoaches != null) {
			LeagueObject league = leagueModel.createLeague(leagueName,conferences,freeAgents, freeCoaches);
			String validationResult  =  leagueModel.validateLeague(league);
			if (validationResult.equalsIgnoreCase("Success")) {
				if ( managers != null){
					System.out.println(managers);
					ManagerObject managerObject = new ManagerObject(managers);
					managerList.add(managerObject);
				}
				return league;
			} else {
				playerCommunication.sendMessage(validationResult);
			}
		}
		return null;
	}

	private ArrayList<ConferenceObject> createConferences(JSONArray jsonConferences) {
		if (jsonConferences == null) {
			return null;
		}
		ArrayList<ConferenceObject> conferences = new ArrayList<>();
		for (Object c: jsonConferences) {
			ArrayList<DivisionObject> divisions = createDivisions((JSONArray)((JSONObject) c).get("divisions"));
			String conferenceName = (String)((JSONObject) c).get("conferenceName");
			if (divisions != null) {
				ConferenceObject newConference = new ConferenceObject(conferenceName, divisions);
				String validationResult  = leagueModel.validateConference(newConference);
				if (validationResult.equalsIgnoreCase("Success")) {
					conferences.add(newConference);
				} else {
					playerCommunication.sendMessage(validationResult);
					return null;
				}
			} else {
				return null;
			}
		}
		return conferences;
	}

	private ArrayList<DivisionObject> createDivisions(JSONArray jsonDivisions) {
		if (jsonDivisions == null) {
			return null;
		}
		ArrayList<DivisionObject> divisions = new ArrayList<>();
		for (Object d: jsonDivisions) {
			ArrayList<TeamObject> teams = createTeams((JSONArray)((JSONObject) d).get("teams"));
			String divisionName = (String)((JSONObject) d).get("divisionName");
			if (teams != null) {
				DivisionObject newDivision = new DivisionObject(divisionName,teams);
				String validationResult  = leagueModel.validateDivision(newDivision);
				if (validationResult.equalsIgnoreCase("Success")) {
					divisions.add(newDivision);
				} else {
					playerCommunication.sendMessage(validationResult);
					return null;
				}
			} else {
				return null;
			}
		}
		return divisions;
	}

	private ArrayList<TeamObject> createTeams(JSONArray jsonTeams) {
		if (jsonTeams == null) {
			return null;
		}
		ArrayList<TeamObject> teams = new ArrayList<>();
		for (Object t: jsonTeams) {
			ArrayList<PlayerObject> players = createPlayers((JSONArray)((JSONObject) t).get("players"));
			String teamName = (String)((JSONObject) t).get("teamName");
			String managerName = (String)((JSONObject) t).get("generalManager");
			JSONObject coach = (JSONObject) ((JSONObject) t).get("headCoach");
			CoachObject coachDetails = createCoach(coach);
			if (players != null && teamName != null && managerName != null && coachDetails != null) {
				TeamObject newTeam = new TeamObject(teamName, coachDetails, managerName, players);
				String validationResult  = leagueModel.validateTeam(newTeam);
				if (validationResult.equalsIgnoreCase("Success")) {
					teams.add(newTeam);
				}  else {
					playerCommunication.sendMessage(validationResult);
					return null;
				}
			} else
				return null;
		}
		return teams;
	}

	private ArrayList<PlayerObject> createPlayers(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<PlayerObject> players = new ArrayList<>();
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
			PlayerObject newPlayer = new PlayerObject(playerName, position, captain, age, skating, shooting, checking, saving);
			String validationResult  = leagueModel.validatePlayer(newPlayer);
			if (validationResult.equalsIgnoreCase("Success")) {
				players.add(newPlayer);
			} else {
				playerCommunication.sendMessage(validationResult);
				return null;
			}
		}
		return players;
	}

	private ArrayList<FreeAgentObject> createFreeAgents(JSONArray jsonPlayers) {
		if (jsonPlayers == null) {
			return null;
		}
		ArrayList<FreeAgentObject> players = new ArrayList<>();
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
			FreeAgentObject newPlayer = new FreeAgentObject(playerName, position, age, skating, shooting, checking, saving);
			String validationResult  = newPlayer.getResult();
			if (validationResult.equalsIgnoreCase("Success")) {
				players.add(newPlayer);
			} else {
				playerCommunication.sendMessage(validationResult);
				return null;
			}
		}
		return players;
	}

	private CoachObject createCoach(JSONObject jsonCoachDetails) {
		if (jsonCoachDetails == null){
			return null;
		}
		String coachName = (String) jsonCoachDetails.get("name");
		double skating = Double.parseDouble(jsonCoachDetails.get("skating").toString());
		double shooting = Double.parseDouble(jsonCoachDetails.get("shooting").toString());
		double checking = Double.parseDouble(jsonCoachDetails.get("checking").toString());
		double saving = Double.parseDouble(jsonCoachDetails.get("saving").toString());
		return new CoachObject(coachName,skating,shooting,checking,saving);
	}

	private ArrayList<CoachObject> createFreeCoaches(JSONArray jsonCoaches) {
		if (jsonCoaches == null){
			return null;
		}
		ArrayList<CoachObject> coaches = new ArrayList<>();
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
			CoachObject newCoach = new CoachObject(coachName,skating,shooting,checking,saving);
			String validationResult  = newCoach.getResult();
			if (validationResult.equalsIgnoreCase("Success")) {
				coaches.add(newCoach);
			} else {
				playerCommunication.sendMessage(validationResult);
				return null;
			}
		}
		return coaches;
	}

	private ArrayList<ManagerObject> createFreeManagers(JSONArray jsonManagers) {
		if (jsonManagers == null){
			return null;
		}
		ArrayList<ManagerObject> managers = new ArrayList<>();
		for (int i=0; i<jsonManagers.size(); i++){
			String name = (String) jsonManagers.get(i);
			if (name.isEmpty()) {
				playerCommunication.sendMessage(("Manager name is empty"));
			}
			ManagerObject managerObject = new ManagerObject();
			managerObject.setName(name);
			String validationResult  = managerObject.validate();
			if (validationResult.equalsIgnoreCase("Success")) {
				managers.add(managerObject);
			} else {
				playerCommunication.sendMessage(validationResult);
				return null;
			}
		}
		return managers;
	}

}
