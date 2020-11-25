package com.dhl.g05.database;

public abstract class AbstractDataBaseFactory {
	private static AbstractDataBaseFactory abstractDatabaseFactory;

	public static AbstractDataBaseFactory getFactory() {
		return abstractDatabaseFactory;
	}

	public static void setFactory(AbstractDataBaseFactory databaseFactory) {
		abstractDatabaseFactory = databaseFactory;
	}

	public abstract ISerializeModel getSerializeModel();
	public abstract IFileOperation getFileDetails();
	public abstract ICheckTeam getCheckTeam();
	public abstract IDeserializeModel getDeserializeModel();
}
