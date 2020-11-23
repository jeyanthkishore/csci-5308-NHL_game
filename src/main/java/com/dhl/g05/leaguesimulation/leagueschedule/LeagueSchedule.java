package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;
import java.util.List;

import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.team.TeamModel;

public class LeagueSchedule implements ILeagueSchedule {
    private List<ISchedule> regularSchedule;
    private List<ISchedule> playoffSchedule;

    @Override
    public List<ISchedule> getRegularSchedule() {
        return regularSchedule;
    }

    @Override
    public void setRegularSchedule(List<ISchedule> regularSchedule) {
        this.regularSchedule = regularSchedule;
    }

    @Override
    public List<ISchedule> getPlayoffSchedule() {
        return playoffSchedule;
    }

    @Override
    public void setPlayoffSchedule(List<ISchedule> playoffSchedule) {
        this.playoffSchedule = playoffSchedule;
    }

    @Override
    public void generateRegularSeasonSchedule(ILeague league) {
    }


    @Override
    public void generatePlayoffSchedule(LeagueModel league, ILeagueStanding standingSystem) {
    }


    @Override
    public boolean anyUnplayedGamesOnThisDate(LocalDate date) {
        return false;
    }

    @Override
    public ISchedule getScheduledMatchOnThisDate(LocalDate date) {
        ISchedule todaySchedule = null;
        return todaySchedule;
    }

    @Override
    public boolean isStanleyCupWinnerDetermined() {
        return false;
    }

    @Override
    public void updateScheduleAfterGamePlayed(ISchedule schedule) {
    }

    @Override
	public TeamModel getStanleyCupWinner() {
        int lastMatch = playoffSchedule.size() - 1;
        return playoffSchedule.get(lastMatch).getWinningTeam();
    }
}
