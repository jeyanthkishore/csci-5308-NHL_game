package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.manager.ManagerModel;

public interface IManagerLoad {

	public List<ManagerModel> loadLeagueManagerObject(String leagueName);
	
}
