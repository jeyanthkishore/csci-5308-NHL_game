package com.dhl.g05.league;

import java.util.ArrayList;
import java.util.HashMap;

import com.dhl.g05.conference.ConferenceModel;

public class LeaguePersistenceMock implements ILeagueModelPersistence{
	@Override
	public ArrayList<HashMap<String, Object>> loadDetails() {
		ArrayList<HashMap<String,Object>> leagueNames = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> league = new HashMap<String,Object>();
		league.put("league_name","HockeyLeague");
		leagueNames.add(league);
		league = new HashMap<String,Object>();
		league.put("league_name","CanadaLeague");
		leagueNames.add(league);
		return leagueNames;
	}

	@Override
	public int saveLeagueObject(LeagueModel leagueObject) {
		if(leagueObject.getLeagueName().equals("HockeyLeague")) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int loadLeagueObject(int leagueId,LeagueModel leagueObject) {
		if(leagueId == 1) {
			ArrayList<ConferenceModel> conference = new ArrayList<ConferenceModel>();
			conference.add(new ConferenceModel("Western Conference",null));
			leagueObject.setLeagueName("HockeyLeague");
			leagueObject.setConferenceDetails(conference);
			return 1;
		}
		return 0;
	}

	@Override
	public int loadLeagueFromTeam(String teamName) {
		if(teamName.equals("Striker Six")) {
			return 1;
		}
		return 0;
	}
}
