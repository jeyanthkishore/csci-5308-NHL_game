package com.dhl.g05.database;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.communication.AbstractCommunicationFactory;
import com.dhl.g05.communication.CommunicationFactory;
import com.dhl.g05.filehandler.LeagueModelJson;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.statemachine.AbstractStateMachineFactory;
import com.dhl.g05.statemachine.StateMachineFactory;

public class SerialiseLeagueModelTest {
	
	
	 @BeforeClass
	    public static void setup() {
	        AbstractCommunicationFactory.setFactory(new CommunicationFactory());
	        AbstractDataBaseFactory.setFactory(new DataBaseFactory());
	        AbstractStateMachineFactory.setFactory(
	                new StateMachineFactory(
	                		AbstractCommunicationFactory.getFactory().getCommunication(),
	                		new LeagueModelJson()
	                )
	        );
	    }
	
	
	@Test
	public void serialiseObjectsTest() {
		ISerializeModel serial = new SerialiseLeagueModel();
		JsonMockDataDb data = new JsonMockDataDb();
		String teamName = "dummy";
		assertTrue(serial.serialiseObjects(data.league,teamName));
	}

//	@Test
//	public void deSerialiseObjectTest() throws FileNotFoundException, IOException, ParseException {
//		ISerializeModel serial = new SerialiseLeagueModel();
//		JsonMockDataDb data = new JsonMockDataDb();
//		assertTrue(serial.serialiseObjects(data.league));
//		LeagueModelCreatorFromJSON deSerialize = new LeagueModelCreatorFromJSON(new MockPlayerCommunication());
//		LeagueModel result = deSerialize.createLeagueFromFile(SerialiseLeagueConstant.FilePath.getValue());
//		assertNotNull(result);
//	}
//	
//	@Test
//	public void deSerialiseObjectFailTest() throws FileNotFoundException, IOException, ParseException {
//		ISerializeModel serial = new SerialiseLeagueModel();
//		JsonMockDataDb data = new JsonMockDataDb();
//		data.league.setFreeAgent(null);
//		assertTrue(serial.serialiseObjects(data.league));
//		LeagueModelCreatorFromJSON deSerialize = new LeagueModelCreatorFromJSON(new MockPlayerCommunication());
//		LeagueModel result = deSerialize.createLeagueFromFile(SerialiseLeagueConstant.FilePath.getValue());
//		assertNull(result);
//	}
	
}

