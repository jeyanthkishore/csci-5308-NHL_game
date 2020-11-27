package com.dhl.g05.gameplayconfig;

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
	public void setGameResolver(IGameResolver gameResolver) {
		this.gameResolver = gameResolver;
	}

	@Override
	public void setAging(IAging aging) {
		this.aging = aging;
	}

	@Override
	public IAging getAging() {
		return aging;
	}
	
	@Override
	public void setInjuries(IInjury injuries) {
		this.injuries = injuries;
	}
	
	@Override
	public IInjury getInjuries() {
		return injuries;
	}


	@Override
	public void setTraining(ITraining training) {
		this.training = training;
	}
	
	@Override
	public ITraining getTraining() {
		return training;
	}

	@Override
	public ITradingConfig getTrading() {
		return trading;
	}
	
	@Override
	public void setTrading(ITradingConfig trading) {
		this.trading = trading;
	}

	@Override
	public IGameResolver getGameResolver() {
		return gameResolver;
	}

}
