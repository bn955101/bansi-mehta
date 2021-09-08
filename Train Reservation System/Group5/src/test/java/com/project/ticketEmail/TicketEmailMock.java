package com.project.ticketEmail;

public class TicketEmailMock {

	public ITicketEmail createTicketPrintrMock(ITicketEmail ticketPrint) {
		ticketPrint.setReservationId(111);
		ticketPrint.setTrainCode(123);
		ticketPrint.setTrainName("ABC");
		ticketPrint.setTrainType("AC Seater");
		ticketPrint.setAmountPaid(123.0);
		ticketPrint.setSourceStation("Station A");
		ticketPrint.setDestinationStation("Station B");
		return ticketPrint;
	}
}