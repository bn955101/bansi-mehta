package com.project.reservation;

public abstract class ReservationAbstractFactoryTest {
	private static ReservationAbstractFactoryTest instance = null;

	public static ReservationAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new ReservationConcreteFactoryTest();
		}
		return instance;
	}
	
	public abstract SeatAllocationDAOMock createSeatAllocationDAOMock();

	public abstract PassengerMock createPassengerMock();

	public abstract ReservationMock createReservationMock();

	public abstract ReservationDAOMock createReservationDAOMock();

}
