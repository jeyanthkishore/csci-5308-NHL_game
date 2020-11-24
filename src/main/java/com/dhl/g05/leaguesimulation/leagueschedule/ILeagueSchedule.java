package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;
import java.util.List;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.team.ITeam;

public interface ILeagueSchedule {

	List<ISchedule> getRegularSchedule();

	void setRegularSchedule(List<ISchedule> regularSchedule);

	List<ISchedule> getPlayoffSchedule();

	void setPlayoffSchedule(List<ISchedule> playoffSchedule);

	void generateRegularSeasonSchedule(ILeague league);

	void generatePlayoffSchedule(ILeague league, ILeagueStanding standingSystem);

	boolean anyUnplayedGamesOnGivenDate(LocalDate date);

	ISchedule getScheduledMatchOnThisDate(LocalDate date);

	boolean isStanleyCupWinnerDetermined();

	void updateScheduleAfterGamePlayed(ISchedule schedule);

	ITeam getStanleyCupWinner();

	void addRegularSeasonDates();

	void addPlayoffSeasonDates();


}