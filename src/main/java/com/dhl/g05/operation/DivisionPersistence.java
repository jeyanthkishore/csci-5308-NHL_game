package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.division.DivisionModel;
import com.dhl.g05.leaguemodel.division.IDivisionModelPersistence;
import com.dhl.g05.leaguemodel.team.TeamModel;

public class DivisionPersistence implements IDivisionModelPersistence{

	private List<TeamModel> teamList = new ArrayList<TeamModel>();

	@Override
	public int saveDivisionObject(int conferenceId, DivisionModel divisionObject) {
		StoredProcedure sp= new StoredProcedure();
		String divisionName = divisionObject.getDivisionName();
		int divisionId = sp.saveDivision(divisionName,conferenceId);
		return divisionId;
	}

	@Override
	public int loadDivisionObject(int conferenceId, DivisionModel divisionObject) {
		String divisionName = divisionObject.getDivisionName();
		StoredProcedure sp= new StoredProcedure();
		String teamName,coachName,managerName;
		List<HashMap<String, Object>> teamValue = new ArrayList<HashMap<String,Object>>();
		
		int divisonId = sp.getDivisionID(divisionName, conferenceId);
		teamValue = sp.fetchAllTeams(divisonId);
		for(HashMap<String, Object> team : teamValue) {
			teamName = team.get("team_name").toString();
			coachName = team.get("coach_name").toString();
			managerName = team.get("manager_name").toString();
			double skating = Double.parseDouble(team.get("skating").toString());
			double shooting = Double.parseDouble(team.get("shooting").toString());
			double checking = Double.parseDouble(team.get("checking").toString());
			double saving = Double.parseDouble(team.get("saving").toString());
			teamList.add(new TeamModel(teamName,new CoachModel(coachName,skating,shooting,checking,saving),managerName,null));
		}
		divisionObject.setTeamDetails(teamList);
		return divisonId;
	}

}
