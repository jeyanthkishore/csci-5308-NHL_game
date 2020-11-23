package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.IStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.Standing;
import com.dhl.g05.team.ITeam;

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
    	regularSchedule = new ArrayList<>();
        List<IConference> allConferences = new ArrayList<>();
        List<IDivision> allDivisions = new ArrayList<>();
        List<ITeam> allTeams = new ArrayList<>();
        int totalTeams;

        for (IConference conference: league.getConferenceDetails()) {
            for (IDivision division: conference.getDivisionDetails()) {
                for (ITeam team: division.getTeamDetails()) {
                    allConferences.add(conference);
                    allDivisions.add(division);
                    allTeams.add(team);
                }
            }
        }

        totalTeams = allTeams.size();
        for (int i = 0; i < totalTeams - 1; i++) {
            for (int j = i + 1; j < totalTeams; j++) {
                ISchedule schedule = new Schedule();

                schedule.setFirstConference(allConferences.get(i));
                schedule.setFirstDivision(allDivisions.get(i));
                schedule.setFirstTeam(allTeams.get(i));

                schedule.setSecondConference(allConferences.get(j));
                schedule.setSecondDivision(allDivisions.get(j));
                schedule.setSecondTeam(allTeams.get(j));

                regularSchedule.add(schedule);
            }
        }
    }
    
	@Override
	public void addRegularSeasonDates() {
		int daysBetweenSeason = (int)DateHandler.getInstance().getDaysBetweenSeason();
		LocalDate regularSeasonStartDate = DateHandler.getInstance().getRegularSeasonStartDate();
		addDatesToSchedule(regularSchedule,daysBetweenSeason,regularSeasonStartDate);
	}

	@Override
	public void addPlayoffSeasonDates() {
		int getDaysBetweenPlayoff = (int)DateHandler.getInstance().getDaysBetweenPlayoff();
		LocalDate playoffSeasonStartDate = DateHandler.getInstance().getPlayoffSeasonStartDate();
		addDatesToSchedule(playoffSchedule,getDaysBetweenPlayoff,playoffSeasonStartDate);
	}
	
    private void addDatesToSchedule(List<ISchedule> Schedule, int days,LocalDate startDate) {
    	int noOfGames = Schedule.size();
    	 int noOfGamesPerDay = (noOfGames / days);
    	 int extraGames = (noOfGames % days);

         int scheduleIndex = 0;
         for (int i = 0; i < days; i++) {
             for (int j = 0; j < noOfGamesPerDay; j++) {
            	 Schedule.get(scheduleIndex).setDate(startDate);
                 scheduleIndex++;
             }
             
             if (extraGames > 0) {
            	 Schedule.get(scheduleIndex).setDate(startDate);
                 scheduleIndex++;
                 extraGames--;
             }
             
             startDate = startDate.plusDays(1);
         }
	}

	@Override
    public void generatePlayoffSchedule(LeagueModel league, ILeagueStanding standingSystem) {
        for (IConference conference: league.getConferenceDetails()) {
            List<List<IStanding>> allDivisionStandings = new ArrayList<>();
            List<List<IStanding>> wildCardDivision = new ArrayList<>();
            for (IDivision division: conference.getDivisionDetails()) {
                List<IStanding> divisionStandings = standingSystem.getStandingsInDivision(division);
                allDivisionStandings.add(divisionStandings);
            }
            for(int i = 0 ; i < 2 ; i++) {
            	int j = 1;
            	ISchedule schedule = new Schedule();
            	schedule.setFirstConference(allDivisionStandings.get(i).get(j).getConference());
            	schedule.setFirstDivision(allDivisionStandings.get(i).get(j).getDivision());
            	schedule.setFirstTeam(allDivisionStandings.get(i).get(j).getTeam());
            	j++;
            	schedule.setSecondConference(allDivisionStandings.get(i).get(j).getConference());
            	schedule.setSecondDivision(allDivisionStandings.get(i).get(j).getDivision());
            	schedule.setSecondTeam(allDivisionStandings.get(i).get(j).getTeam());
            	playoffSchedule.add(schedule);
            }
            
            wildCardDivision = selectWildCardDivision(allDivisionStandings);
            
            for(int k = 0, i =0 ; k < 2 ; k++ , i++) {
            	int j = 0;
            	ISchedule schedule = new Schedule();
            	schedule.setFirstConference(allDivisionStandings.get(i).get(j).getConference());
            	schedule.setFirstDivision(allDivisionStandings.get(i).get(j).getDivision());
            	schedule.setFirstTeam(allDivisionStandings.get(i).get(j).getTeam());
            	if(i == 1) {
            		j++;
            		i = 0;
            	}
            	schedule.setSecondConference(wildCardDivision.get(i).get(j).getConference());
            	schedule.setSecondDivision(wildCardDivision.get(i).get(j).getDivision());
            	schedule.setSecondTeam(wildCardDivision.get(i).get(j).getTeam());
            	playoffSchedule.add(schedule);

            }
        }
    }

    private List<List<IStanding>> selectWildCardDivision(List<List<IStanding>> allDivisionStandings) {
    	
    	for (int i = 0; i < 3; i++) {
            allDivisionStandings.get(0).remove(0);
            allDivisionStandings.get(1).remove(0);
        }

        allDivisionStandings.get(0).addAll(allDivisionStandings.get(1));
        allDivisionStandings.get(0).sort(Standing.rankingComparator);
		return allDivisionStandings;
	}

	@Override
    public boolean anyUnplayedGamesOnThisDate(LocalDate date) {
        List<ISchedule> scheduleListToCheck = null;
        if (DateHandler.getInstance().isRegularSeasonOngoing(date)) {
            scheduleListToCheck = regularSchedule;
        }
        else if (DateHandler.getInstance().isPlayoffSeasonOngoing(date)) {
            scheduleListToCheck = playoffSchedule;
        }
        if (scheduleListToCheck == null) {
            return false;
        }

        for (ISchedule schedule : scheduleListToCheck) {
            if (schedule.getDate().isEqual(date) && schedule.getIsGamePlayed() == false) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ISchedule getScheduledMatchOnThisDate(LocalDate date) {
        List<ISchedule> scheduleList;
        ISchedule currentSchedule = null;
        if (DateHandler.getInstance().isRegularSeasonOngoing(date)) {
            scheduleList = regularSchedule;
        }
        else {
        	scheduleList = playoffSchedule;
        }

        for (ISchedule schedule : scheduleList) {
            if (schedule.getDate().isEqual(date) && schedule.getIsGamePlayed() == false) {
            	currentSchedule = schedule;
            }
        }
        return currentSchedule;
    }

    @Override
    public boolean isStanleyCupWinnerDetermined() {
    	 if (playoffSchedule == null) {
             return false;
         }
         for (ISchedule schedule: playoffSchedule) {
             if (schedule.getIsGamePlayed() == false) {
                 return false;
             }
         }
         return true;
    }

    @Override
    public void updateScheduleAfterGamePlayed(ISchedule schedule) {

        schedule.setIsGamePlayed(true);
        if (DateHandler.getInstance().isPlayoffSeasonOngoing(schedule.getDate())) {
            int index = playoffSchedule.indexOf(schedule);
            if (index < playoffSchedule.size() - 1) {
                if (index % 2 == 0) {
                    ISchedule newSchedule = new Schedule();
                    if (schedule.getWinningTeam() == schedule.getFirstTeam()) {
                        newSchedule.setFirstConference(schedule.getFirstConference());
                        newSchedule.setFirstDivision(schedule.getFirstDivision());
                        newSchedule.setFirstTeam(schedule.getFirstTeam());
                    }
                    else {
                        newSchedule.setFirstConference(schedule.getSecondConference());
                        newSchedule.setFirstDivision(schedule.getSecondDivision());
                        newSchedule.setFirstTeam(schedule.getSecondTeam());
                    }

                    LocalDate date = playoffSchedule.get(playoffSchedule.size() - 1).getDate();
                    newSchedule.setDate(date.plusDays(1));
                    playoffSchedule.add(newSchedule);
                }
                else {
                    ISchedule existingSchedule = playoffSchedule.get(playoffSchedule.size() - 1);
                    if (schedule.getWinningTeam() == schedule.getFirstTeam()) {
                        existingSchedule.setSecondConference(schedule.getFirstConference());
                        existingSchedule.setSecondDivision(schedule.getFirstDivision());
                        existingSchedule.setSecondTeam(schedule.getFirstTeam());
                    }
                    else {
                        existingSchedule.setSecondConference(schedule.getSecondConference());
                        existingSchedule.setSecondDivision(schedule.getSecondDivision());
                        existingSchedule.setSecondTeam(schedule.getSecondTeam());
                    }
                }
            }
        }
    }

    @Override
	public ITeam getStanleyCupWinner() {
        int lastMatch = playoffSchedule.size() - 1;
        return playoffSchedule.get(lastMatch).getWinningTeam();
    }

}
