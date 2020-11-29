package com.dhl.g05.simulation;

import java.time.LocalDate;
import java.util.List;

import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;

public interface ILeagueSchedule {

	List<IScheduleModel> getRegularSeasonSchedule();

	void setRegularSeasonSchedule(List<IScheduleModel> regularSchedule);

	List<IScheduleModel> getPlayoffSeasonSchedule();

	void setPlayoffSeasonSchedule(List<IScheduleModel> playoffSchedule);

	void generateRegularSeasonSchedule(ILeague league);

	void generatePlayoffSchedule(ILeague league, ILeagueStanding standingSystem);

	boolean isGamesUnplayedOnCurrentDay(LocalDate date);

	IScheduleModel getMatchOnCurrentDate(LocalDate date);

	boolean isStanleyCupWinnerDetermined();

	void updateScheduleAfterGame(IScheduleModel schedule);

	ITeam getStanleyCupWinner();

	void addRegularSeasonDates();

	void addPlayoffSeasonDates();

}