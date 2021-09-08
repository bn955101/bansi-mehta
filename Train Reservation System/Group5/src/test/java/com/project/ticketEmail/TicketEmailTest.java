package com.project.ticketEmail;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.reservation.IPassengerInformation;
import com.project.reservation.PassengerInformation;

public class TicketEmailTest {
	
	TicketEmailAbstractFactoryTest ticketEmailAbstractFactoryTest = TicketEmailAbstractFactoryTest.instance();
	TicketEmailAbstractFactory ticketEmailAbstractFactory = TicketEmailAbstractFactory.instance();
	
	@Test
	void testGetReservationId() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setReservationId(1);
		assertEquals(ticketPrint.getReservationId(), 1);
	}

	@Test
	void testSetReservationId() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setReservationId(1);
		assertEquals(ticketPrint.getReservationId(), 1);
	}
	
	@Test
	void testGetTrainCode() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setTrainCode(1);
		assertEquals(ticketPrint.getTrainCode(), 1);
	}

	@Test
	void testSetTrainCode() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setTrainCode(1);
		assertEquals(ticketPrint.getTrainCode(), 1);
	}
	
	@Test
	void testGetTrainName() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setTrainName("ABC");
		assertEquals(ticketPrint.getTrainName(), "ABC");
	}

	@Test
	void testSetTrainName() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setTrainName("ABC");
		assertEquals(ticketPrint.getTrainName(), "ABC");
	}
	
	@Test
	void testGetSourceStation() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setSourceStation("A");
		assertEquals(ticketPrint.getSourceStation(), "A");
	}

	@Test
	void testSetSourceStation() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setSourceStation("A");
		assertEquals(ticketPrint.getSourceStation(), "A");
	}
	
	@Test
	void testGetDestinationStation() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setSourceStation("A");
		assertEquals(ticketPrint.getSourceStation(), "A");
	}

	@Test
	void testSetDestinationStation() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setDestinationStation("A");
		assertEquals(ticketPrint.getDestinationStation(), "A");
	}
	
	@Test
	void testGetAmountPaid() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setAmountPaid(100.0);
		assertEquals(String.valueOf(ticketPrint.getAmountPaid()), "100.0");
	}

	@Test
	void testSetAmountPaid() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setAmountPaid(100.0);
		assertEquals(String.valueOf(ticketPrint.getAmountPaid()), "100.0");
	}
	
	@Test
	void testGetTrainType() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setTrainType("AC");
		assertEquals(ticketPrint.getTrainType(), "AC");
	}

	@Test
	void testSetTrainType() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		ticketPrint.setTrainType("AC");
		assertEquals(ticketPrint.getTrainType(), "AC");
	}
	
	@Test
	void testGetPassengerInformation() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		PassengerInformation passenger = new PassengerInformation();
		passenger.setAge(23);
		passengerInformation.add(passenger);
		ticketPrint.setPassengerInformation(passengerInformation);
		assertEquals(ticketPrint.getPassengerInformation().get(0).getAge(), 23);
	}

	@Test
	void testSetPassengerInformation() {
		ITicketEmail ticketPrint = ticketEmailAbstractFactory.createNewTicketEmail();
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		PassengerInformation passenger = new PassengerInformation();
		passenger.setAge(23);
		passengerInformation.add(passenger);
		ticketPrint.setPassengerInformation(passengerInformation);
		assertEquals(ticketPrint.getPassengerInformation().get(0).getAge(), 23);
	}
}