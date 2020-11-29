package com.dhl.g05.simulation;

public interface IGamePlayConfig {

	IInjury getInjuriesConfig();

	IGameResolver getGameResolverConfig();

	void setGameResolverConfig(IGameResolver gameResolver);

	void setTradingConfig(ITradingConfig trading);

	void setAgingConfig(IAging aging);

	void setInjuriesConfig(IInjury injury);

	void setTrainingConfig(ITraining training);

	ITraining getTrainingConfig();

	IAging getAgingConfig();

	ITradingConfig getTradingConfig();

	/*IGameSimulationConfig getGameSimulationConfig();

	void setGameSimulationConfig(IGameSimulationConfig gameSimulationConfig);*/

}