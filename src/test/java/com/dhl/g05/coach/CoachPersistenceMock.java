package com.dhl.g05.coach;

import java.util.List;

import com.dhl.g05.MockData.JsonMockDataDb;

public class CoachPersistenceMock implements ICoachModelPersistence,ICoachLoad{

	@Override
	public int saveLeagueCoachObject(int league_id, CoachModel object) {
		return 1;
	}
	
	@Override
	public List<CoachModel> loadLeagueCoachObject(String leagueName) {
		JsonMockDataDb mock = new JsonMockDataDb();
		if(leagueName.equalsIgnoreCase("HockeyLeague")) {
			return mock.coachList;
		}
		return null;
	}
	
}
