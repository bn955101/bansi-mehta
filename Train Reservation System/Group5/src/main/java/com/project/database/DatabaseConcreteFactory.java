package com.project.database;

public class DatabaseConcreteFactory extends DatabaseAbstactFactory{
	private IDatabaseUtilities databaseUtilities;
	
	@Override
	public IDatabaseUtilities createDatabaseUtilities() {
		if (null ==  databaseUtilities) {
			databaseUtilities = new DatabaseUtilities();
		}
		return databaseUtilities;
	}	

}
