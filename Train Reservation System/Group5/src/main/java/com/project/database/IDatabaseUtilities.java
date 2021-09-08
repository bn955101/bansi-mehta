package com.project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public interface IDatabaseUtilities {

	Connection establishConnection();

	void closeConnection(Connection conn);

	void closeStatement(CallableStatement statement);

	void closeResultSet(ResultSet resultSet);

}
