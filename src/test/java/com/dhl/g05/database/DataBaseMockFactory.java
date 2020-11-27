package com.dhl.g05.database;

public class DataBaseMockFactory extends DatabaseAbstractFactory{

	@Override
	public ISerializeModel getSerializeModel() {
		return new SerializeLeagueModelMock();
	}

	@Override
	public IFileOperation getFileDetails() {
		return new FileOperationMock();
	}

	@Override
	public ICheckTeam getCheckTeam() {
		return new CheckTeamMock();
	}

	@Override
	public IDeserializeModel getDeserializeModel() {
		return new DeserializeLeagueModelMock();
	}

}