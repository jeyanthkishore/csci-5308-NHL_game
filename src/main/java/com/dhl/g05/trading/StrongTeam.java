package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class StrongTeam implements IStrongTeam {

	private TeamModel strongTeam;
	private String conferenceName;
	private String divisionName;
	private List<PlayerModel> strongestPlayersToTrade;

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

	double strongestTeamStrength = 0.00;

	public List<PlayerModel> findTeamToSwap(List<PlayerModel> weakestPlayersToTrade, TeamModel teamTrading,
			LeagueModel league) {
		System.out.println("In strong");
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();

		for (ConferenceModel conference : league.getConferenceDetails()) {
			for (DivisionModel division : conference.getDivisionDetails()) {
				for (TeamModel team : division.getTeamDetails()) {
					if (((teamTrading.getTeamName()).equals(team.getTeamName()))
							&& ((teamInitiatingTrade.getConferenceName()).equals(conference.getConferenceName()))
							&& ((teamInitiatingTrade.getDivisionName()).equals(division.getDivisionName()))) {
						{
							// do something
						}
					} else {
						System.out.println("In strong 1");
						System.out.println(team.getTeamName());
						List<PlayerModel> sortPlayersStrongToWeak = sortPlayer.sortByDescending(team.getPlayerList());
						boolean result = findPlayersToSwap(sortPlayersStrongToWeak, weakestPlayersToTrade);
						if (result) {
							this.setStrongTeam(team);
							this.setConferenceName(conference.getConferenceName());
							this.setDivisionName(division.getDivisionName());
							System.out.println("strong team is " + this.getStrongTeam().getTeamName());
							for (PlayerModel player : this.getStrongestPlayersToTrade()) {
								System.out.println(player.getPlayerName());
							}

						} else {
							break;
						}
					}
				}
			}
		}
		System.out.println("weak team is " + teamTrading.getTeamName());

		return this.getStrongestPlayersToTrade();
	}

	public boolean findPlayersToSwap(List<PlayerModel> sortPlayersStrongToWeak,
			List<PlayerModel> weakestPlayersToTrade) {
		ISortPlayerStrength sortPlayer = TradingConfig.instance().getSortplayerstrength();
		IWeakTeam teamInitiatingTrade = TradingConfig.instance().getWeakteam();
		int goalie = teamInitiatingTrade.getNumberOfGoalies();
		int skaters = teamInitiatingTrade.getNumberOfSkaters();
		// ISortPlayerStrength sort = TradingConfig.instance().getSortplayerstrength();
		boolean isFinalTradeTeam = false;
		List<PlayerModel> goodSkaters = new ArrayList<PlayerModel>();
		List<PlayerModel> goodGoalies = new ArrayList<PlayerModel>();
		List<PlayerModel> finalSwapGoalies = new ArrayList<PlayerModel>();
		List<PlayerModel> finalSwapSkaters = new ArrayList<PlayerModel>();
		List<PlayerModel> finalSwapPlayers = new ArrayList<PlayerModel>();

		for (PlayerModel strongPlayer : sortPlayersStrongToWeak) {
			for (PlayerModel weakPlayer : weakestPlayersToTrade) {
				if (strongPlayer.getPlayerStrength() >= weakPlayer.getPlayerStrength()
						&& (strongPlayer.getPosition().equalsIgnoreCase("forward")
								|| (strongPlayer.getPosition().equalsIgnoreCase("defense")))
						&& (weakPlayer.getPosition().equalsIgnoreCase("defense")
								|| weakPlayer.getPosition().equalsIgnoreCase("forward"))) {
					goodSkaters.add(strongPlayer);
					break;

				}
				{
					if ((strongPlayer.getPlayerStrength() >= weakPlayer.getPlayerStrength())
							&& (strongPlayer.getPosition().equalsIgnoreCase("goalie"))
							&& (weakPlayer.getPosition().equalsIgnoreCase("goalie"))) {
						goodGoalies.add(strongPlayer);
						break;
					}
				}

			}
		}

		if (goodGoalies.size() > 0) {
			goodGoalies = sortPlayer.sortByDescending(goodGoalies);
			if (goodGoalies.size() > 1) {
				finalSwapGoalies = goodGoalies.subList(0, goalie);
			}
			if (goodGoalies.size() == 1) {
				finalSwapGoalies = goodGoalies;
			}
			finalSwapPlayers.addAll(finalSwapGoalies);
		}

		if (goodSkaters.size() > 0) {

			goodSkaters = (sortPlayer.sortByDescending(goodSkaters));
			if (goodSkaters.size() > 1) {
				finalSwapSkaters = goodSkaters.subList(0, skaters);
			}
			if (goodSkaters.size() == 1) {
				finalSwapSkaters = goodSkaters;
			}
			finalSwapPlayers.addAll(finalSwapSkaters);
		}

		if (finalSwapPlayers.size() == (skaters + goalie)) {
			double strength = 0.0;
			System.out.println();
			System.out.println("this is the final list of players to trade from the strongest team");
			for (PlayerModel player : finalSwapPlayers) {
				System.out.println(
						player.getPlayerName() + "  " + player.getPosition() + " " + player.getPlayerStrength());
				strength += player.getPlayerStrength();
			}

			if (strength > strongestTeamStrength) {
				this.setStrongestPlayersToTrade(finalSwapPlayers);
				strongestTeamStrength = strength;
				isFinalTradeTeam = true;
			}

			return isFinalTradeTeam;
		}

		else {
			return isFinalTradeTeam;
		}
	}

}
