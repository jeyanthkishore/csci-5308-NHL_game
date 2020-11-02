package com.dhl.g05.league;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.dhl.g05.MockData.JsonMockDataDb;


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
		LeaguePersistenceMock db = new LeaguePersistenceMock();
		LeagueModel object = new LeagueModel(data.leagueName,data.conferenceList,data.freeAgentList,data.coachList, data.managerList, data.gamePlayConfig,db);
		assertSame(data.leagueName,object.getLeagueName());
		assertSame(data.freeAgentList,object.getFreeAgent());
		assertSame(data.conferenceList,object.getConferenceDetails());
		assertSame(data.coachList, object.getFreeCoach());
		assertSame(data.gamePlayConfig,object.getGamePlayConfig());
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
		LeaguePersistenceMock data = new LeaguePersistenceMock();
		LeagueModel league = new LeagueModel(mock);
		assertEquals(1,league.saveLeagueObject(data));
	}

	@Test
	public void loadLeagueObjectTest() {
		LeaguePersistenceMock data = new LeaguePersistenceMock();
		LeagueModel league = new LeagueModel();
		assertEquals(1,league.loadLeagueObject(1,data));
	}

	@Test
	public void loadLeagueFromTeamTest() {
		LeaguePersistenceMock data = new LeaguePersistenceMock();
		LeagueModel league = new LeagueModel();
		assertEquals(1,league.loadLeagueFromTeam("Striker Six", data));
	}
	
	@Test
	public void isLeagueNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueEmpty();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.LeagueNameEmpty,league.validate());
	}

	@Test
	public void isLeagueNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setLeagueNull();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.LeagueNameEmpty,league.validate());
	}

	@Test
	public void checkLeagueName() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel league = new LeagueModel(mock);
		assertEquals("HockeyLeague", league.getLeagueName());
	}

	@Test
	public void checkEmptyConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeConference();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.ConferenceListEmpty,league.validate());
	}

	@Test
	public void checkOddConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeOneConference();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.NoEvenConferenceCount,league.validate());
	}

	@Test
	public void checkLeagueExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeaguePersistenceMock dbMock = new LeaguePersistenceMock();
		LeagueModel league = new LeagueModel(mock.leagueName,mock.conferenceList,mock.freeAgentList,mock.coachList, mock.managerList,mock.gamePlayConfig,dbMock);
		assertSame(LeagueConstant.LeagueExists,league.validate());
	}

	@Test
	public void checkLeagueNotExistenceTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeaguePersistenceMock dbMock = new LeaguePersistenceMock();
		LeagueModel league = new LeagueModel("dummyData",mock.conferenceList,mock.freeAgentList,mock.coachList, mock.managerList,mock.gamePlayConfig, dbMock);
		assertSame(LeagueConstant.Success,league.validate());
	}


	@Test
	public void isCoachListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachListEmpty();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.CoachListEmpty,league.validate());
	}

	@Test
	public void isManagerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setCoachListEmpty();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.CoachListEmpty,league.validate());
	}
	
	@Test
	public void validateLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeaguePersistenceMock data = new LeaguePersistenceMock();
		LeagueModel league = new LeagueModel(mock.leagueName,mock.conferenceList,mock.freeAgentList, mock.coachList, mock.managerList, mock.gamePlayConfig,data);
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
		assertSame(LeagueConstant.FreeAgentsNotValid,league.validate());
		mock = new JsonMockDataDb();
		mock.setFreeAgentListNotValid();
		league = new LeagueModel(mock);
		assertSame(LeagueConstant.FreeAgentsNotValid,league.validate());
	}

}
