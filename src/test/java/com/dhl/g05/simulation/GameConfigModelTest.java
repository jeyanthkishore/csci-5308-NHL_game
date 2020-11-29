package com.dhl.g05.simulation;

import static org.junit.Assert.assertSame;

import com.dhl.g05.ApplicationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameConfigModelTest {

	public int averageRetirementAge = 35;
	public int maximumAge = 60;
	public double statDecayChance=0.05;
	public double randownWinChance = 0.1;
	public double randomInjuryChance = 0.05;
	public int injuryDaysLow = 1;
	public int injuryDaysHigh = 120;
	public int daysUntilTraining = 100;
	public int lossPoint = 8;
	public double randomTradeOffer = 0.05;
	public double randomAcceptanceChance = 0.05;
	public int maxPlayerPerTrade = 2;
	private static SimulationAbstractFactory simulationAbstractFactory;

	@BeforeClass
	public static void init() {
		simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	}

	@Test
	public void setGameResolverTest() {
		IGameResolver gameResolver = new GameResolverConfig();
		gameResolver.setRandomWinChance(0.5);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setGameResolver(gameResolver);
		assertSame(gameResolver,gameConfig.getGameResolver());
	}

	@Test
	public void getGameResolverTest() {
		IGameResolver gameResolver = simulationAbstractFactory.createGameResolverConfig();
		gameResolver.setRandomWinChance(0.9);
		IGamePlayConfig gameConfig = simulationAbstractFactory.createGamePlayConfig();
		gameConfig.setGameResolver(gameResolver);
		assertSame(gameResolver,gameConfig.getGameResolver());
	}
	
	@Test
	public void setInjuryConfigTest() {
		IInjury injury = simulationAbstractFactory.createInjuryConfig();
		injury.setInjuryDaysHigh(140);
		injury.setInjuryDaysLow(5);
		injury.setRandomInjuryChance(0.05);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setInjuries(injury);
		assertSame(injury,gameConfig.getInjuries());
	}

	@Test
	public void getInjuryConfigTest() {
		IInjury injury = simulationAbstractFactory.createInjuryConfig();
		injury.setInjuryDaysHigh(120);
		injury.setInjuryDaysLow(1);
		injury.setRandomInjuryChance(0.05);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setInjuries(injury);
		assertSame(injury,gameConfig.getInjuries());
	}
	
	@Test
	public void setTradingConfigTest() {
		ITradingConfig tradeConfig = simulationAbstractFactory.createTradingConfig();
		tradeConfig.setLossPoint(8);
		tradeConfig.setMaxPlayersPerTrade(2);
		tradeConfig.setRandomTradeOfferChance(0.05);
		tradeConfig.setRandomAcceptanceChance(0.05);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setTrading(tradeConfig);
		assertSame(tradeConfig,gameConfig.getTrading());
	}
	
	@Test
	public void getTradingConfigTest() {
		ITradingConfig tradeConfig = simulationAbstractFactory.createTradingConfig();
		tradeConfig.setLossPoint(8);
		tradeConfig.setMaxPlayersPerTrade(5);
		tradeConfig.setRandomTradeOfferChance(0.15);
		tradeConfig.setRandomAcceptanceChance(0.05);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setTrading(tradeConfig);
		assertSame(tradeConfig,gameConfig.getTrading());
	}
	
	@Test
	public void getTrainingConfigTest() {
		ITraining trainingConfig = simulationAbstractFactory.createTrainingConfig();
		trainingConfig.setDaysUntilStatIncreaseCheck(100);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setTraining(trainingConfig);
		assertSame(trainingConfig,gameConfig.getTraining());
	}
	
	@Test
	public void setTrainingConfigTest() {
		ITraining trainingConfig = simulationAbstractFactory.createTrainingConfig();
		trainingConfig.setDaysUntilStatIncreaseCheck(120);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setTraining(trainingConfig);
		assertSame(trainingConfig,gameConfig.getTraining());
	}
	
	@Test
	public void setAgingConfigTest() {
		IAging agingConfig = simulationAbstractFactory.createAgingConfig();
		agingConfig.setAverageRetirementAge(50);
		agingConfig.setMaximumAge(66);
		agingConfig.setStatDecayChance(0.07);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setAging(agingConfig);
		assertSame(agingConfig,gameConfig.getAging());
	}
	
	@Test
	public void getAgingConfigTest() {
		IAging agingConfig = simulationAbstractFactory.createAgingConfig();
		agingConfig.setAverageRetirementAge(20);
		agingConfig.setMaximumAge(71);
		agingConfig.setStatDecayChance(0.09);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setAging(agingConfig);
		assertSame(agingConfig,gameConfig.getAging());
	}
	
}
