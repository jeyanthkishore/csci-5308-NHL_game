package com.dhl.g05.simulation.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.IPlayer;
import com.dhl.g05.model.ITeam;
import com.dhl.g05.model.ModelAbstractFactory;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;
import com.dhl.g05.simulation.leaguesimulation.ScheduleModel;

public class InjuryCheckTest {
	private static ITeam teamOne;
	private static ITeam teamTwo;
	private static ModelAbstractFactory modelFactory;
	private static SimulationAbstractFactory simulationfactory;

	@BeforeClass
	public static void init() {
		DateHandler.instance().performDateAssignment(Year.now().getValue());
		simulationfactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		modelFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
		teamOne = modelFactory.createTeamModel();
		teamTwo = modelFactory.createTeamModel();
		IPlayer playerOne = modelFactory.createPlayerModel();
		playerOne.setInjuryStatus(true);
		IPlayer playerTwo = modelFactory.createPlayerModel();
		playerTwo.setInjuryStatus(false);
		List<IPlayer> playerList = new ArrayList<>();
		playerList.add(playerOne);
		playerList.add(playerTwo);
		teamOne.setPlayerList(playerList);
		teamTwo.setPlayerList(playerList);
	}
	
	
	private ILeague createInjuryCheckLeague() {
		
		ILeague league = modelFactory.createLeagueModel();
		
		IInjury injuryConfig = simulationfactory.createInjuryConfig();
		injuryConfig.setInjuryDaysHigh(100);
		injuryConfig.setInjuryDaysLow(1);
		injuryConfig.setRandomInjuryChance(0.2);
		IGamePlayConfig gamePlayConfig = simulationfactory.createGamePlayConfig();
		gamePlayConfig.setInjuriesConfig(injuryConfig);
		league.setGamePlayConfig(gamePlayConfig);
		return league;
	}
	
	@Test
	public void injuryStateNextTradeStateTest() {
		ILeague league = createInjuryCheckLeague();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue(), Month.AUGUST, 30));
		
		AbstractState state = simulationfactory.createInjuryCheckState(teamOne, teamTwo);
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof TradeState);
	}
	
	@Test
	public void injuryStateNextAgingStateTest() {
		ILeague league = createInjuryCheckLeague();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		league.setDaysSinceStatIncrease(140);
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(true);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		
		AbstractState state = simulationfactory.createInjuryCheckState(teamOne, teamTwo);
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AgingState);
	}
	
	@Test
	public void injuryStateNextGameStateTest() {
		ILeague league = createInjuryCheckLeague();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		league.setDaysSinceStatIncrease(140);
		IScheduleModel schedule = new ScheduleModel();
		schedule.setIsGameCompleted(false);
		schedule.setScheduleDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 30));
		List<IScheduleModel> scheduleList = new ArrayList<>();
		scheduleList.add(schedule);
		league.getLeagueSchedule().setPlayoffSeasonSchedule(scheduleList);
		
		AbstractState state = simulationfactory.createInjuryCheckState(teamOne, teamTwo);
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof SimulateGameState);
	}
	
	
}
