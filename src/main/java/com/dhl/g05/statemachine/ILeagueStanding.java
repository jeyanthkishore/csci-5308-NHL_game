package com.dhl.g05.statemachine;

import java.util.List;

import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;

public interface ILeagueStanding {

	List<IStandingModel> getStandingList();

	void setStandingList(List<IStandingModel> standingsList);

	void createStandingList(ILeague league);

	void updateStatisticsForWinningTeam(IConference conference, IDivision division, ITeam team);

	void updateStatisticsForLosingTeam(IConference conference, IDivision division, ITeam team);

	List<IStandingModel> getRankingAcrossDivision(IDivision division);

	List<IStandingModel> getRankingAcrossConference(IConference conference);

	List<IStandingModel> getRankingAcrossLeague();


}