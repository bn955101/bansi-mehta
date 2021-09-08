package com.project.ticketEmail;

public class TicketEmailConcreteFactory extends TicketEmailAbstractFactory{

	private ITicketEmail ticketEmail;
	private ITicketEmailDAO ticketEmailDAO;
	
	@Override
	public ITicketEmailDAO createTicketEmailDAO() {
		if (ticketEmailDAO == null) {
			ticketEmailDAO = new TicketEmailDAO();
		}
		return ticketEmailDAO;
	}
	
	@Override
	public ITicketEmailDAO createNewTicketEmailDAO() {
		return new TicketEmailDAO();
	}
	
	@Override
	public ITicketEmail createTicketEmail() {
		if(null == ticketEmail) {
			ticketEmail = new TicketEmail();
		}
		return ticketEmail;
	}
	
	@Override
	public ITicketEmail createNewTicketEmail() {
		return new TicketEmail();
	}
}
