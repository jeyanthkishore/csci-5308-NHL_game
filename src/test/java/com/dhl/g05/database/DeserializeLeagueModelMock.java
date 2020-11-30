package com.dhl.g05.database;

import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;

public class DeserializeLeagueModelMock implements IDeserializeModel {

	@Override
	public ILeague deserializeObjects(String name, ILeague leagueModel) {
		ModelMockAbstractFactory modelMockFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		if(name.equalsIgnoreCase("deserializeLeague")) {
			LeagueMockData mock = modelMockFactory.createLeagueMockData();
			leagueModel = mock.getLeague();
			return leagueModel;
		}
		return null;
	}

}
