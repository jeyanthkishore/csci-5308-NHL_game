package com.dhl.g05.leaguemodel.manager;

public interface IManagerPersistence {
	
	public int saveLeagueManagerObject(int league_id, ManagerObject managerObject);


	public int loadLeagueManagerObject(String leagueName, ManagerObject managerObject);
	
}
