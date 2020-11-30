package com.dhl.g05.simulation.statemachine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameResolverConfig implements IGameResolver{

	static final Logger logger = LogManager.getLogger(GameResolverConfig.class);
	private double randomWinChance;

	public void setRandomWinChance(double winChance) {
		this.randomWinChance = winChance;
	}

	public double getRandomWinChance() {
		return randomWinChance;
	}
	
	@Override
	public GameResolverConstant Validate() {
		logger.info("Validating GameResolver Config");
		
		if(randomWinChance > 1 || randomWinChance < 0) {
			return GameResolverConstant.RandWinError;
		}
		return GameResolverConstant.Success;
	}
	
}
