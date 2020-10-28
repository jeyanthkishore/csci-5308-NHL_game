package com.dhl.g05.leaguemodel;

public class Aging implements IAging{
    private int averageRetirementAge;
    private int maximumAge;

    public Aging(int averageRetirementAge, int maximumAge){
        this.averageRetirementAge = averageRetirementAge;
        this.maximumAge = maximumAge;
    }

    public int getAverageRetirementAge() {
        return averageRetirementAge;
    }

    public void setAverageRetirementAge(int averageRetirementAge) {
        this.averageRetirementAge = averageRetirementAge;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }
}