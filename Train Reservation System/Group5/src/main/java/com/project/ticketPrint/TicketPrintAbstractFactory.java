package com.project.ticketPrint;

public abstract class TicketPrintAbstractFactory {
	private static TicketPrintAbstractFactory instance = null;
	public abstract ITicketPrint createTicketPrint();
	public abstract ITicketPrint createNewTicketPrint();
	public abstract ITicketPrintDAO createTicketPrintDAO();
	public abstract ITicketPrintDAO createNewTicketPrintDAO();
	
	public static TicketPrintAbstractFactory instance() {
		if (null == instance) {
			instance = new TicketPrintConcreteFactory();
		}
		return instance;
	}
	
}
