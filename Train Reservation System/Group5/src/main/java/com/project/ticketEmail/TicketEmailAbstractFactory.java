package com.project.ticketEmail;

public abstract class TicketEmailAbstractFactory {

	private static TicketEmailAbstractFactory instance = null;
	
	public abstract ITicketEmail createTicketEmail();
	public abstract ITicketEmail createNewTicketEmail();
	public abstract ITicketEmailDAO createTicketEmailDAO();
	public abstract ITicketEmailDAO createNewTicketEmailDAO();
	
	public static TicketEmailAbstractFactory instance() {
		if (null == instance) {
			instance = new TicketEmailConcreteFactory();
		}
		return instance;
	}
}
