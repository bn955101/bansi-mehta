package com.project.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.setup.TrainMock;

class AvailableSeatsTest {

	@Test
	void testFindAvailableSeats() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ISeatAvailibilityDAO seatAvailibilityDAOMock = lookupAbstractFactoryTest.createSeatAvailibilityDAOMock();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		List<ITrain> listOfTrain = new ArrayList<ITrain>();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();

		availableSeats.findAvailableSeats(listOfTrain, searchTrain, seatAvailibilityDAOMock);
		listOfTrain.add(train);
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		train = trainMock.createTrainMock(train);

		assertEquals(50, listOfTrain.get(0).getAvailableSeat());
	}

	@Test
	void testfindAvailableSeatCountInSingleTrain() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ISeatAvailibilityDAO seatAvailibilityDAOMock = lookupAbstractFactoryTest.createSeatAvailibilityDAOMock();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();

		train = trainMock.createTrainMock(train);
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		availableSeats.findAvailableSeatCountInSingleTrain(train, searchTrain, seatAvailibilityDAOMock);

		assertEquals(222, train.getAvailableSeat());
	}

	@Test
	void testListOfMiddleStation() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();

		train = trainMock.createTrainMock(train);
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);

		List<Integer> middleStation = availableSeats.listOfMiddleStation(train, searchTrain);
		List<Integer> middleStationForTest = new ArrayList<Integer>();

		middleStationForTest.add(1);
		middleStationForTest.add(2);
		middleStationForTest.add(3);
		middleStationForTest.add(4);

		assertEquals(middleStation, middleStationForTest);
	}

	@Test
	void testFindMaximumSeatInSingleTrain() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		ISeatAvailibilityDAO seatAvailibilityDAOMock = lookupAbstractFactoryTest.createSeatAvailibilityDAOMock();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();

		train = trainMock.createTrainMock(train);

		assertEquals(2,
				availableSeats.findMaximumSeatInSingleTrain(train, new Date(61202516585000L), seatAvailibilityDAOMock));
	}

}
