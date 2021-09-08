package com.project.findMyTrain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

public class FindMyTrainDAO implements IFindMyTrainDAO {
	public final String DAYS = "days";
	public final String DEPARTURE_TIME = "departureTime";
	public final String START_STATION = "startStation";
	public final String MIDDLE_STATION = "middleStations";
	public final String END_STATION = "endStation";
	public final String DISTANCE = "distance";
	public final String STATION_ID = "stationId";
	public final String STATION_NAME = "stationName";

	@Override
	public ITrain getLiveTrainStatus(int trainCode, Date startDate) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call getTrain(?)}");
			statement.setInt(1, trainCode);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				train.setDays(resultSet.getString(DAYS));
				train.setDepartureTime(resultSet.getString(DEPARTURE_TIME));
				train.setStartStation(resultSet.getString(START_STATION));
				train.setMiddleStations(resultSet.getString(MIDDLE_STATION));
				train.setEndStation(resultSet.getString(END_STATION));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return train;
	}

	@Override
	public double getRouteInformation(Integer startStation, Integer endStation) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		double distance = 0;

		try {
			statement = connection.prepareCall("{call getRoutebyStation(?, ?)}");
			statement.setInt(1, startStation);
			statement.setInt(2, endStation);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				distance = resultSet.getDouble(DISTANCE);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return distance;
	}

	@Override
	public Map<Integer, String> getStationInformation() {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		HashMap<Integer, String> hashMapOfStation = new HashMap<Integer, String>();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call getAllStation()}");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				hashMapOfStation.put(resultSet.getInt(STATION_ID), resultSet.getString(STATION_NAME));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return hashMapOfStation;
	}

}
