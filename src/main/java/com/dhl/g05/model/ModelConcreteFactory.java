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
	public IFreeAgent createFreeAgentModel() {
		return new FreeAgentModel();
	}

	@Override
	public ICoach createCoachModel() {
		return new CoachModel();
	}

}
