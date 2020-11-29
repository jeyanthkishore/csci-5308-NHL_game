package com.dhl.g05.model;

public class ModelConcreteFactory extends ModelAbstractFactory{

	@Override
	public ILeague createLeagueModel() {
		return new LeagueModel();
	}

	@Override
	public ILeague createLeagueModel(ILeagueModel league) {
		return new LeagueModel(league);
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
	public ITeam createTeamModel(ITeamModel team) {
		return new TeamModel(team);
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
	public IPlayer createPlayerModel(IPlayerModel player) {
		return new PlayerModel(player);
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
	public IFreeAgent createFreeAgentModel(String playerName, String position, double skating, double shooting, double checking, double saving, int birthDay, int birthMonth, int birthYear) {
		return new FreeAgentModel(playerName, position, skating, shooting, checking, saving, birthDay, birthMonth, birthYear);
	}

	@Override
	public IFreeAgent createFreeAgentModel(IFreeAgentModel freeAgent) {
		return new FreeAgentModel(freeAgent);
	}

	@Override
	public ICoach createCoachModel() {
		return new CoachModel();
	}

	@Override
	public ICoach createCoachModel(String name, double skating, double shooting, double checking, double saving) {
		return new CoachModel(name, skating, shooting, checking, saving);
	}

	@Override
	public IGenerateNewPlayers createNewPlayers() {
		return new GenerateNewPlayers();
	}

	@Override
	public IPlayerDraft createPlayerDraft() {
		return new PlayerDraft();
	}

	@Override
	public IPlayerTraining createPlayerTraining() {
		return new PlayerTraining();
	}


}
