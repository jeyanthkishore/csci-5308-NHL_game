package com.dhl.g05.gameplayconfig;

public interface IAging {
	
    int getAverageRetirementAge();

    void setAverageRetirementAge(int averageRetirementAge);

    int getMaximumAge();

    void setMaximumAge(int maximumAge);

    AgingConstant validate();
}
