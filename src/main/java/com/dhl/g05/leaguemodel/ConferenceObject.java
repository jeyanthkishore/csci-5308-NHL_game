package com.dhl.g05.leaguemodel;
import java.util.List;

public class ConferenceObject {
	
	private String conferenceName;
	private List<DivisionObject> divisionDetails;
	
	public ConferenceObject() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	public ConferenceObject(ILeagueValidation conferenceObject) {
		conferenceObject.loadConferenceData(this);
	}
	
	public ConferenceObject(String conference, List<DivisionObject> divisiondetail) {
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
