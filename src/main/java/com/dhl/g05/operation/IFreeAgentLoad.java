package com.dhl.g05.operation;

import java.util.List;

import com.dhl.g05.leaguemodel.freeagent.FreeAgentModel;

public interface IFreeAgentLoad {

	public List<FreeAgentModel> loadFreeAgentObject(String leagueName);
	
}
