package com.dhl.g05.statemachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
			
			JSONObject training = (JSONObject)gamePlayConfigs.get("training");
			
			JSONObject aging = (JSONObject)gamePlayConfigs.get("aging");
			
			JSONObject gameResolver = (JSONObject)gamePlayConfigs.get("gameResolver");
			
			JSONObject injuries = (JSONObject)gamePlayConfigs.get("injuries");
			
			JSONObject trading = (JSONObject)gamePlayConfigs.get("trading");
			
			if (setTrainingConfig(training) == false) {
				
				return false;
				
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
		
		File file = new File(fileName);
		reader = new FileReader(file);
		
		LeagueObject league = createLeague((JSONObject)parser.parse(reader));
		
		leagueModel.setLeague(league);
		return (leagueModel.getLeague() != null);
	}
	
	private LeagueObject createLeague(JSONObject leagueData) {
		if (leagueData == null) return null;
		
		ArrayList<ConferenceObject> conferences = createConferences((JSONArray)leagueData.get("conferences"));
		ArrayList<PlayerObject> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
	
		
		String leagueName = (String)leagueData.get("leagueName");
		
		
		if (conferences != null && freeAgents != null) {
			LeagueObject league = leagueModel.createLeague(leagueName,conferences,freeAgents); 
			String validationResult  =  leagueModel.validateLeague(league);
			
			if (validationResult.equalsIgnoreCase("Success")) {
				return league;
			} else {
				playerCommunication.sendMessage(validationResult);
			}
		} 
		return null;
	}
	
	
	private ArrayList<ConferenceObject> createConferences(JSONArray jsonConferences) {
		if (jsonConferences == null) return null;

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
		if (jsonDivisions == null) return null;
		
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
		if (jsonTeams == null) return null;
		
		ArrayList<TeamObject> teams = new ArrayList<>();
	
		for (Object t: jsonTeams) {
			
			ArrayList<PlayerObject> players = createPlayers((JSONArray)((JSONObject) t).get("players"));
			
			String teamName = (String)((JSONObject) t).get("teamName");
			String managerName = (String)((JSONObject) t).get("generalManager");
			String coachName = (String)((JSONObject) t).get("headCoach");
			
			
			if (players != null) {
				TeamObject newTeam = new TeamObject(teamName, managerName, coachName, players);
				
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
		if (jsonPlayers == null) return null;
	
		ArrayList<PlayerObject> players = new ArrayList<>();
		
		for (Object p: jsonPlayers) {
			
			
			
			String playerName = (String)((JSONObject) p).get("playerName");
			String position = (String)((JSONObject) p).get("position");
			Boolean captain = (Boolean)((JSONObject) p).get("captain");
			
			if (playerName == null ||position == null || captain == null) {
				playerCommunication.sendMessage("player missing field");
				return null;
				
			}
			
			PlayerObject newPlayer = new PlayerObject(playerName, position, captain);
			
				
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
	
	private ArrayList<PlayerObject> createFreeAgents(JSONArray freeAgents) {
		
		return createPlayers(freeAgents);	
	
	}
	
}
