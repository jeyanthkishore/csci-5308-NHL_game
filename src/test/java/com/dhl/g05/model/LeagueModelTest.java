package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import com.dhl.g05.database.ISerializeModel;
import com.dhl.g05.database.SerializeLeagueModelMock;


public class LeagueModelTest {

	private static ModelAbstractFactory modelAbstractFactory;
	private static ModelMockAbstractFactory modelMockAbstractFactory;

	@BeforeClass
	public static void init() {
		modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		modelMockAbstractFactory = ApplicationTestConfiguration.instance().getModelMockConcreteFactoryState();
	}

	@Test
	public void leagueConstructorTest() {
		ILeague league = modelAbstractFactory.createLeagueModel();
		assertNull(league.getLeagueName());
		assertNull(league.getConferenceDetails());
		assertNull(league.getFreeAgent());
		assertNull(league.getFreeCoach());
		assertNull(league.getManagerList());
		assertNull(league.getGamePlayConfig());
	}

	@Test
	public void leagueReferenceConstructorTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(leagueMock.leagueName,league.getLeagueName());
		assertSame(leagueMock.freeAgentList,league.getFreeAgent());
		assertSame(leagueMock.conferenceList,league.getConferenceDetails());
		assertSame(leagueMock.coachList, league.getFreeCoach());
	}

	@Test
	public void getLeagueNameTest() {
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setLeagueName("League");
		assertSame("League",league.getLeagueName());
	}

	@Test
	public void setLeagueNameTest() {
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setLeagueName("LeagueTest");
		assertSame("LeagueTest",league.getLeagueName());
	}

	@Test
	public void getConferenceDetailsTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setConferenceDetails(leagueMock.conferenceList);
		assertSame(leagueMock.conferenceList,league.getConferenceDetails());
	}

	@Test
	public void setConferenceDetailsTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setConferenceDetails(leagueMock.conferenceList);
		assertSame(leagueMock.conferenceList,league.getConferenceDetails());
	}

	@Test
	public void getFreeAgentTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setFreeAgent(leagueMock.freeAgentList);
		assertSame(leagueMock.freeAgentList,league.getFreeAgent());
	}

	@Test
	public void setFreeAgentTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setFreeAgent(leagueMock.freeAgentList);
		assertSame(leagueMock.freeAgentList,league.getFreeAgent());
	}

	@Test
	public void getFreeCoachTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setFreeCoach(leagueMock.coachList);
		assertSame(leagueMock.coachList, league.getFreeCoach());
	}

	@Test
	public void setFreeCoachTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league.setFreeCoach(leagueMock.coachList);
		assertSame(leagueMock.coachList, league.getFreeCoach());
	}

	@Test
	public void isLeagueNameEmptyTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.setLeagueEmpty();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.LeagueNameEmpty,league.validate());
	}

	@Test
	public void isLeagueNameNullTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.setLeagueNull();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.LeagueNameEmpty,league.validate());
	}

	@Test
	public void checkEmptyConference() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.removeConference();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.ConferenceListEmpty,league.validate());
	}
	
	@Test
	public void checkNullFreeAgent() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setFreeAgent(null);
		assertSame(LeagueConstant.FreeAgentsNotValid,league.validate());
	}
	
	
	@Test
	public void checkNullConference() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setConferenceDetails(null);
		assertSame(LeagueConstant.ConferenceListEmpty,league.validate());
	}
	
	@Test
	public void checkOddConference() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.removeOneConference();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.NoEvenConferenceCount,league.validate());
	}

	@Test
	public void addRetiredFreeAgentToListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague leagueModel = modelAbstractFactory.createLeagueModel(leagueMock);
		leagueModel.addRetiredFreeAgentToList(new FreeAgentModel(leagueMock.freeAgentOne, leagueMock.positionForward, leagueMock.skating, leagueMock.shooting, leagueMock.checking, leagueMock.saving, leagueMock.birthDay, leagueMock.birthMonth, leagueMock.birthYear));
		assertEquals(41,leagueModel.getRetiredFreeAgentsList().size());
	}

	@Test
	public void addRetiredPlayersToListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague leagueModel = modelAbstractFactory.createLeagueModel(leagueMock);
		List<IPlayer> players = leagueMock.playerList;
		IPlayer player = players.get(0);
		leagueModel.addRetiredPlayersToList(player);
		assertEquals(2,leagueModel.getRetiredPlayersList().size());
	}

	@Test
	public void setRetiredFreeAgentsListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setRetiredFreeAgentsList(leagueMock.retiredFreeAgentsList);
		assertEquals(leagueMock.retiredFreeAgentsList.size(),league.getRetiredFreeAgentsList().size());
	}

	@Test
	public void getRetiredFreeAgentsListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setRetiredFreeAgentsList(leagueMock.retiredFreeAgentsList);
		assertEquals(leagueMock.retiredFreeAgentsList.size(),league.getRetiredFreeAgentsList().size());
	}

	@Test
	public void getRetiredPlayersListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setRetiredPlayersList(leagueMock.retiredPlayersList);
		assertEquals(leagueMock.retiredPlayersList.size(),league.getRetiredPlayersList().size());
	}

	@Test
	public void setRetiredPlayersListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setRetiredPlayersList(leagueMock.retiredPlayersList);
		assertEquals(leagueMock.retiredPlayersList.size(),league.getRetiredPlayersList().size());
	}

	@Test
	public void isCoachListEmptyValidateTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.setCoachListEmpty();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.CoachListEmpty,league.validate());
	}

	@Test
	public void isManagerListEmptyValidateTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.setCoachListEmpty();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.CoachListEmpty,league.validate());
	}

	@Test
	public void getManagerListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(league.getManagerList(), leagueMock.managerList);
	}

	@Test
	public void setManagerListTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(league.getManagerList(), leagueMock.managerList);
	}


	@Test
	public void removeRetiredFreeAgentsFromLeagueTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague leagueModel = modelAbstractFactory.createLeagueModel(leagueMock);
		List<IFreeAgent> freeAgents = leagueMock.freeAgentList;
		leagueModel.removeRetiredFreeAgentsFromLeague(freeAgents.get(0));
		assertEquals(leagueMock.freeAgentList.size()-1,leagueModel.getFreeAgent().size()-1);

	}
	@Test
	public void addNewFreeAgentsToLeagueSizeTest()
	{
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague leagueModel = modelAbstractFactory.createLeagueModel(leagueMock);
		IFreeAgent agent= new FreeAgentModel();
		int size=leagueModel.getFreeAgent().size();
		List<IFreeAgent> freeAgentList= agent.ConvertPlayerToFreeAgent(leagueMock.playerList);
		leagueModel.addNewFreeAgentsToLeague(freeAgentList);
		assertEquals(leagueModel.getFreeAgent().size(),size + (leagueMock.playerList.size()));
		
	}
	@Test
	public void addNewFreeAgentsToLeaguePlayer1Test()
	{
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague leagueModel = modelAbstractFactory.createLeagueModel(leagueMock);
		leagueModel.setFreeAgent(leagueMock.freeAgentList);
		IFreeAgent agent= new FreeAgentModel();
		List<IFreeAgent> freeAgentList= agent.ConvertPlayerToFreeAgent(leagueMock.playerList);
		leagueModel.addNewFreeAgentsToLeague(freeAgentList);
		assertTrue(leagueModel.getFreeAgent().contains(freeAgentList.get(1)));
	}
	
	@Test
	public void addNewFreeAgentsToLeagueAgentNotPresentTest()
	{
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague leagueModel = modelAbstractFactory.createLeagueModel(leagueMock);
		IFreeAgent agent= new FreeAgentModel();
		List<IFreeAgent> freeAgentList= agent.ConvertPlayerToFreeAgent(leagueMock.playerList);
		IFreeAgent newPlayer= freeAgentList.get(1); 
		freeAgentList.remove(1);
		leagueModel.addNewFreeAgentsToLeague(freeAgentList);
		assertFalse(leagueModel.getFreeAgent().contains(newPlayer));
	}
 
	@Test
	public void validateTest() {
		LeagueMockData leagueMock = modelMockAbstractFactory.createLeagueMockData();
		ILeague league = modelAbstractFactory.createLeagueModel();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		leagueMock.setLeagueEmpty();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.LeagueNameEmpty,league.validate());
		leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.removeConference();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.ConferenceListEmpty,league.validate());
		leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.removeOneConference();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.NoEvenConferenceCount,league.validate());
		leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.setFreeAgentListEmpty();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.FreeAgentsNotValid,league.validate());
		leagueMock = modelMockAbstractFactory.createLeagueMockData();
		leagueMock.setFreeAgentListNotValid();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		assertSame(LeagueConstant.FreeAgentsNotValid,league.validate());
		leagueMock = modelMockAbstractFactory.createLeagueMockData();
		league = modelAbstractFactory.createLeagueModel(leagueMock);
		league.setLeagueName("DummyLEague");
		league.setManagerList(null);
		assertSame(LeagueConstant.ManagerListEmpty,league.validate());
	}
	
	@Test
	public void saveLeagueObjectTest() {
		ILeague league = modelAbstractFactory.createLeagueModel();
		ISerializeModel serializeLeague = new SerializeLeagueModelMock();
		assertTrue(league.saveLeagueObject(serializeLeague, "serializeLeague"));
	}

}
