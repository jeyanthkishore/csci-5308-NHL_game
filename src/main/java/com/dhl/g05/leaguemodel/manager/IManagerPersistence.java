package com.dhl.g05.leaguemodel.manager;

import com.dhl.g05.leaguemodel.league.LeagueModel;

public interface IManagerPersistence {

	public int saveLeagueManagerObject(int league_id, LeagueModel leagueObject);

	public int loadLeagueManagerObject(String leagueName, LeagueModel leagueObject);

}
