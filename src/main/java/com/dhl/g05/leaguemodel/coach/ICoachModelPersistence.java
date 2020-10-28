package com.dhl.g05.leaguemodel.coach;

import com.dhl.g05.leaguemodel.league.LeagueObject;

public interface ICoachModelPersistence {
	
	public int loadLeagueCoachObject(String leagueName, LeagueObject league);
	
	public int saveLeagueCoachObject(int league_id, LeagueObject leagueObject);
}
