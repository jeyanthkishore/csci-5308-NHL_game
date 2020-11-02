package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;


public class StrongTeam implements IStrongTeam {

	private TeamModel strongTeam;
	private String conferenceName;
	private String divisionName;
	private double strengthOfStrongestPlayers = 0.00;
	private List<PlayerModel> strongestPlayersToTrade;

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

	public TeamModel getStrongTeam() {
		return strongTeam;
	}

	public void setStrongTeam(TeamModel strongTeam) {
		this.strongTeam = strongTeam;
	}

	public List<PlayerModel> getStrongestPlayersToTrade() {
		return strongestPlayersToTrade;
	}

	public void setStrongestPlayersToTrade(List<PlayerModel> strongestPlayersToTrade) {
		this.strongestPlayersToTrade = strongestPlayersToTrade;
	}

	public boolean findTeamToSwap(LeagueModel league) {

		boolean isTradePossible = false;
		double strengthOfStrongestPLayer = 0.00;
		List<PlayerModel> playersRequested = new ArrayList<PlayerModel>();
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		String position = teamInitiatingTrade.getOfferedPlayerPosition();
		int numberOfPlayersToTrade = teamInitiatingTrade.getNumberOfPlayersOffered();
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();

		for (ConferenceModel conference : league.getConferenceDetails()) {
			for (DivisionModel division : conference.getDivisionDetails()) {
				for (TeamModel team : division.getTeamDetails()) {
					int countOfPlayers = 0;
					double strengthOfPlayers = 0.00;
					if (((teamInitiatingTrade.getWeakTeam().getTeamName()).equals(team.getTeamName()))
							&& ((teamInitiatingTrade.getConferenceName()).equals(conference.getConferenceName()))
							&& ((teamInitiatingTrade.getDivisionName()).equals(division.getDivisionName()))) {
						continue;
					} else {
						List<PlayerModel> strongestPlayers = sortPlayer.sortByDescending(team.getPlayerList());
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
