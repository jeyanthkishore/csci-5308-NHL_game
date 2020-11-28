package com.dhl.g05.model;

public interface ICoach {

    String getName();

    void setName(String name);

    double getSkating();

    void setSkating(double skating);

    double getShooting();

    void setShooting(double shooting);

    double getChecking();

    void setChecking(double checking);

    double getSaving();

    void setSaving(double saving);

    CoachConstant validate();

    boolean isCoachNameNull();

    boolean isCoachNameEmpty();

    boolean isCoachStatNotValid();

}
