package com.project.findMyTrain;


public class DistanceDataMock {
	
	public IDistanceData distanceDataMock(IDistanceData distanceData) {
		distanceData.setStartStation(1);
		distanceData.setEndStation(5);
		distanceData.setDistance(2000);
		return distanceData;
	}
	
}
