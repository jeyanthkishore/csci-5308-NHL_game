package com.dhl.g05.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.dhl.g05.model.GenerateNewPlayers;
import com.dhl.g05.model.IGenerateNewPlayers;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.IPlayerDraft;
import com.dhl.g05.model.PlayerDraft;
import com.dhl.g05.statemachine.ILeagueStanding;
import com.dhl.g05.statemachine.IStandingModel;
import com.dhl.g05.statemachine.LeagueStanding;


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
		for (IStandingModel standing : standings) {
			System.out.println(standing.getTeam().getTeamName() + " " + standing.getConference().getConferenceName()
					+ " " + standing.getDivision().getDivisionName()+ standing.getTotalPoints());
		}
		playerDraft.playerDraft(leagueStanding,mock.mockTradePick(standings));
	}
	
	@Test
	public void createPickOrderSizeTest() {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		pickOrder = getPickOrder();
		assertSame(pickOrder.size(), 7);
	}
	
	@Test
	public void createPickOrderTeamTest() {
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		pickOrder = getPickOrder();
		String teamInRound1Pick1 = pickOrder.get(1).get(0).getTeam().getTeamName();
		assertSame(teamInRound1Pick1, "Boston");
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

	public Map<Integer, List<IStandingModel>> getPickOrder() {
		leagueStanding.setStandingList(mock.mockStandings());
		List<IStandingModel> standings = leagueStanding.getRankingAcrossLeague();
		List<IStandingModel> teamsEligibleForPick = new ArrayList<>();
		Map<Integer, List<IStandingModel>> pickOrder = new HashMap<>();
		for (int i = standings.size() - 1; i >= 0; i--) {
			teamsEligibleForPick.add(standings.get(i));
		}
		pickOrder = playerDraft.createPickOrder(teamsEligibleForPick, mock.mockTradePick(standings));
		return pickOrder;
	}
}
