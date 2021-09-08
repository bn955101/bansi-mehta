package com.project.lookup;

import java.sql.Date;

public class BookedTicketsMock {

	public IBookedTickets createBookedTicketsMockForSeatNumberOne(IBookedTickets bookedTicket) {
		bookedTicket.setReservationId(1);
		bookedTicket.setTrainId(1);
		bookedTicket.setReservationDate(new Date(61202516585000L));
		bookedTicket.setSourceStationId(1);
		bookedTicket.setDestinationId(3);
		bookedTicket.setAmountPaid(1000);
		bookedTicket.setTicketBooked(1);
		bookedTicket.setUserId(1);
		return bookedTicket;
	}

	public IBookedTickets createBookedTicketsMockForSeatNumberTwo(IBookedTickets bookedTicket) {
		bookedTicket.setReservationId(2);
		bookedTicket.setTrainId(1);
		bookedTicket.setReservationDate(new Date(61202516585000L));
		bookedTicket.setSourceStationId(3);
		bookedTicket.setDestinationId(4);
		bookedTicket.setAmountPaid(1000);
		bookedTicket.setTicketBooked(1);
		bookedTicket.setUserId(1);
		return bookedTicket;
	}

}
