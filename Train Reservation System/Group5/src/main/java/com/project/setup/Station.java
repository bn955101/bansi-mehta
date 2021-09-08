package com.project.setup;

public class Station implements IStation {
	public int stationId;
	public String stationName;
	public String stationCode;
	public String stationCity;
	public String stationState;

	public Station() {
	}

	public Station(int stationId, String stationName, String stationCode, String stationCity, String stationState) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
		this.stationCode = stationCode;
		this.stationCity = stationCity;
		this.stationState = stationState;
	}

	@Override
	public int getStationId() {
		return stationId;
	}

	@Override
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	@Override
	public String getStationName() {
		return stationName;
	}

	@Override
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Override
	public String getStationCode() {
		return stationCode;
	}

	@Override
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	@Override
	public String getStationState() {
		return stationState;
	}

	@Override
	public void setStationState(String stationState) {
		this.stationState = stationState;
	}

	@Override
	public String getStationCity() {
		return stationCity;
	}

	@Override
	public void setStationCity(String stationCity) {
		this.stationCity = stationCity;
	}

	@Override
	public boolean isStationNameInvalid() {
		String StationNameWithoutSpace = this.stationName.trim();

		if (StationNameWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isStationCodeInvalid() {
		String StationCodeWithoutSpace = this.stationCode.trim();

		if (StationCodeWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isStationStateInvalid() {
		String StationStateWithoutSpace = this.stationState.trim();

		if (StationStateWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isStationCityInvalid() {
		String StationCityWithoutSpace = this.stationCity.trim();

		if (StationCityWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

}
