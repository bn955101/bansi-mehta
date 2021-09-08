package com.project.setup;

public class StationMock {

	public IStation createStationMock(IStation station) {
		station.setStationId(1);
		station.setStationCity("Halifax");
		station.setStationCode("HAL");
		station.setStationName("Halifax Connective");
		station.setStationState("Nova Scotia");
		return station;
	}

}
