package com.dhl.g05.database;

public abstract class DatabaseAbstractFactory {
	private static DatabaseAbstractFactory databaseAbstractFactory;
	
	public static DatabaseAbstractFactory instance(DatabaseState state) {
		databaseAbstractFactory = state.concreteMethod();
		return databaseAbstractFactory;
	}

	public abstract ISerializeModel createSerializeObject();
	public abstract IFileOperation createFileOperation();
	public abstract ITeamDatabaseOperation createTeamDatabaseOperation();
	public abstract IDeserializeModel createDeserializeObject();
	
}
