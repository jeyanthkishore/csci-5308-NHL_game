package com.dhl.g05.division;

public interface IDivisionModelPersistence {

	public int saveDivisionObject(int conferenceId, DivisionModel divisionObject);

	public int loadDivisionObject(int conferenceId, DivisionModel divisionObject);

}
