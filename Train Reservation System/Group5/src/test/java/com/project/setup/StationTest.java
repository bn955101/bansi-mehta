package com.project.setup;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StationTest {
	SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
	SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();

	@Test
	void testGetStationId() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationId(1);

		assertEquals(station.getStationId(), 1);
	}

	@Test
	void testSetStationId() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationId(1);

		assertEquals(station.getStationId(), 1);
	}

	@Test
	void testGetStationName() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationName("Halifax");

		assertEquals(station.getStationName(), "Halifax");
	}

	@Test
	void testSetStationName() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationName("Halifax");

		assertEquals(station.getStationName(), "Halifax");
	}

	@Test
	void testGetStationCode() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationCode("HAL");

		assertEquals(station.getStationCode(), "HAL");
	}

	@Test
	void testSetStationCode() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationCode("HAL");

		assertEquals(station.getStationCode(), "HAL");
	}

	@Test
	void testGetStationState() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationState("Connective");

		assertEquals(station.getStationState(), "Connective");
	}

	@Test
	void testSetStationState() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationState("Connective");

		assertEquals(station.getStationState(), "Connective");
	}

	@Test
	void testGetStationCity() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationCity("University");

		assertEquals(station.getStationCity(), "University");
	}

	@Test
	void testSetStationCity() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationCity("University");

		assertEquals(station.getStationCity(), "University");
	}

	@Test
	void testIsStationNameInvalid() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationName("Halifax");
		assertFalse(station.isStationNameInvalid());

		station.setStationName("");
		assertTrue(station.isStationNameInvalid());

		station.setStationName(" ");
		assertTrue(station.isStationNameInvalid());
	}

	@Test
	void testIsStationCodeInvalid() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationCode("Halifax");
		assertFalse(station.isStationCodeInvalid());

		station.setStationCode("");
		assertTrue(station.isStationCodeInvalid());

		station.setStationCode(" ");
		assertTrue(station.isStationCodeInvalid());
	}

	@Test
	void testIsStationStateInvalid() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationState("Halifax");
		assertFalse(station.isStationStateInvalid());

		station.setStationState("");
		assertTrue(station.isStationStateInvalid());

		station.setStationState(" ");
		assertTrue(station.isStationStateInvalid());
	}

	@Test
	void testIsStationCityInvalid() {
		IStation station = setupAbstractFactory.createNewStation();

		station.setStationCity("Halifax");
		assertFalse(station.isStationCityInvalid());

		station.setStationCity("");
		assertTrue(station.isStationCityInvalid());

		station.setStationCity(" ");
		assertTrue(station.isStationCityInvalid());
	}

}
