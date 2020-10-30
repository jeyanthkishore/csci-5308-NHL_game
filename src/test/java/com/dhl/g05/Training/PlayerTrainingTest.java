package com.dhl.g05.Training;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class PlayerTrainingTest {
	
	@Test
	public void implementTrainingTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerTraining train = new PlayerTraining(data.league);
		assertNotNull(train.implementTraining());
	}
	
	@Test
	public void performTrainingForPlayerTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerTraining train = new PlayerTraining(data.league);
		assertNotNull(train.performTrainingForPlayer(data.playerList.get(0), data.coachDetails));
	}
	
	@Test
	public void trainingAlgorithmTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerTraining train = new PlayerTraining(data.league);
		assertTrue(train.trainingAlgorithm(10.0, 11.1));
	}
	
}
