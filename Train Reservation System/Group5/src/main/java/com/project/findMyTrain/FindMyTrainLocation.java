package com.project.findMyTrain;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.project.setup.ITrain;

public class FindMyTrainLocation implements IFindMyTrainLocation {
	public final Double ONE_POINT_FIVE = 1.5;
	public final int FIVE = 5;

	@Override
	public String findMyTrainCalculation(ITrain train, Date startDate, IFindMyTrainDAO findMyTrainDAO) {
		FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
		String trainLocation = null;
		List<Integer> stationIdList = new ArrayList<Integer>();

		stationIdList.add(Integer.valueOf(train.getStartStation()));
		if (train.getMiddleStations() == null) {
		} else {
			String[] middleStationList = train.getMiddleStations().split(",");

			for (String middleStation : middleStationList) {
				stationIdList.add(Integer.valueOf(middleStation));
			}
		}
		stationIdList.add(Integer.valueOf(train.getEndStation()));

		double totalDistance = 0;
		List<DistanceData> distanceDataList = new ArrayList<>();

		for (int i = 0; i < stationIdList.size() - 1; i++) {
			double distance = findMyTrainDAO.getRouteInformation(stationIdList.get(i), stationIdList.get(i + 1));

			totalDistance += distance;
			IDistanceData distanceData = findMyTrainAbstractFactory.createNewDistanceData();

			distanceData.setStartStation(stationIdList.get(i));
			distanceData.setEndStation(stationIdList.get(i + 1));
			distanceData.setDistance(distance);
			distanceDataList.add((DistanceData) distanceData);
		}
		Map<Integer, String> stationInformationMap = findMyTrainDAO.getStationInformation();
		Instant instant = startDate.toInstant();
		ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zonedDateTime.toLocalDate();
		DayOfWeek day = date.getDayOfWeek();

		if (train.getDays().contains(day.getDisplayName(TextStyle.FULL, new Locale("EN")))) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			String departureTimeOfTrain = train.getDepartureTime();
			LocalTime departureTime = LocalTime.parse(departureTimeOfTrain);
			LocalDateTime trainDepartureDateTime = LocalDateTime.of(date, departureTime);
			long minutes = ChronoUnit.MINUTES.between(trainDepartureDateTime, currentDateTime);
			Double distance = (totalDistance * ONE_POINT_FIVE);
			long totalMinutesFromTotalDistance = Long.valueOf(distance.intValue())
					+ ((distanceDataList.size() - 1) * FIVE);

			if (totalMinutesFromTotalDistance <= minutes) {
				trainLocation = "Train already reached at destination.";
			} else if (0 >= minutes) {
				trainLocation = "Train has not departed from source.";
			} else {
				long totalMinutes = 0;
				boolean found = true;

				for (int i = 0; i < distanceDataList.size(); i++) {
					Double distanceCount = distanceDataList.get(i).getDistance() * ONE_POINT_FIVE;

					totalMinutes += Long.valueOf(distanceCount.intValue()) + FIVE;
					if (totalMinutes > minutes && found) {
						trainLocation = "Train is between station : "
								+ stationInformationMap.get(distanceDataList.get(i).getStartStation())
								+ " and station : "
								+ stationInformationMap.get(distanceDataList.get(i).getEndStation());
						found = false;
					}
				}
			}
		} else {
			trainLocation = "No train running on that day.";
		}
		return trainLocation;
	}

}
