package com.dhl.g05.gameplayconfig;

public class GamePlayConfigFactory extends AbstractGamePlayConfigFactory{

    @Override
    public IAging getAging() {
        return new Aging();
    }

    @Override
    public IInjury getInjury() {
        return new Injury();
    }

    @Override
    public ITradingConfig getTradingConfig() {
        return new TradingConfig();
    }

    @Override
    public ITraining getTraining() {
        return new TrainingConfig();
    }

	@Override
	public IGameResolver getGameResolver() {
		return new GameResolverConfig();
	}
}
