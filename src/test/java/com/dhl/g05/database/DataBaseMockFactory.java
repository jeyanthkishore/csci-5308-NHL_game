package com.dhl.g05.database;

public class DataBaseMockFactory extends DatabaseAbstractFactory{

	@Override
	public ISerializeModel createSerializeObject() {
		return new SerializeLeagueModelMock();
	}

	@Override
	public IFileOperation createFileOperation() {
		return new FileOperationMock();
	}

	@Override
	public ITeamDatabaseOperation createTeamDatabaseOperation() {
		return new TeamDatabaseOperationMock();
	}

	@Override
	public IDeserializeModel createDeserializeObject() {
		return new DeserializeLeagueModelMock();
	}

}
