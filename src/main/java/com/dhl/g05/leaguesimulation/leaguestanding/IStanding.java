package com.dhl.g05.leaguesimulation.leaguestanding;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.team.TeamModel;

public interface IStanding {

	ConferenceModel getConference();

	void setConference(ConferenceModel conference);

	DivisionModel getDivision();

	void setDivision(DivisionModel division);

	TeamModel getTeam();

	void setTeam(TeamModel team);

	int getGamesPlayed();

	void setGamesPlayed(int gamesPlayed);

	void incrementGamesPlayed();

	int getGamesWon();

	void setGamesWon(int gamesWon);

	void incrementGamesWon();

	int getPoints();

	void setPoints(int points);

	void incrementPoints();

}