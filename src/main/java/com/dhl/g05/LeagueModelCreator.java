package com.dhl.g05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dhl.g05.leagueobjects.*;

public class LeagueModelCreator {
	
	private File file;
	private FileReader reader;
	private JSONParser parser;


	public LeagueModelCreator() {
		parser = new JSONParser();
	}
	
	public LeagueObject createLeagueFromFile(String fileName) {
		return null;
	}
	
	private LeagueObject createLeague(JSONObject leagueData, LeagueObject league) {
		return null;
	}
	
	private Map<String,ConferenceObject> createConferences(JSONArray jsonConferences) {
		return null;
	}
	
	private Map<String,DivisionObject> createDivisions(JSONArray jsonDivisions) {
		return null;
	}
	
	private Map<String,TeamObject> createTeams(JSONArray teams) {
		return null;
	}
	
	private Map<String,PlayerObject> createPlayers(JSONArray players) {
		return null;
	}
	
	private Map<String,PlayerObject> createFreeAgents(JSONArray freeAgents) {
		return null;
	}
	
	private JSONObject parse(File file) throws IOException, ParseException {
		return null;
	}
	

}
