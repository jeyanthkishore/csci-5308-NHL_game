package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.team.ITeam;

public interface IStrongTeam {

	public String getConferenceName();

	public void setConferenceName(String conferenceName);

	public String getDivisionName();

	public void setDivisionName(String divisionName);

	public ITeam getStrongTeam();

	public void setStrongTeam(ITeam strongTeam);

	public double getStrengthOfStrongestPlayers();

	public void setStrengthOfStrongestPlayers(double strengthOfStrongestPlayers);

	public List<IPlayer> getStrongestPlayersToTrade();

	public void setStrongestPlayersToTrade(List<IPlayer> strongestPlayersToTrade);

	public boolean findTeamToSwap(ILeague league);
}
