package com.project.reservation;

public abstract class ReservationAbstractFactory {
	private static ReservationAbstractFactory instance = null;
	
	public static ReservationAbstractFactory instance() {
        if (instance == null) {
            instance = new ReservationConcreteFactory();
        }
        return instance;
    }
	
	public abstract IReservation createReservation();
	
	public abstract IReservation createNewReservation();
	
	public abstract IPassengerInformation createPassengerInformation();
	
	public abstract IPassengerInformation createNewPassengerInformation();
	
	public abstract IReservationDAO createNewReservationDAO();
	
	public abstract IReservationDAO createReservationDAO();
	
	public abstract ISeatAllocationDAO createNewSeatAllocationDAO();
	
	public abstract ISeatAllocationDAO createSeatAllocationDAO();
	
	public abstract ISeatAllocation createNewSeatAllocation();
	
	public abstract ISeatAllocation createSeatAllocation();
	
}
