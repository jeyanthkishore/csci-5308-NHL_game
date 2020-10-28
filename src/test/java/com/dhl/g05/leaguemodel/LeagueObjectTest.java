package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.operation.DbPersistanceMock;


public class LeagueObjectTest {

	@Test
	public void leagueConstructorTest() {
		LeagueObject object = new LeagueObject();
		assertNull(object.getLeagueName());
		assertNull(object.getConferenceDetails());
		assertNull(object.getFreeAgent());
		assertNull(object.getFreeCoach());
	}

	@Test
	public void leagueParameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		DbPersistanceMock db = new DbPersistanceMock();
		LeagueObject object = new LeagueObject(data.leagueName,data.conferenceList,data.freeAgentList,data.coachList,db);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void leagueReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject(data);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void getLeagueNameTest() {
		LeagueObject object = new LeagueObject();
		object.setLeagueName("League");
		assertSame("League",object.getLeagueName());
	}

	@Test
	public void setLeagueNameTest() {
		LeagueObject object = new LeagueObject();
		object.setLeagueName("League");
		assertSame("League",object.getLeagueName());
	}

	@Test
	public void getConferenceDetailsTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setConferenceDetails(data.conferenceList);
		assertSame(data.conferenceList,object.getConferenceDetails());
	}

	@Test
	public void setConferenceDetailsTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setConferenceDetails(data.conferenceList);
		assertSame(data.conferenceList,object.getConferenceDetails());
	}

	@Test
	public void getFreeAgentTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setFreeAgent(data.freeAgentList);
		assertSame(data.freeAgentList,object.getFreeAgent());
	}

	@Test
	public void setFreeAgentTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setFreeAgent(data.freeAgentList);
		assertSame(data.freeAgentList,object.getFreeAgent());
	}

	@Test
	public void getFreeCoachTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setFreeCoach(data.coachList);
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void setFreeCoachTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueObject object = new LeagueObject();
		object.setFreeCoach(data.coachList);
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void saveLeagueObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		LeagueObject valid = new LeagueObject(mock);
		assertEquals(1,valid.saveLeagueObject(data));
	}

	@Test
	public void loadLeagueObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		LeagueObject valid = new LeagueObject();
		assertEquals(1,valid.loadLeagueObject(mock.leagueName,data));
	}

	@Test
	public void isLeagueNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}

	@Test
	public void isLeagueNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}

	@Test
	public void checkLeagueName() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertEquals("HockeyLeague", valid.getLeagueName());
	}

	@Test
	public void checkEmptyConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeConference();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isConferenceListEmpty());
	}

	@Test
	public void checkEvenConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.hasEvenNumberConference());
	}

	@Test
	public void checkOddConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneConference();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.hasEvenNumberConference());
	}

	@Test
	public void checkLeagueExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock dbMock = new DbPersistanceMock();
		LeagueObject valid = new LeagueObject(mock.leagueName,mock.conferenceList,mock.freeAgentList,mock.coachList,dbMock);
		assertTrue(valid.checkLeaguePresent());
	}

	@Test
	public void checkLeagueNotExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock dbMock = new DbPersistanceMock();
		LeagueObject valid = new LeagueObject("dummyData",mock.conferenceList,mock.freeAgentList,mock.coachList,dbMock);
		assertFalse(valid.checkLeaguePresent());
	}

	@Test
	public void checkFreeAgentWrongPositionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.isFreeAgentPositionWrong());
	}


	@Test
	public void checkFreeAgentWrongPositionChange() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentPositionWrong());
	}

	@Test
	public void isFreeAgentAttributeEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueObject valid = new LeagueObject(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void isFreeAgentNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void checkFreeAgentNameeNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void checkFreeAgentPositionEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void checkFreeAgentPositionNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionNull();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void isCoachListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachListEmpty();
		LeagueObject valid = new LeagueObject(mock);
		assertTrue(valid.isCoachListEmpty());
	}

	@Test
	public void validateLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		LeagueObject league = new LeagueObject(mock.leagueName,mock.conferenceList,mock.freeAgentList, mock.coachList ,data);
		league.setLeagueName("DummyLEague");
		assertSame(ValidateEnumModel.Success,league.validate());
		league = new LeagueObject(mock);
		mock.setLeagueEmpty();
		league = new LeagueObject(mock);
		assertSame(ValidateEnumModel.LeagueNameEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.removeConference();
		league = new LeagueObject(mock);
		assertSame(ValidateEnumModel.ConferenceListEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.removeOneConference();
		league = new LeagueObject(mock);
		assertSame(ValidateEnumModel.NoEvenConferenceCount,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentListEmpty();
		league = new LeagueObject(mock);
		assertSame(ValidateEnumModel.FreeAgentsEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		league = new LeagueObject(mock);
		assertSame(ValidateEnumModel.FreeAgentAttributeEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		league = new LeagueObject(mock);
		assertSame(ValidateEnumModel.ImproperPlayerPosition,league.validate());
	}

}
