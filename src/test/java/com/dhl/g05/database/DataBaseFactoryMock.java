package com.dhl.g05.database;

public class DataBaseFactoryMock extends AbstractDataBaseFactory{

	@Override
	public ISerializeModel getSerializeModel() {
		return new SerializeLeagueModelMock();
	}

	@Override
	public IFileOperation getFileDetails() {
		return new FileOperationMock();
	}

}
