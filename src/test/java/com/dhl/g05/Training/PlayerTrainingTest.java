package com.dhl.g05.Training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.player.IRandomGeneratorFactory;
import com.dhl.g05.player.RandomGeneratorFactory;

public class PlayerTrainingTest {
	
    private static IRandomGeneratorFactory randomGeneratorFactoryMock;
    private static IPlayerTraining playerTraining;

    @BeforeClass
    public static void setup() {
        randomGeneratorFactoryMock = Mockito.mock(RandomGeneratorFactory.class);
        playerTraining =new PlayerTraining(randomGeneratorFactoryMock);
    }
	
	
	@Test
	public void implementTrainingTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		assertNotNull(playerTraining.implementTraining(data.getLeague()));
	}
	
	@Test
	public void trainingObjectTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel beforeData = data.getLeague();
		JsonMockDataDb dataTwo = new JsonMockDataDb();
		LeagueModel afterData = dataTwo.getLeague();
		assertNotNull(playerTraining.implementTraining(afterData));
		assertNotSame(beforeData,playerTraining.implementTraining(afterData));
		assertFalse(beforeData.toString().equals(playerTraining.implementTraining(afterData).toString()));
	}
	
	@Test
	public void checkPerformance() {
		PlayerTrainingMockData data = new PlayerTrainingMockData();
		Mockito.when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,1)).thenReturn(0.4);
		assertNotNull(playerTraining.implementTraining(data.leagueObject));
		assertEquals("Same",11.0,data.player.getSkating(),0.0);
		assertEquals("Same",13.0,data.player.getShooting(),0.0);
		assertEquals("Same",12.0,data.player.getChecking(),0.0);
		assertEquals("Same",13.0,data.player.getSaving(),0.0);
		Mockito.when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,1)).thenReturn(0.6);
		assertNotNull(playerTraining.implementTraining(data.leagueObject));
		assertEquals("Same",11.0,data.player.getSkating(),0.0);
		assertEquals("Same",13.0,data.player.getShooting(),0.0);
		assertEquals("Same",13.0,data.player.getChecking(),0.0);
		assertEquals("Same",14.0,data.player.getSaving(),0.0);
		Mockito.when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,1)).thenReturn(0.7);
		assertNotNull(playerTraining.implementTraining(data.leagueObject));
		assertEquals("Same",11.0,data.player.getSkating(),0.0);
		assertEquals("Same",13.0,data.player.getShooting(),0.0);
		assertEquals("Same",13.0,data.player.getChecking(),0.0);
		assertEquals("Same",15.0,data.player.getSaving(),0.0);
		Mockito.when(randomGeneratorFactoryMock.getRandomDoubleNumber(0,1)).thenReturn(0.8);
		assertNotNull(playerTraining.implementTraining(data.leagueObject));
		assertEquals("Same",11.0,data.player.getSkating(),0.0);
		assertEquals("Same",13.0,data.player.getShooting(),0.0);
		assertEquals("Same",13.0,data.player.getChecking(),0.0);
		assertEquals("Same",15.0,data.player.getSaving(),0.0);
	}
}
