package com.dhl.g05.serialize;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.dhl.g05.leaguemodel.JsonMockDataDb;
import com.dhl.g05.statemachine.LeagueModelCreatorFromJSON;
import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;

public class SerialiseLeagueModelTest {
	
	@Test
	public void serialiseObjectsTest() {
		ISerializeModel serial = new SerialiseLeagueModel();
		JsonMockDataDb data = new JsonMockDataDb();
		assertTrue(serial.serialiseObjects(data.league));
	}

	@Test
	public void deSerialiseObjectTest() throws FileNotFoundException, IOException, ParseException {
		ISerializeModel serial = new SerialiseLeagueModel();
		JsonMockDataDb data = new JsonMockDataDb();
		assertTrue(serial.serialiseObjects(data.league));
		LeagueModelCreatorFromJSON deSerialize = new LeagueModelCreatorFromJSON(new MockLeagueModel(), new MockPlayerCommunication());
		Boolean result = deSerialize.createLeagueFromFile(SerialiseLeagueConstant.FilePath.getValue());
		assertTrue(result);
	}
	
	@Test
	public void deSerialiseObjectFailTest() throws FileNotFoundException, IOException, ParseException {
		ISerializeModel serial = new SerialiseLeagueModel();
		JsonMockDataDb data = new JsonMockDataDb();
		data.league.setFreeAgent(null);
		assertTrue(serial.serialiseObjects(data.league));
		LeagueModelCreatorFromJSON deSerialize = new LeagueModelCreatorFromJSON(new MockLeagueModel(), new MockPlayerCommunication());
		Boolean result = deSerialize.createLeagueFromFile(SerialiseLeagueConstant.FilePath.getValue());
		assertFalse(result);
	}
	
}
