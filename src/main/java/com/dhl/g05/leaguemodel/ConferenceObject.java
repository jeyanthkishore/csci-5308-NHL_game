package com.dhl.g05.leaguemodel;
import java.util.List;

public class ConferenceObject {
	
	private String conferenceName;
	private List<DivisionObject> divisionDetails;
	
	public ConferenceObject() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	public ConferenceObject(ILeagueModel conferenceObject) {
		conferenceObject.loadConferenceModelData(this);
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
	
}
