package com.dhl.g05.leaguesimulation;

import java.time.LocalDate;
import java.util.List;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.ITeam;

public interface ILeagueSchedule {

	List<IScheduleModel> getRegularSchedule();

	void setRegularSchedule(List<IScheduleModel> regularSchedule);

	List<IScheduleModel> getPlayoffSchedule();

	void setPlayoffSchedule(List<IScheduleModel> playoffSchedule);

	void generateRegularSeasonSchedule(ILeague league);

	void generatePlayoffSchedule(ILeague league, ILeagueStanding standingSystem);

	boolean anyUnplayedGamesOnDate(LocalDate date);

	IScheduleModel getScheduledMatchOnThisDate(LocalDate date);

	boolean isStanleyCupWinnerDetermined();

	void updateScheduleAfterGame(IScheduleModel schedule);

	ITeam getStanleyCupWinner();

	void addRegularSeasonDates();

	void addPlayoffSeasonDates();


}