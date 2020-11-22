package com.dhl.g05.leaguesimulation.leagueschedule;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.IStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.Standing;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class LeagueSchedule implements ILeagueSchedule {
    private List<ISchedule> regularSchedule;
    private List<ISchedule> playoffSchedule;
    private LocalDate regularSeasonStartDate;
    private LocalDate regularSeasonEndDate;
    private LocalDate playoffStartDate;
    private LocalDate playoffEndDate;
    private LocalDate tradeDeadline;

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
//        regularSchedule = new ArrayList<>();
//        generateGamesForRegularSeason(league);
//        generateDates(true);
    }

    private void generateGamesForRegularSeason(ILeague league) {
//        List<ConferenceModel> allConferences = new ArrayList<>();
//        List<DivisionModel> allDivisions = new ArrayList<>();
//        List<TeamModel> allTeams = new ArrayList<>();
//        int totalTeams;
//
//        for (ConferenceModel conference: league.getConferenceDetails()) {
//            for (DivisionModel division: conference.getDivisionDetails()) {
//                for (TeamModel team: division.getTeamDetails()) {
//                    allConferences.add(conference);
//                    allDivisions.add(division);
//                    allTeams.add(team);
//                }
//            }
//        }
//
//        totalTeams = allTeams.size();
//        for (int i = 0; i < totalTeams - 1; i++) {
//            for (int j = i + 1; j < totalTeams; j++) {
//                ISchedule schedule = new Schedule();
//
//                schedule.setFirstConference(allConferences.get(i));
//                schedule.setFirstDivision(allDivisions.get(i));
//                schedule.setFirstTeam(allTeams.get(i));
//
//                schedule.setSecondConference(allConferences.get(j));
//                schedule.setSecondDivision(allDivisions.get(j));
//                schedule.setSecondTeam(allTeams.get(j));
//
//                regularSchedule.add(schedule);
//            }
//        }
    }
