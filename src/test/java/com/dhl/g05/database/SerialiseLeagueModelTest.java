package com.dhl.g05.database;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.LeagueModel;

public class SerialiseLeagueModelTest {
	private static IDeserializeModel serial;
	
	 @BeforeClass
	    public static void setup() {
		 	DatabaseAbstractFactory databasFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		 	serial = databasFactory.createDeserializeObject();
		 	DatabaseState state = new DatabaseMockFactoryState();
			ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	    }
	
	
//	@Test
//	public void serialiseObjectsTest() {
//		ISerializeModel serial = new SerialiseLeagueModel();
//		LeagueMockData data = new LeagueMockData();
//		String teamName = "dummy";
//		assertTrue(serial.serialiseObjects(data.league,teamName));
//	}

//	@Test
//	public void deserialiseObjectsTest() {
//		ILeague data = new LeagueModel();
//		String teamName = "jsonfordeserialize";
//		assertNotNull(serial.deserializeObjects(teamName,data));
//	}
	
}

