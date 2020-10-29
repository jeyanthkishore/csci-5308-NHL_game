package com.dhl.g05.trading;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.dhl.g05.leaguemodel.gameplayconfig.TradingModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class WeakTeam implements IWeakTeam {

	private int numberOfSkaters = 0;
	private int numberOfGoalies = 0;
	private String conferenceName;
	private String divisionName;

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public int getNumberOfSkaters() {
		return numberOfSkaters;
	}

	public void setNumberOfSkaters(int numberOfSkaters) {
		this.numberOfSkaters = numberOfSkaters;
	}

	public int getNumberOfGoalies() {
		return numberOfGoalies;
	}

	public void setNumberOfGoalies(int numberOfGoalies) {
		this.numberOfGoalies = numberOfGoalies;
	}

	public List<PlayerModel> callWeakPlayersAndGetPosition(TeamModel weakTeam, TradingModel trade) {

		List<PlayerModel> weakestPlayersToTrade = findWeakestPlayers(weakTeam.getPlayerList(), trade);
		typeOfPlayersToTrade(weakestPlayersToTrade);
		return weakestPlayersToTrade;

	}

	private List<PlayerModel> findWeakestPlayers(List<PlayerModel> playersOfWeakTeam, TradingModel trade) {
		int maxPersonPerTrade = trade.getMaxPlayersPerTrade();
		System.out.println();
		System.out.println("New itration");

		ISortPlayerStrength sortPlayer = new SortPlayerStrength();
		List<PlayerModel> sortPlayersWeakToStrong = sortPlayer.sortByAscending(playersOfWeakTeam);
		Collection<PlayerModel> players = sortPlayersWeakToStrong;
		List<PlayerModel> weakestPLayersToTrade = players.stream().limit(maxPersonPerTrade)
				.collect(Collectors.toList());
		for (PlayerModel player : weakestPLayersToTrade) {
			System.out.println(player.getPlayerName() + "  " + player.getPosition() + " " + player.getPlayerStrength());

		}
		return weakestPLayersToTrade;
	}

	private void typeOfPlayersToTrade(List<PlayerModel> weakestPLayersToTrade) {
		int golie = 0;
		int skaters = 0;
		List<PlayerModel> weakPlayers = weakestPLayersToTrade;
		setNumberOfSkaters(skaters);
		setNumberOfGoalies(golie);
		int numberOfPlayers = weakPlayers.size();
		for (int i = 0; i < numberOfPlayers; i++) {
			PlayerModel player = weakPlayers.get(i);
			if (player.getPosition().equalsIgnoreCase("forward") || player.getPosition().equalsIgnoreCase("defense")) {
				skaters = skaters + 1;
				setNumberOfSkaters(skaters);
			} else if (player.getPosition().equalsIgnoreCase("goalie")) {
				golie = golie + 1;
				setNumberOfGoalies(golie);
			}
		}
	}


}
