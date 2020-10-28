package com.dhl.g05.leaguemodel.manager;

import java.util.List;

public class ManagerModel {
    private String name;
    private List<ManagerModel> managerList;

    public  ManagerModel() {
       setName(null);
    }

    public ManagerModel(List<ManagerModel> managerList) {
        this.managerList = managerList;

    }

    public List<ManagerModel> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<ManagerModel> managerList) {
        this.managerList = managerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int saveLeagueManagerObject(int leagueId, IManagerPersistence database) {
        return database.saveLeagueManagerObject(leagueId, this);
    }

    public int loadLeagueManagerObject(String leagueName,IManagerPersistence database) {
        return database.loadLeagueManagerObject(leagueName,this);
    }
}
