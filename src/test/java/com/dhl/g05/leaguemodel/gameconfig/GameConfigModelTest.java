package com.dhl.g05.leaguemodel.gameconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.gameplayconfig.GamePlayConfigModel;

public class GameConfigModelTest {

	@Test
	public void gameConfigConstructoNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		GamePlayConfigModel gamePlay = new GamePlayConfigModel(mock.tradeConfig, mock.aging, mock.injury,mock.gameResolver, mock.training);
		assertNotNull(gamePlay.getAging());
		assertNotNull(gamePlay.getGameResolve());
		assertNotNull(gamePlay.getInjuries());
		assertNotNull(gamePlay.getTrading());
		assertNotNull(gamePlay.getTraining());
	}
	
	@Test
	public void gameConfigConstructorTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		GamePlayConfigModel gamePlay = new GamePlayConfigModel(mock.tradeConfig, mock.aging, mock.injury,mock.gameResolver, mock.training);
		assertEquals(mock.tradeConfig,gamePlay.getTrading());
		assertEquals(mock.aging,gamePlay.getAging());
		assertEquals(mock.injury,gamePlay.getInjuries());
		assertEquals(mock.gameResolver,gamePlay.getGameResolve());
		assertEquals(mock.training,gamePlay.getTraining());
	}
	
	@Test
	public void getValuesTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		GamePlayConfigModel gamePlay = new GamePlayConfigModel(mock.tradeConfig, mock.aging, mock.injury,mock.gameResolver, mock.training);
		assertEquals(mock.tradeConfig,gamePlay.getTrading());
		assertEquals(mock.aging,gamePlay.getAging());
		assertEquals(mock.injury,gamePlay.getInjuries());
		assertEquals(mock.gameResolver,gamePlay.getGameResolve());
		assertEquals(mock.training,gamePlay.getTraining());
	}
	
}
