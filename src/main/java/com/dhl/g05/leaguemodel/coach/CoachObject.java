package com.dhl.g05.leaguemodel.coach;

import com.dhl.g05.leaguemodel.ILeagueModelComplete;
import com.dhl.g05.leaguemodel.ValidateEnumModel;

public class CoachObject {
    private String name;
    private double skating;
    private double shooting;
    private double checking;
    private double saving;

    public CoachObject(){
        setName(null);
        setSkating(0.0);
        setSkating(0.0);
        setChecking(0.0);
        setSaving(0.0);
    }

    public CoachObject(String name, double skating, double shooting, double checking, double saving){
        this.name = name;
        this.skating = skating;
        this.shooting = shooting;
        this.checking = checking;
        this.saving = saving;
    }

    public CoachObject(ILeagueModelComplete coach) {
        coach.loadCoachModelData(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSkating() {
        return skating;
    }

    public void setSkating(double skating) {
        this.skating = skating;
    }

    public double getShooting() {
        return shooting;
    }

    public void setShooting(double shooting) {
        this.shooting = shooting;
    }

    public double getChecking() {
        return checking;
    }

    public void setChecking(double checking) {
        this.checking = checking;
    }

    public double getSaving() {
        return saving;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }

    public ValidateEnumModel validate() {
        if(isCoachNameNull() || isCoachNameEmpty()) {
            return ValidateEnumModel.CoachNameEmpty;
        }
        if(!isCoachStatValid()) {
            return ValidateEnumModel.InvalidStateOfCoach;
        }
        return ValidateEnumModel.Success;
    }

    public boolean isCoachNameNull() {
        if(name == null) {
            return true;
        }
        return false;
    }

    public boolean isCoachNameEmpty() {
        if(name.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isCoachStatValid() {
        if (validateStat(skating) && validateStat(shooting) && validateStat(checking) && validateStat(saving)) {
            return true;
        }
        return false;
    }

    public boolean validateStat(double stat) {
        if (stat >= 0.0 && stat <= 1.0) {
            return true;
        }
        return false;
    }


}
