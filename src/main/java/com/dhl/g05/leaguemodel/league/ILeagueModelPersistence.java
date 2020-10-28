package com.dhl.g05.leaguemodel.league;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILeagueModelPersistence {
	
	public ArrayList<HashMap<String, Object>> loadDetails();

	public int saveLeagueObject(LeagueObject leagueObject);
	
	public int loadLeagueObject(String leagueName, LeagueObject leagueObject);
	
}
