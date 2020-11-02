package com.dhl.g05.division;

import java.util.ArrayList;

import com.dhl.g05.team.TeamModel;

public class DivisionPersistenceMock implements IDivisionModelPersistence{

	@Override
	public int saveDivisionObject(int conferenceId,DivisionModel object) {
		if(conferenceId==1 && object.getDivisionName().equals("Atlantic")) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int loadDivisionObject(int conferenceId, DivisionModel divisionObject) {
		if(conferenceId==1 && divisionObject.getDivisionName().equals("Atlantic")) {
			ArrayList<TeamModel> teams = new ArrayList<>();
			teams.add(new TeamModel("Striker Six",null,null,null));
			divisionObject.setTeamDetails(teams);
			return 1;
		}
		return 0;
	}
	
}
