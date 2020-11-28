package com.dhl.g05.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.dhl.g05.simulation.ILeagueStanding;
import com.dhl.g05.simulation.IStandingModel;
import com.dhl.g05.simulation.LeagueStanding;



public class PlayerDraftTest {
	
	PlayerDraftMock mock = new PlayerDraftMock();
	IPlayerDraft playerDraft= new PlayerDraft();
	ILeague league = mock.mockLeagueForStanding();
	ILeagueStanding leagueStanding = new LeagueStanding();
	IGenerateNewPlayers generate = new GenerateNewPlayers();
	List<IPlayer> newPlayers = generate.generatePlayers();
	
	@Test
	public void playerDraftPick() {
		leagueStanding.setStandingList(mock.mockStandings());
		List<IStandingModel> standings = leagueStanding.getRankingAcrossLeague();
		playerDraft.setPickOrderAfterTrading(mock.mockTradePickLatest(standings));
		playerDraft.playerDraft1(leagueStanding);
	}
	
	@Test
	public void createPickOrderSizeTest() {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		pickOrder = getPickOrder();
		assertSame(pickOrder.size(), 7);
	}
	
	@Test
	public void createPickOrderPick1Round1Test() {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		pickOrder = getPickOrder();
		String teamInRound1Pick1 = pickOrder.get(1).get(0).getTeam().getTeamName();
		assertSame(teamInRound1Pick1, "Boston");
	}
	@Test
	public void createPickOrderPick4Round3Test() {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		pickOrder = getPickOrder();
		String teamInRound1Pick1 = pickOrder.get(3).get(3).getTeam().getTeamName();
		assertSame(teamInRound1Pick1, "Thunders");
	}
	@Test
	public void createPickOrderPick1Round7Test() {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		pickOrder = getPickOrder();
		String teamInRound1Pick1 = pickOrder.get(7).get(0).getTeam().getTeamName();
		assertSame(teamInRound1Pick1, "Tigers");
	}
	
	@Test
	public void createPickOrderTradeTeamFirstPickTest1()
	{
		leagueStanding.setStandingList(mock.mockStandings());
		List<IStandingModel> standings = leagueStanding.getRankingAcrossLeague();
		Map<Integer, List<IStandingModel>> pickOrder = getPickOrder();
        String teamThatShouldPick=standings.get(standings.size()-1).getTeam().getTeamName() ;
        String teamThatTakesPick = pickOrder.get(3).get(0).getTeam().getTeamName();
        assertNotSame(teamThatShouldPick,teamThatTakesPick);
	}
	
	@Test
	public void createPickOrderTradeTeamFirstPickTest2()
	{
		leagueStanding.setStandingList(mock.mockStandings());
		List<IStandingModel> standings = leagueStanding.getRankingAcrossLeague();
		Map<Integer, List<IStandingModel>> pickOrder = getPickOrder();
        String teamThatShouldPick=standings.get(standings.size()-1).getTeam().getTeamName() ;
        String teamThatTakesPick = pickOrder.get(1).get(0).getTeam().getTeamName();
        assertSame(teamThatTakesPick,"Boston");
        assertSame(teamThatShouldPick,"Tigers");
	}
	
//	@Test
//	public void pickNewPlayersTest()
//	{
//		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
//		JsonMockDataDb mock = new JsonMockDataDb();
//		pickOrder = getPickOrder();
//		int teamSizeBeforePick=pickOrder.get(1).get(1).getTeam().getPlayerList().size(); 
//		playerDraft.pickNewPlayers(pickOrder,generate.generatePlayers());
//		int teamSizeAfterPick=pickOrder.get(1).get(1).getTeam().getPlayerList().size();
//		assertNotSame(teamSizeBeforePick,teamSizeAfterPick);
//	}

	public Map<Integer, List<IStandingModel>> getPickOrder() {
		leagueStanding.setStandingList(mock.mockStandings());
		List<IStandingModel> standings = leagueStanding.getRankingAcrossLeague();
		List<IStandingModel> weakTeamFirst=new ArrayList<>();
		for(int i=standings.size()-1; i>=0;i--)
		{
			weakTeamFirst.add(standings.get(i));
		}
		List<IStandingModel> teamsEligibleForPickFirst = weakTeamFirst.subList(0,2);
		List<IStandingModel> teamsEligibleForPickLater = weakTeamFirst.subList(2, weakTeamFirst.size());
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		mock.mockTradePickLatest(standings);
		playerDraft.setPickOrderAfterTrading(mock.mockTradePickLatest(standings));
		pickOrder = playerDraft.createPickOrder(teamsEligibleForPickFirst,teamsEligibleForPickLater);
		return pickOrder;
	}

}
