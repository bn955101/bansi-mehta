package com.project.lookup;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class BookedTicketsTest {
	LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
	LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();

	@Test
	void testGetReservationId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setReservationId(1);

		assertEquals(1, bookedTickets.getReservationId());
	}

	@Test
	void testSetReservationId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setReservationId(1);

		assertEquals(1, bookedTickets.getReservationId());
	}

	@Test
	void testGetTrainId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setTrainId(1);

		assertEquals(1, bookedTickets.getTrainId());
	}

	@Test
	void testSetTrainId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setTrainId(1);

		assertEquals(1, bookedTickets.getTrainId());
	}

	@Test
	void testGetDate() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();
		Date date = new Date(61202516585000L);

		bookedTickets.setReservationDate(new Date(61202516585000L));

		assertEquals(date, bookedTickets.getReservationDate());
	}

	@Test
	void testSetDate() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();
		Date date = new Date(61202516585000L);

		bookedTickets.setReservationDate(new Date(61202516585000L));

		assertEquals(date, bookedTickets.getReservationDate());
	}

	@Test
	void testGetSourceStationId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setSourceStationId(1);

		assertEquals(1, bookedTickets.getSourceStationId());
	}

	@Test
	void testSetSourceStationId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setSourceStationId(1);

		assertEquals(1, bookedTickets.getSourceStationId());
	}

	@Test
	void testGetDestinationId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setDestinationId(1);

		assertEquals(1, bookedTickets.getDestinationId());
	}

	@Test
	void testSetDestinationId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setDestinationId(1);

		assertEquals(1, bookedTickets.getDestinationId());
	}

	@Test
	void testGetAmountPaid() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setAmountPaid(100);

		assertEquals(100, bookedTickets.getAmountPaid());
	}

	@Test
	void testSetAmountPaid() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setAmountPaid(100);

		assertEquals(100, bookedTickets.getAmountPaid());
	}

	@Test
	void testGetTicketBooked() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setTicketBooked(3);

		assertEquals(3, bookedTickets.getTicketBooked());
	}

	@Test
	void testSetTicketBooked() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setTicketBooked(3);

		assertEquals(3, bookedTickets.getTicketBooked());
	}

	@Test
	void testGetUserId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setUserId(5);

		assertEquals(5, bookedTickets.getUserId());
	}

	@Test
	void testSetUserId() {
		IBookedTickets bookedTickets = lookupAbstractFactory.createBookedTickets();

		bookedTickets.setUserId(5);

		assertEquals(5, bookedTickets.getUserId());
	}

}
