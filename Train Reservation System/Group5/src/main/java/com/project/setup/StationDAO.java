package com.project.setup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;

@Component
public class StationDAO implements IStationDAO {
	public final String STATION_ID = "stationId";
	public final String STATION_NAME = "stationName";
	public final String STATION_CODE = "stationCode";
	public final String STATION_CITY = "stationCity";
	public final String STATION_STATE = "stationState";
	List<IStation> listOfStation = new ArrayList<IStation>();

	@Override
	public boolean save(IStation station) {
		if (isStationUnique(station.getStationName(), station.getStationCode(), station.getStationId())) {
			return false;
		} else {
			DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
			IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
			Connection connection = databaseUtilities.establishConnection();
			CallableStatement statment = null;

			if (station.getStationId() == 0) {
				try {
					statment = connection.prepareCall("{call addStation( ? , ? , ? , ?)}");
					statment.setString(1, station.getStationName());
					statment.setString(2, station.getStationCode());
					statment.setString(3, station.getStationCity());
					statment.setString(4, station.getStationState());
					statment.execute();
				} catch (SQLException exception) {
					exception.printStackTrace();
				} finally {
					databaseUtilities.closeStatement(statment);
					databaseUtilities.closeConnection(connection);
				}
			} else {
				try {
					statment = connection.prepareCall("{call editStation( ? , ? , ? , ? , ?)}");
					statment.setInt(1, station.getStationId());
					statment.setString(2, station.getStationName());
					statment.setString(3, station.getStationCode());
					statment.setString(4, station.getStationCity());
					statment.setString(5, station.getStationState());
					statment.execute();
				} catch (SQLException exception) {
					exception.printStackTrace();
				} finally {
					databaseUtilities.closeStatement(statment);
					databaseUtilities.closeConnection(connection);
				}
			}
			return true;
		}
	}

	@Override
	public boolean isStationUnique(String stationName, String stationCode, int stationId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;

		try {
			statment = connection.prepareCall("{call checkStation(? ,? , ?)}");
			statment.setString(1, stationName);
			statment.setString(2, stationCode);
			statment.setInt(3, stationId);
			boolean hadResultsForList = statment.execute();

			if (hadResultsForList) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					int count = resultSet.getInt("count");
					if (count > 0) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeConnection(connection);
		}
		return false;
	}

	@Override
	public List<IStation> getAllStation() {
		listOfStation.removeAll(listOfStation);
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;

		try {
			statment = connection.prepareCall("{call getAllStation()}");
			boolean hadResultsForList = statment.execute();

			if (hadResultsForList) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					IStation station = setupAbstractFactory.createNewStation();
					station.setStationId(resultSet.getInt(STATION_ID));
					station.setStationName(resultSet.getString(STATION_NAME));
					station.setStationCode(resultSet.getString(STATION_CODE));
					station.setStationCity(resultSet.getString(STATION_CITY));
					station.setStationState(resultSet.getString(STATION_STATE));
					listOfStation.add(station);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return listOfStation;
	}

	@Override
	public IStation getStation(Integer stationId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		IStation station = setupAbstractFactory.createNewStation();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;

		try {
			statment = connection.prepareCall("{call getStation(?)}");
			statment.setInt(1, stationId);
			boolean hadStation = statment.execute();

			if (hadStation) {
				resultSet = statment.getResultSet();
				if (resultSet.next()) {
					station.setStationId(resultSet.getInt(STATION_ID));
					station.setStationName(resultSet.getString(STATION_NAME));
					station.setStationCode(resultSet.getString(STATION_CODE));
					station.setStationCity(resultSet.getString(STATION_CITY));
					station.setStationState(resultSet.getString(STATION_STATE));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return station;
	}

	@Override
	public void delete(Integer stationId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;

		try {
			statment = connection.prepareCall("{call deleteStation( ? )}");
			statment.setInt(1, stationId);
			statment.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeConnection(connection);
		}
	}

}
