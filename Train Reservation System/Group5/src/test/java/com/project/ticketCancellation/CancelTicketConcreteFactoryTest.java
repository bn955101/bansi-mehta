package com.project.ticketCancellation;

public class CancelTicketConcreteFactoryTest extends CancelTicketAbstractFactoryTest {
	private CalculateAmountTest calculateAmountTest;

	@Override
	public CalculateAmountTest createCalculateAmountTest() {
		if (calculateAmountTest == null) {
			calculateAmountTest = new CalculateAmountTest();
		}
		return new CalculateAmountTest();
	}

	@Override
	public CalculateAmountTest createNewCalculateAmountTest() {
		return new CalculateAmountTest();
	}

	@Override
	public SearchPassengerInformationDAOMock createSearchPassengerInformationDAOMock() {
		return new SearchPassengerInformationDAOMock();
	}

}
