package com.dhl.g05.leaguesimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

public class LeagueStanding implements ILeagueStanding{
	
    private List<IStandingModel> standingsList;

    @Override
	public List<IStandingModel> getStandingList () {
        return standingsList;
    }

    @Override
	public void setStandingList(List<IStandingModel> standings) {
    	standingsList = standings;
    }

    @Override
	public void createStandingList(ILeague	league) {
    	standingsList = new ArrayList<>();

        for (IConference conference: league.getConferenceDetails()) {
            for (IDivision division: conference.getDivisionDetails()) {
                for (ITeam team: division.getTeamDetails()) {
                    IStandingModel standing = new StandingModel();
                    standing.setConference(conference);
                    standing.setDivision(division);
                    standing.setTeam(team);
                    standingsList.add(standing);
                }
            }
        }
    }
    
    @Override
	public List<IStandingModel> getRankingAcrossLeague() {
        List<IStandingModel> leagueStandings = new ArrayList<>();
        for (IStandingModel standing: standingsList) {
            	leagueStandings.add(standing);
        }
        Collections.sort(leagueStandings,StandingModel.rankingComparator);
        return leagueStandings;
    }
    
    @Override
	public List<IStandingModel> getRankingAcrossDivision(IDivision division) {
        List<IStandingModel> divisionStandings = new ArrayList<>();
        for (IStandingModel standing: standingsList) {
            if (standing.getDivision() == division) {
            	divisionStandings.add(standing);
            }
        }
        Collections.sort(divisionStandings,StandingModel.rankingComparator);
        return divisionStandings;
    }
    
    @Override
	public List<IStandingModel> getRankingAcrossConference(IConference conference) {
        List<IStandingModel> conferenceStandings = new ArrayList<>();
        for (IStandingModel standing: standingsList){
            if (standing.getConference() == conference) {
            	conferenceStandings.add(standing);
            }
        }
        Collections.sort(conferenceStandings,StandingModel.rankingComparator);
        return conferenceStandings;
    }
    
    @Override
	public void updateStatisticsForWinningTeam(IConference conference, IDivision division, ITeam team) {
        for (IStandingModel teamstanding: standingsList) {
            if (teamstanding.getConference() == conference && teamstanding.getDivision() == division
            		&& teamstanding.getTeam() == team) {
            	teamstanding.incrementGamesPlayed();
            	teamstanding.incrementGamesWon();
            	teamstanding.incrementPoints();
            }
        }
    }

    @Override
	public void updateStatisticsForLosingTeam(IConference conference, IDivision division, ITeam team) {
        for (IStandingModel teamstanding: standingsList) {
            if (teamstanding.getConference() == conference && teamstanding.getDivision() == division
            		&& teamstanding.getTeam() == team) {
            	teamstanding.incrementGamesPlayed();
                teamstanding.incrementGamesLost();
            }
        }
    }

}
