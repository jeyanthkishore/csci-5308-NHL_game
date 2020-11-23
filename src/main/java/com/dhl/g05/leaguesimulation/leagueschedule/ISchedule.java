package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.conference.IConferenceModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.team.ITeamModel;
import com.dhl.g05.team.TeamModel;

public interface ISchedule {

	IConference getFirstConference();

	void setFirstConference(IConference firstConference);

	IDivision getFirstDivision();

	void setFirstDivision(IDivision firstDivision);

	TeamModel getFirstTeam();

	void setFirstTeam(TeamModel firstTeam);

	IConference getSecondConference();

	void setSecondConference(IConference secondConference);

	IDivision getSecondDivision();

	void setSecondDivision(IDivision secondDivision);

	TeamModel getSecondTeam();

	void setSecondTeam(TeamModel secondTeam);

	LocalDate getDate();

	void setDate(LocalDate date);

	TeamModel getWinningTeam();

	void setWinningTeam(TeamModel winningTeam);

	boolean getIsGamePlayed();

	void setIsGamePlayed(boolean isGamePlayed);

}