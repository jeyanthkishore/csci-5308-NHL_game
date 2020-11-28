package com.dhl.g05.gameplayconfig;

public interface IAging {

	int getAverageRetirementAge();

	void setAverageRetirementAge(int averageRetirementAge);

	int getMaximumAge();

	void setMaximumAge(int maximumAge);

	public double getStatDecayChance();

	public void setStatDecayChance(double statDecayChance);

	AgingConstant validate();
}
