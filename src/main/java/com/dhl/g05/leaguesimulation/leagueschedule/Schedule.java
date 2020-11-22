package com.dhl.g05.leaguesimulation.leagueschedule;

import java.time.LocalDate;

import com.dhl.g05.conference.ConferenceModel;
import com.dhl.g05.division.DivisionModel;
import com.dhl.g05.team.TeamModel;

public class Schedule implements ISchedule {

	private ConferenceModel firstConference;
	private DivisionModel firstDivision;
	private TeamModel firstTeam;
	private ConferenceModel secondConference;
	private DivisionModel secondDivision;
	private TeamModel secondTeam;
	private LocalDate date;
	private boolean isGamePlayed;
	private TeamModel winningTeam;

	@Override
	public ConferenceModel getFirstConference() {
		return firstConference;
	}
	
	@Override
	public void setFirstConference(ConferenceModel firstConference) {
		this.firstConference = firstConference;
	}
	
	@Override
	public DivisionModel getFirstDivision() {
		return firstDivision;
	}
	
	@Override
	public void setFirstDivision(DivisionModel firstDivision) {
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
	public ConferenceModel getSecondConference() {
		return secondConference;
	}
	
	@Override
	public void setSecondConference(ConferenceModel secondConference) {
		this.secondConference = secondConference;
	}
	
	@Override
	public DivisionModel getSecondDivision() {
		return secondDivision;
	}
	
	@Override
	public void setSecondDivision(DivisionModel secondDivision) {
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
