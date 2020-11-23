package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public interface ILeagueStanding {

	List<IStanding> getStandings();

	void setStandings(List<IStanding> standingsList);

	void initializeStandings(LeagueModel league);

	void updateStatsForWinningTeam(IConference conference, IDivision division, TeamModel team);

	void updateStatsForLosingTeam(IConference conference, IDivision division, TeamModel team);

	List<IStanding> getStandingsInDivision(IDivision division);

	List<IStanding> getStandingsInConference(IConference conference);

	IStanding getTopStandingInConference(IConference conference);

}