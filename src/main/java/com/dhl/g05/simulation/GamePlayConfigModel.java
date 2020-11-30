package com.dhl.g05.simulation;

public class GamePlayConfigModel implements IGamePlayConfig {
	ITradingConfig trading;
	IAging aging;
	IInjury injuries;
	IGameResolver gameResolver;
	ITraining training;

	public GamePlayConfigModel() {
		this.trading = null;
		this.aging = null;
		this.injuries = null;
		this.gameResolver = null;
		this.training = null;
	}
	
	@Override
	public void setGameResolverConfig(IGameResolver gameResolver) {
		this.gameResolver = gameResolver;
	}

	@Override
	public void setAgingConfig(IAging aging) {
		this.aging = aging;
	}

	@Override
	public IAging getAgingConfig() {
		return aging;
	}
	
	@Override
	public void setInjuriesConfig(IInjury injuries) {
		this.injuries = injuries;
	}
	
	@Override
	public IInjury getInjuriesConfig() {
		return injuries;
	}

	@Override
	public void setTrainingConfig(ITraining training) {
		this.training = training;
	}
	
	@Override
	public ITraining getTrainingConfig() {
		return training;
	}

	@Override
	public ITradingConfig getTradingConfig() {
		return trading;
	}
	
	@Override
	public void setTradingConfig(ITradingConfig trading) {
		this.trading = trading;
	}

	@Override
	public IGameResolver getGameResolverConfig() {
		return gameResolver;
	}
	
}
