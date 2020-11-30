package com.dhl.g05.simulation;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.ApplicationTestConfiguration;
import com.dhl.g05.model.ILeague;

public class PlayerDraftStateTest {
	private AbstractState state;
	private SimulationAbstractFactory simulationFactory;

	@Before
	public void init() {
		simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		state = simulationFactory.createPlayerDraftState();
		DateHandler.instance().performDateAssignment(2020);
	}
	
	@Test
	public void playerDraftStateTest() {
		SimulationMockAbstractFactory simulationMockFactory = ApplicationTestConfiguration.instance().getSimulationMockConcreteFactoryState();
		ILeague league = simulationMockFactory.createStandingMock().createDummyLeague();
		List<IStandingModel> standingList = simulationMockFactory.createStandingMock().createDummyStandings(league);
		ILeagueStanding leagueStanding = simulationFactory.createLeagueStanding();
		leagueStanding.setStandingList(standingList);
		league.setLeagueStanding(leagueStanding);
		league.setLeagueCurrentDate(LocalDate.of(2021, Month.JULY, 15));
		
		state.setLeague(league);
		state.enter();
		state.performStateTask();
		state.exit();
		assertTrue(state.getNextState() instanceof AdvanceToNextSeasonState);
	}
	
	
}
