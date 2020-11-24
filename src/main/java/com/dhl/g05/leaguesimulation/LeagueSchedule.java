package com.dhl.g05.leaguesimulation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

public class LeagueSchedule implements ILeagueSchedule {
    private List<IScheduleModel> regularGamesSchedule;
    private List<IScheduleModel> playoffGamesSchedule;

    @Override
    public List<IScheduleModel> getRegularSchedule() {
        return regularGamesSchedule;
    }

    @Override
    public void setRegularSchedule(List<IScheduleModel> regularSchedule) {
        this.regularGamesSchedule = regularSchedule;
    }

    @Override
    public List<IScheduleModel> getPlayoffSchedule() {
        return playoffGamesSchedule;
    }

    @Override
    public void setPlayoffSchedule(List<IScheduleModel> playoffSchedule) {
        this.playoffGamesSchedule = playoffSchedule;
    }

	@Override
	public void addRegularSeasonDates() {
		int totalDaysforRegualarSeason = (int)DateHandler.getInstance().getDaysBetweenRegularSeason();
		LocalDate regularSeasonStartDate = DateHandler.getInstance().getRegularSeasonStartDate();
		addDatesToSchedule(regularGamesSchedule,totalDaysforRegualarSeason,regularSeasonStartDate);
	}
    
    @Override
    public void generateRegularSeasonSchedule(ILeague league) {
    	regularGamesSchedule = new ArrayList<>();
        List<IConference> totalConferences = new ArrayList<>();
        List<IDivision> totalDivisions = new ArrayList<>();
        List<ITeam> totalTeams = new ArrayList<>();
        int totalNumberOfTeams;

        for (IConference conference: league.getConferenceDetails()) {
            for (IDivision division: conference.getDivisionDetails()) {
                for (ITeam team: division.getTeamDetails()) {
                	totalConferences.add(conference);
                	totalDivisions.add(division);
                    totalTeams.add(team);
                }
            }
        }

        totalNumberOfTeams = totalTeams.size();
        for (int i = 0; i < totalNumberOfTeams - 1; i++) {
            for (int j = i + 1; j < totalNumberOfTeams; j++) {
                IScheduleModel schedule = new ScheduleModel();
                schedule.setFirstConference(totalConferences.get(i));
                schedule.setFirstDivision(totalDivisions.get(i));
                schedule.setFirstTeam(totalTeams.get(i));
                schedule.setSecondConference(totalConferences.get(j));
                schedule.setSecondDivision(totalDivisions.get(j));
                schedule.setSecondTeam(totalTeams.get(j));

                regularGamesSchedule.add(schedule);
            }
        }
    }
    
	@Override
	public void addPlayoffSeasonDates() {
		int totalDaysforPlayoff = (int)DateHandler.getInstance().getDaysBetweenPlayoff();
		LocalDate playoffSeasonStartDate = DateHandler.getInstance().getPlayoffSeasonStartDate();
		addDatesToSchedule(playoffGamesSchedule,totalDaysforPlayoff,playoffSeasonStartDate);
	}
	
	@Override
    public void generatePlayoffSchedule(ILeague league, ILeagueStanding standingSystem) {
		playoffGamesSchedule = new ArrayList<>();
        for (IConference conference: league.getConferenceDetails()) {
            List<List<IStandingModel>> totalStandings = new ArrayList<>();
            List<List<IStandingModel>> wildCardDivision = new ArrayList<>();
            for (IDivision division: conference.getDivisionDetails()) {
                List<IStandingModel> divisionTotalStandings = standingSystem.getRankingAcrossDivision(division);
                totalStandings.add(divisionTotalStandings);
                List<IStandingModel> divisionWildCardStandings = standingSystem.getRankingAcrossDivision(division);
                wildCardDivision.add(divisionWildCardStandings);
            }
            
            for(int i = 0 ; i < 2 ; i++) {
            	int j = 1;
            	IScheduleModel schedule = new ScheduleModel();
            	schedule.setFirstConference(totalStandings.get(i).get(j).getConference());
            	schedule.setFirstDivision(totalStandings.get(i).get(j).getDivision());
            	schedule.setFirstTeam(totalStandings.get(i).get(j).getTeam());
            	j++;
            	schedule.setSecondConference(totalStandings.get(i).get(j).getConference());
            	schedule.setSecondDivision(totalStandings.get(i).get(j).getDivision());
            	schedule.setSecondTeam(totalStandings.get(i).get(j).getTeam());
            	playoffGamesSchedule.add(schedule);
            }
            
            wildCardDivision = selectWildCardDivision(wildCardDivision);
            for(int k = 0, i =0 ; k < 2 ; k++ , i++) {
            	int j = 0;
            	IScheduleModel schedule = new ScheduleModel();
            	schedule.setFirstConference(totalStandings.get(i).get(j).getConference());
            	schedule.setFirstDivision(totalStandings.get(i).get(j).getDivision());
            	schedule.setFirstTeam(totalStandings.get(i).get(j).getTeam());
            	if(i == 1) {
            		j++;
            		i = 0;
            	}
            	schedule.setSecondConference(wildCardDivision.get(i).get(j).getConference());
            	schedule.setSecondDivision(wildCardDivision.get(i).get(j).getDivision());
            	schedule.setSecondTeam(wildCardDivision.get(i).get(j).getTeam());
            	playoffGamesSchedule.add(schedule);

            }
        }
    }

