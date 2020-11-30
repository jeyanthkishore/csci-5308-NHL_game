package com.dhl.g05.simulation.statemachine;

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
import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.IFreeAgent;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.statemachine.AbstractState;
import com.dhl.g05.simulation.statemachine.PersistState;
import com.dhl.g05.simulation.statemachine.PlayerDraftState;

public class AgingStateTest {
	private AbstractState state;
	private IPlayer player1;
	private ModelAbstractFactory modelFactory;
	private SimulationAbstractFactory simulationFactory;
	private final int playerAge = 2000;
	
	@Before
	public void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		player1 = modelFactory.createPlayerModel();
	}
	
	
	private ILeague agingMockLeague() {
		ILeague leagueMock = modelFactory.createLeagueModel();
		IConference conference = modelFactory.createConferenceModel();
		List<IConference> conferenceDetails = new ArrayList<>();
		List<IDivision> divisionDetails = new ArrayList<>();
		List<ITeam> teamDetails = new ArrayList<>();
		ArrayList<IPlayer> playerDetails = new ArrayList<>();
		ArrayList<IFreeAgent> freeAgentDetails = new ArrayList<>();
		conferenceDetails.add(conference);
		leagueMock.setConferenceDetails(conferenceDetails);
		IDivision division = modelFactory.createDivisionModel();
		divisionDetails.add(division);
		conference.setDivisionDetails(divisionDetails);
		ITeam team = modelFactory.createTeamModel();
		teamDetails.add(team);
		division.setTeamDetails(teamDetails);
		IPlayer player2 = modelFactory.createPlayerModel();
		player1.setBirthDay(10);
		player1.setBirthMonth(04);
		player1.setBirthYear(playerAge);
		player2.setBirthDay(LocalDate.now().getDayOfMonth());
		player2.setBirthMonth(12);
		player2.setBirthYear(2000);
		playerDetails.add(player1);
		playerDetails.add(player2);
		team.setPlayerList(playerDetails);
		leagueMock.setRetiredPlayersList(playerDetails);
		IFreeAgent freeAgent1 = modelFactory.createFreeAgentModel();
		IFreeAgent freeAgent2 = modelFactory.createFreeAgentModel();
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
		IScheduleModel schedule = simulationFactory.createScheduleModel();
		schedule.setIsGameCompleted(true);
		List<IScheduleModel> playoffSchedule = new ArrayList<>();
		playoffSchedule.add(schedule);
		DateHandler.instance().performDateAssignment(Year.now().getValue());
		leagueMock.getLeagueSchedule().setPlayoffSeasonSchedule(playoffSchedule);
		
		return leagueMock;
	}
	
	@Test
	public void performTaskPersistStateTest() {
		state = simulationFactory.createAgingState();
		ILeague leagueMock = agingMockLeague();
		leagueMock.setLeagueCurrentDate(LocalDate.of((Year.now().getValue()), Month.JULY, 15));
		state.setLeague(leagueMock);
		state.enter();
		state.performStateTask();
		state.exit();
		assertEquals((Year.now().getValue())-playerAge,player1.getAge());
		assertTrue(state.getNextState() instanceof PersistState);
	}
	
	@Test
	public void advanceToNextSeasonTest() {
		state = simulationFactory.createAgingState();
		ILeague leagueMock = agingMockLeague();
		leagueMock.setLeagueCurrentDate(LocalDate.of((Year.now().getValue()+1), Month.JULY, 15));
		state = simulationFactory.createAgingState();
		state.setLeague(leagueMock);
		state.enter();
		state.performStateTask();
		state.exit();
		assertEquals((Year.now().getValue()+1)-playerAge,player1.getAge());
		assertTrue(state.getNextState() instanceof PlayerDraftState);
	}
	
}