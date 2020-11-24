package com.dhl.g05.leaguesimulation;

import java.time.LocalDate;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.team.ITeam;

public class ScheduleModel implements IScheduleModel, IInitializeSchedule {

	private IConference firstConference;
	private IDivision firstDivision;
	private ITeam firstTeam;
	private IConference secondConference;
	private IDivision secondDivision;
	private ITeam secondTeam;
	private LocalDate scheduleDate;
	private boolean isGamePlayedCompleted;
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
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		leagueSchedule.generateRegularSeasonSchedule(league);
		leagueSchedule.addRegularSeasonDates();
	}
	
	@Override
	public void generatePlayOff(ILeague league,ILeagueStanding standingSystem) {
		ILeagueSchedule leagueSchedule = new LeagueSchedule();
		leagueSchedule.generatePlayoffSchedule(league,standingSystem);
		leagueSchedule.addPlayoffSeasonDates();
	}

}
