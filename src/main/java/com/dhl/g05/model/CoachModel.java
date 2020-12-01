package com.dhl.g05.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.annotations.Expose;

public class CoachModel implements ICoach{

	static final Logger logger = LogManager.getLogger(CoachModel.class);
	private final static double MAX_STAT = 1.0;
	private final static double MIN_STAT = 0.0;
	
	@Expose
	private String name;
	@Expose
	private double skating;
	@Expose
	private double shooting;
	@Expose
	private double checking;
	@Expose
	private double saving;

	public CoachModel() {
		setName(null);
		setSkating(0.0);
		setSkating(0.0);
		setChecking(0.0);
		setSaving(0.0);
	}

	public CoachModel(String name, double skating, double shooting, double checking, double saving) {
		this.name = name;
		this.skating = skating;
		this.shooting = shooting;
		this.checking = checking;
		this.saving = saving;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getSkating() {
		return skating;
	}

	@Override
	public void setSkating(double skating) {
		this.skating = skating;
	}

	@Override
	public double getShooting() {
		return shooting;
	}

	@Override
	public void setShooting(double shooting) {
		this.shooting = shooting;
	}

	@Override
	public double getChecking() {
		return checking;
	}

	@Override
	public void setChecking(double checking) {
		this.checking = checking;
	}

	@Override
	public double getSaving() {
		return saving;
	}

	@Override
	public void setSaving(double saving) {
		this.saving = saving;
	}

	@Override
	public CoachConstant validate() {
		logger.info("Validating coach details of - name and statistics of Coach : " + getName());
		if(isCoachNameNull() || isCoachNameEmpty()) {
			return CoachConstant.CoachNameEmpty;
		}
		if(isCoachStatNotValid()) {
			return CoachConstant.InvalidStateOfCoach;
		}
		return CoachConstant.Success;
	}

	private boolean isCoachNameNull() {
		if(name == null) {
			return true;
		}
		return false;
	}

	private boolean isCoachNameEmpty() {
		if(name.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isCoachStatNotValid() {
		logger.info("Validating coach statistics of Coach "+ getName());
		if (validateStat(skating) && validateStat(shooting) && validateStat(checking) && validateStat(saving)) {
			return false;
		}
		return true;
	}

	private boolean validateStat(double stat) {
		if (stat >= MIN_STAT && stat <= MAX_STAT) {
			return true;
		}
		return false;
	}

}
