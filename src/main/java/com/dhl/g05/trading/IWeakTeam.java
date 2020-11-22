package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.gameplayconfig.ITradingConfig;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.team.ITeam;

public interface IWeakTeam {

	public ITeam getWeakTeam();

	public void setWeakTeam(ITeam weakTeam);

	public String getConferenceName();

	public void setConferenceName(String conferenceName);

	public String getDivisionName();

	public void setDivisionName(String divisionName);

	public int getNumberOfPlayersOffered();

	public void setNumberOfPlayersOffered(int numberOfPlayersOffered);

	public List<IPlayer> getPlayersOffered();

	public String getOfferedPlayerPosition();

	public void setOfferedPlayerPosition(String offeredPlayerPosition);

	public double getStrengthOfPlayersOffered();

	public void setStrengthOfPlayersOffered(double strengthOfPlayersOffered);

	public void setPlayersOffered(List<IPlayer> playersOffered);

	public void playersToOffer(ITradingConfig trade);
}