//
    private void generateDates(boolean isRegularSeason) {
//        LocalDate startDate;
//        LocalDate endDate;
//        List<ISchedule> scheduleList;
//        if (isRegularSeason) {
//            startDate = regularSeasonStartDate;
//            endDate = regularSeasonEndDate;
//            scheduleList = regularSchedule;
//        }
//        else {
//            startDate = playoffStartDate;
//            endDate = playoffEndDate;
//            scheduleList = playoffSchedule;
//        }
//
//        int days =(int)(DAYS.between(startDate, endDate));
//        int noOfGames = scheduleList.size();
//
//        int noOfGamesPerDay = noOfGames / days;
//        int extraGames = noOfGames % days;
//
//        LocalDate scheduleDate = startDate;
//        int scheduleIndex = 0;
//        for (int i = 0; i < days; i++) {
//            for (int j = 0; j < noOfGamesPerDay; j++) {
//                scheduleList.get(scheduleIndex).setDate(scheduleDate);
//                scheduleIndex++;
//            }
//
//            if (extraGames > 0) {
//                scheduleList.get(scheduleIndex).setDate(scheduleDate);
//                scheduleIndex++;
//                extraGames--;
//            }
//
//            scheduleDate = scheduleDate.plusDays(1);
//        }
    }

    @Override
    public void generatePlayoffSchedule(LeagueModel league, ILeagueStanding standingSystem) {
//        playoffSchedule = new ArrayList<>();
//        generatePlayoffRound1(league, standingSystem);
//        generateDates(false);
    }

    private void generatePlayoffRound1(ILeague league, ILeagueStanding standingSystem) {
        for (ConferenceModel conference: league.getConferenceDetails()) {
            List<List<IStanding>> allDivisionStandings = new ArrayList<>();
            for (DivisionModel division: conference.getDivisionDetails()) {
                List<IStanding> divisionStandings = standingSystem.getStandingsInDivision(division);
                allDivisionStandings.add(divisionStandings);
            }

            ISchedule schedule1 = new Schedule();
            schedule1.setFirstConference(allDivisionStandings.get(0).get(0).getConference());
            schedule1.setFirstDivision(allDivisionStandings.get(0).get(0).getDivision());
            schedule1.setFirstTeam(allDivisionStandings.get(0).get(0).getTeam());

            ISchedule schedule2 = new Schedule();
            schedule2.setFirstConference(allDivisionStandings.get(1).get(0).getConference());
            schedule2.setFirstDivision(allDivisionStandings.get(1).get(0).getDivision());
            schedule2.setFirstTeam(allDivisionStandings.get(1).get(0).getTeam());

            ISchedule schedule3 = new Schedule();
            schedule3.setFirstConference(allDivisionStandings.get(0).get(1).getConference());
            schedule3.setFirstDivision(allDivisionStandings.get(0).get(1).getDivision());
            schedule3.setFirstTeam(allDivisionStandings.get(0).get(1).getTeam());
            schedule3.setSecondConference(allDivisionStandings.get(0).get(2).getConference());
            schedule3.setSecondDivision(allDivisionStandings.get(0).get(2).getDivision());
            schedule3.setSecondTeam(allDivisionStandings.get(0).get(2).getTeam());

            ISchedule schedule4 = new Schedule();
            schedule4.setFirstConference(allDivisionStandings.get(1).get(1).getConference());
            schedule4.setFirstDivision(allDivisionStandings.get(1).get(1).getDivision());
            schedule4.setFirstTeam(allDivisionStandings.get(1).get(1).getTeam());
            schedule4.setSecondConference(allDivisionStandings.get(1).get(2).getConference());
            schedule4.setSecondDivision(allDivisionStandings.get(1).get(2).getDivision());
            schedule4.setSecondTeam(allDivisionStandings.get(1).get(2).getTeam());

            for (int i = 0; i < 3; i++) {
                allDivisionStandings.get(0).remove(0);
                allDivisionStandings.get(1).remove(0);
            }

            allDivisionStandings.get(0).addAll(allDivisionStandings.get(1));
            allDivisionStandings.get(0).sort(Standing.standingComparator);
            ITeam topTeamInConference = standingSystem.getTopStandingInConference(conference).getTeam();

            if (schedule1.getFirstTeam() == topTeamInConference) {
                schedule1.setSecondConference(allDivisionStandings.get(0).get(1).getConference());
                schedule1.setSecondDivision(allDivisionStandings.get(0).get(1).getDivision());
                schedule1.setSecondTeam(allDivisionStandings.get(0).get(1).getTeam());

                schedule2.setSecondConference(allDivisionStandings.get(0).get(0).getConference());
                schedule2.setSecondDivision(allDivisionStandings.get(0).get(0).getDivision());
                schedule2.setSecondTeam(allDivisionStandings.get(0).get(0).getTeam());
            }
            else if (schedule2.getFirstTeam() == topTeamInConference) {
                schedule1.setSecondConference(allDivisionStandings.get(0).get(0).getConference());
                schedule1.setSecondDivision(allDivisionStandings.get(0).get(0).getDivision());
                schedule1.setSecondTeam(allDivisionStandings.get(0).get(0).getTeam());

                schedule2.setSecondConference(allDivisionStandings.get(0).get(1).getConference());
                schedule2.setSecondDivision(allDivisionStandings.get(0).get(1).getDivision());
                schedule2.setSecondTeam(allDivisionStandings.get(0).get(1).getTeam());
            }

            playoffSchedule.add(schedule1);
            playoffSchedule.add(schedule2);
            playoffSchedule.add(schedule3);
            playoffSchedule.add(schedule4);
        }
    }

    @Override
    public boolean anyUnplayedGamesOnThisDate(LocalDate date) {
//        List<ISchedule> scheduleListToCheck = null;
//        if (isRegularSeasonOngoing(date)) {
//            scheduleListToCheck = regularSchedule;
//        }
//        else if (isPlayoffSeasonOngoing(date)) {
//            scheduleListToCheck = playoffSchedule;
//        }
//        if (scheduleListToCheck == null) {
//            return false;
//        }
//
//        for (ISchedule schedule : scheduleListToCheck) {
//            if (schedule.getDate().isEqual(date) && schedule.getIsGamePlayed() == false) {
//                return true;
//            }
//        }
        return false;
    }

