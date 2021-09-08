package com.project.reservation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PassengerInformationTest {
	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
	
	@Test
	void testGetAmountPaid() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setAmountPaid(100.0);
		
		assertEquals(100.0 ,passengerInformation.getAmountPaid(), 0.2);
	}

	@Test
	void testSetAmountPaid() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setAmountPaid(100.0);
		
		assertEquals(100.0, passengerInformation.getAmountPaid(),  0.2);
	}

	@Test
	void testGetPassengerInformationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setPassengerInformationId(1);
		
		assertEquals( 1 ,passengerInformation.getPassengerInformationId());
	}

	@Test
	void testSetPassengerInformationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setPassengerInformationId(1);
		
		assertEquals(1 ,passengerInformation.getPassengerInformationId());
	}

	@Test
	void testGetReservationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setReservationId(1);
		
		assertEquals( 1 , passengerInformation.getReservationId());
	}

	@Test
	void testSetReservationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setReservationId(1);
		
		assertEquals(1 ,passengerInformation.getReservationId());
	}

	@Test
	void testGetFirstName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setFirstName("Halifax");
		
		assertEquals("Halifax" , passengerInformation.getFirstName() );
	}

	@Test
	void testSetFirstName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setFirstName("Halifax");
		
		assertEquals("Halifax" , passengerInformation.getFirstName());
	}

	@Test
	void testGetLastName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setLastName("Halifax");
		
		assertEquals("Halifax" ,passengerInformation.getLastName() );
	}

	@Test
	void testSetLastName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setLastName("Halifax");
		
		assertEquals("Halifax" , passengerInformation.getLastName());
	}

	@Test
	void testGetGender() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setGender("Female");
		
		assertEquals("Female" , passengerInformation.getGender());
	}

	@Test
	void testSetGender() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setGender("Female");
		
		assertEquals( "Female" , passengerInformation.getGender());
	}

	@Test
	void testGetAge() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setAge(62);
		
		assertEquals( 62 , passengerInformation.getAge());
	}

	@Test
	void testSetAge() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setAge(62);
		
		assertEquals(62 , passengerInformation.getAge() );
	}

	@Test
	void testGetBerthPreference() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setBerthPreference("Lower Berth");
		
		assertEquals("Lower Berth" ,passengerInformation.getBerthPreference() );
	}

	@Test
	void testSetBerthPreference() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setBerthPreference("Lower Berth");
		
		assertEquals("Lower Berth" ,passengerInformation.getBerthPreference() );
	}

	@Test
	void testGetSeatNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setSeatNumber(1);
		
		assertEquals(1 , passengerInformation.getSeatNumber() );
	}

	@Test
	void testSetSeatNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setSeatNumber(1);
		
		assertEquals(1 ,passengerInformation.getSeatNumber());
	}

	@Test
	void testGetCoachNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setCoachNumber("A1");
		
		assertEquals("A1" ,passengerInformation.getCoachNumber());
	}

	@Test
	void testSetCoachNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		
		passengerInformation.setCoachNumber("A1");
		
		assertEquals("A1" ,passengerInformation.getCoachNumber());
	}

	@Test
	void testIsPassengerInformationValid() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		
		passengerInformation = passengerMock.createPassengerMock(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), "");
		
		passengerInformation = passengerMock.createPassengerMockAgeNegative(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.ageInvalid);
		
		passengerInformation = passengerMock.createPassengerMockAgeZero(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.ageInvalid);
		
		passengerInformation = passengerMock.createPassengerMockAgeHuge(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.ageInvalid);
		
		passengerInformation = passengerMock.createPassengerMockFirstNameEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.firstNameMissing);
		
		passengerInformation = passengerMock.createPassengerMockLastNameEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.lastNameMissing);
		
		passengerInformation = passengerMock.createPassengerMockGenderEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.genderMissing);
		
		passengerInformation = passengerMock.createPassengerMockBerthPreferenceEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.berthPreferenceMissing);
	}

	@Test
	void testIsRowNonEmpty() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		
		passengerInformation = passengerMock.createPassengerMock(passengerInformation);
		passengerInformation.setFirstName("");
		passengerInformation.setLastName("");
		assertFalse(passengerInformation.isRowNonEmpty());
	
		passengerInformation = passengerMock.createPassengerMockFirstNameEmpty(passengerInformation);
		assertTrue(passengerInformation.isRowNonEmpty());
		
		passengerInformation = passengerMock.createPassengerMockLastNameEmpty(passengerInformation);
		assertTrue(passengerInformation.isRowNonEmpty());
	}
	
}
