package com.project.cancelTrain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.project.lookup.LookupAbstractFactoryTest;
import com.project.lookup.SearchTrainDAOMock;
import com.project.lookup.SeatAvailibilityDAOMock;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.PassengerMock;
import com.project.reservation.ReservationAbstractFactory;
import com.project.reservation.ReservationAbstractFactoryTest;
import com.project.reservation.ReservationDAOMock;
import com.project.reservation.ReservationMock;
import com.project.setup.RouteDAOMock;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.ticketCancellation.CancelTicketAbstractFactoryTest;
import com.project.ticketCancellation.SearchPassengerInformationDAOMock;

class TrainCancellationTest {
	CancelTrainAbstractFactory cancelTrainAbstractFactory = CancelTrainAbstractFactory.instance();

	@Test
	void testRescheduleOnWeekDays() {
		LookupAbstractFactoryTest lookupConcreteFactoryTest = LookupAbstractFactoryTest.instance();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		CancelTicketAbstractFactoryTest cancelTicketAbstractFactoryTest = CancelTicketAbstractFactoryTest.instance();
		ITrainCancellation trainCancellation = cancelTrainAbstractFactory.createNewTrainCancellation();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
		SearchTrainDAOMock searchTrainDAOMock = lookupConcreteFactoryTest.createSearchTrainDAOMock();
		RouteDAOMock routeDAOMock = setupAbstractFactoryTest.createRouteDAOMock();
		SeatAvailibilityDAOMock seatAvailabilityDAOMock = lookupConcreteFactoryTest.createSeatAvailibilityDAOMock();
		ReservationDAOMock reservationDAOMock = reservationAbstractFactoryTest.createReservationDAOMock();
		SearchPassengerInformationDAOMock searchPassengerInformationDAOMock = cancelTicketAbstractFactoryTest
				.createSearchPassengerInformationDAOMock();

		reservation = reservationMock.creteReservationMock(reservation);
		passenger = passengerMock.createPassengerMock(passenger);
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		trainCancellation.rescheduleOnWeekDays(reservation, searchTrainDAOMock, routeDAOMock, seatAvailabilityDAOMock,
				searchPassengerInformationDAOMock, reservationDAOMock);

		assertEquals(0, reservation.getDeletedTicket());
	}

	@Test
	void testRescheduleOnWeekEnds() {
		LookupAbstractFactoryTest lookupConcreteFactoryTest = LookupAbstractFactoryTest.instance();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		CancelTicketAbstractFactoryTest cancelTicketAbstractFactoryTest = CancelTicketAbstractFactoryTest.instance();
		ITrainCancellation trainCancellation = cancelTrainAbstractFactory.createNewTrainCancellation();
		SearchTrainDAOMock searchTrainDAOMock = lookupConcreteFactoryTest.createSearchTrainDAOMock();
		RouteDAOMock routeDAOMock = setupAbstractFactoryTest.createRouteDAOMock();
		SeatAvailibilityDAOMock seatAvailabilityDAOMock = lookupConcreteFactoryTest.createSeatAvailibilityDAOMock();
		ReservationDAOMock reservationDAOMock = reservationAbstractFactoryTest.createReservationDAOMock();
		SearchPassengerInformationDAOMock searchPassengerInformationDAOMock = cancelTicketAbstractFactoryTest
				.createSearchPassengerInformationDAOMock();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);

		reservation = reservationMock.creteReservationMock(reservation);
		passenger = passengerMock.createPassengerMock(passenger);
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		trainCancellation.rescheduleOnWeekEnds(reservation, searchTrainDAOMock, routeDAOMock, seatAvailabilityDAOMock,
				searchPassengerInformationDAOMock, reservationDAOMock);

		assertEquals(0, reservation.getDeletedTicket());
	}

}
