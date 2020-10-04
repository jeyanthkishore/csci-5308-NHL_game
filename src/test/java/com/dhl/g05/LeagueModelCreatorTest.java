package com.dhl.g05;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dhl.g05.leagueobjects.ConferenceObject;
import com.dhl.g05.leagueobjects.LeagueObject;
import com.dhl.g05.leagueobjects.PlayerObject;


public class LeagueModelCreatorTest {

	@Test
	public void testCreateLeagueFromFileNoFile() {
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
		Exception exception = new Exception();
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
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
		Exception exception = new Exception();
		
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
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
		LeagueObject league = null;
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles//jsonGoodInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
		
		assertNotNull(league);
		assertNotNull(league.getConferences());
		assertNotNull(league.getFreeAgents());
		assertEquals(league.getName(),"Dalhousie Hockey League");
		
	}
	
	
	@Test
	public void testCreateLeagueFromFilePlayersBad() {
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
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
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
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
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
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
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
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
		LeagueModelCreator  leagueModelCreator = new LeagueModelCreator();
		LeagueObject league = new LeagueObject(new String(), new HashMap<String,ConferenceObject>(), new HashMap<String,PlayerObject>());
		try {
			league  = leagueModelCreator.createLeagueFromFile("src/test/java/com/dhl/g05/jsontestfiles/jsonBadFreeAngentInfo.json");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		assertNull(league);
	}

}

