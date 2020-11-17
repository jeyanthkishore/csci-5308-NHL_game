package com.dhl.g05.statemachine;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import com.dhl.g05.communication.IPlayerCommunication;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.DateHandler;

public class InitializeSeasonState extends AbstractState{
	private IPlayerCommunication communication;
	private LocalDate currentDate;
	private LeagueModel league;

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
		return true;
	}

	@Override
	public boolean exit() {
		return true;
	}
	
	
}
