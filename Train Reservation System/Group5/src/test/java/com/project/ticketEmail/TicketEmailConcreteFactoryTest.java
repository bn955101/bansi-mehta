package com.project.ticketEmail;

public class TicketEmailConcreteFactoryTest extends TicketEmailAbstractFactoryTest{

	@Override
	public TicketEmailMock createTicketEmailMock() {
		return new TicketEmailMock();
	}
}