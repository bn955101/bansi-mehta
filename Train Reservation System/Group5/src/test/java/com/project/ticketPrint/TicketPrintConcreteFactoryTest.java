package com.project.ticketPrint;

public class TicketPrintConcreteFactoryTest extends TicketPrintAbstractFactoryTest {

	@Override
	public TicketPrintMock createTicketPrintMock() {
		return new TicketPrintMock();
	}

}