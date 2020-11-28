package com.dhl.g05.simulation;

public interface IGamePlayConfig {

	IInjury getInjuries();

	IGameResolver getGameResolver();

	void setGameResolver(IGameResolver gameResolver);

	void setTrading(ITradingConfig trading);

	void setAging(IAging aging);

	void setInjuries(IInjury injury);

	void setTraining(ITraining training);

	ITraining getTraining();

	IAging getAging();

	ITradingConfig getTrading();

	/*IGameSimulationConfig getGameSimulationConfig();

	void setGameSimulationConfig(IGameSimulationConfig gameSimulationConfig);*/

}