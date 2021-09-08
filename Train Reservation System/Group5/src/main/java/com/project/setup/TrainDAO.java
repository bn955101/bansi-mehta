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
public class TrainDAO implements ITrainDAO {

	@Override
	public List<Train> getAllTrain() {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		List<Train> listOfTrain = new ArrayList<Train>();
		listOfTrain.removeAll(listOfTrain);
		CallableStatement statement = null;

		try {
			statement = connection.prepareCall("{call getAllTrain()}");
			boolean hasResultsForList = statement.execute();

			if (hasResultsForList) {
				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					Train train = new Train();

					train.setTrainId(resultSet.getInt(1));
					train.setTrainCode(resultSet.getInt(2));
					train.setTrainName(resultSet.getString(3));
					train.setTrainType(resultSet.getString(4));
					train.setDays(resultSet.getString(5));
					train.setDepartureTime(resultSet.getString(6));
					train.setTotalCoaches(resultSet.getInt(7));
					train.setStartStation(resultSet.getString(8));
					train.setMiddleStations(resultSet.getString(9));
					train.setEndStation(resultSet.getString(10));
					String middleStationString = "";

					if (resultSet.getString(9) == null) {

					} else {
						String[] middleStations = resultSet.getString(9).split(",");

						for (String midd : middleStations) {
							int middle = Integer.parseInt(midd);
							CallableStatement statement1 = connection.prepareCall("{call getMiddleStation(?)}");

							statement1.setInt(1, middle);
							boolean hasResultsForList1 = statement1.execute();

							if (hasResultsForList1) {
								ResultSet resultSet1 = statement1.getResultSet();

								while (resultSet1.next()) {
									middleStationString += resultSet1.getString(1) + ",";
								}
							}
						}
						train.setMiddleStations(middleStationString);
					}
					listOfTrain.add(train);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return listOfTrain;
	}

	@Override
	public boolean saveTrain(ITrain train) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		boolean validRoute = validateRoutes(connection, train);

		if (validRoute) {
			if (train.getTrainId() == 0) {
				try {
					CallableStatement statement = connection.prepareCall("{call addTrain( ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

					statement.setInt(1, train.getTrainCode());
					statement.setString(2, train.getTrainName());
					statement.setString(3, train.getTrainType());
					statement.setString(4, train.getDays());
					statement.setString(5, train.getDepartureTime());
					statement.setInt(6, train.getTotalCoaches());
					statement.setInt(7, Integer.parseInt(train.getStartStation()));
					statement.setString(8, train.getMiddleStations());
					statement.setInt(9, Integer.parseInt(train.getEndStation()));
					statement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				CallableStatement statement = null;
				try {
					statement = connection.prepareCall("{call editTrain( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
					statement.setInt(1, train.getTrainId());
					statement.setInt(2, train.getTrainCode());
					statement.setString(3, train.getTrainName());
					statement.setString(4, train.getTrainType());
					statement.setString(5, train.getDays());
					statement.setString(6, train.getDepartureTime());
					statement.setInt(7, train.getTotalCoaches());
					statement.setInt(8, Integer.parseInt(train.getStartStation()));
					statement.setString(9, train.getMiddleStations());
					statement.setInt(10, Integer.parseInt(train.getEndStation()));
					statement.execute();
				} catch (SQLException exception) {
					exception.printStackTrace();
				} finally {
					databaseUtilities.closeStatement(statement);
					databaseUtilities.closeConnection(connection);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean validateRoutes(Connection connection, ITrain train) {
		List<Integer> allStations = new ArrayList<>();

		allStations.add(Integer.parseInt(train.getStartStation()));
		if (train.getMiddleStations() == null) {
		} else {
			String[] middleStationsList = train.getMiddleStations().split(",");

			for (String mid : middleStationsList) {
				allStations.add(Integer.parseInt(mid));
			}
		}
		allStations.add(Integer.parseInt(train.getEndStation()));
		boolean routeFound = true;

		for (int i = 0; i < allStations.size() - 1; i++) {
			CallableStatement statement;
			try {
				statement = connection.prepareCall("{call checkRoute( ?, ?)}");
				statement.setInt(1, allStations.get(i));
				statement.setInt(2, allStations.get(i + 1));
				boolean hasResultsForList1 = statement.execute();

				if (hasResultsForList1) {
					ResultSet resultSet = statement.getResultSet();
					int routeId = -1;

					while (resultSet.next()) {
						routeId = resultSet.getInt(1);
					}
					if (routeId > 0) {
						routeFound = true;
					} else {
						routeFound = false;
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return routeFound;
	}

	@Override
	public ITrain getTrain(Integer trainId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ITrain train = new Train();

		try {
			statement = connection.prepareCall("{call getTrain(?)}");
			statement.setInt(1, trainId);
			boolean hasResult = statement.execute();

			if (hasResult) {
				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					train.setTrainId(resultSet.getInt(1));
					train.setTrainCode(resultSet.getInt(2));
					train.setTrainName(resultSet.getString(3));
					train.setTrainType(resultSet.getString(4));
					train.setDays(resultSet.getString(5));
					train.setDepartureTime(resultSet.getString(6));
					train.setTotalCoaches(resultSet.getInt(7));
					train.setStartStation(String.valueOf(resultSet.getInt(8)));
					train.setMiddleStations(resultSet.getString(9));
					train.setEndStation(String.valueOf(resultSet.getInt(10)));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return train;
	}

	@Override
	public void deleteTrain(Integer trainId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;

		try {
			statement = connection.prepareCall("{call deleteTrain( ? )}");
			statement.setInt(1, trainId);
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
	}

}
