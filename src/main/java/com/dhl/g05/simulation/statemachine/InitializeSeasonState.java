package com.dhl.g05.simulation.statemachine;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.simulation.DateHandler;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.leaguesimulation.IScheduleModel;

public class InitializeSeasonState extends AbstractState{
	private LocalDate currentDate;
	private ILeague league;
	SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();

	@Override
	public boolean enter() {
		league = this.getLeague();
		league.resetDaysSinceStatIncrease();
		currentDate = league.getLeagueCurrentDate();
		return true;
	}

	@Override
	public boolean performStateTask() {
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
		IScheduleModel leagueSchedule = stateFactory.createScheduleModel();
		leagueSchedule.generateRegularSeason(league);
		return true;
	}

	@Override
	public boolean exit() {
		SimulationAbstractFactory stateFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
		this.setNextState(stateFactory.createAdvancedTimeState());
		return true;
	}
	
	
}
