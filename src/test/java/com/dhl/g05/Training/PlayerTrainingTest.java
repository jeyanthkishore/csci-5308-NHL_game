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
	
}
