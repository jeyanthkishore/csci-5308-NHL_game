package com.dhl.g05.leaguesimulation.leaguestanding;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

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
	public void createStandings(ILeague	league) {
        standings = new ArrayList<>();

        for (IConference conference: league.getConferenceDetails()) {
            for (IDivision division: conference.getDivisionDetails()) {
                for (ITeam team: division.getTeamDetails()) {
                    IStanding standing = new Standing();
                    standing.setConference(conference);
                    standing.setDivision(division);
                    standing.setTeam(team);
                    standings.add(standing);
                }
            }
        }
    }

    @Override
	public void updateWinningTeamData(IConference conference, IDivision division, ITeam team) {
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
	public void updateLosingTeamData(IConference conference, IDivision division, ITeam team) {
        for (IStanding standing: standings) {
            if (standing.getConference() == conference &&
                    standing.getDivision() == division &&
                    standing.getTeam() == team) {
                standing.incrementGamesPlayed();
                standing.incrementGamesLost();
            }
        }
    }

    @Override
	public List<IStanding> getStandingsInDivision(IDivision division) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing: standings){
            if (standing.getDivision() == division){
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.rankingComparator);
        return myStandings;
    }

    @Override
	public List<IStanding> getStandingsInConference(IConference conference) {
        List<IStanding> myStandings = new ArrayList<>();
        for (IStanding standing: standings){
            if (standing.getConference() == conference){
                myStandings.add(standing);
            }
        }
        myStandings.sort(Standing.rankingComparator);
        return myStandings;
    }

}
