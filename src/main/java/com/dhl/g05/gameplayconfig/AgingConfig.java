package com.dhl.g05.gameplayconfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgingConfig implements IAging {

	static final Logger logger = LogManager.getLogger(AgingConfig.class);
	private int MAX_STAT_DECAY_CHANCE = 1;
	private int MIN_STAT_DECAY_CHANCE = 0;
	private int MIN_AGE = 0;
	private int averageRetirementAge;
	private int maximumAge;
	private double statDecayChance;

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
		logger.info("Validating aging details");
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

	@Override
	public boolean isMaximumAgeNotValid(int maximumAge) {
		if (maximumAge > MIN_AGE) {
			return false;
		}
		return true;
	}

	@Override
    public boolean isAverageRetirementAgeNotValid(int averageRetirementAge) {
        if(averageRetirementAge > MIN_AGE) {
            return false;
        }
        return true;
    }

    @Override
	public boolean isStatDecayChanceNotValid(double statDecayChance) {
		if (statDecayChance < MAX_STAT_DECAY_CHANCE && statDecayChance > MIN_STAT_DECAY_CHANCE) {
			return false;
		}
		return true;
	}
}
