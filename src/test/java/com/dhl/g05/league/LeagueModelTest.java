package com.dhl.g05.league;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.database.SerializeLeagueModelMock;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.freeagent.IFreeAgent;
import com.dhl.g05.mockdata.JsonMockDataDb;
import com.dhl.g05.player.IPlayer;


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
		object.setLeagueName("LeagueTest");
		assertSame("LeagueTest",object.getLeagueName());
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
	public void checkEmptyConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.removeConference();
		LeagueModel league = new LeagueModel(mock);
		assertSame(LeagueConstant.ConferenceListEmpty,league.validate());
	}
	
	@Test
	public void checkNullFreeAgent() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel league = new LeagueModel(mock);
		league.setFreeAgent(null);
		assertSame(LeagueConstant.FreeAgentsNotValid,league.validate());
	}
	
	
	@Test
	public void checkNullConference() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel league = new LeagueModel(mock);
		league.setConferenceDetails(null);
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
	public void addRetiredFreeAgentToListTest(){
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague leagueModel = new LeagueModel(mock);
		List<IFreeAgent> freeAgents = mock.freeAgentList;
		IFreeAgent freeAgent = freeAgents.get(0);
		leagueModel.addRetiredFreeAgentToList(new FreeAgentModel(mock.freeAgentOne, mock.positionForward, mock.skating, mock.shooting, mock.checking, mock.saving, mock.birthDay, mock.birthMonth, mock.birthYear));
		assertEquals(41,leagueModel.getRetiredFreeAgentsList().size());
	}

	@Test
	public void addRetiredPlayersToListTest(){
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague leagueModel = new LeagueModel(mock);
		List<IPlayer> players = mock.playerList;
		IPlayer player = players.get(0);
		leagueModel.addRetiredPlayersToList(player);
		assertEquals(2,leagueModel.getRetiredPlayersList().size());
	}

	@Test
	public void setRetiredFreeAgentsListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague league = new LeagueModel(mock);
		league.setRetiredFreeAgentsList(mock.retiredFreeAgentsList);
		assertEquals(mock.retiredFreeAgentsList.size(),league.getRetiredFreeAgentsList().size());
	}

	@Test
	public void getRetiredFreeAgentsListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague league = new LeagueModel(mock);
		league.setRetiredFreeAgentsList(mock.retiredFreeAgentsList);
		assertEquals(mock.retiredFreeAgentsList.size(),league.getRetiredFreeAgentsList().size());
	}

	@Test
	public void getRetiredPlayersListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague league = new LeagueModel(mock);
		league.setRetiredPlayersList(mock.retiredPlayersList);
		assertEquals(mock.retiredPlayersList.size(),league.getRetiredPlayersList().size());
	}

	@Test
	public void setRetiredPlayersListTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague league = new LeagueModel(mock);
		league.setRetiredPlayersList(mock.retiredPlayersList);
		assertEquals(mock.retiredPlayersList.size(),league.getRetiredPlayersList().size());
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
	public void removeRetiredFreeAgentsFromLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague leagueModel = new LeagueModel(mock);
		List<IFreeAgent> freeAgents = mock.freeAgentList;
		leagueModel.removeRetiredFreeAgentsFromLeague(freeAgents.get(0));
		assertEquals(mock.freeAgentList.size()-1,leagueModel.getFreeAgent().size()-1);

	}
	@Test
	public void addNewFreeAgentsToLeagueSizeTest()
	{
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague leagueModel = new LeagueModel(mock);
		IFreeAgent agent= new FreeAgentModel();
		int size=leagueModel.getFreeAgent().size();
		List<IFreeAgent> freeAgentList= agent.ConvertPlayerToFreeAgent(mock.playerList);
		leagueModel.addNewFreeAgentsToLeague(freeAgentList);
		assertEquals(leagueModel.getFreeAgent().size(),size + (mock.playerList.size()));
		
	}
	@Test
	public void addNewFreeAgentsToLeaguePlayer1Test()
	{
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague leagueModel = new LeagueModel(mock);
		IFreeAgent agent= new FreeAgentModel();
		List<IFreeAgent> freeAgentList= agent.ConvertPlayerToFreeAgent(mock.playerList);
		leagueModel.addNewFreeAgentsToLeague(freeAgentList);
		assertTrue(leagueModel.getFreeAgent().contains(freeAgentList.get(1)));
	}
	
	@Test
	public void addNewFreeAgentsToLeagueAgentNotPresentTest()
	{
		JsonMockDataDb mock = new JsonMockDataDb();
		ILeague leagueModel = new LeagueModel(mock);
		IFreeAgent agent= new FreeAgentModel();
		List<IFreeAgent> freeAgentList= agent.ConvertPlayerToFreeAgent(mock.playerList);
		IFreeAgent newPlayer= freeAgentList.get(1); 
		freeAgentList.remove(1);
		leagueModel.addNewFreeAgentsToLeague(freeAgentList);
		assertFalse(leagueModel.getFreeAgent().contains(newPlayer));
	}
 
	@Test
	public void validateLeagueTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		LeagueModel league = new LeagueModel();
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
		mock = new JsonMockDataDb();
		league = new LeagueModel(mock);
		league.setLeagueName("DummyLEague");
		league.setManagerList(null);
		assertSame(LeagueConstant.ManagerListEmpty,league.validate());
	}
	
	@Test
	public void saveLeagueObjectTest() {
		ILeague league = new LeagueModel();
		ISerializeModel serializeLeague = new SerializeLeagueModelMock();
		assertTrue(league.saveLeagueObject(serializeLeague, "TeamName"));
	}

}
