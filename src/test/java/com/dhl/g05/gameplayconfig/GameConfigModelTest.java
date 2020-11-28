package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.statemachine.AgingConfig;
import com.dhl.g05.statemachine.GamePlayConfigModel;
import com.dhl.g05.statemachine.GameResolverConfig;
import com.dhl.g05.statemachine.IAging;
import com.dhl.g05.statemachine.IGamePlayConfig;
import com.dhl.g05.statemachine.IGameResolver;
import com.dhl.g05.statemachine.IInjury;
import com.dhl.g05.statemachine.ITradingConfig;
import com.dhl.g05.statemachine.ITraining;
import com.dhl.g05.statemachine.InjuryConfig;
import com.dhl.g05.statemachine.TradingConfig;
import com.dhl.g05.statemachine.TrainingConfig;

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
		IGameResolver gameResolver = new GameResolverConfig();
		gameResolver.setRandomWinChance(0.9);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setGameResolver(gameResolver);
		assertSame(gameResolver,gameConfig.getGameResolver());
	}
	
	@Test
	public void setInjuryConfigTest() {
		IInjury injury = new InjuryConfig();
		injury.setInjuryDaysHigh(140);
		injury.setInjuryDaysLow(5);
		injury.setRandomInjuryChance(0.05);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setInjuries(injury);
		assertSame(injury,gameConfig.getInjuries());
	}

	@Test
	public void getInjuryConfigTest() {
		IInjury injury = new InjuryConfig();
		injury.setInjuryDaysHigh(120);
		injury.setInjuryDaysLow(1);
		injury.setRandomInjuryChance(0.05);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setInjuries(injury);
		assertSame(injury,gameConfig.getInjuries());
	}
	
	@Test
	public void setTradingConfigTest() {
		ITradingConfig tradeConfig = new TradingConfig();
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
		ITradingConfig tradeConfig = new TradingConfig();
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
		ITraining trainingConfig = new TrainingConfig();
		trainingConfig.setDaysUntilStatIncreaseCheck(100);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setTraining(trainingConfig);
		assertSame(trainingConfig,gameConfig.getTraining());
	}
	
	@Test
	public void setTrainingConfigTest() {
		ITraining trainingConfig = new TrainingConfig();
		trainingConfig.setDaysUntilStatIncreaseCheck(120);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setTraining(trainingConfig);
		assertSame(trainingConfig,gameConfig.getTraining());
	}
	
	@Test
	public void setAgingConfigTest() {
		IAging agingConfig = new AgingConfig();
		agingConfig.setAverageRetirementAge(50);
		agingConfig.setMaximumAge(66);
		agingConfig.setStatDecayChance(0.07);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setAging(agingConfig);
		assertSame(agingConfig,gameConfig.getAging());
	}
	
	@Test
	public void getAgingConfigTest() {
		IAging agingConfig = new AgingConfig();
		agingConfig.setAverageRetirementAge(20);
		agingConfig.setMaximumAge(71);
		agingConfig.setStatDecayChance(0.09);
		IGamePlayConfig gameConfig = new GamePlayConfigModel();
		gameConfig.setAging(agingConfig);
		assertSame(agingConfig,gameConfig.getAging());
	}
	
}
