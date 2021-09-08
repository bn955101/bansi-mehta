package com.project.database;

public abstract class DatabaseAbstactFactory {
	private static DatabaseAbstactFactory instance = null;

	public static DatabaseAbstactFactory instance() {
		if (null == instance) {
			instance = new DatabaseConcreteFactory();
		}
		return instance;
	}

	public abstract IDatabaseUtilities createDatabaseUtilities();

}
