package com.dhl.g05;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dhl.g05.leagueobjects.ConferenceObject;
import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;


public class LeagueModelCreatorTest {
	LeagueModelCreator  leagueModelCreator;
	Exception exception;
	
	@Before
	public void init() {
		leagueModelCreator = new LeagueModelCreator();
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
			LeagueObject league  = leagueModelCreator.createLeagueFromFile("filedoesnotexist");
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
			LeagueObject league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonInvalidFile.json");
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
		LeagueObject league = null;
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles//jsonGoodInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		
		assertNotNull(league);
	}
	
	
	@Test
	public void testCreateLeagueFromFilePlayersBad() {
		LeagueObject league = new LeagueObject(new String(), new HashMap<String,ConferenceObject>(), new HashMap<String,PlayerObject>());
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadPlayerInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertNull(league);

	}

	@Test
	public void testCreateLeagueFromFileTeamsBad() {
		LeagueObject league = new LeagueObject(new String(), new HashMap<String,ConferenceObject>(), new HashMap<String,PlayerObject>());
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadTeamInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertNull(league);
	}
	
	@Test
	public void testCreateLeagueFromFileDivisionsBad() {
		LeagueObject league = new LeagueObject(new String(), new HashMap<String,ConferenceObject>(), new HashMap<String,PlayerObject>());
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadDivisionInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertNull(league);
		
	}
	
	@Test
	public void testCreateLeagueFromFileConferencesBad() {
		LeagueObject league = new LeagueObject(new String(), new HashMap<String,ConferenceObject>(), new HashMap<String,PlayerObject>());
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadConferenceInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertNull(league);
	}
	
	@Test
	public void testCreateLeagueFromFileFreeAgentsBad() {
		LeagueObject league = new LeagueObject(new String(), new HashMap<String,ConferenceObject>(), new HashMap<String,PlayerObject>());
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadFreeAngentInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertNull(league);
	}

}

