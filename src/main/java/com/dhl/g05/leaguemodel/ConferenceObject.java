package com.dhl.g05.leaguemodel;
import java.util.List;

import com.dhl.g05.operation.IDataBasePersistence;

public class ConferenceObject {
	
	private String conferenceName;
	private List<DivisionObject> divisionDetails;
	private String result;
	
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
		result = validate();
	}
	
	public int saveConferenceObject(int leagueId,IDataBasePersistence database) {
		return database.saveConferenceObject(leagueId,this);
	}
	public int loadConferenceObject(int leagueId,IDataBasePersistence database) {
		return database.loadConferenceObject(leagueId,this);
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	public String validate() {
		if(isNameEmptyOrNull()) {
			return "Conference Name Is Empty";
		}
		if(isDivisionListEmpty()) {
			return "Division List Is Empty";
		}
		if(!hasEvenNumberDivision()) {
			return "Division Count Must Be Even";
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
		if(divisionDetails == null || divisionDetails.isEmpty()) {
			return true;
		}
		return false;
	}
}
