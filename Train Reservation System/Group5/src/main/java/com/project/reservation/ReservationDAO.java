package com.project.reservation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;

public class ReservationDAO implements IReservationDAO {
	public final String RESERVATION_ID = "reservationId";
	public final String TRAIN_ID = "trainId";
	public final String RESERVATION_DATE = "reservationDate";
	public final String SOURCE_STATION_ID = "sourceStationId";
	public final String DESTINATION_STATION_ID = "destinationStationId";
	public final String AMOUNT_PAID = "amountPaid";
	public final String TRAIN_TYPE = "trainType";

	@Override
	public IReservation saveReservationInformation(IReservation reservation) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call addReservation( ? , ? , ?, ?, ?, ?, ?, ?)}");
			statement.setInt(1, reservation.getTrainId());
			statement.setString(2, reservation.getTrainType());
			statement.setInt(3, reservation.getSourceStationId());
			statement.setInt(4, reservation.getDestinationStationId());
			statement.setDouble(5, reservation.getAmountPaid());
			statement.setInt(6, reservation.getPassengerInformation().size());
			statement.setString(7, reservation.getTrainCancelEvent());
			statement.setDate(8, reservation.getStartDate());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int reservationId = resultSet.getInt(RESERVATION_ID);

				reservation.setReservationId(reservationId);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return reservation;
	}

	@Override
	public void savePassengerInformation(IReservation reservation) {
		if (reservation.getReservationId() > 0) {
			DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
			IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
			Connection connection = databaseUtilities.establishConnection();
			CallableStatement statement = null;

			try {
				statement = connection.prepareCall("{call addPassengerInformation( ? , ? , ?, ?, ?, ?, ?, ?, ?)}");
				if (reservation.getPassengerInformation().size() > 0) {
					for (int index = 0; index < reservation.getPassengerInformation().size(); index++) {
						IPassengerInformation passengerInformation = reservation.getPassengerInformation().get(index);

						statement.setInt(1, reservation.getReservationId());
						statement.setString(2, passengerInformation.getFirstName());
						statement.setString(3, passengerInformation.getLastName());
						statement.setString(4, passengerInformation.getGender());
						statement.setInt(5, passengerInformation.getAge());
						statement.setString(6, passengerInformation.getBerthPreference());
						statement.setString(7, String.valueOf(passengerInformation.getSeatNumber()));
						statement.setString(8, passengerInformation.getCoachNumber());
						statement.setDouble(9, passengerInformation.getAmountPaid());
						statement.execute();
					}
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			} finally {
				databaseUtilities.closeStatement(statement);
				databaseUtilities.closeConnection(connection);
			}
		}
	}

}
