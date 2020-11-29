package com.dhl.g05.model;

public class ModelConcreteFactory extends ModelAbstractFactory{

	@Override
	public ILeague createLeagueModel() {
		return new LeagueModel();
	}

	@Override
	public IConference createConferenceModel() {
		return new ConferenceModel();
	}

	@Override
	public IDivision createDivisionModel() {
		return new DivisionModel();
	}

	@Override
	public ITeam createTeamModel() {
		return new TeamModel();
	}

	@Override
	public IPlayer createPlayerModel() {
		return new PlayerModel();
	}

	@Override
	public IPlayer createPlayerModel(String playerName, String position, Boolean captain, double skating, double shooting, double checking, double saving, int birthDay, int birthMonth, int birthYear) {
		return new PlayerModel(playerName, position, captain, skating, shooting, checking, saving, birthDay, birthMonth, birthYear);
	}

	@Override
	public IPlayerInjured createPlayerInjury() {
		return new PlayerInjury();
	}

	@Override
	public IPlayerInjury createInjuredPlayer() {
		return new PlayerModel();
	}

	@Override
	public IPlayerRetired createPlayerRetirement() {
		return new PlayerRetirement();
	}

	@Override
	public IPlayerRetirement createRetiredPlayer() {
		return new PlayerModel();
	}

	@Override
	public IRandomNumberFactory createRandomNumber() {
		return new RandomNumberFactory();
	}

	@Override
	public IFreeAgent createFreeAgentModel() {
		return new FreeAgentModel();
	}

	@Override
	public ICoach createCoachModel() {
		return new CoachModel();
	}


}
