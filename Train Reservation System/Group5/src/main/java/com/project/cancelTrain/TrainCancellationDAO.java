package com.project.cancelTrain;

import com.project.setup.ICancelTrain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;

public class TrainCancellationDAO implements ITrainCancellationDAO {
	public final String TRAIN_ID = "trainId";
	public final String RESERVATION_ID = "reservationId";
	public final String RESERVATION_AMOUNT_PAID = "totalReservationAmount";
	public final String SOURCE_STATION_ID = "sourceStationId";
	public final String DESTINATION_STATION_ID = "destinationStationId";
	public final String TICKET_BOOKED = "ticketBooked";
	public final String TRAIN_TYPE = "trainType";
	public final String START_DATE = "startDate";
	public final String TRAIN_CANCEL = "trainCancel";
	public final String AMOUNT_PAID = "totalReservationAmount";
	public final String PASSENGER_INFORMATION_ID = "passengerInformationId";
	public final String FIRST_NAME = "firstName";
	public final String LAST_NAME = "lastName";
	public final String GENDER = "gender";
	public final String AGE = "age";
	public final String BERTH_PREFERNECE = "berthPreference";
	public final String SEAT_NUMBER = "seatNumber";
	public final String COACH_NUMBER = "coachNumber";

	@SuppressWarnings("resource")
	@Override
	public List<IReservation> fetchAllReservations(ICancelTrain cancelTrain) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		int trainId = 0;
		List<IReservation> reservationList = new ArrayList<IReservation>(0);
		
		try {
			statement = connection.prepareCall("{call getTrainId(?)}");
			statement.setInt(1, cancelTrain.getTrainCode());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				trainId = resultSet.getInt(TRAIN_ID);
			}
			if (trainId > 0) {
				statement = connection.prepareCall("{call getAllReservations(?, ?)}");
				statement.setInt(1, trainId);
				statement.setString(2, cancelTrain.getCancellationDate().toString());
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					int reservationId = resultSet.getInt(RESERVATION_ID);
					int indexExists = -1;
					
					for (int index = 0; index < reservationList.size(); index++) {
						if (reservationId == reservationList.get(index).getReservationId()) {
							indexExists = index;
							break;
						}
					}
					if (indexExists >= 0) {
						IReservation reservation = reservationList.get(indexExists);
						IPassengerInformation passengerInformation = reservationAbstractFactory
								.createNewPassengerInformation();
						
						passengerInformation.setAge(resultSet.getInt(AGE));
						passengerInformation.setBerthPreference(resultSet.getString(BERTH_PREFERNECE));
						passengerInformation.setFirstName(resultSet.getString(FIRST_NAME));
						passengerInformation.setGender(resultSet.getString(GENDER));
						passengerInformation.setLastName(resultSet.getString(LAST_NAME));
						passengerInformation.setPassengerInformationId(resultSet.getInt(PASSENGER_INFORMATION_ID));
						passengerInformation.setReservationId(reservationId);
						reservation.addInPassengerInformationList(reservation.getPassengerInformation(),
								passengerInformation);
						reservationList.set(indexExists, reservation);
					} else {
						IReservation reservation = reservationAbstractFactory.createNewReservation();
						List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
						IPassengerInformation passengerInformation = reservationAbstractFactory
								.createNewPassengerInformation();

						reservation.setAmountPaid(resultSet.getDouble(RESERVATION_AMOUNT_PAID));
						reservation.setDestinationStationId(resultSet.getInt(DESTINATION_STATION_ID));
						reservation.setReservationId(resultSet.getInt(RESERVATION_ID));
						reservation.setSourceStationId(resultSet.getInt(SOURCE_STATION_ID));
						reservation.setStartDate(resultSet.getDate(START_DATE));
						reservation.setTrainCancelEvent(resultSet.getString(TRAIN_CANCEL));
						reservation.setTrainId(resultSet.getInt(TRAIN_ID));
						reservation.setTrainType(resultSet.getString(TRAIN_TYPE));
						reservation.setReservationId(reservationId);
						reservation.setTicketBooked(resultSet.getInt(TICKET_BOOKED));

						passengerInformation.setAge(resultSet.getInt(AGE));
						passengerInformation.setBerthPreference(resultSet.getString(BERTH_PREFERNECE));
						passengerInformation.setFirstName(resultSet.getString(FIRST_NAME));
						passengerInformation.setGender(resultSet.getString(GENDER));
						passengerInformation.setLastName(resultSet.getString(LAST_NAME));
						passengerInformation.setPassengerInformationId(resultSet.getInt(PASSENGER_INFORMATION_ID));
						passengerInformation.setReservationId(reservationId);
						passengerInformationList.add(passengerInformation);
						reservation.setPassengerInformation(passengerInformationList);
						reservationList.add(reservation);
					}
				}
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return reservationList;
	}
}
