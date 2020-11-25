package com.dhl.g05.league;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILeagueModelPersistence {

	public ArrayList<HashMap<String, Object>> loadDetails();

	public boolean saveLeagueObject(LeagueModel leagueObject);

	public int loadLeagueObject(int leagueId, LeagueModel leagueObject);

	public int loadLeagueFromTeam(String teamName);

}
