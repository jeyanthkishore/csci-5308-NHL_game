package com.dhl.g05.simulation;

public enum InjuryConstant {
    Success("success"),
    RandomInjuryChanceError("Value of Random Chance must be between 0 and 1"),
    InjuryDaysError("Value of HighInjury days should be grater than value of LowInjury Days");

    private String value;

    InjuryConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
