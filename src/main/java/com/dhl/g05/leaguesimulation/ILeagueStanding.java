package com.dhl.g05.leaguesimulation;

import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

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