package com.dhl.g05.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.player.PlayerModel;

public class TeamPersistenceMock implements ITeamModelPersistence{
	
	@Override
	public int saveTeamObject(int divisionId, TeamModel object, CoachModel coachObject) {
		if(divisionId==1 && object.getTeamName().equals("Striker Six")) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int loadTeamObject(int divisionId, TeamModel teamObject, CoachModel coachObject) {
		if(divisionId==1 && teamObject.getTeamName().equals("Striker Six")) {
			ArrayList<PlayerModel> players = new ArrayList<>();
			players.add(new PlayerModel("Cristiano Ronaldo",null,null,10,10,10,10,20,20,2000));
			teamObject.setPlayerList(players);
			return 1;
		}
		return 0;
	}
	
	@Override
	public List<HashMap<String, Object>> loadAllTeamName() {
		List<HashMap<String,Object>> teamName = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> name = new HashMap<String, Object>();
		name.put("team_name", "Striker Six");
		teamName.add(name);
		name = new HashMap<String, Object>();
		name.put("team_name", "Rockers");
		teamName.add(name);
		return teamName;
	}
}
