package com.dhl.g05.simulation.statemachine;

public enum GameStatConfigConstant {

    Success("success"),
    PenaltyChanceNotValid("Penalty chance should be in range of 0 to 1");

    private String value;

    GameStatConfigConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
