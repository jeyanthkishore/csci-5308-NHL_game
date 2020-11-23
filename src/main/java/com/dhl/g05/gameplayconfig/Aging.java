package com.dhl.g05.gameplayconfig;

public class Aging implements IAging {
	private int averageRetirementAge;
	private int maximumAge;
	private double statDecayChance;

	public Aging() {
	}

	public Aging(int averageRetirementAge, int maximumAge, double statDecayChance) {
		this.averageRetirementAge = averageRetirementAge;
		this.maximumAge = maximumAge;
		this.statDecayChance = statDecayChance;
	}

	@Override
	public int getAverageRetirementAge() {
		return averageRetirementAge;
	}

	@Override
	public void setAverageRetirementAge(int averageRetirementAge) {
		this.averageRetirementAge = averageRetirementAge;
	}

	@Override
	public int getMaximumAge() {
		return maximumAge;
	}

	@Override
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	public double getStatDecayChance() {
		return statDecayChance;
	}

	public void setStatDecayChance(double statDecayChance) {
		this.statDecayChance = statDecayChance;
	}

	@Override
	public AgingConstant validate() {
		if (isMaximumAgeNotValid(maximumAge)) {
			return AgingConstant.MaximumAgeNotValid;
		}
		if (isAverageRetirementAgeNotValid(averageRetirementAge)) {
			return AgingConstant.AverageRetirementAgeNotValid;
		}
		if (isStatDecayChanceNotValid(statDecayChance)) {
			return AgingConstant.StatDecayChanceNotValid;
		}
		return AgingConstant.Success;
	}

	public boolean isMaximumAgeNotValid(int age) {
		if (age <= 0) {
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
    
	public boolean isStatDecayChanceNotValid(double statDecayChance) {
		if (statDecayChance > 1 || statDecayChance < 0) {
			return true;
		}
		return false;
	}
}
