package com.dhl.g05.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.g05.simulation.IStandingModel;
import com.dhl.g05.simulation.StandingModel;

public class PlayerDraftMock {

	public  List<IStandingModel> mockStandings() {
		ILeague league = new LeagueModel();
		league.setLeagueName("DHL");
		IConference conference1 = new ConferenceModel();
		conference1.setConferenceName("Eastern");
		IConference conference2 = new ConferenceModel();
		conference2.setConferenceName("Northern");
		IDivision division1 = new DivisionModel();
		division1.setDivisionName("Atlantic");
		IDivision division2 = new DivisionModel();
		division2.setDivisionName("Pacific");
		IDivision division3 = new DivisionModel();
		division3.setDivisionName("Indian");
		IDivision division4 = new DivisionModel();
		division4.setDivisionName("Mediterranean");
		ITeam team1 = new TeamModel();
		team1.setTeamName("Tigers");
		IPlayer player1 = new PlayerModel();
		((FreeAgentModel) player1).setPlayerName("Player1");
		player1.setPosition("goalie");
		player1.setPlayerStrength(7);
		IPlayer player2 = new PlayerModel();
		((FreeAgentModel) player2).setPlayerName("Player2");
		player2.setPosition("forward");
		player2.setPlayerStrength(10);
		List<IPlayer> playerDetails1 = new ArrayList<>();
		playerDetails1.add(player1);
		playerDetails1.add(player2);
		team1.setPlayerList(playerDetails1);
		
		ITeam team2 = new TeamModel();
		team2.setTeamName("Thunders");
		IPlayer player1T2 = new PlayerModel();
		((FreeAgentModel) player1T2).setPlayerName("Player1");
		player1T2.setPosition("goalie");
		player1T2.setPlayerStrength(7);
		IPlayer player2T2 = new PlayerModel();
		((FreeAgentModel) player2T2).setPlayerName("Player2");
		player2T2.setPosition("forward");
		player2T2.setPlayerStrength(10);
		List<IPlayer> playerDetails2 = new ArrayList<>();
		playerDetails2.add(player1T2);
		playerDetails2.add(player2T2);
		team2.setPlayerList(playerDetails2);
		
		ITeam team3 = new TeamModel();
		team3.setTeamName("Boston");
		IPlayer player1T3 = new PlayerModel();
		((FreeAgentModel) player1T3).setPlayerName("Player1");
		player1T3.setPosition("goalie");
		player1T3.setPlayerStrength(7);
		IPlayer player2T3 = new PlayerModel();
		((FreeAgentModel) player2T3).setPlayerName("Player2");
		player2T3.setPosition("forward");
		player2T3.setPlayerStrength(10);
		List<IPlayer> playerDetails3 = new ArrayList<>();
		playerDetails3.add(player1T2);
		playerDetails3.add(player2T2);
		team3.setPlayerList(playerDetails3);
		
		ITeam team4 = new TeamModel();
		team4.setTeamName("Rockers");
		team3.setTeamName("Boston");
		IPlayer player1T4 = new PlayerModel();
		((FreeAgentModel) player1T4).setPlayerName("Player1");
		player1T4.setPosition("goalie");
		player1T4.setPlayerStrength(7);
		IPlayer player2T4 = new PlayerModel();
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
		IStandingModel standing1 = new StandingModel();
		standing1.setConference(conference1);
		standing1.setDivision(division1);
		standing1.setTeam(team1);
		standing1.setTotalGamesPlayed(7);
		standing1.setTotalGamesWon(1);
		standing1.setTotalPoints(2);
		IStandingModel standing2 = new StandingModel();
		standing2.setConference(conference1);
		standing2.setDivision(division2);
		standing2.setTeam(team2);
		standing2.setTotalGamesPlayed(7);
		standing2.setTotalGamesWon(3);
		standing2.setTotalPoints(6);

		IStandingModel standing3 = new StandingModel();
		standing3.setConference(conference2);
		standing3.setDivision(division3);
		standing3.setTeam(team3);
		standing3.setTotalGamesPlayed(7);
		standing3.setTotalGamesWon(2);
		standing3.setTotalPoints(4);

		IStandingModel standing4 = new StandingModel();
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
		ILeague league = new LeagueModel();
		league.setLeagueName("DHL");
		IConference conference1 = new ConferenceModel();
		conference1.setConferenceName("Eastern");
		IConference conference2 = new ConferenceModel();
		conference2.setConferenceName("Northern");
		IDivision division1 = new DivisionModel();
		division1.setDivisionName("Atlantic");
		IDivision division2 = new DivisionModel();
		division1.setDivisionName("Pacific");
		IDivision division3 = new DivisionModel();
		division1.setDivisionName("Indian");
		IDivision division4 = new DivisionModel();
		division1.setDivisionName("Mediterranean");
		
		ITeam team1 = new TeamModel();
		team1.setTeamName("Tigers");
		IPlayer player1 = new PlayerModel();
		((FreeAgentModel) player1).setPlayerName("Player1");
		player1.setPosition("goalie");
		player1.setPlayerStrength(7);
		IPlayer player2 = new PlayerModel();
		((FreeAgentModel) player2).setPlayerName("Player2");
		player2.setPosition("forward");
		player2.setPlayerStrength(10);
		List<IPlayer> playerDetails1 = new ArrayList<>();
		playerDetails1.add(player1);
		playerDetails1.add(player2);
		team1.setPlayerList(playerDetails1);
		
		ITeam team2 = new TeamModel();
		team2.setTeamName("Thunders");
		IPlayer player1T2 = new PlayerModel();
		((FreeAgentModel) player1T2).setPlayerName("Player1");
		player1T2.setPosition("goalie");
		player1T2.setPlayerStrength(7);
		IPlayer player2T2 = new PlayerModel();
		((FreeAgentModel) player2T2).setPlayerName("Player2");
		player2T2.setPosition("forward");
		player2T2.setPlayerStrength(10);
		List<IPlayer> playerDetails2 = new ArrayList<>();
		playerDetails2.add(player1T2);
		playerDetails2.add(player2T2);
		team2.setPlayerList(playerDetails2);
		
		ITeam team3 = new TeamModel();
		team3.setTeamName("Boston");
		IPlayer player1T3 = new PlayerModel();
		((FreeAgentModel) player1T3).setPlayerName("Player1");
		player1T3.setPosition("goalie");
		player1T3.setPlayerStrength(7);
		IPlayer player2T3 = new PlayerModel();
		((FreeAgentModel) player2T3).setPlayerName("Player2");
		player2T3.setPosition("forward");
		player2T3.setPlayerStrength(10);
		List<IPlayer> playerDetails3 = new ArrayList<>();
		playerDetails3.add(player1T2);
		playerDetails3.add(player2T2);
		team3.setPlayerList(playerDetails3);
		
		ITeam team4 = new TeamModel();
		team4.setTeamName("Rockers");
		team3.setTeamName("Boston");
		IPlayer player1T4 = new PlayerModel();
		((FreeAgentModel) player1T4).setPlayerName("Player1");
		player1T4.setPosition("goalie");
		player1T4.setPlayerStrength(7);
		IPlayer player2T4 = new PlayerModel();
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
		conferenceDetails.add(conference1);
		conferenceDetails.add(conference2);
		divisionDetails1.add(division1);
		divisionDetails1.add(division2);
		divisionDetails2.add(division3);
		divisionDetails2.add(division4);
		conference1.setDivisionDetails(divisionDetails1);
		conference2.setDivisionDetails(divisionDetails2);
		league.setConferenceDetails(conferenceDetails);
		return league;
	}
	public Map<Integer,List<IStandingModel>> mockTradePick(List<IStandingModel> standing) {
		Map<Integer,List<IStandingModel>> afterTradePickOrder= new HashMap<>() ;
		List<IStandingModel> standings = standing;
		List<IStandingModel> Round1= new ArrayList<>();
		Round1.add(0, standings.get(3));
		Round1.add(0,standings.get(1));

		List<IStandingModel> Round2= new ArrayList<>();
		Round2.add(0, standings.get(1));
		List<IStandingModel> Round3= new ArrayList<>();
		Round3.add(standings.get(2));
		Round3.add(standings.get(3));
		List<IStandingModel> Round4= new ArrayList<>();
		Round4.add(standings.get(0));
		Round4.add(standings.get(1));
		List<IStandingModel> Round5= new ArrayList<>();

		Round5.add(standings.get(1));
		Round5.add(standings.get(3));
		List<IStandingModel> Round6= null;
		List<IStandingModel> Round7= Round1;
		afterTradePickOrder.put(1, Round1);
		afterTradePickOrder.put(2, Round2);
		afterTradePickOrder.put(3, Round3);
		afterTradePickOrder.put(4, Round4);
		afterTradePickOrder.put(5, Round5);
		afterTradePickOrder.put(6, Round6);
		afterTradePickOrder.put(7, Round7);
		for (int i = 1; i <=7; i++)
		{
			System.out.println();
			System.out.print("Round" +(i) + " Teams ");
			if(afterTradePickOrder.get(i) == null)
			{
				continue;
			}
			else
			{
			List<IStandingModel> a = afterTradePickOrder.get(i);
			for (IStandingModel standingTeam : a)
			{
				System.out.print("  " + standingTeam.getTeam().getTeamName());
			}
			
		}
		}
		System.out.println();
		return afterTradePickOrder;
	}
}
