package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.Random;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.TeamModel;


public class MockLeagueModel {

	public LeagueModel leagueMock() {

		Random playerStrength = new Random();
		Random teamStrength = new Random();

		LeagueModel leagueMock = new LeagueModel();
		leagueMock.setLeagueName("DHL");

		ConferenceModel conference1 = new ConferenceModel();
		conference1.setConferenceName("Eastern");
		ArrayList<ConferenceModel> conferenceDetails = new ArrayList<ConferenceModel>();
		conferenceDetails.add(conference1);
		leagueMock.setConferenceDetails(conferenceDetails);

		DivisionModel division1 = new DivisionModel();
		division1.setDivisionName("Atlantic");



		ArrayList<DivisionModel> divisionDetails = new ArrayList<DivisionModel>();
		divisionDetails.add(division1);

		conference1.setDivisionDetails(divisionDetails);

		TeamModel team1 = new TeamModel();
		team1.setTeamName("Tigers");
		team1.setUserTeam(false);
		team1.setLossCount(10);

		TeamModel team2 = new TeamModel();
		team2.setTeamName("Rythm");
		team2.setUserTeam(true);
		team2.setLossCount(10);

		
		ArrayList<TeamModel> teamDetails1 = new ArrayList<TeamModel>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);

		division1.setTeamDetails(teamDetails1);


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



		ArrayList<PlayerModel> playerDetailsTeam1 = new ArrayList<PlayerModel>();
		ArrayList<PlayerModel> playerDetailsTeam2 = new ArrayList<PlayerModel>();


		player1Team1.setPlayerName("Player1Team1");
		player1Team1.setPlayerStrength(4);
		player1Team1.setPosition("goalie");

		player2Team1.setPlayerName("Player2Team1");
		player2Team1.setPlayerStrength(5);
		player2Team1.setPosition("forward");

		player3Team1.setPlayerName("Player3Team1");
		player3Team1.setPlayerStrength(8);
		player3Team1.setPosition("forward");

		player4Team1.setPlayerName("Player4Team1");
		player4Team1.setPlayerStrength(9);
		player4Team1.setPosition("defense");

		player5Team1.setPlayerName("Player5Team1");
		player5Team1.setPlayerStrength(3);
		player5Team1.setPosition("defense");

		playerDetailsTeam1.add(player1Team1);
		playerDetailsTeam1.add(player2Team1);
		playerDetailsTeam1.add(player3Team1);
		playerDetailsTeam1.add(player4Team1);
		playerDetailsTeam1.add(player5Team1);
		team1.setPlayerList(playerDetailsTeam1);

		player1Team2.setPlayerName("player1Team2");
		player1Team2.setPlayerStrength(6);
		player1Team2.setPosition("defense");

		player2Team2.setPlayerName("player2Team2");
		player2Team2.setPlayerStrength(8);
		player2Team2.setPosition("goalie");

		player3Team2.setPlayerName("Player3Team2");
		player3Team2.setPlayerStrength(2);
		player3Team2.setPosition("goalie");

		player4Team2.setPlayerName("Player4Team2");
		player4Team2.setPlayerStrength(9);
		player4Team2.setPosition("forward");

		player5Team2.setPlayerName("Player5Team2");
		player5Team2.setPlayerStrength(6);
		player5Team2.setPosition("forward");

		playerDetailsTeam2.add(player1Team2);
		playerDetailsTeam2.add(player2Team2);
		playerDetailsTeam2.add(player3Team2);
		playerDetailsTeam2.add(player4Team2);
		playerDetailsTeam2.add(player5Team2);
		team2.setPlayerList(playerDetailsTeam2);


		return leagueMock;
	}
	public LeagueModel leagueMock1()
	{
	Random playerStrength = new Random();
	Random teamStrength = new Random();

	LeagueModel leagueMock = new LeagueModel();
	leagueMock.setLeagueName("DHL");

	ConferenceModel conference1 = new ConferenceModel();
	conference1.setConferenceName("Eastern");
	ArrayList<ConferenceModel> conferenceDetails = new ArrayList<ConferenceModel>();
	conferenceDetails.add(conference1);
	leagueMock.setConferenceDetails(conferenceDetails);

	DivisionModel division1 = new DivisionModel();
	division1.setDivisionName("Atlantic");

	ArrayList<DivisionModel> divisionDetails = new ArrayList<DivisionModel>();
	divisionDetails.add(division1);
	conference1.setDivisionDetails(divisionDetails);

	TeamModel team1 = new TeamModel();
	team1.setTeamName("Tigers");
	team1.setUserTeam(false);
	team1.setLossCount(10);

	TeamModel team2 = new TeamModel();
	team2.setTeamName("Rythm");
	team2.setUserTeam(false);
	team2.setLossCount(10);

	ArrayList<TeamModel> teamDetails1 = new ArrayList<TeamModel>();
	teamDetails1.add(team1);
	teamDetails1.add(team2);

	division1.setTeamDetails(teamDetails1);

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

	ArrayList<PlayerModel> playerDetailsTeam1 = new ArrayList<PlayerModel>();
	ArrayList<PlayerModel> playerDetailsTeam2 = new ArrayList<PlayerModel>();

	player1Team1.setPlayerName("Player1Team1");
	player1Team1.setPlayerStrength(4);
	player1Team1.setPosition("defense");

	player2Team1.setPlayerName("Player2Team1");
	player2Team1.setPlayerStrength(5);
	player2Team1.setPosition("forward");

	player3Team1.setPlayerName("Player3Team1");
	player3Team1.setPlayerStrength(8);
	player3Team1.setPosition("forward");

	player4Team1.setPlayerName("Player4Team1");
	player4Team1.setPlayerStrength(9);
	player4Team1.setPosition("goalie");

	player5Team1.setPlayerName("Player5Team1");
	player5Team1.setPlayerStrength(3);
	player5Team1.setPosition("defense");

	playerDetailsTeam1.add(player1Team1);
	playerDetailsTeam1.add(player2Team1);
	playerDetailsTeam1.add(player3Team1);
	playerDetailsTeam1.add(player4Team1);
	playerDetailsTeam1.add(player5Team1);
	team1.setPlayerList(playerDetailsTeam1);

	player1Team2.setPlayerName("player1Team2");
	player1Team2.setPlayerStrength(6);
	player1Team2.setPosition("defense");

	player2Team2.setPlayerName("player2Team2");
	player2Team2.setPlayerStrength(8);
	player2Team2.setPosition("goalie");

	player3Team2.setPlayerName("Player3Team2");
	player3Team2.setPlayerStrength(2);
	player3Team2.setPosition("goalie");

	player4Team2.setPlayerName("Player4Team2");
	player4Team2.setPlayerStrength(9);
	player4Team2.setPosition("defense");

	player5Team2.setPlayerName("Player5Team2");
	player5Team2.setPlayerStrength(6);
	player5Team2.setPosition("forward");

	playerDetailsTeam2.add(player1Team2);
	playerDetailsTeam2.add(player2Team2);
	playerDetailsTeam2.add(player3Team2);
	playerDetailsTeam2.add(player4Team2);
	playerDetailsTeam2.add(player5Team2);
	team2.setPlayerList(playerDetailsTeam2);
	

	return leagueMock;
}
}
