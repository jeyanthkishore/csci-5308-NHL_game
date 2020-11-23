package com.dhl.g05.leaguesimulation.leaguestanding;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.team.ITeam;

public interface IStanding {

	IConference getConference();

	void setConference(IConference conference);

	IDivision getDivision();

	void setDivision(IDivision division);

	ITeam getTeam();

	void setTeam(ITeam team);

	int getGamesPlayed();

	void setGamesPlayed(int gamesPlayed);

	void incrementGamesPlayed();

	int getGamesWon();

	void setGamesWon(int gamesWon);

	void incrementGamesWon();

	int getPoints();

	void setPoints(int points);

	void incrementPoints();

	void incrementGamesLost();

	int getGamesLost();

	void setGamesLost(int gamesLost);

}