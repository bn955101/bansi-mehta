package com.project.reservation;

public class ReservationDAOMock implements IReservationDAO {

	@Override
	public IReservation saveReservationInformation(IReservation reservation) {
		reservation.setReservationId(1);
		return reservation;
	}

	@Override
	public void savePassengerInformation(IReservation reservation) {

	}

}
