package com.dhl.g05.leaguemodel;

public class CoachObject {
    private String name;
    private String result;
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
        this.result = validate();
    }

    public CoachObject(ILeagueModel coach) {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String validate() {
        if(isCoachNameNull() || isCoachNameEmpty()) {
            return "Coach Name Should Not have Empty Value";
        }
        if(!isCoachStatValid()) {
            return "Invalid state of coach";
        }
        return "success";
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
