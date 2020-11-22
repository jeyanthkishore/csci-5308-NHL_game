package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public interface ILeagueStanding {

	List<IStanding> getStandings();

	void setStandings(List<IStanding> standingsList);

	void initializeStandings(LeagueModel league);

	void updateStatsForWinningTeam(ConferenceModel conference, DivisionModel division, TeamModel team);

	void updateStatsForLosingTeam(ConferenceModel conference, DivisionModel division, TeamModel team);

	List<IStanding> getStandingsInDivision(DivisionModel division);

	List<IStanding> getStandingsInConference(ConferenceModel conference);

	IStanding getTopStandingInConference(ConferenceModel conference);

}