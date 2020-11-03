package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;


public interface IWeakTeam {
	public TeamModel getWeakTeam();
	public void setWeakTeam(TeamModel weakTeam);

	public String getConferenceName();

	public void setConferenceName(String conferenceName);

	public String getDivisionName();

	public void setDivisionName(String divisionName);

	public int getNumberOfPlayersOffered();

	public void setNumberOfPlayersOffered(int numberOfPlayersOffered);

	public List<PlayerModel> getPlayersOffered();

	public String getOfferedPlayerPosition();

	public void setOfferedPlayerPosition(String offeredPlayerPosition);

	public double getStrengthOfPlayersOffered();

	public void setStrengthOfPlayersOffered(double strengthOfPlayersOffered);

	public void setPlayersOffered(List<PlayerModel> playersOffered);

	public void playersToOffer(TradingModel trade);
}
