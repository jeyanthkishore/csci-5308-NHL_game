package com.dhl.g05.gameplayconfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;

public class GamePlayPersistence implements IGameConfigPersistence,ILoadGamePlayConfig{

	@Override
	public int saveGamePlayObject(int leagueId, GamePlayConfigModel gamePlayConfigModel) {
		StoredProcedure sp = new StoredProcedure();
		GameResolverConfig resolve = gamePlayConfigModel.getGameResolve();
		double randomWinChance = resolve.getRandomWinChance();
		int resolveId = sp.saveGameResolver(leagueId,randomWinChance);
		if(resolveId == 0) {
			return 0;
		}
		TrainingConfig training = gamePlayConfigModel.getTraining();
		int daysWithoutTraining = training.getDaysUntilStatIncreaseCheck();
		int trainingId = sp.saveTraining(leagueId,daysWithoutTraining);
		if(trainingId == 0) {
			return 0;
		}
		return 0;
	}

	@Override
	public GamePlayConfigModel loadGamePlayObject(String leagueName) {
		StoredProcedure sp = new StoredProcedure();
		List<HashMap<String,Object>> gameValue = new ArrayList<HashMap<String,Object>>();
		gameValue = sp.fetchAllGameConfig(leagueName);
		double randomWinChance = Double.parseDouble(gameValue.get(0).get("randomwin_chance").toString());
		int daysUntilTrainingCheck = Integer.parseInt(gameValue.get(0).get("daysuntil_statincrease_check").toString());
		GameResolverConfig resolve = new GameResolverConfig(randomWinChance);
		TrainingConfig training = new TrainingConfig(daysUntilTrainingCheck);
		GamePlayConfigModel gamePlay = new GamePlayConfigModel(null, null, null, resolve, training);
		return gamePlay;
	}

}
