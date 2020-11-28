package com.dhl.g05.model;

import java.util.List;

public interface IConference {

	void setConferenceName(String conference);

	List<IDivision> getDivisionDetails();

	void setDivisionDetails(List<IDivision> divisionDetails);

	String getConferenceName();

	ConferenceConstant validate();

}