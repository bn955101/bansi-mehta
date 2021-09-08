package com.project.findMyTrain;

public interface IDistanceData {

	int getStartStation();

	void setStartStation(int startStation);

	int getEndStation();

	void setEndStation(int endStation);

	double getDistance();

	void setDistance(double distance);

	boolean isStringNullOrEmpty(String string);

	boolean isDateValid(String string);

	boolean isTrainCodeValid(String trainCode);

}
