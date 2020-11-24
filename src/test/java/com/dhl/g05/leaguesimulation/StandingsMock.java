package com.dhl.g05.leaguesimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class StandingsMock {
	
    public ILeague createDummyLeague() {
    	ILeague league = new LeagueModel();
    	List<ITeam> teamList = new ArrayList<>();
    	List<IDivision> divisionList = new ArrayList<>();
    	List<IConference> conferneceList = new ArrayList<>();
    	ITeam[] team = new TeamModel[5];
    	IDivision[] division = new DivisionModel[2];
    	IConference[] conference = new ConferenceModel[2];
    	for(int con=0; con<2;con++) {
    		divisionList = new ArrayList<>();
    		for(int div=0;div<2;div++) {
    			teamList = new ArrayList<>();
    			for(int teamCount = 0; teamCount < 5;teamCount++) {
    				team[teamCount] = new TeamModel();
    				teamList.add(team[teamCount]);
    			}
    			division[div] = new DivisionModel();
    			division[div].setTeamDetails(teamList);
    			divisionList.add(division[div]);
    		}
    		conference[con] = new ConferenceModel();
    		conference[con].setDivisionDetails(divisionList);
    		conferneceList.add(conference[con]);
    	}
    	league.setConferenceDetails(conferneceList);
        return league;
    }

    public List<IStandingModel> createDummyStandings(ILeague league) {
    	
    	Random rand = new Random();
    	List<IStandingModel> standings = new ArrayList<>();
    	
    	for(IConference conference : league.getConferenceDetails()) {
    		for(IDivision division : conference.getDivisionDetails()) {
    			for(ITeam team : division.getTeamDetails()) {
    				IStandingModel standing = new StandingModel();
    				standing.setConference(conference);
    				standing.setDivision(division);
    				standing.setTeam(team);
    				standing.setTotalGamesPlayed(14);
    				int gamesWon = rand.nextInt(10);
    				standing.setTotalGamesLost(gamesWon);
    				standing.setTotalPoints(gamesWon*2);
    				standings.add(standing);
    			}
    		}
    		
    	}
    	
        return standings;
    }
    
}
