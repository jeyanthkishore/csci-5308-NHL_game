package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public class LeagueStanding implements ILeagueStanding{
    private List<IStanding> standings;

    @Override
	public List<IStanding> getStandings() {
        return standings;
    }

    @Override
	public void setStandings(List<IStanding> standingsList) {
        standings = standingsList;
    }

    @Override
	public void initializeStandings(LeagueModel league) {
        standings = new ArrayList<>();

//        for (ConferenceModel conference: league.getConferenceDetails()) {
//            for (DivisionModel division: conference.getDivisionDetails()) {
//                for (TeamModel team: division.getTeamDetails()) {
//                    IStanding standing = new Standing();
//                    standing.setConference(conference);
//                    standing.setDivision(division);
//                    standing.setTeam(team);
//                    standings.add(standing);
//                }
//            }
//        }
    }

    @Override
	public void updateStatsForWinningTeam(ConferenceModel conference, DivisionModel division, TeamModel team) {
        for (IStanding standing: standings) {
            if (standing.getConference() == conference &&
                standing.getDivision() == division &&
                standing.getTeam() == team) {
                standing.incrementGamesPlayed();
                standing.incrementGamesWon();
                standing.incrementPoints();
            }
        }
    }

    @Override
	public void updateStatsForLosingTeam(ConferenceModel conference, DivisionModel division, TeamModel team) {
        for (IStanding standing: standings) {
            if (standing.getConference() == conference &&
                    standing.getDivision() == division &&
                    standing.getTeam() == team) {
                standing.incrementGamesPlayed();
            }
        }
    }

    @Override
	public List<IStanding> getStandingsInDivision(DivisionModel division) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing: standings){
            if (standing.getDivision() == division){
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.standingComparator);
        return myStandings;
    }

    @Override
	public List<IStanding> getStandingsInConference(ConferenceModel conference) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing: standings){
            if (standing.getConference() == conference){
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.standingComparator);
        return myStandings;
    }

    @Override
	public IStanding getTopStandingInConference(ConferenceModel conference) {
        return getStandingsInConference(conference).get(0);
    }
}
