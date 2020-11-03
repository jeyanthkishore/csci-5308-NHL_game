package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;


public class WeakTeam implements IWeakTeam {

	private TeamModel weakTeam;
	private String conferenceName;
	private String divisionName;
	private int numberOfPlayersOffered;
	private String offeredPlayerPosition;
	private List<PlayerModel> playersOffered;
	private double strengthOfPlayersOffered = 0.0;

	public double getStrengthOfPlayersOffered() {
		return strengthOfPlayersOffered;
	}

	public void setStrengthOfPlayersOffered(double strengthOfPlayersOffered) {
		this.strengthOfPlayersOffered = strengthOfPlayersOffered;
	}

	public String getOfferedPlayerPosition() {
		return offeredPlayerPosition;
	}

	public TeamModel getWeakTeam() {
		return weakTeam;
	}

	public void setWeakTeam(TeamModel weakTeam) {
		this.weakTeam = weakTeam;
	}

	public void setOfferedPlayerPosition(String offeredPlayerPosition) {
		this.offeredPlayerPosition = offeredPlayerPosition;
	}

	public List<PlayerModel> getPlayersOffered() {
		return playersOffered;
	}

	public void setPlayersOffered(List<PlayerModel> playersOffered) {
		this.playersOffered = playersOffered;
	}

	public String getWeakPlayerPosition() {
		return offeredPlayerPosition;
	}

	public void setWeakPlayerPosition(String weakPlayerPosition) {
		this.offeredPlayerPosition = weakPlayerPosition;
	}

	public int getNumberOfPlayersOffered() {
		return numberOfPlayersOffered;
	}

	public void setNumberOfPlayersOffered(int numberOfPlayersOffered) {
		this.numberOfPlayersOffered = numberOfPlayersOffered;
	}

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

	public void playersToOffer(TradingModel trade) {

		String weakPosition = "";
		int weakPlayers = 0;
		double strength = 0.0;
		int maxPersonPerTrade = trade.getMaxPlayersPerTrade();
		List<PlayerModel> offeredPlayers = new ArrayList<PlayerModel>();
		List<PlayerModel> playersOfWeakTeam = weakTeam.getPlayerList();
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();
		List<PlayerModel> sortPlayersWeakToStrong = sortPlayer.sortByAscending(playersOfWeakTeam);

		Collection<PlayerModel> players = sortPlayersWeakToStrong;
		List<PlayerModel> weakestPLayersToTrade = players.stream().limit(maxPersonPerTrade)
				.collect(Collectors.toList());
		weakPosition = weakestPLayersToTrade.get(0).getPosition();

		for (PlayerModel player : weakestPLayersToTrade) {
			if (player.getPosition().equals(weakPosition)) {
				offeredPlayers.add(player);
				strength += player.getPlayerStrength();
				weakPlayers++;
			}
		}
		setNumberOfPlayersOffered(weakPlayers);
		setPlayersOffered(offeredPlayers);
		setOfferedPlayerPosition(weakPosition);
		setStrengthOfPlayersOffered(strength);
	}


}
