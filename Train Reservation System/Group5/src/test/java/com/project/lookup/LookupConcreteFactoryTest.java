package com.project.lookup;

public class LookupConcreteFactoryTest extends LookupAbstractFactoryTest {

	@Override
	public SearchTrainMock createSearchTrainMock() {
		return new SearchTrainMock();
	}

	@Override
	public SeatAvailibilityDAOMock createSeatAvailibilityDAOMock() {
		return new SeatAvailibilityDAOMock();
	}

	@Override
	public BookedTicketsMock createBookedTicketsMock() {
		return new BookedTicketsMock();
	}

	@Override
	public SearchTrainDAOMock createSearchTrainDAOMock() {
		return new SearchTrainDAOMock();
	}

}
