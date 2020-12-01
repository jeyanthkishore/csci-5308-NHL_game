package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.leaguesimulation.IStandingModel;

public class PlayerDraftMock {
   
	public  List<IStandingModel> mockStandings() {
		ILeague league=ApplicationConfiguration.instance().getModelConcreteFactoryState().createLeagueModel();
		league.setLeagueName("DHL");
		IConference conference1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createConferenceModel();
		conference1.setConferenceName("Eastern");
		IConference conference2 =  ApplicationConfiguration.instance().getModelConcreteFactoryState().createConferenceModel();
		conference2.setConferenceName("Northern");
		IDivision division1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createDivisionModel();
		division1.setDivisionName("Atlantic");
		IDivision division2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createDivisionModel();
		division2.setDivisionName("Pacific");
		IDivision division3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createDivisionModel();
		division3.setDivisionName("Indian");
		IDivision division4 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createDivisionModel();
		division4.setDivisionName("Mediterranean");
		ITeam team1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team1.setTeamName("Tigers");
		IGenerateNewPlayers youngPlayers =ApplicationConfiguration.instance().getModelConcreteFactoryState().createNewPlayers();
		youngPlayers.setNumberOfTeams(7);
		List<IPlayer> newPlayers= youngPlayers.generatePlayers();
		team1.setPlayerList(newPlayers);
		ITeam team2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team2.setTeamName("Thunders");
		IPlayer player1T2 =ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1T2).setPlayerName("Player1");
		player1T2.setPosition("goalie");
		player1T2.setPlayerStrength(7);
		IPlayer player2T2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2T2).setPlayerName("Player2");
		player2T2.setPosition("forward");
		player2T2.setPlayerStrength(10);
		List<IPlayer> playerDetails2 = new ArrayList<>();
		playerDetails2.add(player1T2);
		playerDetails2.add(player2T2);
		team2.setPlayerList(playerDetails2);
		
		ITeam team3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team3.setTeamName("Boston");
		IPlayer player1T3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1T3).setPlayerName("Player1");
		player1T3.setPosition("goalie");
		player1T3.setPlayerStrength(7);
		IPlayer player2T3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2T3).setPlayerName("Player2");
		player2T3.setPosition("forward");
		player2T3.setPlayerStrength(10);
		List<IPlayer> playerDetails3 = new ArrayList<>();
		playerDetails3.add(player1T2);
		playerDetails3.add(player2T2);
		team3.setPlayerList(playerDetails3);
		
		ITeam team4 =ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team4.setTeamName("Rockers");
		team3.setTeamName("Boston");
		IPlayer player1T4 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1T4).setPlayerName("Player1");
		player1T4.setPosition("goalie");
		player1T4.setPlayerStrength(7);
		IPlayer player2T4 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2T3).setPlayerName("Player2");
		player2T4.setPosition("forward");
		player2T4.setPlayerStrength(10);
		List<IPlayer> playerDetails4 = new ArrayList<>();
		playerDetails4.add(player1T2);
		playerDetails4.add(player2T2);
		team4.setPlayerList(playerDetails4);
		
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails1 = new ArrayList<>();
		List<IDivision> divisionDetails2 = new ArrayList<>();
		divisionDetails1.add(division1);
		divisionDetails1.add(division2);
		divisionDetails2.add(division3);
		divisionDetails2.add(division4);
		conference1.setDivisionDetails(divisionDetails1);
		conference2.setDivisionDetails(divisionDetails2);
		conferenceDetails.add(conference1);
		conferenceDetails.add(conference2);
		league.setConferenceDetails(conferenceDetails);
		IStandingModel standing1 = ApplicationConfiguration.instance().getSimulationConcreteFactoryState().createStandingModel();
		standing1.setConference(conference1);
		standing1.setDivision(division1);
		standing1.setTeam(team1);
		standing1.setTotalGamesPlayed(7);
		standing1.setTotalGamesWon(1);
		standing1.setTotalPoints(2);
		IStandingModel standing2 = ApplicationConfiguration.instance().getSimulationConcreteFactoryState().createStandingModel();
		standing2.setConference(conference1);
		standing2.setDivision(division2);
		standing2.setTeam(team2);
		standing2.setTotalGamesPlayed(7);
		standing2.setTotalGamesWon(3);
		standing2.setTotalPoints(6);

		IStandingModel standing3 = ApplicationConfiguration.instance().getSimulationConcreteFactoryState().createStandingModel();
		standing3.setConference(conference2);
		standing3.setDivision(division3);
		standing3.setTeam(team3);
		standing3.setTotalGamesPlayed(7);
		standing3.setTotalGamesWon(2);
		standing3.setTotalPoints(4);

		IStandingModel standing4 =ApplicationConfiguration.instance().getSimulationConcreteFactoryState().createStandingModel();
		standing4.setConference(conference2);
		standing4.setDivision(division4);
		standing4.setTeam(team4);
		standing4.setTotalGamesPlayed(6);
		standing4.setTotalGamesWon(2);
		standing4.setTotalPoints(4);

		List<IStandingModel> standings = new ArrayList<>();
		standings.add(standing1);
		standings.add(standing2);
		standings.add(standing3);
		standings.add(standing4);
		return standings;
	}

	public ILeague mockLeagueForStanding() {
		ILeague league=ApplicationConfiguration.instance().getModelConcreteFactoryState().createLeagueModel();
		league.setLeagueName("DHL");
		IConference conference1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createConferenceModel();
		conference1.setConferenceName("Eastern");
		IConference conference2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createConferenceModel();
		conference2.setConferenceName("Northern");
		IDivision division1 =ApplicationConfiguration.instance().getModelConcreteFactoryState().createDivisionModel();
		division1.setDivisionName("Atlantic");
		IDivision division2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createDivisionModel();
		division1.setDivisionName("Pacific");
        division1.setDivisionName("Indian");
		division1.setDivisionName("Mediterranean");
		ITeam team1 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team1.setTeamName("Tigers");
		IGenerateNewPlayers youngPlayers =ApplicationConfiguration.instance().getModelConcreteFactoryState().createNewPlayers();
		youngPlayers.setNumberOfTeams(4);
		List<IPlayer> newPlayers= youngPlayers.generatePlayers();
		team1.setPlayerList(newPlayers);
		ITeam team2 = new TeamModel();
		team2.setTeamName("Thunders");
		IPlayer player1T2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1T2).setPlayerName("Player1");
		player1T2.setPosition("goalie");
		player1T2.setPlayerStrength(7);
		IPlayer player2T2 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2T2).setPlayerName("Player2");
		player2T2.setPosition("forward");
		player2T2.setPlayerStrength(10);
		List<IPlayer> playerDetails2 = new ArrayList<>();
		playerDetails2.add(player1T2);
		playerDetails2.add(player2T2);
		team2.setPlayerList(playerDetails2);
		
		ITeam team3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team3.setTeamName("Boston");
		IPlayer player1T3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1T3).setPlayerName("Player1");
		player1T3.setPosition("goalie");
		player1T3.setPlayerStrength(7);
		IPlayer player2T3 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2T3).setPlayerName("Player2");
		player2T3.setPosition("forward");
		player2T3.setPlayerStrength(10);
		List<IPlayer> playerDetails3 = new ArrayList<>();
		playerDetails3.add(player1T2);
		playerDetails3.add(player2T2);
		team3.setPlayerList(playerDetails3);
		
		ITeam team4 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createTeamModel();
		team4.setTeamName("Rockers");
		team3.setTeamName("Boston");
		IPlayer player1T4 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player1T4).setPlayerName("Player1");
		player1T4.setPosition("goalie");
		player1T4.setPlayerStrength(7);
		IPlayer player2T4 = ApplicationConfiguration.instance().getModelConcreteFactoryState().createPlayerModel();
		((FreeAgentModel) player2T3).setPlayerName("Player2");
		player2T4.setPosition("forward");
		player2T4.setPlayerStrength(10);
		List<IPlayer> playerDetails4 = new ArrayList<>();
		playerDetails4.add(player1T2);
		playerDetails4.add(player2T2);
		team4.setPlayerList(playerDetails4);
		
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails1 = new ArrayList<>();
		List<IDivision> divisionDetails2 = new ArrayList<>();
		List<ITeam> teamDetaile1=new ArrayList<>(); 
		teamDetaile1.add(team1);
		teamDetaile1.add(team2);
		List<ITeam> teamDetaile2=new ArrayList<>();
		teamDetaile2.add(team3);
		teamDetaile2.add(team4);
		division1.setTeamDetails(teamDetaile1);
		division2.setTeamDetails(teamDetaile2);
		conferenceDetails.add(conference1);
		conferenceDetails.add(conference2);
		divisionDetails1.add(division1);
		divisionDetails1.add(division2);
		conference1.setDivisionDetails(divisionDetails1);
		conference2.setDivisionDetails(divisionDetails2);
		league.setConferenceDetails(conferenceDetails);
		return league;
	}

	public Map<Integer, List<Map<IStandingModel,IStandingModel>>> mockTradePickLatest(List<IStandingModel> standing) {
		List<IStandingModel> standings = standing;
		Map<Integer, List<Map<IStandingModel,IStandingModel>>> finalRoundtradeTeamPick=new HashMap<>();
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound1=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound1= new HashMap<>();
		teamsExchangingPickInRound1.put(standings.get(3), standings.get(1));
		teamsExchangingPickInRound1.put(standings.get(2), standings.get(0));
		listOfTeamsExchangingPickInRound1.add(teamsExchangingPickInRound1);
		finalRoundtradeTeamPick.put(1, listOfTeamsExchangingPickInRound1);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound2=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound2= new HashMap<>();
		teamsExchangingPickInRound2.put(standings.get(1), standings.get(0));
		listOfTeamsExchangingPickInRound2.add(teamsExchangingPickInRound2);
		finalRoundtradeTeamPick.put(2, listOfTeamsExchangingPickInRound2);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound3=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound3= new HashMap<>();
		teamsExchangingPickInRound3.put(standings.get(3), standings.get(2));
		listOfTeamsExchangingPickInRound3.add(teamsExchangingPickInRound3);
		finalRoundtradeTeamPick.put(3, listOfTeamsExchangingPickInRound3);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound4=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound4= new HashMap<>();
		teamsExchangingPickInRound4.put(standings.get(1), standings.get(0));
		listOfTeamsExchangingPickInRound4.add(teamsExchangingPickInRound4);
		finalRoundtradeTeamPick.put(4, listOfTeamsExchangingPickInRound4);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound5=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound5= new HashMap<>();
		teamsExchangingPickInRound5.put(standings.get(1), standings.get(2));
		teamsExchangingPickInRound5.put(standings.get(2), standings.get(0));
		teamsExchangingPickInRound5.put(standings.get(3), standings.get(1));
		listOfTeamsExchangingPickInRound5.add(teamsExchangingPickInRound5);
		finalRoundtradeTeamPick.put(5, listOfTeamsExchangingPickInRound5);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound6=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound6= new HashMap<>();
		teamsExchangingPickInRound6.put(standings.get(1), standings.get(0));
		listOfTeamsExchangingPickInRound6.add(teamsExchangingPickInRound6);
		finalRoundtradeTeamPick.put(6, listOfTeamsExchangingPickInRound6);
		
		List<Map<IStandingModel,IStandingModel>> listOfTeamsExchangingPickInRound7=new ArrayList<>();
		Map<IStandingModel,IStandingModel> teamsExchangingPickInRound7= new HashMap<>();
		teamsExchangingPickInRound7.put(standings.get(2), standings.get(3));
		listOfTeamsExchangingPickInRound7.add(teamsExchangingPickInRound7);
		finalRoundtradeTeamPick.put(7, listOfTeamsExchangingPickInRound7);
		
		return finalRoundtradeTeamPick;
	}

}
