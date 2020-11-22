package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.team.ITeamModel;
import com.dhl.g05.team.TeamModel;

public interface ISchedule {

	ConferenceModel getFirstConference();

	void setFirstConference(ConferenceModel firstConference);

	DivisionModel getFirstDivision();

	void setFirstDivision(DivisionModel firstDivision);

	TeamModel getFirstTeam();

	void setFirstTeam(TeamModel firstTeam);

	ConferenceModel getSecondConference();

	void setSecondConference(ConferenceModel secondConference);

	DivisionModel getSecondDivision();

	void setSecondDivision(DivisionModel secondDivision);

	TeamModel getSecondTeam();

	void setSecondTeam(TeamModel secondTeam);

	LocalDate getDate();

	void setDate(LocalDate date);

	TeamModel getWinningTeam();

	void setWinningTeam(TeamModel winningTeam);

	boolean getIsGamePlayed();

	void setIsGamePlayed(boolean isGamePlayed);

}