package com.dhl.g05.gameplayconfig;

public class GameResolverConfig implements IGameResolver{

	private double randomWinChance;
	
	public GameResolverConfig(double winChance) {
		this.randomWinChance = winChance;
	}

	public double getRandomWinChance() {
		return randomWinChance;
	}
	
}
