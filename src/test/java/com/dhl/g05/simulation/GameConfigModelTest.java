package com.dhl.g05.simulation;

import static org.junit.Assert.assertSame;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;

public class GameConfigModelTest {
	private static SimulationAbstractFactory simulationFactory;
	
	@BeforeClass
	public static void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	}

	@Test
	public void setGameResolverTest() {
		IGameResolver gameResolver = simulationFactory.createGameResolverConfig();
		gameResolver.setRandomWinChance(0.5);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setGameResolver(gameResolver);
		assertSame(gameResolver,gameConfig.getGameResolver());
	}

	@Test
	public void getGameResolverTest() {
		IGameResolver gameResolver = simulationFactory.createGameResolverConfig();
		gameResolver.setRandomWinChance(0.9);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setGameResolver(gameResolver);
		assertSame(gameResolver,gameConfig.getGameResolver());
	}
	
	@Test
	public void setInjuryConfigTest() {
		IInjury injury = simulationFactory.createInjuryConfig();
		injury.setInjuryDaysHigh(140);
		injury.setInjuryDaysLow(5);
		injury.setRandomInjuryChance(0.05);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setInjuries(injury);
		assertSame(injury,gameConfig.getInjuries());
	}

	@Test
	public void getInjuryConfigTest() {
		IInjury injury = simulationFactory.createInjuryConfig();
		injury.setInjuryDaysHigh(120);
		injury.setInjuryDaysLow(1);
		injury.setRandomInjuryChance(0.05);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setInjuries(injury);
		assertSame(injury,gameConfig.getInjuries());
	}
	
	@Test
	public void setTradingConfigTest() {
		ITradingConfig tradeConfig = simulationFactory.createTradingConfig();
		tradeConfig.setLossPoint(8);
		tradeConfig.setMaxPlayersPerTrade(2);
		tradeConfig.setRandomTradeOfferChance(0.05);
		tradeConfig.setRandomAcceptanceChance(0.05);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setTrading(tradeConfig);
		assertSame(tradeConfig,gameConfig.getTrading());
	}
	
	@Test
	public void getTradingConfigTest() {
		ITradingConfig tradeConfig = simulationFactory.createTradingConfig();
		tradeConfig.setLossPoint(8);
		tradeConfig.setMaxPlayersPerTrade(5);
		tradeConfig.setRandomTradeOfferChance(0.15);
		tradeConfig.setRandomAcceptanceChance(0.05);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setTrading(tradeConfig);
		assertSame(tradeConfig,gameConfig.getTrading());
	}
	
	@Test
	public void getTrainingConfigTest() {
		ITraining trainingConfig = simulationFactory.createTrainingConfig();
		trainingConfig.setDaysUntilStatIncreaseCheck(100);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setTraining(trainingConfig);
		assertSame(trainingConfig,gameConfig.getTraining());
	}
	
	@Test
	public void setTrainingConfigTest() {
		ITraining trainingConfig = simulationFactory.createTrainingConfig();
		trainingConfig.setDaysUntilStatIncreaseCheck(120);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setTraining(trainingConfig);
		assertSame(trainingConfig,gameConfig.getTraining());
	}
	
	@Test
	public void setAgingConfigTest() {
		IAging agingConfig = simulationFactory.createAgingConfig();
		agingConfig.setAverageRetirementAge(50);
		agingConfig.setMaximumAge(66);
		agingConfig.setStatDecayChance(0.07);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setAging(agingConfig);
		assertSame(agingConfig,gameConfig.getAging());
	}
	
	@Test
	public void getAgingConfigTest() {
		IAging agingConfig = simulationFactory.createAgingConfig();
		agingConfig.setAverageRetirementAge(20);
		agingConfig.setMaximumAge(71);
		agingConfig.setStatDecayChance(0.09);
		IGamePlayConfig gameConfig = simulationFactory.createGamePlayConfig();
		gameConfig.setAging(agingConfig);
		assertSame(agingConfig,gameConfig.getAging());
	}
	
}
