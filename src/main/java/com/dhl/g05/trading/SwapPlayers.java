package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class SwapPlayers implements ISwapPlayers {

	public void swapPlayers(TeamModel weakTeam, TeamModel strongTeam, List<PlayerModel> weakestPlayersToTrade,
			List<PlayerModel> strongestPlayersToTrade) {
//		TradeDescision descision = new TradeDescision();
		List<PlayerModel> weakTeamPlayers = weakTeam.getPlayerList();
		List<PlayerModel> strongTeamPlayers = strongTeam.getPlayerList();
		List<PlayerModel> weakPlayersToTrade = weakestPlayersToTrade;
		List<PlayerModel> strongPlayersToTrade = strongestPlayersToTrade;

		weakTeamPlayers.addAll(strongPlayersToTrade);
		strongTeamPlayers.addAll(weakestPlayersToTrade);
		strongTeamPlayers.removeAll(strongestPlayersToTrade);
		weakTeamPlayers.removeAll(weakPlayersToTrade);

		weakTeam.setPlayerList(weakTeamPlayers);
		strongTeam.setPlayerList(strongTeamPlayers);

		System.out.println("The weak team is" + weakTeam.getTeamName());
		System.out.println();
		for (PlayerModel player : weakTeam.getPlayerList()) {
			System.out.println(player.getPlayerName() + " " + player.getPlayerStrength() + " " + player.getPosition());

		}
		System.out.println("The strong team is" + strongTeam.getTeamName());
		System.out.println();
		for (PlayerModel player : strongTeam.getPlayerList()) {

			System.out.println(player.getPlayerName() + "  " + player.getPosition() + " " + player.getPlayerStrength());
		}
	}
}