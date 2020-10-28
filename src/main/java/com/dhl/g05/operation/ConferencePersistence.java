package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.conference.ConferenceModel;
import com.dhl.g05.leaguemodel.conference.IConferenceModelPersistence;
import com.dhl.g05.leaguemodel.division.DivisionModel;

public class ConferencePersistence implements IConferenceModelPersistence{

	private List<DivisionModel> divisionList = new ArrayList<DivisionModel>();

	@Override
	public int saveConferenceObject(int leagueId, ConferenceModel conferenceObject) {
		StoredProcedure sp= new StoredProcedure();
		String conferenceName = conferenceObject.getConferenceName();
		int conferenceId = sp.saveConference(leagueId,conferenceName) ;
		return conferenceId;
	}

	@Override
	public int loadConferenceObject(int leagueId, ConferenceModel conferenceObject) {
		String conferenceName = conferenceObject.getConferenceName();
		List<HashMap<String, Object>> divisionValue = new ArrayList<HashMap<String,Object>>();
		
		StoredProcedure sp= new StoredProcedure();
		int conferenceId = sp.getConferenceID(conferenceName, leagueId);
		divisionValue = sp.fetchAllDivisions(conferenceId);
		for(HashMap<String, Object> div : divisionValue) {
			String divisionName = div.get("division_name").toString();
			divisionList.add(new DivisionModel(divisionName,null));
		}
		conferenceObject.setDivisionDetails(divisionList);
		return conferenceId;
	}

}
