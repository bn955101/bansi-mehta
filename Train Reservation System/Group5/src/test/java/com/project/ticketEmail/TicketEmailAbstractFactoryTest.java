package com.project.ticketEmail;

public abstract class TicketEmailAbstractFactoryTest {
	
	private static TicketEmailAbstractFactoryTest instance = null;
	
	public static TicketEmailAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new TicketEmailConcreteFactoryTest();
		}
		return instance;
	}
	
	public abstract TicketEmailMock createTicketEmailMock();
	
}