package com.dhl.g05.leaguemodel.league;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILeagueModelPersistence {
	
	public ArrayList<HashMap<String, Object>> loadDetails();

	public int saveLeagueObject(LeagueModel leagueObject);
	
	public int loadLeagueObject(String leagueName, LeagueModel leagueObject);
	
}
