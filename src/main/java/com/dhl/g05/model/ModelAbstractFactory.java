package com.dhl.g05.model;

public abstract class ModelAbstractFactory {

	private static ModelAbstractFactory modelAsbtractFactory;

	public static ModelAbstractFactory getInstance(ModelState state) {
		modelAsbtractFactory = state.concreteMethod();
		return modelAsbtractFactory;
	}
	
	public abstract ILeague createLeagueModel();
	public abstract IConference createConferenceModel();
	public abstract IDivision createDivisionModel();
	public abstract ITeam createTeamModel();
	public abstract IPlayer createPlayerModel();
	public abstract IFreeAgent createFreeAgentModel();
	public abstract ICoach createCoachModel();
}
