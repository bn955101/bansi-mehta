package com.project.setup;

public interface IStation {

	int getStationId();

	void setStationId(int stationId);

	String getStationName();

	void setStationName(String stationName);

	String getStationCode();

	void setStationCode(String stationCode);

	String getStationState();

	void setStationState(String stationState);

	String getStationCity();

	void setStationCity(String stationCity);

	boolean isStationCityInvalid();

	boolean isStationStateInvalid();

	boolean isStationCodeInvalid();

	boolean isStationNameInvalid();

}