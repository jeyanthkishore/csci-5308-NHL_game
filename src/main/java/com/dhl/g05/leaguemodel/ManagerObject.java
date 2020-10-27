package com.dhl.g05.leaguemodel;

import java.util.List;

public class ManagerObject {
    private String name;
    private List<ManagerObject> managerList;

    public  ManagerObject() {
      //  setName(null);
    }

    public ManagerObject(List<ManagerObject> managerList) {
        this.managerList = managerList;

    }

    public List<ManagerObject> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<ManagerObject> managerList) {
        this.managerList = managerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String validate() {
        if(isManagerNameNull() || isManagerNameEmpty()) {
            return "Manager Name Should Not have Empty Value";
        }
        return "success";
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
