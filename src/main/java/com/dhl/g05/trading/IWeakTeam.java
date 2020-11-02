package com.dhl.g05.trading;

import java.util.List;

import com.dhl.g05.gameplayconfig.TradingModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;

public interface IWeakTeam {
	public String getConferenceName();

	public void setConferenceName(String conferenceName);

	public String getDivisionName();

	public void setDivisionName(String divisionName);

	public int getNumberOfSkaters();

	public void setNumberOfSkaters(int numberOfSkaters);

	public int getNumberOfGoalies();

	public void setNumberOfGoalies(int numberOfGoalies);

	public List<PlayerModel> callWeakPlayersAndGetPosition(TeamModel weakTeam, TradingModel trade);
}
