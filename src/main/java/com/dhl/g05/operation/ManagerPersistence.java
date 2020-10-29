package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.manager.IManagerPersistence;
import com.dhl.g05.leaguemodel.manager.ManagerModel;

public class ManagerPersistence implements IManagerPersistence,IManagerLoad{

	private List<ManagerModel> managerList = new ArrayList<ManagerModel>();

	@Override
	public int saveLeagueManagerObject(int league_id, ManagerModel managerObject) {
		StoredProcedure sp= new StoredProcedure();
		String managername = managerObject.getName();
	    int managerId = sp.saveFreeManager(league_id, managername);
		return managerId;
	}

	@Override
	public List<ManagerModel> loadLeagueManagerObject(String leagueName) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String,Object>> managers = new ArrayList<HashMap<String,Object>>();
		
		managers = sp.fetchAllFreeManager(league_id);
		for(HashMap<String, Object> manager : managers) {
			String name = manager.get("name").toString();
			ManagerModel managerNameObject = new ManagerModel();
			managerNameObject.setName(name);
			managerList.add(managerNameObject);
		}
		return managerList;
	}

}
