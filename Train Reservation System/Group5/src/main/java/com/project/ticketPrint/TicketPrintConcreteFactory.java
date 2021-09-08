package com.project.ticketPrint;

public class TicketPrintConcreteFactory extends TicketPrintAbstractFactory {
	private ITicketPrint ticketPrint;
	private ITicketPrintDAO ticketPrintDAO;
	
	@Override
	public ITicketPrintDAO createNewTicketPrintDAO() {
		return new TicketPrintDAO();
	}

	@Override
	public ITicketPrintDAO createTicketPrintDAO() {
		if (null == ticketPrintDAO) {
			ticketPrintDAO = new TicketPrintDAO();
		}
		return ticketPrintDAO;
	}

	@Override
	public ITicketPrint createTicketPrint() {
		if (null == ticketPrint) {
			ticketPrint = new TicketPrint();
		}
		return ticketPrint;
	}

	@Override
	public ITicketPrint createNewTicketPrint() {
		return new TicketPrint();
	}
}
