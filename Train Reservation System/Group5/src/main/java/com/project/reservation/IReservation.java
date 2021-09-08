package com.project.reservation;

import java.sql.Date;
import java.util.List;

public interface IReservation {

	int getReservationId();

	void setReservationId(int reservationId);

	int getTrainId();

	void setTrainId(int trainId);

	int getSourceStationId();

	void setSourceStationId(int sourceStationId);

	int getDestinationStationId();

	void setDestinationStationId(int destinationStationId);

	double getAmountPaid();

	void setAmountPaid(double amountPaid);

	List<IPassengerInformation> getPassengerInformation();

	void setPassengerInformation(List<IPassengerInformation> passengerInformation);

	void setDistance(double distance);

	double getDistance();

	String getTrainType();

	void setTrainType(String trainType);

	void calculateReservationFarePerPassenger(IReservation reservation);

	IReservation calculateTotalReservationFare(IReservation reservation);

	void addInPassengerInformationList(List<IPassengerInformation> passengerInformationList,
			IPassengerInformation passengerInformation);

	void removeEmptyPassengerRow(IReservation reservation);

	String validateReservation(IReservation reservation);

	boolean isPassengerListEmpty(IReservation reservation);

	void setTrainCancelEvent(String trainCancelEvent);

	String getTrainCancelEvent();

	void setStartDate(Date startDate);

	Date getStartDate();

	void setTicketBooked(int ticketBooked);

	int getTicketBooked();

	void setDeletedTicket(int deletedTicket);

	int getDeletedTicket();

}