package com.dhl.g05.statemachine;

import java.util.ArrayList;
import java.util.List;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leaguestanding.IStanding;
import com.dhl.g05.leaguesimulation.leaguestanding.Standing;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class DummyLeagueMock {

	private LeagueModel createDummyLeague() {
        LeagueModel league = new LeagueModel();
        List<ITeam> teamList = new ArrayList<>();
        List<IDivision> divisionList = new ArrayList<>();
        List<IConference> conferenceList = new ArrayList<>();

        IConference conference1 = new ConferenceModel();
        IConference conference2 = new ConferenceModel();

        IDivision division1 = new DivisionModel();
        IDivision division2 = new DivisionModel();
        IDivision division3 = new DivisionModel();
        IDivision division4 = new DivisionModel();

        ITeam team1 = new TeamModel();
        ITeam team2 = new TeamModel();
        ITeam team3 = new TeamModel();
        ITeam team4 = new TeamModel();
        ITeam team5 = new TeamModel();
        ITeam team6 = new TeamModel();
        ITeam team7 = new TeamModel();
        ITeam team8 = new TeamModel();
        ITeam team9 = new TeamModel();
        ITeam team10 = new TeamModel();
        ITeam team11 = new TeamModel();
        ITeam team12 = new TeamModel();
        ITeam team13 = new TeamModel();
        ITeam team14 = new TeamModel();
        ITeam team15 = new TeamModel();
        ITeam team16 = new TeamModel();
        ITeam team17 = new TeamModel();
        ITeam team18 = new TeamModel();
        ITeam team19 = new TeamModel();
        ITeam team20 = new TeamModel();
        
        teamList.add(team1);
        teamList.add(team2);
        teamList.add(team3);
        teamList.add(team4);
        teamList.add(team5);
        division1.setTeamDetails(teamList);
        teamList = new ArrayList<>();
        teamList.add(team6);
        teamList.add(team7);
        teamList.add(team8);
        teamList.add(team9);
        teamList.add(team10);
        division2.setTeamDetails(teamList);
        teamList = new ArrayList<>();
        teamList.add(team11);
        teamList.add(team12);
        teamList.add(team13);
        teamList.add(team14);
        teamList.add(team15);
        division3.setTeamDetails(teamList);
        teamList = new ArrayList<>();
        teamList.add(team16);
        teamList.add(team17);
        teamList.add(team18);
        teamList.add(team19);
        teamList.add(team20);
        division4.setTeamDetails(teamList);

        divisionList.add(division1);
        divisionList.add(division2);
        conference1.setDivisionDetails(divisionList);
        divisionList = new ArrayList<>();
        divisionList.add(division3);
        divisionList.add(division4);
        conference2.setDivisionDetails(divisionList);
        
        conferenceList.add(conference1);
        conferenceList.add(conference2);
        league.setConferenceDetails(conferenceList);
        

        
        return league;
    }
	
	private List<IStanding> dummyStanding(LeagueModel league) {
        IConference conference1 = league.getConferenceDetails().get(0);
        IConference conference2 = league.getConferenceDetails().get(1);
        IDivision division1 = conference1.getDivisionDetails().get(0);
        IDivision division2 = conference1.getDivisionDetails().get(1);
        IDivision division3 = conference2.getDivisionDetails().get(0);
        IDivision division4 = conference2.getDivisionDetails().get(1);
        ITeam team1 = division1.getTeamDetails().get(0);
        ITeam team2 = division1.getTeamDetails().get(1);
        ITeam team3 = division1.getTeamDetails().get(2);
        ITeam team4 = division1.getTeamDetails().get(3);
        ITeam team5 = division1.getTeamDetails().get(4);
        ITeam team6 = division2.getTeamDetails().get(0);
        ITeam team7 = division2.getTeamDetails().get(1);
        ITeam team8 = division2.getTeamDetails().get(2);
        ITeam team9 = division2.getTeamDetails().get(3);
        ITeam team10 = division2.getTeamDetails().get(4);
        ITeam team11 = division3.getTeamDetails().get(0);
        ITeam team12 = division3.getTeamDetails().get(1);
        ITeam team13 = division3.getTeamDetails().get(2);
        ITeam team14 = division3.getTeamDetails().get(3);
        ITeam team15 = division3.getTeamDetails().get(4);
        ITeam team16 = division4.getTeamDetails().get(0);
        ITeam team17 = division4.getTeamDetails().get(1);
        ITeam team18 = division4.getTeamDetails().get(2);
        ITeam team19 = division4.getTeamDetails().get(3);
        ITeam team20 = division4.getTeamDetails().get(4);

        IStanding standing1 = new Standing();
        standing1.setConference(conference1);
        standing1.setDivision(division1);
        standing1.setTeam(team1);
        standing1.setGamesPlayed(7);
        standing1.setGamesWon(2);
        standing1.setPoints(4);

        IStanding standing2 = new Standing();
        standing2.setConference(conference1);
        standing2.setDivision(division1);
        standing2.setTeam(team2);
        standing2.setGamesPlayed(7);
        standing2.setGamesWon(6);
        standing2.setPoints(12);

        IStanding standing3 = new Standing();
        standing3.setConference(conference1);
        standing3.setDivision(division1);
        standing3.setTeam(team3);
        standing3.setGamesPlayed(8);
        standing3.setGamesWon(4);
        standing3.setPoints(8);

        IStanding standing4 = new Standing();
        standing4.setConference(conference1);
        standing4.setDivision(division1);
        standing4.setTeam(team4);
        standing4.setGamesPlayed(7);
        standing4.setGamesWon(5);
        standing4.setPoints(11);

        IStanding standing5 = new Standing();
        standing5.setConference(conference1);
        standing5.setDivision(division1);
        standing5.setTeam(team5);
        standing5.setGamesPlayed(8);
        standing5.setGamesWon(4);
        standing5.setPoints(9);

        IStanding standing6 = new Standing();
        standing6.setConference(conference1);
        standing6.setDivision(division2);
        standing6.setTeam(team6);
        standing6.setGamesPlayed(7);
        standing6.setGamesWon(6);
        standing6.setPoints(12);

        IStanding standing7 = new Standing();
        standing7.setConference(conference1);
        standing7.setDivision(division2);
        standing7.setTeam(team7);
        standing7.setGamesPlayed(8);
        standing7.setGamesWon(5);
        standing7.setPoints(10);

        IStanding standing8 = new Standing();
        standing8.setConference(conference1);
        standing8.setDivision(division2);
        standing8.setTeam(team8);
        standing8.setGamesPlayed(7);
        standing8.setGamesWon(4);
        standing8.setPoints(9);

        IStanding standing9 = new Standing();
        standing9.setConference(conference1);
        standing9.setDivision(division2);
        standing9.setTeam(team9);
        standing9.setGamesPlayed(8);
        standing9.setGamesWon(2);
        standing9.setPoints(4);

        IStanding standing10 = new Standing();
        standing10.setConference(conference1);
        standing10.setDivision(division2);
        standing10.setTeam(team10);
        standing10.setGamesPlayed(8);
        standing10.setGamesWon(4);
        standing10.setPoints(8);

        IStanding standing11 = new Standing();
        standing11.setConference(conference2);
        standing11.setDivision(division3);
        standing11.setTeam(team11);
        standing11.setGamesPlayed(8);
        standing11.setGamesWon(3);
        standing11.setPoints(6);

        IStanding standing12 = new Standing();
        standing12.setConference(conference2);
        standing12.setDivision(division3);
        standing12.setTeam(team12);
        standing12.setGamesPlayed(8);
        standing12.setGamesWon(7);
        standing12.setPoints(10);

        IStanding standing13 = new Standing();
        standing13.setConference(conference2);
        standing13.setDivision(division3);
        standing13.setTeam(team13);
        standing13.setGamesPlayed(7);
        standing13.setGamesWon(5);
        standing13.setPoints(10);

        IStanding standing14 = new Standing();
        standing14.setConference(conference2);
        standing14.setDivision(division3);
        standing14.setTeam(team14);
        standing14.setGamesPlayed(7);
        standing14.setGamesWon(4);
        standing14.setPoints(8);

        IStanding standing15 = new Standing();
        standing15.setConference(conference2);
        standing15.setDivision(division3);
        standing15.setTeam(team15);
        standing15.setGamesPlayed(7);
        standing15.setGamesWon(4);
        standing15.setPoints(8);

        IStanding standing16 = new Standing();
        standing16.setConference(conference2);
        standing16.setDivision(division4);
        standing16.setTeam(team16);
        standing16.setGamesPlayed(7);
        standing16.setGamesWon(7);
        standing16.setPoints(14);

        IStanding standing17 = new Standing();
        standing17.setConference(conference2);
        standing17.setDivision(division4);
        standing17.setTeam(team17);
        standing17.setGamesPlayed(7);
        standing17.setGamesWon(5);
        standing17.setPoints(10);

        IStanding standing18 = new Standing();
        standing18.setConference(conference2);
        standing18.setDivision(division4);
        standing18.setTeam(team18);
        standing18.setGamesPlayed(7);
        standing18.setGamesWon(0);
        standing18.setPoints(0);

        IStanding standing19 = new Standing();
        standing19.setConference(conference2);
        standing19.setDivision(division4);
        standing19.setTeam(team19);
        standing19.setGamesPlayed(7);
        standing19.setGamesWon(4);
        standing19.setPoints(8);

        IStanding standing20 = new Standing();
        standing20.setConference(conference2);
        standing20.setDivision(division4);
        standing20.setTeam(team20);
        standing20.setGamesPlayed(7);
        standing20.setGamesWon(6);
        standing20.setPoints(12);

        List<IStanding> standings = new ArrayList<>();
        standings.add(standing1);
        standings.add(standing2);
        standings.add(standing3);
        standings.add(standing4);
        standings.add(standing5);
        standings.add(standing6);
        standings.add(standing7);
        standings.add(standing8);
        standings.add(standing9);
        standings.add(standing10);
        standings.add(standing11);
        standings.add(standing12);
        standings.add(standing13);
        standings.add(standing14);
        standings.add(standing15);
        standings.add(standing16);
        standings.add(standing17);
        standings.add(standing18);
        standings.add(standing19);
        standings.add(standing20);

        return standings;
    }
	
}
