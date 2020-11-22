package com.dhl.g05.division;

public interface IDivisionModelPersistence {

	public int saveDivisionObject(int conferenceId, IDivision divisionObject);

	public int loadDivisionObject(int conferenceId, IDivision divisionObject);

}
