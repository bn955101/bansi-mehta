package com.project.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.security.SecurityAbstractFactory;

@Component
public class UserDAO implements IUserDAO {

	@Override
	public IUser getUserByUsername(String username) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		IUser userfromDB = userAbstractFactory.createUser();
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = databaseUtilities.establishConnection();
			statement = connection.prepareCall("{call findUserByUserName(? )}");
			statement.setString(1, username);
			boolean hadResults = statement.execute();

			if (hadResults) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					int id = resultSet.getInt("id");
					String userName = resultSet.getString("userName");
					String password = resultSet.getString("password");
					boolean enabled = resultSet.getBoolean("enabled");
					String role = resultSet.getString("role");

					userfromDB.setId(id);
					userfromDB.setUserName(userName);
					userfromDB.setPassword(password);
					userfromDB.setEnabled(enabled);
					userfromDB.setRole(role);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return userfromDB;
	}

	@Override
	public void saveUser(IUser user) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;

		if (user.getId() == 0) {
			try {
				BCryptPasswordEncoder encoder = securityAbstractFactory.createPasswordEncoder();
				String encodedPassword = encoder.encode(user.getPassword());

				statement = connection.prepareCall("{call addUser( ? , ? , ? , ?, ?, ?, ? , ? , ? , ? , ? )}");
				statement.setString(1, user.getFirstName());
				statement.setString(2, user.getLastName());
				statement.setString(3, user.getGender());
				java.sql.Date sqlDate = new java.sql.Date(user.getDateOfBirth().getTime());

				statement.setDate(4, sqlDate);
				statement.setString(5, String.valueOf(user.getMobileNumber()));
				statement.setString(6, user.getUserName());
				statement.setString(7, encodedPassword);
				statement.setString(8, user.getQuestionOne());
				statement.setString(9, user.getAnswerOne());
				statement.setString(10, user.getQuestionTwo());
				statement.setString(11, user.getAnswerTwo());
				statement.execute();
			} catch (SQLException exception) {
				exception.printStackTrace();
			} finally {
				databaseUtilities.closeStatement(statement);
				databaseUtilities.closeConnection(connection);
			}
		}
	}

	@Override
	public boolean isUserExists(String username) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		boolean hadResults = false;

		try {
			connection = databaseUtilities.establishConnection();
			statement = connection.prepareCall("{call findUserByUserName( ? )}");
			statement.setString(1, username);
			hadResults = statement.execute();
			if (hadResults) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean isUserPresentWithSameQuestionAndAnswer(IUser user) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		boolean hadResults = false;

		try {
			connection = databaseUtilities.establishConnection();
			statement = connection.prepareCall("{call findUserByUserNameAndQuestionAndAnswer(? , ? , ? , ? , ? )}");
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getQuestionOne());
			statement.setString(3, user.getAnswerOne());
			statement.setString(4, user.getQuestionTwo());
			statement.setString(5, user.getAnswerTwo());
			hadResults = statement.execute();
			if (hadResults) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return true;
	}

	@Override
	public boolean updatePassword(IUser user) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		BCryptPasswordEncoder encoder = securityAbstractFactory.createPasswordEncoder();
		String encodedpassword = encoder.encode(user.getPassword());
		Connection connection = null;
		CallableStatement statement = null;
		boolean hadResults = false;

		try {
			connection = databaseUtilities.establishConnection();
			statement = connection.prepareCall("{call updatePassword(? , ?)}");
			statement.setString(1, user.getUserName());
			statement.setString(2, encodedpassword);
			hadResults = statement.execute();
			if (hadResults) {
				return true;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return true;
	}

}
