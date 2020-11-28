package com.dhl.g05.statemachine;

import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ITeam;

public interface IStandingModel {

	IConference getConference();

	void setConference(IConference conference);

	IDivision getDivision();

	void setDivision(IDivision division);

	ITeam getTeam();

	void setTeam(ITeam team);

	int getTotalGamesPlayed();

	void setTotalGamesPlayed(int gamesPlayed);

	void incrementGamesPlayed();

	int getTotalGamesWon();

	void setTotalGamesWon(int gamesWon);

	int getTotalGamesLost();
	
	void setTotalGamesLost(int gamesLost);
	
	void incrementGamesWon();

	void incrementGamesLost();
	
	int getTotalPoints();

	void setTotalPoints(int points);

	void incrementPoints();

}