package com.dhl.g05.database;

public class DatabaseFactory extends DatabaseAbstractFactory{

	@Override
	public ISerializeModel getSerializeModel() {
		return new SerialiseLeagueModel();
	}

	@Override
	public IFileOperation getFileDetails() {
		return new FileOperation();
	}

	@Override
	public ICheckTeam getCheckTeam() {
		return new CheckTeam();
	}

	@Override
	public IDeserializeModel getDeserializeModel() {
		return new DeserializeLeagueModel();
	}

}