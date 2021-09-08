package com.project.lookup;

import java.sql.Date;

public class BookedTickets implements IBookedTickets {
	private int reservationId;
	private int trainId;
	private Date reservationDate;
	private int sourceStationId;
	private int destinationId;
	private double amountPaid;
	private int ticketBooked;
	private int userId;

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
	public Date getReservationDate() {
		return reservationDate;
	}

	@Override
	public void setReservationDate(Date date) {
		this.reservationDate = date;
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
	public int getDestinationId() {
		return destinationId;
	}

	@Override
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
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
	public int getTicketBooked() {
		return ticketBooked;
	}

	@Override
	public void setTicketBooked(int ticketBooked) {
		this.ticketBooked = ticketBooked;
	}

	@Override
	public int getUserId() {
		return userId;
	}

	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
