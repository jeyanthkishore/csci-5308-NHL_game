package com.dhl.g05.model;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.annotations.Expose;
import com.mysql.cj.util.StringUtils;

public class ConferenceModel implements IConference {

	static final Logger logger = LogManager.getLogger(ConferenceModel.class);
	@Expose
	private String conferenceName;
	@Expose
	private List<IDivision> divisions;
	
	public ConferenceModel() {
		setConferenceName(null);
		setDivisionDetails(null);
	}
	
	@Override
	public void setConferenceName(String conference) {
		this.conferenceName = conference;
	}
	
	@Override
	public String getConferenceName() {
		return conferenceName;
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
	public ConferenceConstant validate() {
		logger.info("Validating Conference Details");
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
