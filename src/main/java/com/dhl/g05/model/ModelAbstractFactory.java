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
	public abstract IPlayer createPlayerModel(String playerName, String position, Boolean captain,double skating, double shooting, double checking, double saving, int birthDay,int birthMonth,int birthYear);
	public abstract IPlayerInjured createPlayerInjury();
	public abstract IPlayerInjury createInjuredPlayer();
	public abstract IPlayerRetired createPlayerRetirement();
	public abstract IPlayerRetirement createRetiredPlayer();
	public abstract IRandomNumberFactory createRandomNumber();
	public abstract IFreeAgent createFreeAgentModel();
	public abstract ICoach createCoachModel();

}
