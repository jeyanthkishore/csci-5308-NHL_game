package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueModel;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.TeamModel;

public class StrongTeam implements IStrongTeam {

	private ITeam strongTeam;
	private String conferenceName;
	private String divisionName;
	private double strengthOfStrongestPlayers = 0.00;
	private List<IPlayer> strongestPlayersToTrade;

	public double getStrengthOfStrongestPlayers() {
		return strengthOfStrongestPlayers;
	}

	public void setStrengthOfStrongestPlayers(double strengthOfStrongestPlayers) {
		this.strengthOfStrongestPlayers = strengthOfStrongestPlayers;
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

	public ITeam getStrongTeam() {
		return strongTeam;
	}

	public void setStrongTeam(ITeam strongTeam) {
		this.strongTeam = strongTeam;
	}

	public List<IPlayer> getStrongestPlayersToTrade() {
		return strongestPlayersToTrade;
	}

	public void setStrongestPlayersToTrade(List<IPlayer> strongestPlayersToTrade) {
		this.strongestPlayersToTrade = strongestPlayersToTrade;
	}

	public boolean findTeamToSwap(ILeague league) {

		boolean isTradePossible = false;
		double strengthOfStrongestPLayer = 0.00;
		List<IPlayer> playersRequested = new ArrayList<>();
		IWeakTeam teamInitiatingTrade = Trading.instance().getWeakteam();
		String position = teamInitiatingTrade.getOfferedPlayerPosition();
		int numberOfPlayersToTrade = teamInitiatingTrade.getNumberOfPlayersOffered();
		ISortPlayerStrength sortPlayer = AbstractTradingFactory.getFactory().getSortplayerstrength();

		for (IConference conference : league.getConferenceDetails()) {
			for (IDivision division : conference.getDivisionDetails()) {
				for (ITeam team : division.getTeamDetails()) {
					int countOfPlayers = 0;
					double strengthOfPlayers = 0.00;
					if (((teamInitiatingTrade.getWeakTeam().getTeamName()).equals(team.getTeamName()))
							&& ((teamInitiatingTrade.getConferenceName()).equals(conference.getConferenceName()))
							&& ((teamInitiatingTrade.getDivisionName()).equals(division.getDivisionName()))) {
						continue;
					} else {
						List<IPlayer> strongestPlayers = sortPlayer.sortByDescending(team.getPlayerList());
						for (int i = 0; i < teamInitiatingTrade.getPlayersOffered().size(); i++) {
							for (int j = 0; j < strongestPlayers.size(); j++) {
								if (strongestPlayers.get(j).getPlayerStrength() >= teamInitiatingTrade
										.getPlayersOffered().get(i).getPlayerStrength()
										&& strongestPlayers.get(j).getPosition().equals(position)) {
									if (playersRequested.contains(strongestPlayers.get(j))) {
										continue;
									} else {
										playersRequested.add(strongestPlayers.get(j));
										strengthOfPlayers += strongestPlayers.get(j).getPlayerStrength();
										countOfPlayers++;
										break;
									}
								}
							}
							if (countOfPlayers == numberOfPlayersToTrade) {
								break;
							}
						}
						if ((strengthOfPlayers > strengthOfStrongestPLayer)
								&& countOfPlayers == numberOfPlayersToTrade) {
							setStrengthOfStrongestPlayers(strengthOfPlayers);
							setStrongTeam(team);
							setStrongestPlayersToTrade(playersRequested);
							this.setConferenceName(conference.getConferenceName());
							this.setDivisionName(division.getDivisionName());
							isTradePossible = true;
						}
					}
				}
			}
		}
		return isTradePossible;
	}
}
