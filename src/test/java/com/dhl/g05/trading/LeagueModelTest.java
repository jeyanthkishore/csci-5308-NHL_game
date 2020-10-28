package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Random;

import com.dhl.g05.leaguemodel.conference.ConferenceObject;
import com.dhl.g05.leaguemodel.division.DivisionObject;
import com.dhl.g05.leaguemodel.league.LeagueObject;
import com.dhl.g05.leaguemodel.player.PlayerObject;
import com.dhl.g05.leaguemodel.team.TeamObject;


public class LeagueModelTest {
	
	public LeagueObject leagueMock() {
		
		Random playerStrength = new Random();
		Random teamStrength = new Random();
		
		LeagueObject leagueMock = new LeagueObject();
		leagueMock.setLeagueName("DHL");
		
		ConferenceObject conference1= new ConferenceObject();
		conference1.setConferenceName("Eastern");
		ArrayList<ConferenceObject> conferenceDetails = new ArrayList<ConferenceObject>();
		conferenceDetails.add(conference1);
		leagueMock.setConferenceDetails(conferenceDetails);
		
		
		DivisionObject division1 = new DivisionObject();
		division1.setDivisionName("Atlantic");
		
		DivisionObject division2 = new DivisionObject();
		division2.setDivisionName("Pacific");
		
		ArrayList<DivisionObject> divisionDetails = new ArrayList<DivisionObject>();
		divisionDetails.add(division1);
		divisionDetails.add(division2);
		conference1.setDivisionDetails(divisionDetails);
		
		TeamObject team1 = new TeamObject();
		team1.setTeamName("Tigers");
		
		TeamObject team2 = new TeamObject();
		team2.setTeamName("Rythm");
		
		TeamObject team3 = new TeamObject();
		team3.setTeamName("Hawkers");
		
		TeamObject team4 = new TeamObject();
		team4.setTeamName("Montreal");
		
		TeamObject team5 = new TeamObject();
		team5.setTeamName("Boston");
		
		ArrayList<TeamObject> teamDetails1 = new ArrayList<TeamObject>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		teamDetails1.add(team3);
		division1.setTeamDetails(teamDetails1);
		
		ArrayList<TeamObject> teamDetails2 = new ArrayList<TeamObject>();
		teamDetails2.add(team4);
		teamDetails2.add(team5);
		division2.setTeamDetails(teamDetails2);
		
		
		PlayerObject player1Team1 = new PlayerObject();
		PlayerObject player2Team1 = new PlayerObject();
		PlayerObject player3Team1 = new PlayerObject();
		PlayerObject player4Team1 = new PlayerObject();
		PlayerObject player5Team1 = new PlayerObject();
		
		PlayerObject player1Team2 = new PlayerObject();
		PlayerObject player2Team2 = new PlayerObject();
		PlayerObject player3Team2 = new PlayerObject();
		PlayerObject player4Team2 = new PlayerObject();
		PlayerObject player5Team2 = new PlayerObject();

		PlayerObject player1Team3 = new PlayerObject();
		PlayerObject player2Team3 = new PlayerObject();
		PlayerObject player3Team3 = new PlayerObject();
		PlayerObject player4Team3 = new PlayerObject();
		PlayerObject player5Team3 = new PlayerObject();
		
		PlayerObject player1Team4 = new PlayerObject();
		PlayerObject player2Team4= new PlayerObject();
		PlayerObject player3Team4 = new PlayerObject();
		PlayerObject player4Team4 = new PlayerObject();
		PlayerObject player5Team4 = new PlayerObject();
		
		PlayerObject player1Team5 = new PlayerObject();
		PlayerObject player2Team5= new PlayerObject();
		PlayerObject player3Team5 = new PlayerObject();
		PlayerObject player4Team5 = new PlayerObject();
		PlayerObject player5Team5 = new PlayerObject();

		ArrayList<PlayerObject> playerDetailsTeam1 = new ArrayList<PlayerObject>();
		ArrayList<PlayerObject> playerDetailsTeam2 = new ArrayList<PlayerObject>();
		ArrayList<PlayerObject> playerDetailsTeam3 = new ArrayList<PlayerObject>();
		ArrayList<PlayerObject> playerDetailsTeam4 = new ArrayList<PlayerObject>();
		ArrayList<PlayerObject> playerDetailsTeam5 = new ArrayList<PlayerObject>();
		
		player1Team1.setPlayerName("Player1Team1");
		player1Team1.setPlayerStrength(playerStrength.nextInt(10) +1);
		player2Team1.setPlayerName("Player2Team1");
		player2Team1.setPlayerStrength(playerStrength.nextInt(10) +1);
		player3Team1.setPlayerName("Player3Team1");
		player3Team1.setPlayerStrength(playerStrength.nextInt(10) +1);
		player4Team1.setPlayerName("Player4Team1");
		player4Team1.setPlayerStrength(playerStrength.nextInt(10) +1);
		player5Team1.setPlayerName("Player5Team1");
		player5Team1.setPlayerStrength(playerStrength.nextInt(10) +1);
		playerDetailsTeam1.add(player1Team1);
		playerDetailsTeam1.add(player2Team1);
		playerDetailsTeam1.add(player3Team1);
		playerDetailsTeam1.add(player4Team1);
		playerDetailsTeam1.add(player5Team1);
		team1.setPlayerList(playerDetailsTeam1);
		
		player1Team2.setPlayerName("player1Team2");
		player1Team2.setPlayerStrength(playerStrength.nextInt(10) +1);
		player2Team2.setPlayerName("player2Team2");
		player2Team2.setPlayerStrength(playerStrength.nextInt(10) +1);
		player3Team2.setPlayerName("Player3Team2");
		player3Team2.setPlayerStrength(playerStrength.nextInt(10) +1);
		player4Team2.setPlayerName("Player4Team2");
		player4Team2.setPlayerStrength(playerStrength.nextInt(10) +1);
		player5Team2.setPlayerName("Player5Team2");
		player5Team2.setPlayerStrength(playerStrength.nextInt(10) +1);
		playerDetailsTeam2.add(player1Team2);
		playerDetailsTeam2.add(player2Team2);
		playerDetailsTeam2.add(player3Team2);
		playerDetailsTeam2.add(player4Team2);
		playerDetailsTeam2.add(player5Team2);
		team2.setPlayerList(playerDetailsTeam2);
		
		player1Team3.setPlayerName("player1Team3");
		player1Team3.setPlayerStrength(playerStrength.nextInt(10) +1);
		player2Team3.setPlayerName("player2Team3");
		player2Team3.setPlayerStrength(playerStrength.nextInt(10) +1);
		player3Team3.setPlayerName("Player3Team3");
		player3Team3.setPlayerStrength(playerStrength.nextInt(10) +1);
		player4Team3.setPlayerName("Player4Team3");
		player4Team3.setPlayerStrength(playerStrength.nextInt(10) +1);
		player5Team3.setPlayerName("Player5Team3");
		player5Team3.setPlayerStrength(playerStrength.nextInt(10) +1);
		playerDetailsTeam3.add(player1Team3);
		playerDetailsTeam3.add(player2Team3);
		playerDetailsTeam3.add(player3Team3);
		playerDetailsTeam3.add(player4Team3);
		playerDetailsTeam3.add(player5Team3);
		team3.setPlayerList(playerDetailsTeam3);
		
		player1Team4.setPlayerName("player1Team4");
		player1Team4.setPlayerStrength(playerStrength.nextInt(10) +1);
		player2Team4.setPlayerName("player2Team4");
		player2Team4.setPlayerStrength(playerStrength.nextInt(10) +1);
		player3Team4.setPlayerName("player3Team4");
		player3Team4.setPlayerStrength(playerStrength.nextInt(10) +1);
		player4Team4.setPlayerName("player4Team4");
		player4Team4.setPlayerStrength(playerStrength.nextInt(10) +1);
		player5Team4.setPlayerName("player5Team4");
		player5Team4.setPlayerStrength(playerStrength.nextInt(10) +1);
		playerDetailsTeam4.add(player1Team4);
		playerDetailsTeam4.add(player2Team4);
		playerDetailsTeam4.add(player3Team4);
		playerDetailsTeam4.add(player4Team4);
		playerDetailsTeam4.add(player5Team4);
		team4.setPlayerList(playerDetailsTeam4);

		player1Team5.setPlayerName("player1Team5");
		player1Team5.setPlayerStrength(playerStrength.nextInt(10) +1);
		player2Team5.setPlayerName("player2Team5");
		player2Team5.setPlayerStrength(playerStrength.nextInt(10) +1);
		player3Team5.setPlayerName("player3Team5");
		player3Team5.setPlayerStrength(playerStrength.nextInt(10) +1);
		player4Team4.setPlayerName("player4Team4");
		player4Team4.setPlayerStrength(playerStrength.nextInt(10) +1);
		player4Team5.setPlayerName("player4Team5");
		player4Team5.setPlayerStrength(playerStrength.nextInt(10) +1);
		playerDetailsTeam5.add(player1Team5);
		playerDetailsTeam5.add(player2Team5);
		playerDetailsTeam5.add(player3Team5);
		playerDetailsTeam5.add(player4Team5);
		playerDetailsTeam5.add(player5Team5);
		team5.setPlayerList(playerDetailsTeam5);

		return leagueMock;
	}

}
