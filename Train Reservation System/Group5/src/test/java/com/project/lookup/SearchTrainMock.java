package com.project.lookup;

import java.sql.Date;

public class SearchTrainMock {

	public ISearchTrain createSearchTrainMock(ISearchTrain searchTrain) {
		searchTrain.setSourceStation("1");
		searchTrain.setDestinationStation("4");
		searchTrain.setDateOfJourny(new Date(System.currentTimeMillis()));
		searchTrain.setTrainType("AC SLEEPER");
		return searchTrain;
	}

	public ISearchTrain createSearchTrainMockWithEqualSourceAndDestinationStation(ISearchTrain searchTrain) {
		searchTrain.setSourceStation("1");
		searchTrain.setDestinationStation("1");
		searchTrain.setDateOfJourny(new Date(System.currentTimeMillis()));
		searchTrain.setTrainType("AC SLEEPER");
		return searchTrain;
	}

}
