package com.dhl.g05.leaguemodel;

public class CoachObject {
    private String name;
    private String result;
    private float skating;
    private float shooting;
    private float checking;
    private float saving;

    public CoachObject(){
        setName(null);
        setSkating(0);
        setSkating(0);
        setChecking(0);
        setSaving(0);
    }

    public CoachObject(String name, float skating, float shooting, float checking, float saving){

        this.name = name;

        this.skating = skating;

        this.shooting = shooting;

        this.checking = checking;

        this.saving = saving;

        this.result = validate();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSkating() {
        return skating;
    }

    public void setSkating(float skating) {
        this.skating = skating;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getChecking() {
        return checking;
    }

    public void setChecking(float checking) {
        this.checking = checking;
    }

    public float getSaving() {
        return saving;
    }

    public void setSaving(float saving) {
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

    public boolean validateStat(float stat) {

        if (stat >= 0 && stat <= 1) {

            return true;
        }

        return false;

    }
}
