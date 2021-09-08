package com.project.lookup;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class SearchTrainTest {
	LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
	LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();

	@Test
	void testGetSourceStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setSourceStation("Surat");

		assertEquals("Surat", searchTrain.getSourceStation());
	}

	@Test
	void testSetSourceStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setSourceStation("Surat");

		assertEquals("Surat", searchTrain.getSourceStation());
	}

	@Test
	void testGetDestinationStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setDestinationStation("Ahembdabad");

		assertEquals("Ahembdabad", searchTrain.getDestinationStation());
	}

	@Test
	void testSetDestinationStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setDestinationStation("Ahembdabad");

		assertEquals("Ahembdabad", searchTrain.getDestinationStation());
	}

	@Test
	void testSetDateofJourny() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setDateOfJourny(new Date(System.currentTimeMillis()));

		assertEquals(new Date(System.currentTimeMillis()), searchTrain.getDateOfJourny());
	}

	@Test
	void testGetTrainType() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setTrainType("AC SLEEPER");

		assertEquals("AC SLEEPER", searchTrain.getTrainType());
	}

	@Test
	void testSetTrainType() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		searchTrain.setTrainType("AC SLEEPER");

		assertEquals("AC SLEEPER", searchTrain.getTrainType());
	}

	@Test
	void testIssourceStationAndDestinationStationSame() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		assertEquals(true, searchTrain.isSourceStationAndDestinationStationSame("1", "1"));
		assertEquals(false, searchTrain.isSourceStationAndDestinationStationSame("1", "3"));
	}

	@Test
	void testIsDatePreviousDate() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		assertEquals(false, searchTrain.isDatePreviousDate(new Date(System.currentTimeMillis() + 1000000000L)));
		assertEquals(true, searchTrain.isDatePreviousDate(new Date(System.currentTimeMillis() - 1000000000L)));
	}

	@Test
	void testIsDateInWithinOneMonthPeriod() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		assertEquals(true,
				searchTrain.isDateInWithinOneMonthPeriod(new java.util.Date(System.currentTimeMillis() + 1000000000L)));
		assertEquals(false,
				searchTrain.isDateInWithinOneMonthPeriod(new java.util.Date(System.currentTimeMillis() + 9000000000L)));
	}

}
