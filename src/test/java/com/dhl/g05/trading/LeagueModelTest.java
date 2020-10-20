package com.dhl.g05.trading;

import java.util.ArrayList;

import com.dhl.g05.leaguemodel.ConferenceObject;
import com.dhl.g05.leaguemodel.DivisionObject;
import com.dhl.g05.leaguemodel.LeagueObject;
import com.dhl.g05.leaguemodel.TeamObject;


public class LeagueModelTest {
	
	public LeagueObject leagueMock() {
		
		LeagueObject leagueMock = new LeagueObject();
		ConferenceObject conference= new ConferenceObject();
		DivisionObject division = new DivisionObject();
		TeamObject team1 = new TeamObject();
		TeamObject team2 = new TeamObject();

		ArrayList<ConferenceObject> conferenceDetails = new ArrayList<ConferenceObject>();
		ArrayList<DivisionObject> divisionDetails = new ArrayList<DivisionObject>();
		ArrayList<TeamObject> teamDetails = new ArrayList<TeamObject>();

		leagueMock.setLeagueName("DHL");
		conference.setConferenceName("Eastern");
		division.setDivisionName("Atlantic");
		team1.setTeamName("Tigers");
		team2.setTeamName("Rythm");

		conferenceDetails.add(conference);
		divisionDetails.add(division);
		teamDetails.add(team1);
		teamDetails.add(team2);

		leagueMock.setConferenceDetails(conferenceDetails);
		conference.setDivisionDetails(divisionDetails);
		division.setTeamDetails(teamDetails);

		return leagueMock;
	}

}
