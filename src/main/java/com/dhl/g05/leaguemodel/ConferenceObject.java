package com.dhl.g05.leaguemodel;
import java.util.List;

import com.dhl.g05.operation.IDataBasePersistence;

public class ConferenceObject {
	
	private String conferenceName;
	private List<DivisionObject> divisions;
	
	public ConferenceObject() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	public ConferenceObject(ILeagueModel conferenceObject) {
		conferenceObject.loadConferenceModelData(this);
	}
	
	public ConferenceObject(String conference, List<DivisionObject> divisiondetail) {
		conferenceName = conference;
		divisions = divisiondetail;
	}
	
	public int saveConferenceObject(int leagueId,IDataBasePersistence database) {
		return database.saveConferenceObject(leagueId,this);
	}
	public int loadConferenceObject(int leagueId,IDataBasePersistence database) {
		return database.loadConferenceObject(leagueId,this);
	}
	
	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conference) {
		this.conferenceName = conference;
	}

	public List<DivisionObject> getDivisionDetails() {
		return divisions;
	}

	public void setDivisionDetails(List<DivisionObject> divisionDetails) {
		this.divisions = divisionDetails;
	}
	public ValidateEnumModel validate() {
		if(isNameEmptyOrNull()) {
			return ValidateEnumModel.ConferenceNameEmpty;
		}
		if(isDivisionListEmpty()) {
			return ValidateEnumModel.DivisionListEmpty;
		}
		if(!hasEvenNumberDivision()) {
			return ValidateEnumModel.NoEvenDivisionCount;
		}
		return ValidateEnumModel.Success;
	}
	public boolean hasEvenNumberDivision() {
		if(divisions.size()%2 == 0) {
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
		if(divisions == null || divisions.isEmpty()) {
			return true;
		}
		return false;
	}
}
