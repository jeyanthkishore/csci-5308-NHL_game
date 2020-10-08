package com.dhl.g05.operation;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class DbPersistanceMock implements IDataBasePersistence{

	@Override
	public void loadModel(OperationModel operationModel) {
		if(operationModel.getLeagueName().equals("HockeyLeague")
				&& operationModel.getConferenceName().equals("Western Conference")
				&&operationModel.getDivisionName().equals("Atlantic")
				&& operationModel.getTeamName().equals("Striker Six")) {
			JsonMockDataDb mockData = new JsonMockDataDb();
			operationModel.setLeagueObject(mockData.getLeague());
		}else {
			operationModel.setLeagueObject(null);
		}
		
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		
	}

	@Override
	public boolean checkLeagueExistence(OperationModel operationModel) {
		if(operationModel.getLeagueName().equalsIgnoreCase("HockeyLeague")) {
			return true;
		}
		return false;
	}
}
