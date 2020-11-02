package com.dhl.g05.gameplayconfig;

public class GamePlayConfigModel implements IGamePlayConfig {
	
	TradingModel trading;
	Aging aging;
	Injury injuries;
	GameResolverConfig gameResolver;
	TrainingConfig training;

	public GamePlayConfigModel(TradingModel trade,Aging age,Injury injury,GameResolverConfig gameresolve,TrainingConfig train) {
		this.trading = trade;
		this.aging = age;
		this.injuries = injury;
		this.gameResolver = gameresolve;
		this.training = train;
	}
	
	public Injury getInjuries() {
		return injuries;
	}

	public GameResolverConfig getGameResolve() {
		return gameResolver;
	}

	public TrainingConfig getTraining() {
		return training;
	}

	public Aging getAging() {
		return aging;
	}

	public TradingModel getTrading() {
		return trading;
	}

	public int saveGamePlayObject(int leagueId,IGameConfigPersistence database) {
		return database.saveGamePlayObject(leagueId,this);
	}
}