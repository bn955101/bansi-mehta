package com.project.ticketPrint;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.reservation.IPassengerInformation;
import com.project.reservation.PassengerInformation;
import com.project.ticketPrint.ITicketPrint;
import com.project.ticketPrint.TicketPrintAbstractFactory;

public class TicketPrintTest {
	TicketPrintAbstractFactoryTest ticketPrintAbstractFactoryTest = TicketPrintAbstractFactoryTest.instance();
	TicketPrintAbstractFactory ticketPrintAbstractFactory = TicketPrintAbstractFactory.instance();

	@Test
	void testGetReservationId() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setReservationId(1);

		assertEquals(1, ticketPrint.getReservationId());
	}

	@Test
	void testSetReservationId() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setReservationId(1);

		assertEquals(1, ticketPrint.getReservationId());
	}

	@Test
	void testGetTrainCode() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setTrainCode(1);

		assertEquals(1, ticketPrint.getTrainCode());
	}

	@Test
	void testSetTrainCode() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setTrainCode(1);

		assertEquals(1, ticketPrint.getTrainCode());
	}

	@Test
	void testGetTrainName() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setTrainName("ABC");

		assertEquals("ABC", ticketPrint.getTrainName());
	}

	@Test
	void testSetTrainName() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setTrainName("ABC");

		assertEquals("ABC", ticketPrint.getTrainName());
	}

	@Test
	void testGetSourceStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setSourceStation("A");

		assertEquals("A", ticketPrint.getSourceStation());
	}

	@Test
	void testSetSourceStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setSourceStation("A");

		assertEquals("A", ticketPrint.getSourceStation());
	}

	@Test
	void testGetDestinationStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setSourceStation("A");

		assertEquals("A", ticketPrint.getSourceStation());
	}

	@Test
	void testSetDestinationStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setDestinationStation("A");

		assertEquals("A", ticketPrint.getDestinationStation());
	}

	@Test
	void testGetAmountPaid() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setAmountPaid(100.0);

		assertEquals("100.0", String.valueOf(ticketPrint.getAmountPaid()));
	}

	@Test
	void testSetAmountPaid() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setAmountPaid(100.0);

		assertEquals("100.0", String.valueOf(ticketPrint.getAmountPaid()));
	}

	@Test
	void testGetTrainType() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setTrainType("AC");

		assertEquals("AC", ticketPrint.getTrainType());
	}

	@Test
	void testSetTrainType() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();

		ticketPrint.setTrainType("AC");

		assertEquals("AC", ticketPrint.getTrainType());
	}

	@Test
	void testGetPassengerInformation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		PassengerInformation passenger = new PassengerInformation();

		passenger.setAge(23);
		passengerInformation.add(passenger);
		ticketPrint.setPassengerInformation(passengerInformation);

		assertEquals(ticketPrint.getPassengerInformation().get(0).getAge(), 23);
	}

	@Test
	void testSetPassengerInformation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		PassengerInformation passenger = new PassengerInformation();

		passenger.setAge(23);
		passengerInformation.add(passenger);
		ticketPrint.setPassengerInformation(passengerInformation);

		assertEquals(ticketPrint.getPassengerInformation().get(0).getAge(), 23);
	}

}