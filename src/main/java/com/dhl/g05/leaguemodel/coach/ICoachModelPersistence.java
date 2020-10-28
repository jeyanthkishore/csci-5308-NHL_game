package com.dhl.g05.leaguemodel.coach;

import com.dhl.g05.leaguemodel.league.LeagueModel;

public interface ICoachModelPersistence {
	
	public int loadLeagueCoachObject(String leagueName, LeagueModel league);
	
	public int saveLeagueCoachObject(int league_id, LeagueModel leagueObject);
}
