package com.dhl.g05.simulation.statemachine;

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

public class AdvanceToNextSeasonStateTest {
	
	private AbstractState state;
	private static SimulationAbstractFactory simulationAbstractFactory;

	@Before
	public void init() {
		simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = simulationAbstractFactory.createAdvanceToNextSeasonState();
		DateHandler.instance().performDateAssignment(Year.now().getValue());
	}
	
	@Test
	public void performTaskTest() {
		ModelAbstractFactory modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
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
		IPlayer player1 = modelFactory.createPlayerModel();
		IPlayer player2 = modelFactory.createPlayerModel();
		player1.setBirthDay(10);
		player1.setBirthMonth(04);
		player1.setBirthYear(2000);
		player1.setAge(22);
		player2.setBirthDay(LocalDate.now().getDayOfMonth());
		player2.setBirthMonth(12);
		player2.setBirthYear(2000);
		player1.setAge(25);
		playerDetails.add(player1);
		playerDetails.add(player2);
		team.setPlayerList(playerDetails);
		leagueMock.setRetiredPlayersList(playerDetails);
		IFreeAgent freeAgent1 = modelFactory.createFreeAgentModel();
		IFreeAgent freeAgent2 = modelFactory.createFreeAgentModel();
		freeAgent1.setBirthDay(22);
		freeAgent1.setBirthMonth(11);
		freeAgent1.setBirthYear(2000);
		freeAgent1.setAge(25);
		freeAgent2.setBirthDay(LocalDate.now().getDayOfMonth());
		freeAgent2.setBirthMonth(12);
		freeAgent2.setAge(25);
		freeAgent2.setBirthYear(2000);
		freeAgentDetails.add(freeAgent1);
		freeAgentDetails.add(freeAgent2);
		leagueMock.setRetiredFreeAgentsList(freeAgentDetails);
		leagueMock.setFreeAgent(freeAgentDetails); 
		
		ITeam winningTteam = modelFactory.createTeamModel();
		List<IScheduleModel> scheduleList = new ArrayList<>();
		IScheduleModel schedule = simulationAbstractFactory.createScheduleModel();
		schedule.setIsGameCompleted(true);
		schedule.setWinningTeam(winningTteam);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		scheduleList.add(schedule);
		
		IAging aging = simulationAbstractFactory.createAgingConfig();
		aging.setAverageRetirementAge(40);
		aging.setMaximumAge(50);
		aging.setStatDecayChance(0.02);
		IGamePlayConfig gamePlayConfig = simulationAbstractFactory.createGamePlayConfig();
		gamePlayConfig.setAgingConfig(aging);
		leagueMock.setGamePlayConfig(gamePlayConfig);
		
		leagueMock.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		state.setLeague(leagueMock);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof PersistState);
	}

}
