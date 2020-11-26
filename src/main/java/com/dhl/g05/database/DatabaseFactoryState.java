package com.dhl.g05.database;

public class DatabaseFactoryState extends DatabaseState{

	@Override
	public DatabaseAbstractFactory concreteMethod() {
		return new DatabaseFactory();
	}

}
