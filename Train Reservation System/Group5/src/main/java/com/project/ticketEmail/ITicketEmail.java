package com.project.ticketEmail;

import java.util.List;

import com.project.reservation.IPassengerInformation;

public interface ITicketEmail {

	int getReservationId();

	void setReservationId(int reservationId);

	int getTrainCode();

	void setTrainCode(int trainCode);
	
	String getTrainName();

	void setTrainName(String trainName);

	String getSourceStation();

	void setSourceStation(String sourceStation);

	String getDestinationStation();

	void setDestinationStation(String destinationStation);

	double getAmountPaid();

	void setAmountPaid(double amountPaid);

	String getTrainType();

	void setTrainType(String trainType);

	List<IPassengerInformation> getPassengerInformation();

	void setPassengerInformation(List<IPassengerInformation> passengerInformation);	
}
