package com.dhl.g05.gameplayconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class GameResolverConfigTest {
	
	@Test
	public void gameResolverConstructorTest() {
		GameResolverConfig game = new GameResolverConfig(0.2);
		assertEquals("success",0.2, game.getRandomWinChance(),0);
	}
	
	@Test
	public void getRandomWinChanceTest() {
		GameResolverConfig game = new GameResolverConfig(0.2);
		assertEquals("success",0.2, game.getRandomWinChance(),0);
	}
	
	@Test
	public void winChanceValidationTest() {
		GameResolverConfig game = new GameResolverConfig(2.2);
		assertSame(GameResolverConstant.RandWinError,game.Validate());
	}
	
	@Test
	public void negativeValidationTest() {
		GameResolverConfig game = new GameResolverConfig(-0.9);
		assertSame(GameResolverConstant.RandWinError,game.Validate());
	}
	
}