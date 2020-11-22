package com.dhl.g05.player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.gameplayconfig.Aging;
import com.dhl.g05.gameplayconfig.IAging;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.team.TeamModel;

public class PlayerBirthdayTest {

	@Test
	public void decreaseStatOnBirthdayTest1() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getSaving(), 10 - 1, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest2() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getChecking(), 10 - 1, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest3() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getSkating(), 10 - 1, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest4() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(0).getShooting(), 10 - 1, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest5() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getSaving(), 15, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest6() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getShooting(), 15, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest7() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getChecking(), 15, 0);
	}

	@Test
	public void decreaseStatOnBirthdayTest8() {
		List<PlayerModel> playerDetails = callPlayerBirthday();
		assertEquals(playerDetails.get(1).getSkating(), 15, 0);
	}

	public List<PlayerModel> callPlayerBirthday() {
		IAging agingConfig = new Aging();
		agingConfig.setStatDecayChance(1);
		ILeague league = mockTOCheckBirthdate();
		ArrayList<PlayerModel> playerDetails = new ArrayList<>();
		IPlayerBirthday playerBirthday = new PlayerBirthday();
		playerBirthday.decreaseStatOnBirthday(league, agingConfig);
		for (ConferenceModel c : league.getConferenceDetails()) {
			for (DivisionModel d : c.getDivisionDetails()) {
				for (TeamModel t : d.getTeamDetails()) {
					for (PlayerModel p : t.getPlayerList()) {
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
		ConferenceModel conference = new ConferenceModel();
		ArrayList<ConferenceModel> conferenceDetails = new ArrayList<ConferenceModel>();
		ArrayList<DivisionModel> divisionDetails = new ArrayList<DivisionModel>();
		ArrayList<TeamModel> teamDetails = new ArrayList<TeamModel>();
		ArrayList<PlayerModel> playerDetails = new ArrayList<PlayerModel>();
		conferenceDetails.add(conference);
		leagueMock.setConferenceDetails(conferenceDetails);
		DivisionModel division = new DivisionModel();
		division.setDivisionName("Atlantic");
		divisionDetails.add(division);
		conference.setDivisionDetails(divisionDetails);
		TeamModel team = new TeamModel();
		team.setTeamName("Tigers");
		teamDetails.add(team);
		division.setTeamDetails(teamDetails);
		PlayerModel player1 = new PlayerModel();
		PlayerModel player2 = new PlayerModel();
		player1.setPlayerName("Player1");
		player1.setPosition("goalie");
		player1.setBirthDay(22);
		player1.setBirthMonth(11);
		player1.setBirthYear(2000);
		player1.setSkating(10);
		player1.setShooting(10);
		player1.setChecking(10);
		player1.setSaving(10);
		player2.setPlayerName("Player2");
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
