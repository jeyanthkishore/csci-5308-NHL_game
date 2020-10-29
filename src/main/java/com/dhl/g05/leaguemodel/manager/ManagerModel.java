package com.dhl.g05.leaguemodel.manager;

public class ManagerModel {

	private String name;

	public  ManagerModel() {
		setName(null);
	}

	public ManagerModel(String name) {
		this.name = name;
	}

	public ManagerModel(IManagerModel manager) {
		manager.loadManagerModelData(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int saveLeagueManagerObject(int leagueId, IManagerPersistence database) {
		return database.saveLeagueManagerObject(leagueId, this);
	}
	
	public ManagerConstant validate() {
		if(isManagerNameNull() || isManagerNameEmpty()) {
			return ManagerConstant.ManagerNameEmpty;
		}
		return ManagerConstant.Success;
	}

	public boolean isManagerNameNull() {
		if(name == null) {
			return true;
		}
		return false;
	}

	public boolean isManagerNameEmpty() {
		if(name.isEmpty()) {
			return true;
		}
		return false;
	}

}
