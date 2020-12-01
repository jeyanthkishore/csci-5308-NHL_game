package com.dhl.g05.database;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;

public class SerialiseLeagueModelTest {
	private static ISerializeModel serial;
	
	 @BeforeClass
	    public static void setup() {
		 	DatabaseAbstractFactory databasFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		 	serial = databasFactory.createSerializeObject();
		 	DatabaseMockAbstractFactory mockDatabaseState = ApplicationTestConfiguration.instance().getDatabaseMockConcreteFactoryState();
		    DatabaseState state = mockDatabaseState.createMockDatabaseState();
			ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	    }
	
	
	@Test
	public void serialiseObjectsTest() {
		ModelMockAbstractFactory modelFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		LeagueMockData data = modelFactory.createLeagueMockData();
		String teamName = "serializeLeague";
		assertTrue(serial.serialiseObjects(data.getLeague(),teamName));
	}

}

