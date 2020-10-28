package com.dhl.g05.leaguemodel.player;

public interface IPlayerModelPersistence {
	
	public int savePlayerObject(int teamId, PlayerObject playerObject);
	
	public int loadPlayerObject(int teamId, PlayerObject playerObject);

}
