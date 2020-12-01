package com.dhl.g05.database;

public class DatabaseMockConcreteFactory extends DatabaseMockAbstractFactory{

	@Override
	public DatabaseMockOperationFactoryState createMockDatabaseState() {
		return new DatabaseMockOperationFactoryState();
	}

}
