package com.dhl.g05.gameplayconfig;

public enum AgingConstant {

    Success("success"),
    MaximumAgeNotValid("Maximum age should be grater than 1"),
    AverageRetirementAgeNotValid("Average retirement age should be grater than 1");


    private String value;

    AgingConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
