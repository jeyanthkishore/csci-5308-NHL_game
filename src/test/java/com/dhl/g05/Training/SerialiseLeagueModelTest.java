package com.dhl.g05.Training;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;

public class SerialiseLeagueModelTest {
	
	@Test
	public void serialiseObjectsTest() {
		ISerializeModel serial = new SerialiseLeagueModel();
		JsonMockDataDb data = new JsonMockDataDb();
		assertTrue(serial.serialiseObjects(data.league));
	}

}
