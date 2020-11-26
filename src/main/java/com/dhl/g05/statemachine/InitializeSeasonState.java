package com.dhl.g05.statemachine;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.leaguesimulation.DateHandler;
import com.dhl.g05.leaguesimulation.IInitializeSchedule;
import com.dhl.g05.leaguesimulation.ScheduleModel;

public class InitializeSeasonState extends AbstractState{
	private IPlayerCommunication communication;
	private LocalDate currentDate;
	private ILeague league;

	public InitializeSeasonState(IPlayerCommunication communicate) {
		this.communication = communicate;
	}

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
		DateHandler dateObject  = DateHandler.getInstance();
		dateObject.performDateAssignment(currentYear);
		league.getLeagueStanding().createStandingList(league);
		IInitializeSchedule leagueSchedule = new ScheduleModel();
		leagueSchedule.generateRegularSeason(league);
		return true;
	}

	@Override
	public boolean exit() {
		StateMachineAbstractFactory stateFactory = ApplicationConfiguration.instance().getStateMachineFactoryState();
		this.setNextState(stateFactory.getAdvancedTimeState());
		return true;
	}
	
	
}
