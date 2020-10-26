package com.dhl.g05.Training;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class PlayerTrainingTest {
	
	@Test
	public void implementTrainingTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerTraining train = new PlayerTraining();
		assertNotNull(train.implementTraining(data.league));
	}
	
	@Test
	public void performTrainingForPlayerTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerTraining train = new PlayerTraining();
		assertNotNull(train.performTrainingForPlayer(data.playerList.get(0), data.coachDetails));
	}
	
	@Test
	public void trainingAlgorithmTest() {
		PlayerTraining train = new PlayerTraining();
		assertTrue(train.trainingAlgorithm(10.0, 11.1));
	}
	
}
