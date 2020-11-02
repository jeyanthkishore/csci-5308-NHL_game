package com.dhl.g05.player;

public class PlayerPersistenceMock implements IPlayerModelPersistence{

	@Override
	public int savePlayerObject(int teamId,PlayerModel object) {
		if(teamId==1 && object.getPlayerName().equals("Cristiano Ronaldo")) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int loadPlayerObject(int teamId, PlayerModel playerObject) {
		if(teamId==1 && playerObject.getPlayerName().equals("Cristiano Ronaldo")) {
			return 1;
		}
		return 0;
	}
	
}
