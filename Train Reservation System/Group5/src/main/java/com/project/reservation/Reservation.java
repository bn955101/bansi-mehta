package com.project.reservation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.project.lookup.ITrainFilterAndFairCalculation;
import com.project.lookup.LookupAbstractFactory;

public class Reservation implements IReservation {
	public int reservationId;
	public int trainId;
	public int sourceStationId;
	public int destinationStationId;
	public double amountPaid;
	public double distance;
	public String trainType;
	public String trainCancelEvent;
	public Date startDate;
	public int ticketBooked;
	public int deletedTicket;
	public List<IPassengerInformation> passengerInformation;
	public static final int NUMBER_OF_PASSENGER_PER_RESERVATION = 6;

	public Reservation() {
		this.initializePassengerList();
	}

	private void initializePassengerList() {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(
				NUMBER_OF_PASSENGER_PER_RESERVATION);

		for (int index = 0; index < NUMBER_OF_PASSENGER_PER_RESERVATION; index++) {
			this.addInPassengerInformationList(passengerInformationList,
					reservationAbstractFactory.createNewPassengerInformation());
		}
		this.setPassengerInformation(passengerInformationList);
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
	public int getTrainId() {
		return trainId;
	}

	@Override
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	@Override
	public int getSourceStationId() {
		return sourceStationId;
	}

	@Override
	public void setSourceStationId(int sourceStationId) {
		this.sourceStationId = sourceStationId;
	}

	@Override
	public int getDestinationStationId() {
		return destinationStationId;
	}

	@Override
	public void setDestinationStationId(int destinationStationId) {
		this.destinationStationId = destinationStationId;
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
	public List<IPassengerInformation> getPassengerInformation() {
		return passengerInformation;
	}

	@Override
	public void addInPassengerInformationList(List<IPassengerInformation> passengerInformationList,
			IPassengerInformation passengerInformation) {
		passengerInformationList.add(passengerInformation);
	}

	@Override
	public void setPassengerInformation(List<IPassengerInformation> passengerInformation) {
		this.passengerInformation = passengerInformation;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public double getDistance() {
		return this.distance;
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
	public String getTrainCancelEvent() {
		return trainCancelEvent;
	}

	@Override
	public void setTrainCancelEvent(String trainCancelEvent) {
		this.trainCancelEvent = trainCancelEvent;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public int getTicketBooked() {
		return ticketBooked;
	}

	@Override
	public void setTicketBooked(int ticketBooked) {
		this.ticketBooked = ticketBooked;
	}

	@Override
	public void setDeletedTicket(int deletedTicket) {
		this.deletedTicket = deletedTicket;
	}

	@Override
	public int getDeletedTicket() {
		return deletedTicket;
	}

	@Override
	public void calculateReservationFarePerPassenger(IReservation reservation) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation findFare = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();

		try {
			double fareBasedOnTrainType = findFare.calculateFareByTrainType(reservation.getDistance(),
					reservation.getTrainType());
			double fareBasedOnDistance = findFare.calculateFareByDistance(reservation.getDistance(),
					fareBasedOnTrainType);
			int passengerInformationLength = reservation.getPassengerInformation().size();

			for (int index = 0; index < passengerInformationLength; index++) {
				double amountPaid = findFare.calculateFareByAge(fareBasedOnDistance,
						reservation.getPassengerInformation().get(index).getAge());

				reservation.getPassengerInformation().get(index).setPassengerInformationId(0);
				reservation.getPassengerInformation().get(index).setReservationId(0);
				reservation.getPassengerInformation().get(index).setAmountPaid(amountPaid);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public IReservation calculateTotalReservationFare(IReservation reservation) {
		this.calculateReservationFarePerPassenger(reservation);
		int passengerInformationLength = reservation.getPassengerInformation().size();
		double amountPaid = 0.0;

		for (int index = 0; index < passengerInformationLength; index++) {
			amountPaid = amountPaid + reservation.getPassengerInformation().get(index).getAmountPaid();
		}
		reservation.setAmountPaid(amountPaid);
		reservation.setReservationId(0);
		return reservation;
	}

	@Override
	public void removeEmptyPassengerRow(IReservation reservation) {
		List<IPassengerInformation> passengerInformation = reservation.getPassengerInformation();
		List<IPassengerInformation> nonEmptyPassengerInformation = new ArrayList<IPassengerInformation>(0);

		for (int index = 0; index < passengerInformation.size(); index++) {
			boolean rowNonEmpty = passengerInformation.get(index).isRowNonEmpty();

			if (rowNonEmpty) {
				nonEmptyPassengerInformation.add(passengerInformation.get(index));
			}
		}
		reservation.setPassengerInformation(nonEmptyPassengerInformation);
	}

	@Override
	public boolean isPassengerListEmpty(IReservation reservation) {
		if (reservation.getPassengerInformation().size() <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public String validateReservation(IReservation reservation) {
		String errorMessages = "";

		if (this.isPassengerListEmpty(reservation)) {
			errorMessages += ReservationInformationErrorCodes.emptyPassengerList;
			return errorMessages;
		} else {
			for (int index = 0; index < reservation.getPassengerInformation().size(); index++) {
				errorMessages += reservation.getPassengerInformation().get(index).isPassengerInformationValid();
			}
		}
		return errorMessages;
	}

}
