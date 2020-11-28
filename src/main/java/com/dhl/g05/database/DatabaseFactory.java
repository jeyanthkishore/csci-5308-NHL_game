package com.dhl.g05.database;

public class DatabaseFactory extends DatabaseAbstractFactory{

	@Override
	public ISerializeModel createSerializeObject() {
		return new SerialiseLeagueModel();
	}

	@Override
	public IFileOperation createFileOperation() {
		return new FileOperation();
	}

	@Override
	public ICheckTeam createTeamDatabaseOperation() {
		return new TeamDatabaseOperation();
	}

	@Override
	public IDeserializeModel createDeserializeObject() {
		return new DeserializeLeagueModel();
	}

}
