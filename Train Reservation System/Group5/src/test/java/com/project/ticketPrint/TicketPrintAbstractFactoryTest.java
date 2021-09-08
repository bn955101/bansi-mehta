package com.project.ticketPrint;

public abstract class TicketPrintAbstractFactoryTest {
	private static TicketPrintAbstractFactoryTest instance = null;

	public static TicketPrintAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new TicketPrintConcreteFactoryTest();
		}
		return instance;
	}

	public abstract TicketPrintMock createTicketPrintMock();

}