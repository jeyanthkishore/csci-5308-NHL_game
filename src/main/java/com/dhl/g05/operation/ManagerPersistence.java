package com.dhl.g05.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dhl.g05.db.StoredProcedure;
import com.dhl.g05.leaguemodel.manager.IManagerPersistence;
import com.dhl.g05.leaguemodel.manager.ManagerObject;

public class ManagerPersistence implements IManagerPersistence{
	
	private List<ManagerObject> managerList = new ArrayList<ManagerObject>();

	@Override
	public int saveLeagueManagerObject(int league_id, ManagerObject managerObject) {
		StoredProcedure sp= new StoredProcedure();
		managerList = managerObject.getManagerList();
		for(ManagerObject manager: managerList) {
			String name = manager.getName();
			int managerId = sp.saveFreeManager(league_id, name);
		}
		return league_id;
	}

	@Override
	public int loadLeagueManagerObject(String leagueName, ManagerObject managerObject) {
		StoredProcedure sp= new StoredProcedure();
		int league_id = sp.getLeagueID(leagueName);
		List<HashMap<String,Object>> managers = new ArrayList<HashMap<String,Object>>();
		managers = sp.fetchAllFreeManager(league_id);
		for(HashMap<String, Object> manager : managers) {
			String name = manager.get("name").toString();
			ManagerObject managerNameObject = new ManagerObject();
			managerNameObject.setName(name);
			managerList.add(managerNameObject);
		}
		managerObject.setManagerList(managerList);
		return league_id;
	}

}
