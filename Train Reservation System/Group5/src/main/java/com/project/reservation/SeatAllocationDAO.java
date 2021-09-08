package com.project.reservation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;

public class SeatAllocationDAO implements ISeatAllocationDAO {
	public final String TOTAL_COACHES = "totalCoaches";
	public final String RESERVATION_ID = "reservationId";
	public final String COACH_NUMBER = "coachNumber";
	public final String SEAT_NUMBER = "seatNumber";
	public final String START_STATION = "startStation";
	public final String MIDDLE_STATION = "middleStations";
	public final String END_STATION = "endStation";

	public int getTotalCoaches(IReservation reservation) {
		int totalCoaches = 0;
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call getTotalCoaches(?)}");
			statement.setInt(1, reservation.getTrainId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				totalCoaches = resultSet.getInt(TOTAL_COACHES);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return totalCoaches;
	}

	public List<Integer> getTrainStationsByTrainId(IReservation reservation) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		List<Integer> stationIds = new ArrayList<Integer>();

		try {
			statement = connection.prepareCall("{call getTrain(?)}");
			statement.setInt(1, reservation.getTrainId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				stationIds.add(resultSet.getInt(START_STATION));
				String[] middleStationsList = resultSet.getString(MIDDLE_STATION).split(",");

				for (String middleStation : middleStationsList) {
					stationIds.add(Integer.valueOf(middleStation));
				}
				stationIds.add(resultSet.getInt(END_STATION));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (null != resultSet) {
				databaseUtilities.closeResultSet(resultSet);
			}
			if (null != statement) {
				databaseUtilities.closeStatement(statement);
			}
			if (null != connection) {
				databaseUtilities.closeConnection(connection);
			}
		}
		return stationIds;
	}

	public Map<String, Set<Integer>> getReservedPassengerData(IReservation reservation, List<Integer> trainStations) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		CallableStatement anotherStatement = null;
		ResultSet anotherResultSet = null;
		CallableStatement statementForEndStations = null;
		ResultSet resultSetForEndStations = null;
		List<Integer> reservationIds = new ArrayList<Integer>();
		Map<String, Set<Integer>> trainCoachesData = new HashMap<>();

		try {
			List<Integer> newAllStationsListForReservation = new ArrayList<>();
			int startStationIndex = trainStations.indexOf(reservation.getSourceStationId());
			int destinationStationIndex = trainStations.indexOf(reservation.getDestinationStationId());

			newAllStationsListForReservation
					.addAll(trainStations.subList(startStationIndex, destinationStationIndex + 1));
			if (2 < newAllStationsListForReservation.size()) {
				for (int i = 0; i < newAllStationsListForReservation.size() - 1; i++) {
					statement = connection
							.prepareCall("{call getReservationIdFromTrainIdAndStartDateAndStations( ?, ?, ?, ?)}");
					statement.setInt(1, reservation.getTrainId());
					statement.setDate(2, reservation.getStartDate());
					statement.setInt(3, newAllStationsListForReservation.get(i));
					statement.setInt(4, newAllStationsListForReservation.get(i + 1));
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						reservationIds.add(resultSet.getInt(RESERVATION_ID));
					}
				}
			}
			statementForEndStations = connection
					.prepareCall("{call getReservationIdFromTrainIdAndStartDateAndStations( ?, ?, ?, ?)}");
			statementForEndStations.setInt(1, reservation.getTrainId());
			statementForEndStations.setDate(2, reservation.getStartDate());
			statementForEndStations.setInt(3, newAllStationsListForReservation.get(0));
			statementForEndStations.setInt(4,
					newAllStationsListForReservation.get(newAllStationsListForReservation.size() - 1));
			resultSetForEndStations = statementForEndStations.executeQuery();
			while (resultSetForEndStations.next()) {
				reservationIds.add(resultSetForEndStations.getInt(RESERVATION_ID));
			}
			for (int i = 0; i < reservationIds.size(); i++) {
				anotherStatement = connection.prepareCall("{call getReservedPassengerData(?)}");
				anotherStatement.setInt(1, reservationIds.get(i));
				anotherResultSet = anotherStatement.executeQuery();
				while (anotherResultSet.next()) {
					if (trainCoachesData.containsKey(anotherResultSet.getString(COACH_NUMBER))) {
						Set<Integer> seatNumberSet = new HashSet<>();

						seatNumberSet = trainCoachesData.get(anotherResultSet.getString(COACH_NUMBER));
						seatNumberSet.add(anotherResultSet.getInt(SEAT_NUMBER));
						trainCoachesData.replace(anotherResultSet.getString(COACH_NUMBER), seatNumberSet);
					} else {
						Set<Integer> seatNumberSet = new HashSet<>();

						seatNumberSet.add(anotherResultSet.getInt(SEAT_NUMBER));
						trainCoachesData.put(anotherResultSet.getString(COACH_NUMBER), seatNumberSet);
					}
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (null != resultSet) {
				databaseUtilities.closeResultSet(resultSet);
			}
			if (null != statement) {
				databaseUtilities.closeStatement(statement);
			}
			if (null != anotherResultSet) {
				databaseUtilities.closeResultSet(anotherResultSet);
			}
			if (null != anotherStatement) {
				databaseUtilities.closeStatement(anotherStatement);
			}
			if (null != resultSetForEndStations) {
				databaseUtilities.closeResultSet(resultSetForEndStations);
			}
			if (null != statementForEndStations) {
				databaseUtilities.closeStatement(statementForEndStations);
			}
			if (null != anotherStatement) {
				databaseUtilities.closeStatement(anotherStatement);
			}
			if (null != connection) {
				databaseUtilities.closeConnection(connection);
			}
		}
		return trainCoachesData;
	}

}
