package com.dhl.g05.statemachine;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;


import com.dhl.g05.statemachine.mocks.MockLeagueModel;
import com.dhl.g05.statemachine.mocks.MockLeagueModelValidationFails;
import com.dhl.g05.statemachine.mocks.MockPlayerCommunication;




public class LeagueModelCreatorTest {
	LeagueModelCreator leagueModelCreator;
	Exception exception;
	
	@Before
	public void init() {
		leagueModelCreator = new LeagueModelCreator(new MockLeagueModel(), new MockPlayerCommunication());
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
		boolean result = false;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles//jsonGoodInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		assertTrue(result);
	}
	
	
	@Test
	public void testCreateLeagueFromFilePlayersBad() {
		leagueModelCreator = new LeagueModelCreator(new MockLeagueModelValidationFails(),new MockPlayerCommunication());
		boolean result = true;
		try {
			result  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadPlayerInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertFalse(result);

	}

	@Test
	public void testCreateLeagueFromFileTeamsBad() {
		leagueModelCreator = new LeagueModelCreator(new MockLeagueModelValidationFails(),new MockPlayerCommunication());
		boolean result = true;
		try {
			result  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadTeamInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateLeagueFromFileDivisionsBad() {
		leagueModelCreator = new LeagueModelCreator(new MockLeagueModelValidationFails(),new MockPlayerCommunication());
		boolean result = true;
		try {
			result  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadDivisionInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertFalse(result);
		
	}
	
	@Test
	public void testCreateLeagueFromFileConferencesBad() {
		leagueModelCreator = new LeagueModelCreator(new MockLeagueModelValidationFails(),new MockPlayerCommunication());
		boolean result = true;
		try {
			result = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadConferenceInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertFalse(result);
	}
	
	@Test
	public void testCreateLeagueFromFileFreeAgentsBad() {
		leagueModelCreator = new LeagueModelCreator(new MockLeagueModelValidationFails(),new MockPlayerCommunication());
		boolean result = true;
		try {
			result  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadFreeAngentInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertFalse(result);
	}

}

