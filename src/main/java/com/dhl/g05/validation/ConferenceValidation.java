package com.dhl.g05.validation;

import java.util.List;

import com.dhl.g05.leaguemodel.DivisionObject;

public class ConferenceValidation {

	private String conferenceName;
	private List<DivisionObject> divisionDetails;
	
	public ConferenceValidation() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	public ConferenceValidation(ILeagueModelValidation conferenceObject) {
		conferenceObject.loadConferenceData(this);
	}
	
	public ConferenceValidation(String conference, List<DivisionObject> divisiondetail) {
		conferenceName = conference;
		divisionDetails = divisiondetail;
	}
	
	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conference) {
		this.conferenceName = conference;
	}

	public List<DivisionObject> getDivisionDetails() {
		return divisionDetails;
	}

	public void setDivisionDetails(List<DivisionObject> divisionDetails) {
		this.divisionDetails = divisionDetails;
	}
	public String validateConference() {
		if(isNameEmptyOrNull()) {
			return "Conference name is Empty";
		}
		if(isDivisionListEmpty()) {
			return "Division List is Empty";
		}
		if(!hasEvenNumberDivision()) {
			return "Division must be even";
		}
		return "success";
	}
	
	public boolean hasEvenNumberDivision() {
		if(divisionDetails.size()%2 == 0) {
			return true;	
		}
		return false;
	}
	
	public boolean isNameEmptyOrNull() {
		if(conferenceName == "" || conferenceName == null) {
			return true;
		}
		return false;
	}
	
	public boolean isDivisionListEmpty() {
		return divisionDetails.isEmpty();
	}
	

}
