package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.coach.CoachModel;
import com.dhl.g05.leaguemodel.coach.ICoachModelPersistence;
import com.dhl.g05.leaguemodel.league.LeagueModel;

public class CoachPersistence implements ICoachModelPersistence{

	private List<CoachModel> coachList = new ArrayList<CoachModel>();

	@Override
	public int loadLeagueCoachObject(String leagueName, LeagueModel league) {
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
		league.setFreeCoach(coachList);
		return league_id;
	}

	@Override
	public int saveLeagueCoachObject(int league_id, LeagueModel leagueObject) {
		StoredProcedure sp= new StoredProcedure();
		double skating, shooting, checking, saving;
		String name;

		coachList = leagueObject.getFreeCoach();
		for(CoachModel coach: coachList) {
			name = coach.getName();
			skating = coach.getSkating();
			shooting = coach.getShooting();
			checking = coach.getChecking();
			saving = coach.getSaving();
			int coachId = sp.saveFreeCoach(league_id, name, skating, shooting, checking, saving);
		}
		return league_id;
	}

}
