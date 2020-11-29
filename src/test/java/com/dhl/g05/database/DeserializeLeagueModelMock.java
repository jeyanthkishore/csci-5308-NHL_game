package com.dhl.g05.database;

import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.LeagueMockData;

public class DeserializeLeagueModelMock implements IDeserializeModel {

	@Override
	public ILeague deserializeObjects(String name, ILeague leagueModel) {
		if(name.equalsIgnoreCase("TeamName")) {
			LeagueMockData mock = new LeagueMockData();
			leagueModel = mock.league;
			return leagueModel;
		}
		return null;
	}

}
