package com.dhl.g05.conference;
import java.util.List;

import com.dhl.g05.division.IDivision;
import com.mysql.cj.util.StringUtils;

public class ConferenceModel implements IConference {

	private String conferenceName;
	private List<IDivision> divisions;

	public ConferenceModel() {
		setDivisionDetails(null);
		setConferenceName(null);
	}
	
	public ConferenceModel(IConferenceModel conferenceObject) {
		conferenceObject.loadConferenceModelData(this);
	}

	public ConferenceModel(String conference, List<IDivision> divisiondetail) {
		conferenceName = conference;
		divisions = divisiondetail;
	}

	@Override
	public void setConferenceName(String conference) {
		this.conferenceName = conference;
	}

	@Override
	public List<IDivision> getDivisionDetails() {
		return divisions;
	}

	@Override
	public void setDivisionDetails(List<IDivision> divisionDetails) {
		this.divisions = divisionDetails;
	}

	@Override
	public String getConferenceName() {
		return conferenceName;
	}

	@Override
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
