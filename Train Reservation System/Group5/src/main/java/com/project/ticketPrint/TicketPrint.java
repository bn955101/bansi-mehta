package com.project.ticketPrint;

import java.util.List;

import com.project.reservation.IPassengerInformation;

public class TicketPrint implements ITicketPrint {
	public int reservationId;
	public int trainCode;
	public String trainName;
	public String sourceStation;
	public String destinationStation;
	public double amountPaid;
	public String trainType;
	public List<IPassengerInformation> passengerInformation;

	public TicketPrint() {
	}

	public TicketPrint(int reservationId, int trainCode, String trainName, String sourceStation,
			String destinationStation, double amountPaid, String trainType,
			List<IPassengerInformation> passengerInformation) {
		super();
		this.reservationId = reservationId;
		this.trainCode = trainCode;
		this.trainName = trainName;
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
		this.amountPaid = amountPaid;
		this.trainType = trainType;
		this.passengerInformation = passengerInformation;
	}

	@Override
	public int getReservationId() {
		return reservationId;
	}

	@Override
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	@Override
	public int getTrainCode() {
		return trainCode;
	}

	@Override
	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	@Override
	public String getTrainName() {
		return trainName;
	}

	@Override
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	@Override
	public String getSourceStation() {
		return sourceStation;
	}

	@Override
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	@Override
	public String getDestinationStation() {
		return destinationStation;
	}

	@Override
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	@Override
	public double getAmountPaid() {
		return amountPaid;
	}

	@Override
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public String getTrainType() {
		return trainType;
	}

	@Override
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	@Override
	public List<IPassengerInformation> getPassengerInformation() {
		return passengerInformation;
	}

	@Override
	public void setPassengerInformation(List<IPassengerInformation> passengerInformation) {
		this.passengerInformation = passengerInformation;
	}
	
}