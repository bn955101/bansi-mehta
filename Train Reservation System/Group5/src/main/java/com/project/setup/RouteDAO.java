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
public class RouteDAO implements IRouteDAO {
	public final String ROUTE_ID = "routeId";
	public final String SOURCE_STATION_ID = "sourceStationId";
	public final String SOURCE_STATION_NAME = "sourceStationName";
	public final String SOURCE_STATION_CODE = "sourceStationCode";
	public final String SOURCE_STATION_CITY = "sourceStationCity";
	public final String SOURCE_STATION_STATE = "sourceStationState";
	public final String DESTINATION_STATION_ID = "destinationStationId";
	public final String DESTINATION_STATION_NAME = "destinationStationName";
	public final String DESTINATION_STATION_CODE = "destinationStationCode";
	public final String DESTINATION_STATION_CITY = "destinationStationCity";
	public final String DESTINATION_STATION_STATE = "destinationStationState";
	public final String DISTANCE = "distance";

	@Override
	public void saveRoute(IRoute route) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;

		if (route.getRouteId() == 0) {
			try {
				statement = connection.prepareCall("{call addRoute( ? , ? , ?)}");
				statement.setInt(1, route.getSourceId());
				statement.setInt(2, route.getDestinationId());
				statement.setDouble(3, route.getDistance());
				statement.execute();
			} catch (SQLException exception) {
				exception.printStackTrace();
			} finally {
				databaseUtilities.closeStatement(statement);
				databaseUtilities.closeConnection(connection);
			}
		} else {
			try {
				statement = connection.prepareCall("{call editRoute( ? , ? , ? , ?)}");
				statement.setInt(1, route.getRouteId());
				statement.setInt(2, route.getSourceId());
				statement.setInt(3, route.getDestinationId());
				statement.setDouble(4, route.getDistance());
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
	public List<IRoute> getAllRoute() {
		List<IRoute> listOfRoutes = new ArrayList<>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call getAllRoute()}");
			boolean hadResult = statement.execute();

			if (hadResult) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					IRoute route = setupAbstractFactory.createNewRoute();
					IStation sourceStation = setupAbstractFactory.createNewStation();
					IStation destinationStation = setupAbstractFactory.createNewStation();

					route.setRouteId(resultSet.getInt(ROUTE_ID));
					sourceStation.setStationId(resultSet.getInt(SOURCE_STATION_ID));
					sourceStation.setStationName(resultSet.getString(SOURCE_STATION_NAME));
					sourceStation.setStationCode(resultSet.getString(SOURCE_STATION_CODE));
					sourceStation.setStationCity(resultSet.getString(SOURCE_STATION_CITY));
					sourceStation.setStationState(resultSet.getString(SOURCE_STATION_STATE));
					route.setSource(sourceStation);
					route.setSourceId(sourceStation.getStationId());
					destinationStation.setStationId(resultSet.getInt(DESTINATION_STATION_ID));
					destinationStation.setStationName(resultSet.getString(DESTINATION_STATION_NAME));
					destinationStation.setStationCode(resultSet.getString(DESTINATION_STATION_CODE));
					destinationStation.setStationCity(resultSet.getString(DESTINATION_STATION_CITY));
					destinationStation.setStationState(resultSet.getString(DESTINATION_STATION_STATE));
					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.getStationId());
					route.setDistance(resultSet.getDouble(DISTANCE));
					listOfRoutes.add(route);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return listOfRoutes;
	}

	@Override
	public IRoute getRoute(Integer routeId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRoute route = setupAbstractFactory.createNewRoute();
		IStation sourceStation = setupAbstractFactory.createNewStation();
		IStation destinationStation = setupAbstractFactory.createNewStation();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call getRoute(?)}");
			statement.setInt(1, routeId);
			boolean hasRoute = statement.execute();

			if (hasRoute) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					route.setRouteId(resultSet.getInt(ROUTE_ID));
					sourceStation.setStationId(resultSet.getInt(SOURCE_STATION_ID));
					sourceStation.setStationName(resultSet.getString(SOURCE_STATION_NAME));
					sourceStation.setStationCode(resultSet.getString(SOURCE_STATION_CODE));
					sourceStation.setStationCity(resultSet.getString(SOURCE_STATION_CITY));
					sourceStation.setStationState(resultSet.getString(SOURCE_STATION_STATE));
					route.setSource(sourceStation);
					route.setSourceId(sourceStation.getStationId());
					destinationStation.setStationId(resultSet.getInt(DESTINATION_STATION_ID));
					destinationStation.setStationName(resultSet.getString(DESTINATION_STATION_NAME));
					destinationStation.setStationCode(resultSet.getString(DESTINATION_STATION_CODE));
					destinationStation.setStationCity(resultSet.getString(DESTINATION_STATION_CITY));
					destinationStation.setStationState(resultSet.getString(DESTINATION_STATION_STATE));
					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.getStationId());
					route.setDistance(resultSet.getDouble(DISTANCE));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return route;
	}

	@Override
	public void deleteRoute(Integer routeId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;

		try {
			statement = connection.prepareCall("{call deleteRoute( ? )}");
			statement.setInt(1, routeId);
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
	}

	@Override
	public IRoute getRouteByStation(int sourcePoint, int destinationPoint) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		IRoute route = setupAbstractFactory.createNewRoute();

		try {
			statement = connection.prepareCall("{call getRoutebyStation(?, ?)}");
			statement.setInt(1, sourcePoint);
			statement.setInt(2, destinationPoint);
			boolean hasRoute = statement.execute();

			if (hasRoute) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					route.setRouteId(resultSet.getInt(ROUTE_ID));
					route.setDistance(resultSet.getDouble(DISTANCE));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return route;
	}

}
