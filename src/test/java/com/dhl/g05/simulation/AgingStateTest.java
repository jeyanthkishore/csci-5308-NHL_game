package com.dhl.g05.simulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ConferenceModel;
import com.dhl.g05.model.DivisionModel;
import com.dhl.g05.model.FreeAgentModel;
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.LeagueModel;
import com.dhl.g05.model.PlayerModel;
import com.dhl.g05.model.TeamModel;
import com.dhl.g05.simulation.AbstractState;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.IScheduleModel;
import com.dhl.g05.simulation.PersistState;
import com.dhl.g05.simulation.PlayerDraftState;
import com.dhl.g05.simulation.ScheduleModel;
import com.dhl.g05.simulation.StateMachineAbstractFactory;

public class AgingStateTest {
	private AbstractState state;
	
	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createAgingState();
	}
	
	@Test
	public void performTaskTest() {
		ILeague leagueMock = new LeagueModel();
		IConference conference = new ConferenceModel();
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails = new ArrayList<>();
		List<ITeam> teamDetails = new ArrayList<>();
		ArrayList<IPlayer> playerDetails = new ArrayList<>();
		ArrayList<IFreeAgent> freeAgentDetails = new ArrayList<>();
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
		leagueMock.setRetiredPlayersList(playerDetails);
		IFreeAgent freeAgent1 = new FreeAgentModel();
		IFreeAgent freeAgent2 = new FreeAgentModel();
		freeAgent1.setBirthDay(22);
		freeAgent1.setBirthMonth(11);
		freeAgent1.setBirthYear(2000);
		freeAgent2.setBirthDay(LocalDate.now().getDayOfMonth());
		freeAgent2.setBirthMonth(12);
		freeAgent2.setBirthYear(2000);
		freeAgentDetails.add(freeAgent1);
		freeAgentDetails.add(freeAgent2);
		leagueMock.setRetiredFreeAgentsList(freeAgentDetails);
		leagueMock.setFreeAgent(freeAgentDetails);
		
		
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
		ArrayList<IFreeAgent> freeAgentDetails = new ArrayList<>();
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
		leagueMock.setRetiredPlayersList(playerDetails);
		IFreeAgent freeAgent1 = new FreeAgentModel();
		IFreeAgent freeAgent2 = new FreeAgentModel();
		freeAgent1.setBirthDay(22);
		freeAgent1.setBirthMonth(11);
		freeAgent1.setBirthYear(2000);
		freeAgent2.setBirthDay(LocalDate.now().getDayOfMonth());
		freeAgent2.setBirthMonth(12);
		freeAgent2.setBirthYear(2000);
		freeAgentDetails.add(freeAgent1);
		freeAgentDetails.add(freeAgent2);
		leagueMock.setRetiredFreeAgentsList(freeAgentDetails);
		leagueMock.setFreeAgent(freeAgentDetails);
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(true);
		List<IScheduleModel> playoffSchedule = new ArrayList<>();
		playoffSchedule.add(schedule);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		leagueMock.setLeagueCurrentDate(LocalDate.of((Year.now().getValue()+1), Month.JULY, 15));
		leagueMock.getLeagueSchedule().setPlayoffSeasonSchedule(playoffSchedule);
		
		state.setLeague(leagueMock);
		state.enter();
		state.performStateTask();
		state.exit();
		assertEquals(20,player1.getAge());
		assertTrue(state.getNextState() instanceof PlayerDraftState);
	}
	
}