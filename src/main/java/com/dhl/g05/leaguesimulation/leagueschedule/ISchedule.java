package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public interface ISchedule {

	IConference getFirstConference();

	void setFirstConference(IConference firstConference);

	IDivision getFirstDivision();

	void setFirstDivision(IDivision firstDivision);

	ITeam getFirstTeam();

	void setFirstTeam(ITeam Team);

	IConference getSecondConference();

	void setSecondConference(IConference secondConference);

	IDivision getSecondDivision();

	void setSecondDivision(IDivision secondDivision);

	ITeam getSecondTeam();

	void setSecondTeam(ITeam secondTeam);

	LocalDate getDate();

	void setDate(LocalDate date);

	ITeam getWinningTeam();

	void setWinningTeam(ITeam winningTeam);

	boolean getIsGamePlayed();

	void setIsGamePlayed(boolean isGamePlayed);

}