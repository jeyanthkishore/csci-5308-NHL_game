package com.dhl.g05.database;

public class DatabaseMockFactoryState extends DatabaseState {

	@Override
	public DatabaseAbstractFactory concreteMethod() {
		return new DataBaseMockFactory();
	}

}
