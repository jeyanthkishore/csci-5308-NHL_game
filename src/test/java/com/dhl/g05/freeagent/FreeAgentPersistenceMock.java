package com.dhl.g05.freeagent;

import java.util.List;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class FreeAgentPersistenceMock implements IFreeAgentPersistence,IFreeAgentLoad{

	@Override
	public int saveFreeAgentObject(int leagueId, FreeAgentModel freeAgent) {
		if(leagueId==1) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<FreeAgentModel> loadFreeAgentObject(String leagueName) {
		JsonMockDataDb mock = new JsonMockDataDb();
		if(leagueName.equalsIgnoreCase("HockeyLeague")) {
			return mock.freeAgentList;
		}
		return null;
	}
	
}
