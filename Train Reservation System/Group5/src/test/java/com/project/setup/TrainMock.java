package com.project.setup;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TrainMock {

	public ITrain createTrainMock(ITrain train) {
		List<Integer> totalStation = new ArrayList<Integer>();

		totalStation.add(1);
		totalStation.add(2);
		totalStation.add(3);
		totalStation.add(4);
		train.setTrainId(1);
		train.setTrainCode(1);
		train.setTrainName("Halifax Express");
		train.setTrainType("AC Seater");
		train.setDays("Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday");
		train.setDepartureTime("09:00");
		train.setTotalCoaches(7);
		train.setStartStation("A1");
		train.setMiddleStations("A2, A3");
		train.setEndStation("A4");
		train.setTotalStation(totalStation);
		train.setFare(11.0);
		train.setPickUPTime("11:20");
		train.setDropTime("05:10");
		train.setAvailableSeat(50);
		train.setTotalDistance(25);
		train.setStartDate(new Date(System.currentTimeMillis()));
		return train;
	}

}
