package com.dhl.g05.coach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.dhl.g05.db.StoredProcedure;

public class CoachPersistence implements ICoachModelPersistence,ICoachLoad{

	private List<CoachModel> coachList = new ArrayList<CoachModel>();

	@Override
	public List<CoachModel>  loadLeagueCoachObject(String leagueName) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String,Object>> coaches = new ArrayList<HashMap<String,Object>>();
		double skating, shooting, checking, saving;
		String name;

		coaches = sp.fetchAllFreeCoach(league_id);
		for(HashMap<String, Object> coach : coaches) {
			name = coach.get("name").toString();
			skating = Double.parseDouble(coach.get("skating").toString());
			shooting = Double.parseDouble(coach.get("shooting").toString());
			checking = Double.parseDouble(coach.get("checking").toString());
			saving = Double.parseDouble(coach.get("saving").toString());
			coachList.add(new CoachModel(name, skating, shooting, checking, saving));
		}
		return coachList;
	}

	@Override
	public int saveLeagueCoachObject(int league_id, CoachModel coach) {
		StoredProcedure sp= new StoredProcedure();
		double skating, shooting, checking, saving;
		String name;

		name = coach.getName();
		skating = coach.getSkating();
		shooting = coach.getShooting();
		checking = coach.getChecking();
		saving = coach.getSaving();
		int coachId = sp.saveFreeCoach(league_id, name, skating, shooting, checking, saving);
		return coachId;
	}

}
