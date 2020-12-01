package com.dhl.g05.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.statemachine.IInjury;

public class PlayerTraining implements IPlayerTraining {
	
	static final Logger logger = LogManager.getLogger(PlayerTraining.class);
	private IInjury injury;
	private IRandomNumberFactory randomGeneratorFactory;
	
	public PlayerTraining() {
		randomGeneratorFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState().createRandomNumber();
	}

	public void setRandomGeneratorFactory(IRandomNumberFactory randomGeneratorFactory) {
		this.randomGeneratorFactory = randomGeneratorFactory;
	}

	public IPlayer performTrainingForPlayer(IPlayer player, ICoach headCoach,IInjury injury) {
		logger.info("Performing Training for Player");
		
		this.injury = injury;
		Boolean playerInjured = false;

		if(trainingAlgorithm(headCoach.getChecking())) {
			if(player.getChecking() < 20) {
				player.setChecking((player.getChecking()+1));
				logger.info(player.getPlayerName()+" checking stat increased to "+player.getChecking());
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(headCoach.getSaving())) {
			if(player.getSaving() < 20) {
				player.setSaving((player.getSaving()+1));
				logger.info(player.getPlayerName()+" saving stat increased to "+player.getSaving());
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(headCoach.getSkating())) {
			if(player.getSkating() < 20) {
				player.setSkating((player.getSkating()+1));
				logger.info(player.getPlayerName()+" skating stat increased to "+player.getSkating());
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}

		if(trainingAlgorithm(headCoach.getShooting())) {
			if(player.getShooting() < 20) {
				player.setShooting((player.getShooting()+1));
				logger.info(player.getPlayerName()+" shooting stat increased to "+player.getShooting());
			}
		}else {
			playerInjured = isPlayerInjured(player);
		}
		
		player.setInjuryStatus(playerInjured);
		return player;
	}

	private Boolean isPlayerInjured(IPlayer player) {
		logger.info("Checking injury for "+player.getPlayerName());
		
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		IPlayerInjured playerProgress= modelFactory.createPlayerInjury();
		if(playerProgress.checkPlayerInjury(player,injury)) {
			return true;
		}
		return false;
	}

	private Boolean trainingAlgorithm(double coachValue) {
		double randomValue = randomGeneratorFactory.generateRandomDoubleNumber(0,1);
		if(randomValue < coachValue) {
			return true;
		}
		return false;

	}
}
