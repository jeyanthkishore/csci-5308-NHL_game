package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;

import com.dhl.g05.conference.IConference;
import com.dhl.g05.division.IDivision;
import com.dhl.g05.team.TeamModel;

public class Schedule implements ISchedule {

	private IConference firstConference;
	private IDivision firstDivision;
	private TeamModel firstTeam;
	private IConference secondConference;
	private IDivision secondDivision;
	private TeamModel secondTeam;
	private LocalDate date;
	private boolean isGamePlayed;
	private TeamModel winningTeam;

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
	public TeamModel getFirstTeam() {
		return firstTeam;
	}
	
	@Override
	public void setFirstTeam(TeamModel firstTeam) {
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
	public TeamModel getSecondTeam() {
		return secondTeam;
	}
	
	@Override
	public void setSecondTeam(TeamModel secondTeam) {
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
	public TeamModel getWinningTeam() {
		return winningTeam;
	}
	
	@Override
	public void setWinningTeam(TeamModel winningTeam) {
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

}
