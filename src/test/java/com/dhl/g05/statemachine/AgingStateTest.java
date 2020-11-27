package com.dhl.g05.statemachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.IScheduleModel;
import com.dhl.g05.leaguesimulation.ScheduleModel;
import com.dhl.g05.player.IPlayer;
import com.dhl.g05.player.PlayerModel;
import com.dhl.g05.team.ITeam;
import com.dhl.g05.team.TeamModel;

public class AgingStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		state = stateFactory.getAgingState();
	}
	
	@Test
	public void performTaskTest() {
		ILeague leagueMock = new LeagueModel();
		IConference conference = new ConferenceModel();
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails = new ArrayList<>();
		List<ITeam> teamDetails = new ArrayList<>();
		ArrayList<IPlayer> playerDetails = new ArrayList<>();
		conferenceDetails.add(conference);
		leagueMock.setConferenceDetails(conferenceDetails);
		IDivision division = new DivisionModel();
		divisionDetails.add(division);
		conference.setDivisionDetails(divisionDetails);
		ITeam team = new TeamModel();
		teamDetails.add(team);
		division.setTeamDetails(teamDetails);
		IPlayer player1 = new PlayerModel();
		IPlayer player2 = new PlayerModel();
		player1.setBirthDay(22);
		player1.setBirthMonth(11);
		player1.setBirthYear(2000);
		player2.setBirthDay(LocalDate.now().getDayOfMonth());
		player2.setBirthMonth(12);
		player2.setBirthYear(2000);
		playerDetails.add(player1);
		playerDetails.add(player2);
		team.setPlayerList(playerDetails);
		
		state.setLeague(leagueMock);
		state.enter();
		state.performStateTask();
		state.exit();
		assertEquals(20,player1.getAge());
		assertTrue(state.getNextState() instanceof PersistState);
	}
	
	@Test
	public void advanceToNextSeasonTest() {
		ILeague leagueMock = new LeagueModel();
		IConference conference = new ConferenceModel();
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails = new ArrayList<>();
		List<ITeam> teamDetails = new ArrayList<>();
		ArrayList<IPlayer> playerDetails = new ArrayList<>();
		conferenceDetails.add(conference);
		leagueMock.setConferenceDetails(conferenceDetails);
		IDivision division = new DivisionModel();
		divisionDetails.add(division);
		conference.setDivisionDetails(divisionDetails);
		ITeam team = new TeamModel();
		teamDetails.add(team);
		division.setTeamDetails(teamDetails);
		IPlayer player1 = new PlayerModel();
		IPlayer player2 = new PlayerModel();
		player1.setBirthDay(22);
		player1.setBirthMonth(11);
		player1.setBirthYear(2000);
		player2.setBirthDay(LocalDate.now().getDayOfMonth());
		player2.setBirthMonth(12);
		player2.setBirthYear(2000);
		playerDetails.add(player1);
		playerDetails.add(player2);
		team.setPlayerList(playerDetails);
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(true);
		List<IScheduleModel> playoffSchedule = new ArrayList<>();
		playoffSchedule.add(schedule);
		
		leagueMock.getLeagueSchedule().setPlayoffSeasonSchedule(playoffSchedule);
		
		state.setLeague(leagueMock);
		state.enter();
		state.performStateTask();
		state.exit();
		assertEquals(20,player1.getAge());
		assertTrue(state.getNextState() instanceof AdvanceToNextSeasonState);
	}
	
}