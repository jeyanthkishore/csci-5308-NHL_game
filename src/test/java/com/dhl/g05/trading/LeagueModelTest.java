package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Random;

import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.league.LeagueModel;
import com.dhl.g05.leaguemodel.player.PlayerModel;
import com.dhl.g05.leaguemodel.team.TeamModel;


public class LeagueModelTest {
	
	public LeagueModel leagueMock() {
		
		Random playerStrength = new Random();
		Random teamStrength = new Random();
		
		LeagueModel leagueMock = new LeagueModel();
		leagueMock.setLeagueName("DHL");
		
		ConferenceModel conference1= new ConferenceModel();
		conference1.setConferenceName("Eastern");
		ArrayList<ConferenceModel> conferenceDetails = new ArrayList<ConferenceModel>();
		conferenceDetails.add(conference1);
		leagueMock.setConferenceDetails(conferenceDetails);
		
		
		DivisionModel division1 = new DivisionModel();
		division1.setDivisionName("Atlantic");
		
		DivisionModel division2 = new DivisionModel();
		division2.setDivisionName("Pacific");
		
		ArrayList<DivisionModel> divisionDetails = new ArrayList<DivisionModel>();
		divisionDetails.add(division1);
		divisionDetails.add(division2);
		conference1.setDivisionDetails(divisionDetails);
		
		TeamModel team1 = new TeamModel();
		team1.setTeamName("Tigers");
		
		TeamModel team2 = new TeamModel();
		team2.setTeamName("Rythm");
		
		TeamModel team3 = new TeamModel();
		team3.setTeamName("Hawkers");
		
		TeamModel team4 = new TeamModel();
		team4.setTeamName("Montreal");
		
		TeamModel team5 = new TeamModel();
		team5.setTeamName("Boston");
		
		ArrayList<TeamModel> teamDetails1 = new ArrayList<TeamModel>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		teamDetails1.add(team3);
		division1.setTeamDetails(teamDetails1);
		
		ArrayList<TeamModel> teamDetails2 = new ArrayList<TeamModel>();
		teamDetails2.add(team4);
		teamDetails2.add(team5);
		division2.setTeamDetails(teamDetails2);
		
		
		PlayerModel player1Team1 = new PlayerModel();
		PlayerModel player2Team1 = new PlayerModel();
		PlayerModel player3Team1 = new PlayerModel();
		PlayerModel player4Team1 = new PlayerModel();
		PlayerModel player5Team1 = new PlayerModel();
		
		PlayerModel player1Team2 = new PlayerModel();
		PlayerModel player2Team2 = new PlayerModel();
		PlayerModel player3Team2 = new PlayerModel();
		PlayerModel player4Team2 = new PlayerModel();
		PlayerModel player5Team2 = new PlayerModel();

		PlayerModel player1Team3 = new PlayerModel();
		PlayerModel player2Team3 = new PlayerModel();
		PlayerModel player3Team3 = new PlayerModel();
		PlayerModel player4Team3 = new PlayerModel();
		PlayerModel player5Team3 = new PlayerModel();
		
		PlayerModel player1Team4 = new PlayerModel();
		PlayerModel player2Team4= new PlayerModel();
		PlayerModel player3Team4 = new PlayerModel();
		PlayerModel player4Team4 = new PlayerModel();
		PlayerModel player5Team4 = new PlayerModel();
		
		PlayerModel player1Team5 = new PlayerModel();
		PlayerModel player2Team5= new PlayerModel();
		PlayerModel player3Team5 = new PlayerModel();
		PlayerModel player4Team5 = new PlayerModel();
		PlayerModel player5Team5 = new PlayerModel();

		ArrayList<PlayerModel> playerDetailsTeam1 = new ArrayList<PlayerModel>();
		ArrayList<PlayerModel> playerDetailsTeam2 = new ArrayList<PlayerModel>();
		ArrayList<PlayerModel> playerDetailsTeam3 = new ArrayList<PlayerModel>();
		ArrayList<PlayerModel> playerDetailsTeam4 = new ArrayList<PlayerModel>();
		ArrayList<PlayerModel> playerDetailsTeam5 = new ArrayList<PlayerModel>();
		
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
