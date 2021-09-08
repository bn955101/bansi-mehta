package com.project.ticketCancellation;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;
import com.project.reservation.ReservationAbstractFactoryTest;
import com.project.reservation.ReservationMock;

public class CalculateAmountTest {
	CancelTicketAbstractFactoryTest cancelTicketAbstractFactoryTest = CancelTicketAbstractFactoryTest.instance();
	CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();

	@Test
	void testCalculateRefundAmount() {
		SearchPassengerInformationDAOMock searchPassengerInformationDAOMock = cancelTicketAbstractFactoryTest
				.createSearchPassengerInformationDAOMock();
		ICalculateAmount calculateAmount = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		List<Integer> idList = new ArrayList<>();

		reservation = reservationMock.creteReservationMock(reservation);
		idList.add(1);
		idList.add(2);
		double amount = calculateAmount.calculateRefundAmount(reservation, idList, searchPassengerInformationDAOMock);
		assertNotEquals(10.0, amount);
	}

	@Test
	void testCalculateDiscount() {
		ICalculateAmount calculateAmount = cancelTicketAbstractFactory.createNewCalculateAmounts();
		Date date = Date.valueOf("2021-04-04");
		double amount = calculateAmount.calculateDiscount(1000.0, 500.0, date, "09:00");
		
		assertEquals(250.0, amount);
	}
}
