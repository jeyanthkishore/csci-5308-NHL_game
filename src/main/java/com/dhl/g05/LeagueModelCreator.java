package com.dhl.g05;

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

import com.dhl.g05.leagueobjects.*;

public class LeagueModelCreator {
	
	private FileReader reader;
	private JSONParser parser;


	public LeagueModelCreator() {
		
		parser = new JSONParser();
		
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
	
	public LeagueObject createLeagueFromFile(String fileName) throws FileNotFoundException, IOException, ParseException {
		
		reader = new FileReader(new File(fileName));
		LeagueObject league = createLeague((JSONObject)parser.parse(reader));
		reader.close();
		
		return league;
		
	}
	
	private LeagueObject createLeague(JSONObject leagueData) {
		if (leagueData == null) return null;
		
		HashMap<String,ConferenceObject> conferences = createConferences((JSONArray)leagueData.get("conferences"));
		HashMap<String,PlayerObject> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
		String leagueName = (String)leagueData.get("leagueName");
		
		LeagueObject league = new LeagueObject(leagueName,conferences,freeAgents); 
		
		if (league.validate()) return league;
		
		return null;
	}
	
	
	private HashMap<String,ConferenceObject> createConferences(JSONArray jsonConferences) {
		if (jsonConferences == null) return null;
		
		HashMap<String,ConferenceObject> conferences = new HashMap<>();
		
		for (Object c: jsonConferences) {
			
			HashMap<String,DivisionObject> divisions = createDivisions((JSONArray)((JSONObject) c).get("divisions"));
			String conferenceName = (String)((JSONObject) c).get("conferenceName");
			ConferenceObject newConference = new ConferenceObject(conferenceName,divisions);
			
			if (newConference.validate()) {
				
				conferences.put(conferenceName,newConference);
				
			} else {
				
				return null;
				
			}
		}
		
		return conferences;
	}
	
	
	private HashMap<String,DivisionObject> createDivisions(JSONArray jsonDivisions) {
		if (jsonDivisions == null) return null;
		
		HashMap<String,DivisionObject> divisions = new HashMap<>();
		
		for (Object d: jsonDivisions) {
			
			HashMap<String,TeamObject> teams = createTeams((JSONArray)((JSONObject) d).get("teams"));
			String divisionName = (String)((JSONObject) d).get("divisionName");
			DivisionObject newDivision = new DivisionObject(divisionName,teams);
			
			if (newDivision.validate()) {
				
				divisions.put(divisionName,newDivision);
				
			} else {
				
				return null;
				
			}
		}
		
		return divisions;
	}
	
	
	private HashMap<String,TeamObject> createTeams(JSONArray jsonTeams) {
		if (jsonTeams == null) return null;
		
		HashMap<String,TeamObject> teams = new HashMap<>();
	
		for (Object t: jsonTeams) {
			
			HashMap<String,PlayerObject> players = createPlayers((JSONArray)((JSONObject) t).get("players"));
			
			String teamName = (String)((JSONObject) t).get("teamName");
			String managerName = (String)((JSONObject) t).get("generalManager");
			String coachName = (String)((JSONObject) t).get("headCoach");
			
			TeamObject newTeam = new TeamObject(teamName, managerName, coachName, players);
			
			if (newTeam.validate()) {
				
				teams.put(teamName,newTeam);
				
			} else {

				return null;
			
			}
		}
		
		return teams;
	
	}
	
	
	private HashMap<String,PlayerObject> createPlayers(JSONArray jsonPlayers) { 
		if (jsonPlayers == null) return null;
	
		HashMap<String,PlayerObject> players = new HashMap<>();
		
		for (Object p: jsonPlayers) {
			
			ArrayList<Object> playerDetails = new ArrayList<>();
			
			String playerName = (String)((JSONObject) p).get("playerName");
			
			playerDetails.add(playerName);
			playerDetails.add((String)((JSONObject) p).get("position"));
			playerDetails.add((Boolean)((JSONObject) p).get("captain"));
			
			PlayerObject newPlayer = new PlayerObject(playerDetails);
			
			if (newPlayer.validate()) {
				
				players.put(playerName,newPlayer);
				
			} else {
				
				return null;
				
			}
		}
		
		return players;
	}
	
	private HashMap<String,PlayerObject> createFreeAgents(JSONArray freeAgents) {
		
		return createPlayers(freeAgents);	
	
	}
	
}
