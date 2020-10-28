package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.player.IPlayerModelPersistence;
import com.dhl.g05.leaguemodel.player.PlayerModel;

public class PlayerPersistence implements IPlayerModelPersistence{

	@Override
	public int savePlayerObject(int teamId, PlayerModel playerObject) {
		StoredProcedure sp= new StoredProcedure();
		String playerName = playerObject.getPlayerName();
		String position = playerObject.getPosition();
		int positionId = sp.getPositionID(position);
		Boolean captain = playerObject.getCaptain();
		double age = playerObject.getAge();
		double skating = playerObject.getSkating();
		double shooting = playerObject.getShooting();
		double checking = playerObject.getChecking();
		double saving = playerObject.getSaving();
		int captainID = (captain) ? 1 : 0;
		int playerId = sp.savePlayer(teamId,positionId,playerName,captainID,age,skating,shooting,checking,saving);
		return playerId;
	}

	@Override
	public int loadPlayerObject(int teamId, PlayerModel playerObject) {
		String playerName = playerObject.getPlayerName();
		StoredProcedure sp= new StoredProcedure();
		int playerId = sp.getPlayerID(teamId,playerName);
		List<HashMap<String, Object>> playerDetail = new ArrayList<HashMap<String,Object>>();
		playerDetail = sp.fetchPlayerDetails(playerId);
		String position = playerDetail.get(0).get("position_name").toString();
		Boolean captain = Boolean.parseBoolean(playerDetail.get(0).get("player_is_captain").toString());
		double age = Double.parseDouble(playerDetail.get(0).get("age").toString());
		double skating = Double.parseDouble(playerDetail.get(0).get("skating").toString());
		double shooting = Double.parseDouble(playerDetail.get(0).get("shooting").toString());
		double checking = Double.parseDouble(playerDetail.get(0).get("checking").toString());
		double saving = Double.parseDouble(playerDetail.get(0).get("saving").toString());
		playerObject.setPosition(position);
		playerObject.setCaptain(captain);
		playerObject.setAge(age);
		playerObject.setSkating(skating);
		playerObject.setShooting(shooting);
		playerObject.setChecking(checking);
		playerObject.setSaving(saving);

		return playerId;
	}

}
