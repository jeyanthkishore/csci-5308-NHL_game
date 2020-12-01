package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.simulation.statemachine.ITradingConfig;

public class WeakTeam implements IWeakTeam {
	
	static final Logger logger = LogManager.getLogger(StrongTeam.class);
	private ITeam weakTeam;
	private String conferenceName;
	private String divisionName;
	private int numberOfPlayersOffered;
	private String offeredPlayerPosition;
	private List<IPlayer> playersOffered;
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

	public ITeam getWeakTeam() {
		return weakTeam;
	}

	public void setWeakTeam(ITeam weakTeam) {
		this.weakTeam = weakTeam;
	}

	public void setOfferedPlayerPosition(String offeredPlayerPosition) {
		this.offeredPlayerPosition = offeredPlayerPosition;
	}

	public List<IPlayer> getPlayersOffered() {
		return playersOffered;
	}

	public void setPlayersOffered(List<IPlayer> playersOffered) {
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

	public void playersToOffer(ITradingConfig trade) {

		String weakPosition = "";
		int weakPlayers = 0;
		double strength = 0.0;
		int maxPersonPerTrade = trade.getMaxPlayersPerTrade();
		List<IPlayer> offeredPlayers = new ArrayList<>();
		List<IPlayer> playersOfWeakTeam = weakTeam.getPlayerList();
		ISortPlayerStrength sortPlayer = ApplicationConfiguration.instance().getTradingConcreteFactoryState().createSortplayerstrength();
		List<IPlayer> sortPlayersWeakToStrong = sortPlayer.sortByAscending(playersOfWeakTeam);
		Collection<IPlayer> players = sortPlayersWeakToStrong;
		List<IPlayer> weakestPLayersToTrade = players.stream().limit(maxPersonPerTrade).collect(Collectors.toList());
		weakPosition = weakestPLayersToTrade.get(0).getPosition();

		for (IPlayer player : weakestPLayersToTrade) {
			if (player.getPosition().equals(weakPosition)) {
				offeredPlayers.add(player);
				logger.info("Player(s) offered is : "+ player.getPlayerName() + "and position is "+ player.getPosition());
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
