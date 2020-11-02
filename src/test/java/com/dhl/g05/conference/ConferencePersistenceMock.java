package com.dhl.g05.conference;

import java.util.ArrayList;

import com.dhl.g05.division.DivisionModel;

public class ConferencePersistenceMock implements IConferenceModelPersistence{

	@Override
	public int saveConferenceObject(int leagueId,ConferenceModel object) {
		if(leagueId==1 && object.getConferenceName().equals("Western Conference")) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int loadConferenceObject(int leagueId, ConferenceModel conferenceObject) {
		if(leagueId==1 && conferenceObject.getConferenceName().equals("Western Conference")) {
			ArrayList<DivisionModel> divisions = new ArrayList<>();
			divisions.add(new DivisionModel("Atlantic",null));
			conferenceObject.setDivisionDetails(divisions);
			return 1;
		}
		return 0;
	}
	
}
