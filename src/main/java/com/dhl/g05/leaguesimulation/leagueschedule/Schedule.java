package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.league.ILeague;
import com.dhl.g05.league.LeagueModel;
import com.dhl.g05.leaguesimulation.leaguestanding.ILeagueStanding;
import com.dhl.g05.team.ITeam;

public class Schedule implements ISchedule, IInitializeSchedule {

	private IConference firstConference;
	private IDivision firstDivision;
	private ITeam firstTeam;
	private IConference secondConference;
	private IDivision secondDivision;
	private ITeam secondTeam;
	private LocalDate date;
	private boolean isGamePlayed;
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
	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
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
	public boolean getIsGamePlayed() {
		return isGamePlayed;
	}
	
	@Override
	public void setIsGamePlayed(boolean isGamePlayed) {
		this.isGamePlayed = isGamePlayed;
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
