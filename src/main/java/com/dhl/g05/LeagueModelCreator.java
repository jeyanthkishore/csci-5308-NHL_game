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
	private ILeagueModel leagueModel;

	public LeagueModelCreator(ILeagueModel leagueModel) {
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
	
	public boolean createLeagueFromFile(String fileName) throws FileNotFoundException, IOException, ParseException {
		
		reader = new FileReader(new File(fileName));
		LeagueObject league = (createLeague((JSONObject)parser.parse(reader)));
		leagueModel.setLeague(league);
		return (leagueModel.getLeague() == null);
	}
	
	private LeagueObject createLeague(JSONObject leagueData) {
		if (leagueData == null) return null;
		
		ArrayList<ConferenceObject> conferences = createConferences((JSONArray)leagueData.get("conferences"));
		ArrayList<PlayerObject> freeAgents = createFreeAgents((JSONArray)leagueData.get("freeAgents"));
		String leagueName = (String)leagueData.get("leagueName");
		
		LeagueObject league = leagueModel.createLeague(leagueName,conferences,freeAgents); 
		
		if (leagueModel.validateLeague(league)) {
			
			return league;
		}
		
		return null;
	}
	
	
	private ArrayList<ConferenceObject> createConferences(JSONArray jsonConferences) {
		if (jsonConferences == null) return null;
		
		ArrayList<ConferenceObject> conferences = new ArrayList<>();
		
		for (Object c: jsonConferences) {
			
			ArrayList<DivisionObject> divisions = createDivisions((JSONArray)((JSONObject) c).get("divisions"));
			String conferenceName = (String)((JSONObject) c).get("conferenceName");
			ConferenceObject newConference = leagueModel.createConference(conferenceName, divisions);
			
			if (leagueModel.validateConference(newConference)) {
				
				conferences.add(newConference);
				
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
			DivisionObject newDivision = leagueModel.createDivision(divisionName,teams);
			
			if (leagueModel.validateDivision(newDivision)) {
				
				divisions.add(newDivision);
				
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
			
			TeamObject newTeam = leagueModel.createTeam(teamName, managerName, coachName, players);
			
			if (leagueModel.validateTeam(newTeam)) {
				
				teams.add(newTeam);
				
			} else {

				return null;
			
			}
		}
		
		return teams;
	
	}
	
	
	private ArrayList<PlayerObject> createPlayers(JSONArray jsonPlayers) { 
		if (jsonPlayers == null) return null;
	
		ArrayList<PlayerObject> players = new ArrayList<>();
		
		for (Object p: jsonPlayers) {
			
			HashMap<String,Object> playerDetails = new HashMap<>();
			
			String playerName = (String)((JSONObject) p).get("playerName");
			
			playerDetails.put("playerName",playerName);
			playerDetails.put("position",(String)((JSONObject) p).get("position"));
			playerDetails.put("captain",(Boolean)((JSONObject) p).get("captain"));
			
			PlayerObject newPlayer = leagueModel.createPlayer(playerDetails);
			
			if (leagueModel.validatePlayer(newPlayer)) {
				
				players.add(newPlayer);
				
			} else {
				
				return null;
				
			}
		}
		
		return players;
	}
	
	private ArrayList<PlayerObject> createFreeAgents(JSONArray freeAgents) {
		
		return createPlayers(freeAgents);	
	
	}
	
}
