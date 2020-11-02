package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;


public interface IStrongTeam {

	public String getConferenceName();

	public void setConferenceName(String conferenceName);

	public String getDivisionName();

	public void setDivisionName(String divisionName);

	public TeamModel getStrongTeam();

	public void setStrongTeam(TeamModel strongTeam);

	public double getStrengthOfStrongestPlayers();

	public void setStrengthOfStrongestPlayers(double strengthOfStrongestPlayers);

	public List<PlayerModel> getStrongestPlayersToTrade();

	public void setStrongestPlayersToTrade(List<PlayerModel> strongestPlayersToTrade);

	public boolean findTeamToSwap(LeagueModel league);

//	public boolean findPlayersToSwap(List<PlayerObject> sortPlayersStrongToWeak);
}
