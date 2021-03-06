package com.dhl.g05.simulation.leaguesimulation;

import java.time.LocalDate;

import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ILeague;
import com.dhl.g05.model.ITeam;
import com.google.gson.annotations.Expose;

public class ScheduleModel implements IScheduleModel {
	
	@Expose
	private IConference firstConference;
	@Expose
	private IDivision firstDivision;
	@Expose
	private ITeam firstTeam;
	@Expose
	private IConference secondConference;
	@Expose
	private IDivision secondDivision;
	@Expose
	private ITeam secondTeam;
	@Expose
	private LocalDate scheduleDate;
	@Expose
	private boolean isGamePlayedCompleted;
	@Expose
	private ITeam winningTeam;

	@Override
	public IConference getFirstConference() {
		return firstConference;
	}
	
	@Override
	public void setFirstConference(IConference firstConference) {
		this.firstConference = firstConference;
	}
	
	@Override
	public IDivision getFirstDivision() {
		return firstDivision;
	}
	
	@Override
	public void setFirstDivision(IDivision firstDivision) {
		this.firstDivision = firstDivision;
	}
	
	@Override
	public ITeam getFirstTeam() {
		return firstTeam;
	}
	
	@Override
	public void setFirstTeam(ITeam firstTeam) {
		this.firstTeam = firstTeam;
	}
	
	@Override
	public IConference getSecondConference() {
		return secondConference;
	}
	
	@Override
	public void setSecondConference(IConference secondConference) {
		this.secondConference = secondConference;
	}
	
	@Override
	public IDivision getSecondDivision() {
		return secondDivision;
	}
	
	@Override
	public void setSecondDivision(IDivision secondDivision) {
		this.secondDivision = secondDivision;
	}
	
	@Override
	public ITeam getSecondTeam() {
		return secondTeam;
	}
	
	@Override
	public void setSecondTeam(ITeam secondTeam) {
		this.secondTeam = secondTeam;
	}
	
	@Override
	public LocalDate getScheduleDate() {
		return scheduleDate;
	}
	
	@Override
	public void setScheduleDate(LocalDate date) {
		this.scheduleDate = date;
	}
	
	@Override
	public ITeam getWinningTeam() {
		return winningTeam;
	}
	
	@Override
	public void setWinningTeam(ITeam winningTeam) {
		this.winningTeam = winningTeam;
	}

	@Override
	public boolean getIsGameCompleted() {
		return isGamePlayedCompleted;
	}
	
	@Override
	public void setIsGameCompleted(boolean gameCompleted) {
		this.isGamePlayedCompleted = gameCompleted;
	}
	
	@Override
	public void generateRegularSeason(ILeague league) {
		ILeagueSchedule leagueSchedule = league.getLeagueSchedule();
		leagueSchedule.generateRegularSeasonSchedule(league);
		leagueSchedule.addRegularSeasonDates();
	}
	
	@Override
	public void generatePlayOff(ILeague league,ILeagueStanding standingSystem) {
		ILeagueSchedule leagueSchedule = league.getLeagueSchedule();
		leagueSchedule.generatePlayoffSchedule(league,standingSystem);
		leagueSchedule.addPlayoffSeasonDates();
	}

}
