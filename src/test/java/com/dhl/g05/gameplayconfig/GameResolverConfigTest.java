package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.statemachine.GameResolverConfig;
import com.dhl.g05.statemachine.GameResolverConstant;

public class GameResolverConfigTest {
	
	@Test
	public void getRandomWinChanceTest() {
		GameResolverConfig game = new GameResolverConfig();
		game.setRandomWinChance(0.2);
		assertEquals("success",0.2, game.getRandomWinChance(),0);
	}
	
	@Test
	public void setRandomWinChanceTest() {
		GameResolverConfig game = new GameResolverConfig();
		game.setRandomWinChance(0.4);
		assertEquals("success",0.4, game.getRandomWinChance(),0);
	}
	
	@Test
	public void winChanceValidationTest() {
		GameResolverConfig game = new GameResolverConfig();
		game.setRandomWinChance(2.2);
		assertSame(GameResolverConstant.RandWinError,game.Validate());
	}
	
	@Test
	public void negativeValidationTest() {
		GameResolverConfig game = new GameResolverConfig();
		game.setRandomWinChance(-0.9);
		assertSame(GameResolverConstant.RandWinError,game.Validate());
	}
	
}
