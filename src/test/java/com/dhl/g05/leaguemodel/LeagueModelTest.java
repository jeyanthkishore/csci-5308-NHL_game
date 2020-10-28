package com.dhl.g05.leaguemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dhl.g05.leaguemodel.league.LeagueConstant;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.operation.DbPersistanceMock;


public class LeagueModelTest {

	@Test
	public void leagueConstructorTest() {
		LeagueModel object = new LeagueModel();
		assertNull(object.getLeagueName());
		assertNull(object.getConferenceDetails());
		assertNull(object.getFreeAgent());
		assertNull(object.getFreeCoach());
	}

	@Test
	public void leagueParameterConstructorTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		DbPersistanceMock db = new DbPersistanceMock();
		LeagueModel object = new LeagueModel(data.leagueName,data.conferenceList,data.freeAgentList,data.coachList, data.managerList, db);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void leagueReferenceConstructor() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel(data);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void getLeagueNameTest() {
		LeagueModel object = new LeagueModel();
		object.setLeagueName("League");
		assertSame("League",object.getLeagueName());
	}

	@Test
	public void setLeagueNameTest() {
		LeagueModel object = new LeagueModel();
		object.setLeagueName("League");
		assertSame("League",object.getLeagueName());
	}

	@Test
	public void getConferenceDetailsTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel();
		object.setConferenceDetails(data.conferenceList);
		assertSame(data.conferenceList,object.getConferenceDetails());
	}

	@Test
	public void setConferenceDetailsTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel();
		object.setConferenceDetails(data.conferenceList);
		assertSame(data.conferenceList,object.getConferenceDetails());
	}

	@Test
	public void getFreeAgentTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel();
		object.setFreeAgent(data.freeAgentList);
		assertSame(data.freeAgentList,object.getFreeAgent());
	}

	@Test
	public void setFreeAgentTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel();
		object.setFreeAgent(data.freeAgentList);
		assertSame(data.freeAgentList,object.getFreeAgent());
	}

	@Test
	public void getFreeCoachTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel();
		object.setFreeCoach(data.coachList);
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void setFreeCoachTest() {
		JsonMockDataDb data = new JsonMockDataDb();
		LeagueModel object = new LeagueModel();
		object.setFreeCoach(data.coachList);
		assertSame(data.coachList, object.getFreeCoach());
	}

	@Test
	public void saveLeagueObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		LeagueModel valid = new LeagueModel(mock);
		assertEquals(1,valid.saveLeagueObject(data));
	}

	@Test
	public void loadLeagueObjectTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		LeagueModel valid = new LeagueModel();
		assertEquals(1,valid.loadLeagueObject(mock.leagueName,data));
	}

	@Test
	public void isLeagueNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueEmpty();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}

	@Test
	public void isLeagueNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueNull();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isLeagueNameEmptyOrNull());
	}

	@Test
	public void checkLeagueName() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel valid = new LeagueModel(mock);
		assertEquals("HockeyLeague", valid.getLeagueName());
	}

	@Test
	public void checkEmptyConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeConference();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isConferenceListEmpty());
	}

	@Test
	public void checkEvenConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.hasEvenNumberConference());
	}

	@Test
	public void checkOddConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneConference();
		LeagueModel valid = new LeagueModel(mock);
		assertFalse(valid.hasEvenNumberConference());
	}

	@Test
	public void checkLeagueExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock dbMock = new DbPersistanceMock();
		LeagueModel valid = new LeagueModel(mock.leagueName,mock.conferenceList,mock.freeAgentList,mock.coachList, mock.managerList, dbMock);
		assertTrue(valid.checkLeaguePresent());
	}

	@Test
	public void checkLeagueNotExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock dbMock = new DbPersistanceMock();
		LeagueModel valid = new LeagueModel("dummyData",mock.conferenceList,mock.freeAgentList,mock.coachList, mock.managerList, dbMock);
		assertFalse(valid.checkLeaguePresent());
	}

	@Test
	public void checkFreeAgentWrongPositionTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel valid = new LeagueModel(mock);
		assertFalse(valid.isFreeAgentPositionWrong());
	}


	@Test
	public void checkFreeAgentWrongPositionChange() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isFreeAgentPositionWrong());
	}

	@Test
	public void isFreeAgentAttributeEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel valid = new LeagueModel(mock);
		assertFalse(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void isFreeAgentNameEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void checkFreeAgentNameeNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentNameNull();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void checkFreeAgentPositionEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionEmpty();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void checkFreeAgentPositionNull() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setFreeAgentPositionNull();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isFreeAgentDetailsEmptyOrNull());
	}

	@Test
	public void isCoachListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachListEmpty();
		LeagueModel valid = new LeagueModel(mock);
		assertTrue(valid.isCoachListEmpty());
	}

	@Test
	public void validateLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		DbPersistanceMock data = new DbPersistanceMock();
		LeagueModel league = new LeagueModel(mock.leagueName,mock.conferenceList,mock.freeAgentList, mock.coachList, mock.managerList, data);
		league.setLeagueName("DummyLEague");
		assertSame(LeagueConstant.Success,league.validate());
		league = new LeagueModel(mock);
		mock.setLeagueEmpty();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.LeagueNameEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.removeConference();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.ConferenceListEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.removeOneConference();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.NoEvenConferenceCount,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentListEmpty();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.FreeAgentsEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentNameEmpty();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.FreeAgentAttributeEmpty,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentPositionDifferent();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.ImproperPlayerPosition,league.validate());
	}

}
