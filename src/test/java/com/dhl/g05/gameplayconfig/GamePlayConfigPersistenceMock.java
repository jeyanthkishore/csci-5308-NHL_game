package com.dhl.g05.gameplayconfig;

import com.dhl.g05.mockdata.JsonMockDataDb;

public class GamePlayConfigPersistenceMock implements IGameConfigPersistence,ILoadGamePlayConfig{

	@Override
	public int saveGamePlayObject(int leagueId, GamePlayConfigModel gamePlayConfigModel) {
		if(leagueId==1) {
			return 1;
		}
		return 0;
	}

	@Override
	public GamePlayConfigModel loadGamePlayObject(String leaguename) {
		JsonMockDataDb mock = new JsonMockDataDb();
		if(leaguename.equals("HockeyLeague")) {
			return mock.gamePlayConfig;
		}
		return null;
	}
}
