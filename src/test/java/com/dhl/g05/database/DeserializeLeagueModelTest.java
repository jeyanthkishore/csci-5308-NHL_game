package com.dhl.g05.database;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.LeagueMockData;
import com.dhl.g05.model.ModelMockAbstractFactory;

public class DeserializeLeagueModelTest {
	private static IDeserializeModel serial;
	
	 @BeforeClass
	    public static void setup() {
		 	DatabaseAbstractFactory databasFactory = ApplicationConfiguration.instance().getDatabaseConcreteFactoryState();
		 	serial = databasFactory.createDeserializeObject();
		 	DatabaseMockAbstractFactory mockDatabaseState = ApplicationTestConfiguration.instance().getDatabaseMockConcreteFactoryState();
		    DatabaseState state = mockDatabaseState.createMockDatabaseState();
			ApplicationConfiguration.instance().setDataBaseFactoryState(state);
	    }
	
	
	@Test
	public void serialiseObjectsTest() {
		ModelMockAbstractFactory modelFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
		LeagueMockData data = modelFactory.createLeagueMockData();
		String teamName = "deserializeLeague";
		assertNotNull(serial.deserializeObjects(teamName,data.getLeague()));
	}

}

