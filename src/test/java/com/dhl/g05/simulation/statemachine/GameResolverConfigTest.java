package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.GameResolverConstant;
import com.dhl.g05.simulation.statemachine.IGameResolver;

public class GameResolverConfigTest {

	private static SimulationAbstractFactory simulationFactory;

	@BeforeClass
	public static void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
	}

	@Test
	public void getRandomWinChanceTest() {
		IGameResolver game = simulationFactory.createGameResolverConfig();
		game.setRandomWinChance(0.2);
		assertEquals("success",0.2, game.getRandomWinChance(),0);
	}

	@Test
	public void setRandomWinChanceTest() {
		IGameResolver game = simulationFactory.createGameResolverConfig();
		game.setRandomWinChance(0.4);
		assertEquals("success",0.4, game.getRandomWinChance(),0);
	}

	@Test
	public void winChanceValidationTest() {
		IGameResolver game = simulationFactory.createGameResolverConfig();
		game.setRandomWinChance(2.2);
		assertSame(GameResolverConstant.RandWinError,game.Validate());
	}

	@Test
	public void negativeValidationTest() {
		IGameResolver game = simulationFactory.createGameResolverConfig();
		game.setRandomWinChance(-0.9);
		assertSame(GameResolverConstant.RandWinError,game.Validate());
	}

}
