package com.dhl.g05.conference;

import java.util.List;

import com.dhl.g05.division.IDivision;

public interface IConference {

	void setConferenceName(String conference);

	List<IDivision> getDivisionDetails();

	void setDivisionDetails(List<IDivision> divisionDetails);

	String getConferenceName();

	ConferenceConstant validate();

	int saveConferenceObject(int leagueId, IConferenceModelPersistence conferenceDatabase);

	int loadConferenceObject(int leagueId, IConferenceModelPersistence conferenceDatabase);

}