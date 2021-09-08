package com.project.lookup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;

@Component
public class SeatAvailibilityDAO implements ISeatAvailibilityDAO {
	public final String MAX_SEATNO = "maxSeatNo";
	public final String RESERVATION_ID = "reservationId";
	public final String TRAIN_ID = "trainId";
	public final String DATE = "reservationDate";
	public final String SOURCE_STATION_ID = "sourceStationId";
	public final String DESTINATION_STATION_ID = "destinationStationId";
	public final String AMOUNT_PAID = "amountPaid";
	public final String TICKET_BOOKED = "ticketBooked";

	@Override
	public List<IBookedTickets> getListOfTicketsFromSeatNo(ITrain train, Date date, int seatNo) {
		List<IBookedTickets> bookedTickets = new ArrayList<IBookedTickets>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;

		try {
			statment = connection.prepareCall("{call getBookedTicketsOFThatDay( ? , ? , ?)}");
			statment.setDate(1, date);
			statment.setInt(2, train.getTrainId());
			statment.setInt(3, seatNo);
			boolean hadResult = statment.execute();

			if (hadResult) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					IBookedTickets ticket = lookupAbstractFactory.createNewBookedTickets();
					ticket.setReservationId(resultSet.getInt(RESERVATION_ID));
					ticket.setTrainId(resultSet.getInt(TRAIN_ID));
					ticket.setReservationDate(resultSet.getDate(DATE));
					ticket.setSourceStationId(resultSet.getInt(SOURCE_STATION_ID));
					ticket.setDestinationId(resultSet.getInt(DESTINATION_STATION_ID));
					ticket.setAmountPaid(resultSet.getDouble(AMOUNT_PAID));
					ticket.setTicketBooked(resultSet.getInt(TICKET_BOOKED));
					bookedTickets.add(ticket);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return bookedTickets;
	}

	@Override
	public List<Integer> getReservationId(ITrain train, Date date) {
		int reservationId = 0;
		List<Integer> reservationIds = new ArrayList<Integer>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;

		try {
			statment = connection.prepareCall("{call getReservationId( ? , ? )}");
			statment.setInt(1, train.getTrainId());
			statment.setDate(2, date);
			boolean hadResult = statment.execute();

			if (hadResult) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					reservationId = resultSet.getInt(RESERVATION_ID);
					reservationIds.add(reservationId);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return reservationIds;
	}

	@Override
	public int maximumSeatNumberOfReservationId(int reservationId) {
		int maximumNumber = 0;
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;

		try {
			statment = connection.prepareCall("{call getMaximumSeatNo( ? )}");
			statment.setInt(1, reservationId);
			boolean hadResult = statment.execute();

			if (hadResult) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					maximumNumber = resultSet.getInt(MAX_SEATNO);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return maximumNumber;
	}

}
