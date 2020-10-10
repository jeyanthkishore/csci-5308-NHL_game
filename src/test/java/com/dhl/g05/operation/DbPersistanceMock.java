package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class DbPersistanceMock implements IDataBasePersistence{

	@Override
	public void loadModel(OperationModel operationModel) {
		if(operationModel.getTeamName().equals("HockeyLeague")) {
			JsonMockDataDb mockData = new JsonMockDataDb();
			operationModel.setLeagueObject(mockData.getLeague());
		}else {
			operationModel.setLeagueObject(null);
		}
		
	}

	@Override
	public void saveModel(OperationModel operationModel) {
		if(operationModel!=null) {
			operationModel.setResult("success");
		}
	}

	@Override
	public boolean checkLeagueExistence(OperationModel operationModel) {
		if(operationModel.getLeagueName().equalsIgnoreCase("HockeyLeague")) {
			return true;
		}
		return false;
	}

	@Override
	public void loadNewTeams(OperationModel operationModel) {
		ArrayList<HashMap<String,Object>> teamNames = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> team = new HashMap<String,Object>();
		team.put("team_name","Dog");
		teamNames.add(team);
		team.put("team_name","Cat");
		teamNames.add(team);
		operationModel.setNewTeam(teamNames);
	}
}
