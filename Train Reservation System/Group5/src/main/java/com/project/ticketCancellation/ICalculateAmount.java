package com.project.ticketCancellation;

import java.sql.Date;
import java.util.List;

import com.project.reservation.IReservation;

public interface ICalculateAmount {

	public double calculateDiscount(double amountPaid, double refundedAmount, Date trainStartDate,
			String departureTime);

	public double calculateRefundAmount(IReservation reservation, List<Integer> ids,
			ISearchPassengerInformationDAO searchTicketInfo);

}
