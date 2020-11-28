package com.dhl.g05.statemachine;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.LeagueModel;

public class AdvanceTimeStateTest {
	private AbstractState state;

	@Before
	public void init() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineConcreteFactoryState();
		state = stateFactory.createAdvancedTimeState();
	}
	
	@Test
	public void performTaskTrainingTest() {
		LeagueModel league = new LeagueModel();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue(), Month.SEPTEMBER, 30));
		state.setLeague(league);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof TrainingState);
	}
	
	@Test
	public void performTaskPlayoffTest() {
		LeagueModel league = new LeagueModel();
		league.setLeagueCurrentDate(LocalDate.of(Year.now().getValue()+1, Month.APRIL, 02));
		state.setLeague(league);
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(Year.now().getValue());
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof PlayoffScheduleState);
	}
	
}
