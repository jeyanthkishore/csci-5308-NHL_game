package com.dhl.g05.database;

public class DataBaseFactory extends AbstractDataBaseFactory{

	@Override
	public ISerializeModel getSerializeModel() {
		return new SerialiseLeagueModel();
	}

	@Override
	public IFileOperation getFileDetails() {
		return new FileOperation();
	}

}