//    private boolean isRegularSeasonOngoing(LocalDate date) {
//        return date.isEqual(regularSeasonStartDate) ||
//               (date.isAfter(regularSeasonStartDate) && date.isBefore(regularSeasonEndDate)) ||
//               date.isEqual(regularSeasonEndDate);
//    }
//
//    private boolean isPlayoffSeasonOngoing(LocalDate date) {
//        return date.isEqual(playoffStartDate) ||
//               (date.isAfter(playoffStartDate) && date.isBefore(playoffEndDate)) ||
//               date.isEqual(playoffEndDate);
//    }

    @Override
    public ISchedule getScheduledMatchOnThisDate(LocalDate date) {
//        List<ISchedule> scheduleListToCheck;
        ISchedule todaySchedule = null;
//        if (isRegularSeasonOngoing(date)) {
//            scheduleListToCheck = regularSchedule;
//        }
//        else {
//            scheduleListToCheck = playoffSchedule;
//        }
//
//        for (ISchedule schedule : scheduleListToCheck) {
//            if (schedule.getDate().isEqual(date) && schedule.getIsGamePlayed() == false) {
//                todaySchedule = schedule;
//            }
//        }
        return todaySchedule;
    }

    @Override
    public boolean isStanleyCupWinnerDetermined() {
//        if (playoffSchedule == null) {
//            return false;
//        }
//        for (ISchedule schedule: playoffSchedule) {
//            if (schedule.getIsGamePlayed() == false) {
//                return false;
//            }
//        }
        return false;
    }

    @Override
    public void updateScheduleAfterGamePlayed(ISchedule schedule) {
//        schedule.setIsGamePlayed(true);
//        if (isPlayoffSeasonOngoing(schedule.getDate())) {
//            int index = playoffSchedule.indexOf(schedule);
//            if (index < playoffSchedule.size() - 1) {
//                if (index % 2 == 0) {
//                    ISchedule newSchedule = new Schedule();
//                    if (schedule.getWinningTeam() == schedule.getFirstTeam()) {
//                        newSchedule.setFirstConference(schedule.getFirstConference());
//                        newSchedule.setFirstDivision(schedule.getFirstDivision());
//                        newSchedule.setFirstTeam(schedule.getFirstTeam());
//                    }
//                    else {
//                        newSchedule.setFirstConference(schedule.getSecondConference());
//                        newSchedule.setFirstDivision(schedule.getSecondDivision());
//                        newSchedule.setFirstTeam(schedule.getSecondTeam());
//                    }
//
//                    LocalDate date = playoffSchedule.get(playoffSchedule.size() - 1).getDate();
//                    newSchedule.setDate(date.plusDays(1));
//                    playoffSchedule.add(newSchedule);
//                }
//                else {
//                    ISchedule existingSchedule = playoffSchedule.get(playoffSchedule.size() - 1);
//                    if (schedule.getWinningTeam() == schedule.getFirstTeam()) {
//                        existingSchedule.setSecondConference(schedule.getFirstConference());
//                        existingSchedule.setSecondDivision(schedule.getFirstDivision());
//                        existingSchedule.setSecondTeam(schedule.getFirstTeam());
//                    }
//                    else {
//                        existingSchedule.setSecondConference(schedule.getSecondConference());
//                        existingSchedule.setSecondDivision(schedule.getSecondDivision());
//                        existingSchedule.setSecondTeam(schedule.getSecondTeam());
//                    }
//                }
//            }
//        }
    }

    @Override
	public TeamModel getStanleyCupWinner() {
        int lastMatch = playoffSchedule.size() - 1;
        return playoffSchedule.get(lastMatch).getWinningTeam();
    }
}
