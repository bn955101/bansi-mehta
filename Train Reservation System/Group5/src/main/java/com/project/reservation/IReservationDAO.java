package com.project.reservation;

public interface IReservationDAO {

	IReservation saveReservationInformation(IReservation reservation);

	void savePassengerInformation(IReservation reservation);

}
