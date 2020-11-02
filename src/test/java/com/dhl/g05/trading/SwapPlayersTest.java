package com.dhl.g05.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public class SwapPlayersTest {

	List<TeamModel> teams = new ArrayList<TeamModel>();
	ISwapPlayers swap = new SwapPlayers();
	List<PlayerModel> swapList1 = new ArrayList<PlayerModel>();
	List<PlayerModel> swapList2 = new ArrayList<PlayerModel>();

	@Test
	public void swapPlayersTest1() {
		teams = swapMock();
		swapList1.add(teams.get(0).getPlayerList().get(0));
		swapList1.add(teams.get(0).getPlayerList().get(1));
		swapList2.add(teams.get(1).getPlayerList().get(0));
		swapList2.add(teams.get(1).getPlayerList().get(1));
		swap.swapPlayers(teams.get(0), teams.get(1), swapList1, swapList2);
		assertSame(teams.get(0).getPlayerList().size(), teams.get(1).getPlayerList().size());

	}

	@Test
	public void swapPlayersTest2() {
		teams = swapMock();
		swapList1.add(teams.get(0).getPlayerList().get(2));
		swapList2.add(teams.get(1).getPlayerList().get(1));
		swap.swapPlayers(teams.get(0), teams.get(1), swapList1, swapList2);
		assertEquals(teams.get(0).getPlayerList().get(2).getPlayerName(), "JustinTeam2");

	}

	public List<TeamModel> swapMock() {
		TeamModel team1 = new TeamModel();
		team1.setTeamName("Tigers");
		TeamModel team2 = new TeamModel();
		team2.setTeamName("Rythm");
		ArrayList<TeamModel> teamDetails1 = new ArrayList<TeamModel>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		PlayerModel player1Team1 = new PlayerModel();
		PlayerModel player2Team1 = new PlayerModel();
		PlayerModel player3Team1 = new PlayerModel();
		PlayerModel player1Team2 = new PlayerModel();
		PlayerModel player2Team2 = new PlayerModel();
		PlayerModel player3Team2 = new PlayerModel();

		player1Team1.setPlayerName("ShawnTeam1");
		player2Team1.setPlayerName("KristenTeam1");
		player3Team1.setPlayerName("ZackTeam1");
		ArrayList<PlayerModel> playerDetailsTeam1 = new ArrayList<PlayerModel>();
		playerDetailsTeam1.add(player1Team1);
		playerDetailsTeam1.add(player2Team1);
		playerDetailsTeam1.add(player3Team1);
		team1.setPlayerList(playerDetailsTeam1);

		player1Team2.setPlayerName("LiamTeam2");
		player2Team2.setPlayerName("JustinTeam2");
		player3Team2.setPlayerName("ChesterTeam2");
		ArrayList<PlayerModel> playerDetailsTeam2 = new ArrayList<PlayerModel>();
		playerDetailsTeam2.add(player1Team2);
		playerDetailsTeam2.add(player2Team2);
		playerDetailsTeam2.add(player3Team2);
		team2.setPlayerList(playerDetailsTeam2);

		teamDetails1.add(team1);
		teamDetails1.add(team2);
		return teamDetails1;
	}

}