    private List<List<IStandingModel>> selectWildCardDivision(List<List<IStandingModel>> totalStandings) {
    	
    	for (int i = 0; i < 3; i++) {
    		totalStandings.get(0).remove(0);
    		totalStandings.get(1).remove(0);
        }

    	totalStandings.get(0).addAll(totalStandings.get(1));
    	Collections.sort(totalStandings.get(0),StandingModel.rankingComparator);
		return totalStandings;
	}

    private void addDatesToSchedule(List<IScheduleModel> Schedule, int days,LocalDate gameDate) {
    	int totalGames = Schedule.size();
    	 int noOfGamesPerDay = (totalGames / days);
    	 int remainingGames = (totalGames % days);

         int scheduleIndex = 0;
         for (int i = 0; i < days; i++) {
             for (int j = 0; j < noOfGamesPerDay; j++) {
            	 Schedule.get(scheduleIndex).setScheduleDate(gameDate);
                 scheduleIndex++;
             }
             
             if (remainingGames > 0) {
            	 Schedule.get(scheduleIndex).setScheduleDate(gameDate);
                 scheduleIndex++;
                 remainingGames--;
             }
             
             gameDate = gameDate.plusDays(1);
         }
	}
    
    
	@Override
    public boolean anyUnplayedGamesOnDate(LocalDate date) {
        List<IScheduleModel> scheduleList = null;
        if (DateHandler.getInstance().isRegularSeasonOngoing(date)) {
        	scheduleList = regularGamesSchedule;
        } else if (DateHandler.getInstance().isPlayoffSeasonOngoing(date)) {
        	scheduleList = playoffGamesSchedule;
        }
        if (scheduleList == null) {
            return false;
        }

        for (IScheduleModel schedule : scheduleList) {
            if (schedule.getScheduleDate().isEqual(date) && schedule.getIsGameCompleted() == false) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IScheduleModel getScheduledMatchOnThisDate(LocalDate date) {
        List<IScheduleModel> scheduleList;
        IScheduleModel currentSchedule = null;
        if (DateHandler.getInstance().isRegularSeasonOngoing(date)) {
            scheduleList = regularGamesSchedule;
        }
        else {
        	scheduleList = playoffGamesSchedule;
        }

        for (IScheduleModel schedule : scheduleList) {
            if (schedule.getScheduleDate().isEqual(date) && schedule.getIsGameCompleted() == false) {
            	currentSchedule = schedule;
            }
        }
        return currentSchedule;
    }

    @Override
    public boolean isStanleyCupWinnerDetermined() {
    	 if (playoffGamesSchedule == null) {
             return false;
         }
         for (IScheduleModel schedule: playoffGamesSchedule) {
             if (schedule.getIsGameCompleted() == false) {
                 return false;
             }
         }
         return true;
    }

    @Override
    public void updateScheduleAfterGame(IScheduleModel schedule) {

    	schedule.setIsGameCompleted(true);
    	
    	if (DateHandler.getInstance().isPlayoffSeasonOngoing(schedule.getScheduleDate())) {
    		List<Object> winningDetails = new ArrayList<>();
    		int index = playoffGamesSchedule.indexOf(schedule);
    		int indexTotalLength = playoffGamesSchedule.size() - 1;
    		if (index % 2 == 0) {
    			IScheduleModel newSchedule = new ScheduleModel();
    			winningDetails = getWinDetails(schedule);
    			newSchedule.setFirstConference((IConference) winningDetails.get(0));
    			newSchedule.setFirstDivision((IDivision) winningDetails.get(1));
    			newSchedule.setFirstTeam((ITeam) winningDetails.get(2));
    			LocalDate date = playoffGamesSchedule.get(indexTotalLength).getScheduleDate();
    			newSchedule.setScheduleDate(date.plusDays(1));
    			playoffGamesSchedule.add(newSchedule);
    		} else {
    			IScheduleModel oldSchedule = playoffGamesSchedule.get(indexTotalLength);
    			winningDetails = getWinDetails(schedule);
    			oldSchedule.setSecondConference((IConference) winningDetails.get(0));
    			oldSchedule.setSecondDivision((IDivision) winningDetails.get(1));
    			oldSchedule.setSecondTeam((ITeam) winningDetails.get(2));
    		}
    	}
    }

    private List<Object> getWinDetails(IScheduleModel schedule) {
    	List<Object> dataOfWinningTeam = new ArrayList<>();
    	 if (schedule.getWinningTeam() == schedule.getFirstTeam()) {
    		 dataOfWinningTeam.add(schedule.getFirstConference());
    		 dataOfWinningTeam.add(schedule.getFirstDivision());
    		 dataOfWinningTeam.add(schedule.getFirstTeam());
         }
         else {
        	 dataOfWinningTeam.add(schedule.getSecondConference());
        	 dataOfWinningTeam.add(schedule.getSecondDivision());
        	 dataOfWinningTeam.add(schedule.getSecondTeam());
         }
		return dataOfWinningTeam;
    }
    
    @Override
	public ITeam getStanleyCupWinner() {
        int finalMatch = playoffGamesSchedule.size() - 1;
        return playoffGamesSchedule.get(finalMatch).getWinningTeam();
    }

}
