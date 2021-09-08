package com.project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

@Component
public class DatabaseUtilities implements IDatabaseUtilities {
    private String DRIVER = "com.mysql.cj.jdbc.Driver";
	public Connection connection;
	
	private String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_5_PRODUCTION?useSSL=false&serverTimezone=UTC";    
	private String USERNAME = "CSCI5308_5_PRODUCTION_USER";
    private String PASSWORD = "wrq6CK42hXaFvLHV";

	@Override
	public Connection  establishConnection(){
		try {
			Class.forName(DRIVER);
		    String url = URL;
			String userName = USERNAME;
			String password = PASSWORD;
			
		    connection = DriverManager.getConnection(url , userName, password);
			return connection;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	@Override
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void closeStatement(CallableStatement statement) {
		try {
			statement.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
