package com.project.lookup;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.setup.TrainMock;

class TrainFilterAndFairCalculationTest {

	@Test
	void testFilterTrain() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		IRouteDAO routeDAOMock = setupAbstractFactoryTest.createRouteDAOMock();
		IDayCalculation dayCalculationTest = lookupAbstractFactory.createDayCalculation();
		List<ITrain> listOfTrain = new ArrayList<ITrain>();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();

		train = trainMock.createTrainMock(train);
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		listOfTrain.add(train);
		trainFilterAndCalculation.filterTrain(listOfTrain, searchTrain, routeDAOMock, dayCalculationTest);

		assertEquals(1, listOfTrain.size());
	}

	@Test
	void testCountPickUpAndDropUpTimeAndTrainIsAvailableOnThatDayOrNot() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		IDayCalculation dayCalculationTest = lookupAbstractFactory.createDayCalculation();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		double timeAtTrainStartItsJourneyInMinutes = 540;
		double timeRequiredByTrainToReachSourceStationInMinutes = 0;
		double timeRequiredByTrainForDestinationStationInMinutes = 440;

		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		train = trainMock.createTrainMock(train);
		trainFilterAndCalculation.countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(
				timeAtTrainStartItsJourneyInMinutes, timeRequiredByTrainToReachSourceStationInMinutes,
				timeRequiredByTrainForDestinationStationInMinutes, train, searchTrain, dayCalculationTest);

		assertEquals(true,
				trainFilterAndCalculation.countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(
						timeAtTrainStartItsJourneyInMinutes, timeRequiredByTrainToReachSourceStationInMinutes,
						timeRequiredByTrainForDestinationStationInMinutes, train, searchTrain, dayCalculationTest));

	}

	@Test
	void testMinuteToHoursConverter() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		double minutes = 540;

		assertEquals("9:00", trainFilterAndCalculation.minuteToHoursConverter(minutes));
	}

	@Test
	void testMinuteFormater() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		double minutesToBeFormate1 = 5;
		double minutesToBeFormate2 = 50;

		assertEquals("05", trainFilterAndCalculation.minuteFormater(minutesToBeFormate1));
		assertEquals("50", trainFilterAndCalculation.minuteFormater(minutesToBeFormate2));
	}

	@Test
	void testCheckWhetherTrainIsAvailableOrNotOnThatDay() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		IDayCalculation dayCalculationTest = lookupAbstractFactory.createDayCalculation();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		int dayToIncrement = 0;

		train = trainMock.createTrainMock(train);
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);

		assertEquals(true, trainFilterAndCalculation.checkWhetherTrainIsAvailableOrNotOnThatDay(train, dayToIncrement,
				searchTrain, dayCalculationTest));
	}

	@Test
	void testHoursToMinuteConverter() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		String hours = "9:00";

		assertEquals(540, trainFilterAndCalculation.hoursToMinuteConverter(hours));
	}

	@Test
	void testSetStartDateForTrain() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		int dayToRemove = 0;

		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		train = trainMock.createTrainMock(train);
		trainFilterAndCalculation.setStartDateForTrain(train, dayToRemove, searchTrain);

		assertEquals(new Date(System.currentTimeMillis()).toString(), train.getStartDate().toString());
	}

	@Test
	void testSetDateForDropUp() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		int dayToIncrement = 0;

		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		train = trainMock.createTrainMock(train);
		trainFilterAndCalculation.SetDateForDropUp(train, dayToIncrement, searchTrain);

		assertEquals(new Date(System.currentTimeMillis()).toString(), train.getStartDate().toString());
	}

	@Test
	void testSetDateForPickUp() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		int dayToIncrement = 0;

		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		train = trainMock.createTrainMock(train);
		trainFilterAndCalculation.SetDateForPickUp(train, dayToIncrement, searchTrain);

		assertEquals(new Date(System.currentTimeMillis()).toString(), train.getStartDate().toString());
	}

	@Test
	void testGetDaysNameFromDate() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();

		assertEquals("Sunday", trainFilterAndCalculation.getDaysNameFromDate(new Date(1616916605772L)));

	}

	@Test
	void testCalculateFareByDistance() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		double distancezero = 0.0;
		double distanceGreaterThan100Km = 600.0;
		double distanceLessThan100Km = 98.0;
		double fair = 225.0;

		assertEquals(0.0, trainFilterAndCalculation.calculateFareByDistance(distancezero, fair));
		assertEquals(180, trainFilterAndCalculation.calculateFareByDistance(distanceGreaterThan100Km, fair));
		assertEquals(225, trainFilterAndCalculation.calculateFareByDistance(distanceLessThan100Km, fair));
	}

	@Test
	void testCalculateFareByTrainType() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();

		try {
			assertEquals(trainFilterAndCalculation.calculateFareByTrainType(100, "Non AC Sleeper"), 300.0);
			assertEquals(trainFilterAndCalculation.calculateFareByTrainType(100, "AC Sleeper"), 400.0);
			assertEquals(trainFilterAndCalculation.calculateFareByTrainType(100, "Non AC Seater"), 200.0);
			assertEquals(trainFilterAndCalculation.calculateFareByTrainType(100, "AC Seater"), 300.0);
			assertEquals(trainFilterAndCalculation.calculateFareByTrainType(100, "Invaid"), 300.0);
		} catch (Exception exception) {
			assertEquals(exception.getMessage(), "Invalid Train Type");
		}
	}

	@Test
	void testCalculateFareByAge() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ITrainFilterAndFairCalculation trainFilterAndCalculation = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();

		assertEquals(trainFilterAndCalculation.calculateFareByAge(100.0, 0), 0.0);
		assertEquals(trainFilterAndCalculation.calculateFareByAge(100.0, 4), 50.0);
		assertEquals(trainFilterAndCalculation.calculateFareByAge(100.0, 5), 100.0);
		assertEquals(trainFilterAndCalculation.calculateFareByAge(100.0, 20), 100.0);
		assertEquals(trainFilterAndCalculation.calculateFareByAge(100.0, 60), 70.0);
		assertEquals(trainFilterAndCalculation.calculateFareByAge(100.0, 62), 70.0);
	}

}
