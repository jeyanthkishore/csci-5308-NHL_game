package com.dhl.g05.leaguemodel.conference;
import java.util.List;

import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.mysql.cj.util.StringUtils;

public class ConferenceModel {

	private String conferenceName;
	private List<DivisionModel> divisions;

	public ConferenceModel() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	public ConferenceModel(IConferenceModel conferenceObject) {
		conferenceObject.loadConferenceModelData(this);
	}

	public ConferenceModel(String conference, List<DivisionModel> divisiondetail) {
		conferenceName = conference;
		divisions = divisiondetail;
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

	public int saveConferenceObject(int leagueId,IConferenceModelPersistence database) {
		return database.saveConferenceObject(leagueId,this);
	}
	public int loadConferenceObject(int leagueId,IConferenceModelPersistence database) {
		return database.loadConferenceObject(leagueId,this);
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public ConferenceConstant validate() {
		if(isNameEmptyOrNull()) {
			return ConferenceConstant.ConferenceNameEmpty;
		}
		if(isDivisionListEmpty()) {
			return ConferenceConstant.DivisionListEmpty;
		}
		if(hasOddNumberDivision()) {
			return ConferenceConstant.NoEvenDivisionCount;
		}
		return ConferenceConstant.Success;
	}

	private boolean hasOddNumberDivision() {
		if(divisions.size()%2 == 1) {
			return true;	
		}
		return false;
	}

	private boolean isNameEmptyOrNull() {
		return StringUtils.isNullOrEmpty(conferenceName);
	}

	private boolean isDivisionListEmpty() {
		if(divisions == null || divisions.isEmpty()) {
			return true;
		}
		return false;
	}
}
