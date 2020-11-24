/*
package filehandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.filehandler.LeagueModelCreatorFromJSON;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.mocks.MockPlayerCommunication;




public class LeagueModelCreatorFromJSONTest {
	LeagueModelCreatorFromJSON leagueModelCreator;
	Exception exception;

	@Before
	public void init() {
		leagueModelCreator = new LeagueModelCreatorFromJSON(new MockPlayerCommunication());
		exception = new Exception();
	}

	@Test
	public void testisFileValidJsonInvalid() {
		assertFalse(leagueModelCreator.isFileValidJson("src/test/java/com/dhl/g05/jsontestfiles/jsonInvalidFile.json"));
	}

	@Test
	public void testisFileValidJsonValid() {
		assertTrue(leagueModelCreator.isFileValidJson("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json"));
	}

	@Test
	public void testCreateLeagueFromFileNoFile() {
		try {
			leagueModelCreator.createLeagueFromFile("filedoesnotexist");
		} catch (FileNotFoundException e) {
			exception = e;
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertTrue(exception instanceof FileNotFoundException);
	}

	@Test
	public void testCreateLeagueFromFileInvalidJsonFile() {		
		try {
			leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonInvalidFile.json");
		} catch (ParseException e) {
			exception = e;
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		assertTrue(exception instanceof ParseException);
	}

	@Test
	public void testCreateLeagueFromFileGoodFile() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNotNull(result);
	}

	@Test
	public void validLeagueTest() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonGoodInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNotNull(result);
	}

	@Test
	public void badTeamTest() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadTeamInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNull(result);
	}
	
	@Test
	public void badPlayerTest() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadPlayerInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNull(result);
	}
	
	@Test
	public void badFreeAgentTest() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadFreeAngentInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNull(result);
	}
	
	@Test
	public void badDivisionTest() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadDivisionInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNull(result);
	}
	@Test
	public void badConferenceTest() {
		LeagueModel result = null;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonDiConferenceInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertNull(result);
	}
}

*/
