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

public class LeagueModelCreator {
	
	private FileReader reader;

	private JSONParser parser;

	private ILeagueModel leagueModel;

	private IPlayerCommunication playerCommunication;

	public LeagueModelCreator(ILeagueModel leagueModel, IPlayerCommunication playerCommunication) {

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

		ArrayList<FreeAgentObject> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
		
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

			int age = Integer.parseInt(((JSONObject) p).get("age").toString());

			int skating = Integer.parseInt(((JSONObject) p).get("skating").toString());

			int shooting = Integer.parseInt(((JSONObject) p).get("shooting").toString());

			int checking = Integer.parseInt(((JSONObject) p).get("checking").toString());

			int saving = Integer.parseInt(((JSONObject) p).get("saving").toString());

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

		if (jsonPlayers == null) return null;
	
		ArrayList<FreeAgentObject> players = new ArrayList<>();
		
		for (Object p: jsonPlayers) {

			String playerName = (String)((JSONObject) p).get("playerName");

			String position = (String)((JSONObject) p).get("position");

			int age = Integer.parseInt(((JSONObject) p).get("age").toString());

			int skating = Integer.parseInt(((JSONObject) p).get("skating").toString());

			int shooting = Integer.parseInt(((JSONObject) p).get("shooting").toString());

			int checking = Integer.parseInt(((JSONObject) p).get("checking").toString());

			int saving = Integer.parseInt(((JSONObject) p).get("saving").toString());

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
	
}
