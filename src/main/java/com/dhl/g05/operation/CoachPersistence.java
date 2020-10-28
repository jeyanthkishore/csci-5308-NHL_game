package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.coach.CoachObject;
import com.dhl.g05.leaguemodel.coach.ICoachModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueObject;

public class CoachPersistence implements ICoachModelPersistence{
	
	private List<CoachObject> coachList = new ArrayList<CoachObject>();

	@Override
	public int loadLeagueCoachObject(String leagueName, LeagueObject league) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String,Object>> coaches = new ArrayList<HashMap<String,Object>>();
		coaches = sp.fetchAllFreeCoach(league_id);
		for(HashMap<String, Object> coach : coaches) {
			String name = coach.get("name").toString();
			double skating = Double.parseDouble(coach.get("skating").toString());
			double shooting = Double.parseDouble(coach.get("shooting").toString());
			double checking = Double.parseDouble(coach.get("checking").toString());
			double saving = Double.parseDouble(coach.get("saving").toString());
			coachList.add(new CoachObject(name, skating, shooting, checking, saving));
		}
		league.setFreeCoach(coachList);
		return league_id;
	}

	@Override
	public int saveLeagueCoachObject(int league_id, LeagueObject leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		coachList = leagueObject.getFreeCoach();
		for(CoachObject coach: coachList) {
			String name = coach.getName();
			double skating = coach.getSkating();
			double shooting = coach.getShooting();
			double checking = coach.getChecking();
			double saving = coach.getSaving();
			int coachId = sp.saveFreeCoach(league_id, name, skating, shooting, checking, saving);
		}
		return league_id;
	}


}
