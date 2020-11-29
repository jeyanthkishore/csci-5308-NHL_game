package com.dhl.g05.trading;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueModel;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.TeamModel;
import com.dhl.g05.simulation.ITradingConfig;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.TradingConfig;


public class MockLeagueModel {
	
	private static SimulationAbstractFactory simulationAbstractFactory;
    private static ModelAbstractFactory modelAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
    }

	public ILeague leagueMock() {
		ILeague leagueMock = modelAbstractFactory.createLeagueModel();
		leagueMock.setLeagueName("DHL");
		IConference conference1 = modelAbstractFactory.createConferenceModel();
		conference1.setConferenceName("Eastern");
		ArrayList<IConference> conferenceDetails = new ArrayList<>();
		conferenceDetails.add(conference1);
		leagueMock.setConferenceDetails(conferenceDetails);
		IDivision division1 = modelAbstractFactory.createDivisionModel();
		division1.setDivisionName("Atlantic");
		ArrayList<IDivision> divisionDetails = new ArrayList<>();
		divisionDetails.add(division1);
		conference1.setDivisionDetails(divisionDetails);
		ITeam team1 = modelAbstractFactory.createTeamModel();
		team1.setTeamName("Tigers");
		team1.setUserTeam(false);
		team1.setLossCount(10);
		ITeam team2 =modelAbstractFactory.createTeamModel();
		team2.setTeamName("Rythm");
		team2.setUserTeam(false);
		team2.setLossCount(10);
		ArrayList<ITeam> teamDetails1 = new ArrayList<>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		division1.setTeamDetails(teamDetails1);
		IPlayer player1Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player2Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player3Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player4Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player5Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player1Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player2Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player3Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player4Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player5Team2 = modelAbstractFactory.createPlayerModel();
		ArrayList<IPlayer> playerDetailsTeam1 = new ArrayList<>();
		ArrayList<IPlayer> playerDetailsTeam2 = new ArrayList<>();
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

	public ILeague leagueMock1() {
		ILeague leagueMock = modelAbstractFactory.createLeagueModel();
		leagueMock.setLeagueName("DHL");
		IConference conference1 = modelAbstractFactory.createConferenceModel();
		conference1.setConferenceName("Eastern");
		ArrayList<IConference> conferenceDetails = new ArrayList<>();
		conferenceDetails.add(conference1);
		leagueMock.setConferenceDetails(conferenceDetails);
		IDivision division1 = modelAbstractFactory.createDivisionModel();
		division1.setDivisionName("Atlantic");
		ArrayList<IDivision> divisionDetails = new ArrayList<>();
		divisionDetails.add(division1);
		conference1.setDivisionDetails(divisionDetails);
		ITeam team1 =modelAbstractFactory.createTeamModel();
		team1.setTeamName("Tigers");
		team1.setUserTeam(false);
		team1.setLossCount(10);
		ITeam team2 = modelAbstractFactory.createTeamModel();
		team2.setTeamName("Rythm");
		team2.setUserTeam(false);
		team2.setLossCount(10);
		ArrayList<ITeam> teamDetails1 = new ArrayList<>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		division1.setTeamDetails(teamDetails1);
		IPlayer player1Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player2Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player3Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player4Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player5Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player1Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player2Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player3Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player4Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player5Team2 = modelAbstractFactory.createPlayerModel();
		ArrayList<IPlayer> playerDetailsTeam1 = new ArrayList<>();
		ArrayList<IPlayer> playerDetailsTeam2 = new ArrayList<>();
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

	public List<IPlayer> leagueMock2() {
		List<IPlayer> playersOffered = new ArrayList<>();
		IPlayer player1 = modelAbstractFactory.createPlayerModel();
		IPlayer player2 = modelAbstractFactory.createPlayerModel();
		player1.setPlayerName("Brian");
		player1.setPlayerStrength(4);
		player1.setPosition("goalie");
		player2.setPlayerName("Steven");
		player2.setPlayerStrength(9);
		player2.setPosition("forward");
		playersOffered.add(player1);
		playersOffered.add(player2);
		return playersOffered;
	}

	public List<IPlayer> leagueMock3() {
		List<IPlayer> playersRequested = new ArrayList<>();
		IPlayer player1 =modelAbstractFactory.createPlayerModel();
		player1.setPlayerName("Shawn");
		player1.setPlayerStrength(8);
		player1.setPosition("goalie");
		playersRequested.add(player1);
		return playersRequested;
	}

	public ITeam leagueMock4() {
		ITeam weakTeam = modelAbstractFactory.createTeamModel();
		weakTeam.setTeamName("Tigers");
		IPlayer player1 = modelAbstractFactory.createPlayerModel();
		player1.setPlayerName("Brian");
		player1.setPosition("defense");
		player1.setPlayerStrength(8);
		IPlayer player2 = modelAbstractFactory.createPlayerModel();
		player2.setPlayerName("James");
		player2.setPlayerStrength(10);
		player2.setPosition("forward");
		IPlayer player3 = modelAbstractFactory.createPlayerModel();
		player3.setPlayerName("Lily");
		player3.setPlayerStrength(6);
		player3.setPosition("goalie");
		IPlayer player4 = modelAbstractFactory.createPlayerModel();
		player4.setPlayerName("Harry");
		player4.setPlayerStrength(4);
		player4.setPosition("forward");
		IPlayer player5 = modelAbstractFactory.createPlayerModel();
		player5.setPlayerName("Shawn");
		player5.setPlayerStrength(3);
		player5.setPosition("defense");
		List<IPlayer> playerDetails = new ArrayList<>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		playerDetails.add(player4);
		playerDetails.add(player5);
		weakTeam.setPlayerList(playerDetails);
		return weakTeam;
	}

	public ITeam leagueMock5() {
		ITeam weakTeam = new TeamModel();
		weakTeam.setTeamName("TeamA");
		IPlayer player1 = modelAbstractFactory.createPlayerModel();
		player1.setPlayerName("Player1");
		player1.setPosition("goalie");
		player1.setPlayerStrength(7);
		IPlayer player2 = modelAbstractFactory.createPlayerModel();
		player2.setPlayerName("Player2");
		player2.setPosition("forward");
		player2.setPlayerStrength(10);
		IPlayer player3 = modelAbstractFactory.createPlayerModel();
		player3.setPlayerName("Player3");
		player3.setPosition("goalie");
		player3.setPlayerStrength(5);
		IPlayer player4 = modelAbstractFactory.createPlayerModel();
		player4.setPlayerName("Player4");
		player4.setPosition("defense");
		player4.setPlayerStrength(7);
		List<IPlayer> playerDetails = new ArrayList<>();
		playerDetails.add(player1);
		playerDetails.add(player2);
		playerDetails.add(player3);
		playerDetails.add(player4);
		weakTeam.setPlayerList(playerDetails);
		return weakTeam;
	}

	public List<ITeam> leagueMock6() {
		ITeam team1 = modelAbstractFactory.createTeamModel();
		team1.setTeamName("Tigers");
		ITeam team2 = modelAbstractFactory.createTeamModel();
		team2.setTeamName("Rythm");
		ArrayList<ITeam> teamDetails1 = new ArrayList<>();
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		IPlayer player1Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player2Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player3Team1 = modelAbstractFactory.createPlayerModel();
		IPlayer player1Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player2Team2 = modelAbstractFactory.createPlayerModel();
		IPlayer player3Team2 = modelAbstractFactory.createPlayerModel();
		player1Team1.setPlayerName("ShawnTeam1");
		player2Team1.setPlayerName("KristenTeam1");
		player3Team1.setPlayerName("ZackTeam1");
		ArrayList<IPlayer> playerDetailsTeam1 = new ArrayList<>();
		playerDetailsTeam1.add(player1Team1);
		playerDetailsTeam1.add(player2Team1);
		playerDetailsTeam1.add(player3Team1);
		team1.setPlayerList(playerDetailsTeam1);
		player1Team2.setPlayerName("LiamTeam2");
		player2Team2.setPlayerName("JustinTeam2");
		player3Team2.setPlayerName("ChesterTeam2");
		ArrayList<IPlayer> playerDetailsTeam2 = new ArrayList<>();
		playerDetailsTeam2.add(player1Team2);
		playerDetailsTeam2.add(player2Team2);
		playerDetailsTeam2.add(player3Team2);
		team2.setPlayerList(playerDetailsTeam2);
		teamDetails1.add(team1);
		teamDetails1.add(team2);
		return teamDetails1;
	}

	public ITradingConfig TradingConfigMock() {
		ITradingConfig trading = simulationAbstractFactory.createTradingConfig();
		trading.setLossPoint(8);
		trading.setMaxPlayersPerTrade(2);
		trading.setRandomTradeOfferChance(1.00);
		trading.setRandomAcceptanceChance(1.00);
		return trading;
	}
}
