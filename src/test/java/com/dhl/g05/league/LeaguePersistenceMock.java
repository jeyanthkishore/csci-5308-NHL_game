package com.dhl.g05.league;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;

public class LeaguePersistenceMock implements ILeagueModelPersistence{
	@Override
	public ArrayList<HashMap<String, Object>> loadDetails() {
		ArrayList<HashMap<String,Object>> leagueNames = new ArrayList<>();
		HashMap<String,Object> league = new HashMap<String,Object>();
		league.put("league_name","HockeyLeague");
		leagueNames.add(league);
		league = new HashMap<String,Object>();
		league.put("league_name","CanadaLeague");
		leagueNames.add(league);
		return leagueNames;
	}

	@Override
	public boolean saveLeagueObject(LeagueModel leagueObject) {
		if(leagueObject.getLeagueName().equals("HockeyLeague")) {
			return true;
		}
		return false;
	}
	
	@Override
	public int loadLeagueObject(int leagueId,LeagueModel leagueObject) {
		if(leagueId == 1) {
			List<IConference> conference = new ArrayList<>();
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
