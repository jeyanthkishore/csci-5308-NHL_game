package com.dhl.g05.database;

public abstract class DatabaseMockAbstractFactory {
	
	private static DatabaseMockAbstractFactory databaseMockAbstractFactory;

	public static DatabaseMockAbstractFactory instance(DatabaseMockState state) {
		databaseMockAbstractFactory = state.concreteMethod();
		return databaseMockAbstractFactory;
	}

	public abstract DatabaseMockOperationFactoryState createMockDatabaseState();
}
