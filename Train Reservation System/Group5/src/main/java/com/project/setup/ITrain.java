package com.project.setup;

import java.sql.Date;
import java.util.List;

public interface ITrain {

	int getTrainId();

	void setTrainId(int trainId);

	int getTrainCode();

	void setTrainCode(int trainCode);

	String getTrainName();

	void setTrainName(String trainName);

	String getTrainType();

	void setTrainType(String trainType);

	String getDays();

	void setDays(String days);

	String getDepartureTime();

	void setDepartureTime(String departureTime);

	int getTotalCoaches();

	void setTotalCoaches(int totalCoaches);

	String getStartStation();

	void setStartStation(String startStation);

	String getMiddleStations();

	void setMiddleStations(String middleStations);

	String getEndStation();

	void setEndStation(String endStation);

	List<Integer> getTotalStation();

	void setTotalStation(List<Integer> totalStation);

	double getFare();

	void setFare(double fare);

	String getPickUPTime();

	void setPickUPTime(String pickUPTime);

	String getDropTime();

	void setDropTime(String dropTime);

	int getAvailableSeat();

	void setAvailableSeat(int availableSeat);

	double getTotalDistance();

	void setTotalDistance(double totalDistance);

	Date getPickUPDate();

	void setPickUPDate(Date pickUPDate);

	Date getDropUpDate();

	void setDropUpDate(Date dropUpDate);

	Date getStartDate();

	void setStartDate(Date startDate);

	boolean isTrainCodeInvalid();

	boolean isTrainNameInvalid();

	boolean isTrainTypeInvalid();

	boolean isTrainDepartureTimeInvalid();

	boolean isTotalCoachesInvalid();

	boolean isStartStationInvalid();

	boolean isEndStationInvalid();

	boolean isSourceStationAndDestinationStationSame();

}