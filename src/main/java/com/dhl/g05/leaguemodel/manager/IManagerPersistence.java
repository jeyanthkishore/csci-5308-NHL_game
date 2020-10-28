package com.dhl.g05.leaguemodel.manager;

public interface IManagerPersistence {
	
	public int saveLeagueManagerObject(int league_id, ManagerModel managerObject);


	public int loadLeagueManagerObject(String leagueName, ManagerModel managerObject);
	
}
