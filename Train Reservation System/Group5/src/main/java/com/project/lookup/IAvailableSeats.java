package com.project.lookup;

import java.sql.Date;
import java.util.List;

import com.project.setup.ITrain;

public interface IAvailableSeats {

	List<ITrain> findAvailableSeats(List<ITrain> trains, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvalibilityDAO);

	List<Integer> listOfMiddleStation(ITrain train, ISearchTrain searchTrain);

	void findAvailableSeatCountInSingleTrain(ITrain train, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvaillibilityDAO);

	int findMaximumSeatInSingleTrain(ITrain train, Date date, ISeatAvailibilityDAO seatAvaillibilityDAO);

}
