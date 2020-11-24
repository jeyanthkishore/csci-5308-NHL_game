/*
package com.dhl.g05.serialize;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import com.dhl.g05.filehandler.LeagueModelCreatorFromJSON;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.mocks.MockPlayerCommunication;

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
		LeagueModelCreatorFromJSON deSerialize = new LeagueModelCreatorFromJSON(new MockPlayerCommunication());
		LeagueModel result = deSerialize.createLeagueFromFile(SerialiseLeagueConstant.FilePath.getValue());
		assertNotNull(result);
	}
	
	@Test
	public void deSerialiseObjectFailTest() throws FileNotFoundException, IOException, ParseException {
		ISerializeModel serial = new SerialiseLeagueModel();
		JsonMockDataDb data = new JsonMockDataDb();
		data.league.setFreeAgent(null);
		assertTrue(serial.serialiseObjects(data.league));
		LeagueModelCreatorFromJSON deSerialize = new LeagueModelCreatorFromJSON(new MockPlayerCommunication());
		LeagueModel result = deSerialize.createLeagueFromFile(SerialiseLeagueConstant.FilePath.getValue());
		assertNull(result);
	}
	
}
*/
