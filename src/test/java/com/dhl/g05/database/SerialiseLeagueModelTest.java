package com.dhl.g05.database;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.LeagueModel;

public class SerialiseLeagueModelTest {
	
	
	 @BeforeClass
	    public static void setup() {
//	        CommunicationAbstractFactory.setFactory(new CommunicationFactory());
	    }
	
	
//	@Test
//	public void serialiseObjectsTest() {
//		ISerializeModel serial = new SerialiseLeagueModel();
//		LeagueMockData data = new LeagueMockData();
//		String teamName = "dummy";
//		assertTrue(serial.serialiseObjects(data.league,teamName));
//	}

	@Test
	public void deserialiseObjectsTest() {
		IDeserializeModel serial = new DeserializeLeagueModel();
		ILeague data = new LeagueModel();
		String teamName = "dummy";
		assertNotNull(serial.deserializeObjects(teamName,data));
	}
	
}

