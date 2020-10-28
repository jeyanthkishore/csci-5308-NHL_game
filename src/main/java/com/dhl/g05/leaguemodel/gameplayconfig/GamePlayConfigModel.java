package com.dhl.g05.leaguemodel.gameplayconfig;

public class GamePlayConfigModel implements IGamePlayConfig {
	
	TradingModel trading;
	Aging aging;
	Injury injuries;
	GameResolverConfig gameResolve;
	TrainingConfig training;

	public GamePlayConfigModel(TradingModel trade,Aging age,Injury injury,GameResolverConfig gameresolve,TrainingConfig train) {
		this.trading = trade;
		this.aging = age;
		this.injuries = injury;
		this.gameResolve = gameresolve;
		this.training = train;
	}
	
	public Injury getInjuries() {
		return injuries;
	}

	public GameResolverConfig getGameResolve() {
		return gameResolve;
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

}
