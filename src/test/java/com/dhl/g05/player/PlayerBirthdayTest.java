package com.dhl.g05.player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.freeagent.FreeAgentModel;
import com.dhl.g05.gameplayconfig.AbstractGamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.GamePlayConfigFactory;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class PlayerBirthdayTest {
	private static IPlayerBirthday playerBirthday;
	private static IAging aging;
	private static AbstractPlayerFactory playerFactory;
	private static AbstractGamePlayConfigFactory gameConfig;

	@BeforeClass
	public static void setup() {
		AbstractPlayerFactory.setFactory(new PlayerFactory());
		playerFactory = AbstractPlayerFactory.getFactory();
		AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
		gameConfig = AbstractGamePlayConfigFactory.getFactory();
	}

	@Test
	public void decreaseStatOnBirthdayTest1() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getSaving(), 10, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest2() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getChecking(), 10, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest3() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getSkating(), 10, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest4() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getShooting(), 10, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest5() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getSaving(), 15, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest6() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getShooting(), 15, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest7() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getChecking(), 15, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest8() {
		List<IPlayer> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getSkating(), 15, 0);
	}

	public ArrayList<IPlayer> callPlayerBirthday() {
		aging = gameConfig.getAging();
		aging.setStatDecayChance(1);
		ILeague league = mockTOCheckBirthdate();
		ArrayList<IPlayer> playerDetails = new ArrayList<>();
		playerBirthday = playerFactory.getPlayerBirthday();
		playerBirthday.decreaseStatOnBirthday(league, aging);
		for (IConference c : league.getConferenceDetails()) {
			for (IDivision d : c.getDivisionDetails()) {
				for (ITeam t : d.getTeamDetails()) {
					for (IPlayer p : t.getPlayerList()) {
						playerDetails.add(p);

					}
				}
			}
		}
		return playerDetails;
	}

	public LeagueModel mockTOCheckBirthdate() {
		LeagueModel leagueMock = new LeagueModel();
		leagueMock.setLeagueName("DHL");
		IConference conference = new ConferenceModel();
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails = new ArrayList<>();
		List<ITeam> teamDetails = new ArrayList<>();
		ArrayList<IPlayer> playerDetails = new ArrayList<>();
		conferenceDetails.add(conference);
		leagueMock.setConferenceDetails(conferenceDetails);
		IDivision division = new DivisionModel();
		division.setDivisionName("Atlantic");
		divisionDetails.add(division);
		conference.setDivisionDetails(divisionDetails);
		ITeam team = new TeamModel();
		team.setTeamName("Tigers");
		teamDetails.add(team);
		division.setTeamDetails(teamDetails);
		IPlayer player1 = new PlayerModel();
		IPlayer player2 = new PlayerModel();
		((FreeAgentModel) player1).setPlayerName("Player1");
		player1.setPosition("goalie");
		player1.setBirthDay(22);
		player1.setBirthMonth(11);
		player1.setBirthYear(2000);
		player1.setSkating(10);
		player1.setShooting(10);
		player1.setChecking(10);
		player1.setSaving(10);
		((FreeAgentModel) player2).setPlayerName("Player2");
		player2.setPosition("goalie");
		player2.setBirthDay(22);
		player2.setBirthMonth(12);
		player2.setBirthYear(2000);
		player2.setSkating(15);
		player2.setShooting(15);
		player2.setChecking(15);
		player2.setSaving(15);
		playerDetails.add(player1);
		playerDetails.add(player2);
		team.setPlayerList(playerDetails);
		return leagueMock;
	}

}
