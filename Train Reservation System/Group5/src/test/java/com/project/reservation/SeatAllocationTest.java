package com.project.reservation;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class SeatAllocationTest {
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	
	@Test
	void testAllocateSeat() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		
		ISeatAllocation seatAllocation = reservationAbstractFactory.createNewSeatAllocation();
		ISeatAllocationDAO seatAllocationDAO = reservationAbstractFactory.createNewSeatAllocationDAO();
		
		reservation = seatAllocation.allocateSeat(reservation, seatAllocationDAO);
		assertNotNull(reservation);
	}

}
