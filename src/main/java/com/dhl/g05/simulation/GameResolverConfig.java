package com.dhl.g05.simulation;

public class GameResolverConfig implements IGameResolver{

	private double randomWinChance;

	public void setRandomWinChance(double winChance) {
		this.randomWinChance = winChance;
	}

	public double getRandomWinChance() {
		return randomWinChance;
	}
	
	@Override
	public GameResolverConstant Validate() {
		if(randomWinChance > 1 || randomWinChance < 0) {
			return GameResolverConstant.RandWinError;
		}
		return GameResolverConstant.Success;
	}
	
}
