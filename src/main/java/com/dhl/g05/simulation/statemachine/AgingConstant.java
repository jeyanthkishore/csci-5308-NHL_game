package com.dhl.g05.simulation.statemachine;

public enum AgingConstant {

    Success("success"),
    MaximumAgeNotValid("Maximum age should be grater than 1"),
    AverageRetirementAgeNotValid("Average retirement age should be greater than 1"), 
    StatDecayChanceNotValid("Stat Decay Chance should be between 0 and 1 ");

    private String value;

    AgingConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
