package com.dhl.g05.gameplayconfig;

public class Aging implements IAging{
    private int averageRetirementAge;
    private int maximumAge;

    public Aging() {
    }

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

    public AgingConstant validate() {
        if (isMaximumAgeNotValid(maximumAge)) {
            return AgingConstant.MaximumAgeNotValid;
        }
        if (isAverageRetirementAgeNotValid(averageRetirementAge)) {
            return AgingConstant.AverageRetirementAgeNotValid;
        }
        return AgingConstant.Success;
    }

    public boolean isMaximumAgeNotValid(int age) {
        if(age <= 0) {
            return true;
        }
        return false;
    }

    public boolean isAverageRetirementAgeNotValid(int age) {
        if(age <= 0) {
            return true;
        }
        return false;
    }
}
