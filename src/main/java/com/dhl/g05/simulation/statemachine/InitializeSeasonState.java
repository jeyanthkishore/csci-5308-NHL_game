package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;

public class InitializeSeasonState extends AbstractState{
	
	static final Logger logger = LogManager.getLogger(InitializeSeasonState.class);
	private LocalDate currentDate;
	private ILeague league;
	private static SimulationAbstractFactory simulationFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	@Override
	public boolean enter() {
		logger.info("Entering into InitializeSeasonState");
		
		league = this.getLeague();
		league.resetDaysSinceStatIncrease();
		currentDate = league.getLeagueCurrentDate();
		return true;
	}

	@Override
	public boolean performStateTask() {
		logger.info("Initializing parameters for season");
		
		int currentYear;
		if(currentDate == null) {
			currentYear =  Year.now().getValue();
			currentDate = LocalDate.of(currentYear, Month.SEPTEMBER, 30);
	        league.setLeagueCurrentDate(currentDate);
		}else {
			currentYear = league.getLeagueCurrentDate().getYear();
		}
		DateHandler.instance().performDateAssignment(currentYear);
		league.getLeagueStanding().createStandingList(league);
		IScheduleModel leagueSchedule = simulationFactory.createScheduleModel();
		leagueSchedule.generateRegularSeason(league);
		return true;
	}

	@Override
	public boolean exit() {
		logger.info("Exiting into InitializeSeasonState");
		
		this.setNextState(simulationFactory.createAdvancedTimeState());
		return true;
	}
	
	
}
