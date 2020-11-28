package com.dhl.g05.statemachine;

import java.time.LocalDate;

import com.dhl.g05.model.IConference;
import com.dhl.g05.model.IDivision;
import com.dhl.g05.model.ITeam;

public interface IScheduleModel {

	IConference getFirstConference();

	void setFirstConference(IConference firstConference);

	IDivision getFirstDivision();

	void setFirstDivision(IDivision firstDivision);

	ITeam getFirstTeam();

	void setFirstTeam(ITeam Team);

	IConference getSecondConference();

	void setSecondConference(IConference secondConference);

	IDivision getSecondDivision();

	void setSecondDivision(IDivision secondDivision);

	ITeam getSecondTeam();

	void setSecondTeam(ITeam secondTeam);

	LocalDate getScheduleDate();

	void setScheduleDate(LocalDate date);

	ITeam getWinningTeam();

	void setWinningTeam(ITeam winningTeam);

	boolean getIsGameCompleted();

	void setIsGameCompleted(boolean isGameCompleted);

}