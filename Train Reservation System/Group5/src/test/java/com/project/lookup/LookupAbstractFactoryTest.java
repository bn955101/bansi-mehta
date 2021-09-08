package com.project.lookup;

public abstract class LookupAbstractFactoryTest {
	private static LookupAbstractFactoryTest instance = null;

	public static LookupAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new LookupConcreteFactoryTest();
		}
		return instance;
	}

	public abstract SearchTrainMock createSearchTrainMock();

	public abstract SearchTrainDAOMock createSearchTrainDAOMock();

	public abstract SeatAvailibilityDAOMock createSeatAvailibilityDAOMock();

	public abstract BookedTicketsMock createBookedTicketsMock();

}
