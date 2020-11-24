package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public interface ILeagueStanding {

	List<IStanding> getStandings();

	void setStandings(List<IStanding> standingsList);

	void createStandings(ILeague league);

	void updateWinningTeamData(IConference conference, IDivision division, ITeam team);

	void updateLosingTeamData(IConference conference, IDivision division, ITeam team);

	List<IStanding> getStandingsInDivision(IDivision division);

	List<IStanding> getStandingsInConference(IConference conference);

	public List<IStanding> getStandingsInAllConference(ILeague league);

	IStanding getTopStandingInConference(IConference conference);

}