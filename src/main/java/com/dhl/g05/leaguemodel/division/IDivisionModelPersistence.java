package com.dhl.g05.leaguemodel.division;

public interface IDivisionModelPersistence {
	
	public int saveDivisionObject(int conferenceId, DivisionObject divisionObject);
	
	public int loadDivisionObject(int conferenceId, DivisionObject divisionObject);

}
