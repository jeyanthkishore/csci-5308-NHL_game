package com.dhl.g05.Training;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.leaguemodel.league.LeagueModel;

public class PlayerTrainingTest {
	
	@Test
	public void implementTrainingTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		PlayerTraining train = new PlayerTraining(data.getLeague());
		assertNotNull(train.implementTraining());
	}
	
	@Test
	public void trainingObjectTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel beforeData = data.getLeague();
		JsonMockDataDb dataTwo = new JsonMockDataDb();
		LeagueModel afterData = dataTwo.getLeague();
		PlayerTraining train = new PlayerTraining(afterData);
		assertNotNull(train.implementTraining());
		assertNotSame(beforeData,train.implementTraining());
		assertFalse(beforeData.toString().equals(train.implementTraining().toString()));
	}
}
