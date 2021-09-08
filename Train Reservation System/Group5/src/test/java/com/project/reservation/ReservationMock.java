package com.project.reservation;

import java.sql.Date;

public class ReservationMock {

	public IReservation creteReservationMock(IReservation reservation) {
		Date date = Date.valueOf("2021-04-08");

		reservation.setAmountPaid(100.0);
		reservation.setDestinationStationId(2);
		reservation.setDistance(100);
		reservation.setReservationId(1);
		reservation.setSourceStationId(1);
		reservation.setTrainId(1);
		reservation.setTrainType("Non AC Sleeper");
		reservation.setTrainCancelEvent("Cancel Train");
		reservation.setStartDate(date);
		reservation.setTicketBooked(1);
		reservation.setDeletedTicket(0);
		return reservation;
	}

}
