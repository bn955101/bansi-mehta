package com.project.reservation;

public class ReservationConcreteFactory extends ReservationAbstractFactory {
	public IPassengerInformation passengerInformation;
	public IReservation reservation;
	public IReservationDAO reservationDAO;
	private ISeatAllocationDAO seatAllocationDAO;
	private ISeatAllocation seatAllocation;
	
	@Override
	public IReservation createReservation() {
		if  (reservation == null) {
			reservation = new Reservation();
		}
		return reservation;
	}
	
	@Override
	public IReservation createNewReservation() {
		return new Reservation();
	}
	
	@Override
	public IPassengerInformation createPassengerInformation() {
		if (passengerInformation == null) {
			passengerInformation = new PassengerInformation();
		}
		return passengerInformation;
	}
	
	@Override
	public IPassengerInformation createNewPassengerInformation() {
		return new PassengerInformation();
	}
	
	@Override
	public IReservationDAO createReservationDAO() {
		if (null == reservationDAO) {
			reservationDAO = new ReservationDAO();
		}
		return reservationDAO;
	}
	
	@Override
	public IReservationDAO createNewReservationDAO() {
		return new ReservationDAO();
	}
	
	@Override
	public ISeatAllocationDAO createSeatAllocationDAO() {
		if(null == seatAllocationDAO) {
			seatAllocationDAO = new SeatAllocationDAO();
		}
		return seatAllocationDAO;
	}
	
	@Override
	public ISeatAllocationDAO createNewSeatAllocationDAO() {
		return new SeatAllocationDAO();
	}
	
	@Override
	public ISeatAllocation createSeatAllocation() {
		if(null == seatAllocation) {
			seatAllocation = new SeatAllocation();
		}
		return seatAllocation;
	}
	
	@Override
	public ISeatAllocation createNewSeatAllocation() {
		return new SeatAllocation();
	}
	
}
