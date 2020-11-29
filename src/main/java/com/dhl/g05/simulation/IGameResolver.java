package com.dhl.g05.simulation;

public interface IGameResolver {

	void setRandomWinChance(double winChance);
	
	public double getRandomWinChance();
	
	public GameResolverConstant Validate();
	
}
