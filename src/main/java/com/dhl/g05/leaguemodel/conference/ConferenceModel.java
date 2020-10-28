package com.dhl.g05.leaguemodel.conference;
import java.util.List;

import com.dhl.g05.leaguemodel.ILeagueModelComplete;
import com.dhl.g05.leaguemodel.ValidateEnumModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;

public class ConferenceModel {
	
	private String conferenceName;
	private List<DivisionModel> divisions;
	
	public ConferenceModel() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	public ConferenceModel(ILeagueModelComplete conferenceObject) {
		conferenceObject.loadConferenceModelData(this);
	}
	
	public ConferenceModel(String conference, List<DivisionModel> divisiondetail) {
		conferenceName = conference;
		divisions = divisiondetail;
	}
	
	public int saveConferenceObject(int leagueId,IConferenceModelPersistence database) {
		return database.saveConferenceObject(leagueId,this);
	}
	public int loadConferenceObject(int leagueId,IConferenceModelPersistence database) {
		return database.loadConferenceObject(leagueId,this);
	}
	
	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conference) {
		this.conferenceName = conference;
	}

	public List<DivisionModel> getDivisionDetails() {
		return divisions;
	}

	public void setDivisionDetails(List<DivisionModel> divisionDetails) {
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
