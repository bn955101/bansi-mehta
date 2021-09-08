package com.project.reservation;

public interface ISeatAllocation {

	IReservation allocateSeat(IReservation reservation, ISeatAllocationDAO seatAllocationDAO);

}
