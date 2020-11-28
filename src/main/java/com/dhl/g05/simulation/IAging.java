package com.dhl.g05.simulation;

public interface IAging {

	int getAverageRetirementAge();

	void setAverageRetirementAge(int averageRetirementAge);

	int getMaximumAge();

	void setMaximumAge(int maximumAge);

	double getStatDecayChance();

	void setStatDecayChance(double statDecayChance);

	AgingConstant validate();

	boolean isStatDecayChanceNotValid(double statDecayChance);

	boolean isAverageRetirementAgeNotValid(int averageRetirementAge);

	boolean isMaximumAgeNotValid(int maximumAge);

}
