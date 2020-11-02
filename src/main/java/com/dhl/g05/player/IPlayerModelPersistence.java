package com.dhl.g05.player;

public interface IPlayerModelPersistence {

	public int savePlayerObject(int teamId, PlayerModel playerObject);

	public int loadPlayerObject(int teamId, PlayerModel playerObject);

}
