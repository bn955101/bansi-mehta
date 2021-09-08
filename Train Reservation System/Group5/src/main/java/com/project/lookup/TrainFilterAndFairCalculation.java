package com.project.lookup;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.project.setup.IRoute;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;

public class TrainFilterAndFairCalculation implements ITrainFilterAndFairCalculation {
	public static final int MINUTES_TRAIN_STOPS_AT_EACH_STATION = 10;
	public static final int MINUTES_IN_24HOURS = 1440;
	public static final int MINIMUM_KM_FOR_DISCOUNT = 100;
	public static final double DISCOUNT_FOR_FAIR = 0.2;
	public static final String NON_AC_SLEEPER = "Non AC Sleeper";
	public static final String AC_SLEEPER = "AC Sleeper";
	public static final String NON_AC_SEATER = "Non AC Seater";
	public static final String AC_SEATER = "AC Seater";
	public static final int FAIR_FOR_NON_AC_SLEEPER_PER_KILOMETER = 3;
	public static final int FAIR_FOR_AC_SLEEPER_PER_KILOMETER = 4;
	public static final int FAIR_FOR_NON_AC_SEATER_PER_KILOMETER = 2;
	public static final int FAIR_FOR_AC_PER_KILOMETER = 3;
	public static final int MINIMUM_AGE_FOR_CHILDREN = 0;
	public static final int MAXIMUM_AGE_FOR_CHILDREN = 5;
	public static final int MINIMUM_AGE_FOR_SENIO_CITIZEN = 60;
	public static final double MULTIPLIER_FOR_CHILDREN = 0.5;
	public static final double MULTIPLIER_FOR_SENIOR_CITIZEN = 0.7;
	public static final double TIME_REQUIRED_BY_TRAIN_TO_COVER_ONE_KILOMETER = 1;

	@Override
	public List<ITrain> filterTrain(List<ITrain> trains, ISearchTrain searchTrain, IRouteDAO routeDAO,
			IDayCalculation dayCalculation) {
		String timeTrainLeavesStartStationInHour = "0.00";
		double timeRequiredByTrainToReachSourceStationInMinutes = 0;
		double timeRequiredByTrainToDestinationStationInMinutes = 0;
		double timeTrainLeavesStartStationInMinutes = 0;
		double distanceRequiredToReachSourceStationInKm = 0;
		double distanceRequiredForDestinationStationInKm = 0;
		double distanceCoveredDuringJourneyInKm = 0;
		String sourceStaion = null;
		String destinationStation = null;
		int sourceStationIndex = 0;
		int destinationStationIndex = 0;
		boolean trainIsavailableOrNot = true;
		double fare = 0.0;
		int totalTrainFromDB = trains.size();
		List<Integer> trainToBeRemoved = new ArrayList<Integer>();

		for (int index = 0; index < totalTrainFromDB; index++) {
			timeTrainLeavesStartStationInHour = trains.get(index).getDepartureTime();
			timeTrainLeavesStartStationInMinutes = hoursToMinuteConverter(timeTrainLeavesStartStationInHour);
			List<Integer> allStationTrainVisit = trains.get(index).getTotalStation();

			sourceStaion = searchTrain.getSourceStation();
			destinationStation = searchTrain.getDestinationStation();
			sourceStationIndex = allStationTrainVisit.indexOf(Integer.parseInt(sourceStaion));
			destinationStationIndex = allStationTrainVisit.indexOf(Integer.parseInt(destinationStation));
			for (int sourceStationIterator = 0; sourceStationIterator < sourceStationIndex; sourceStationIterator++) {
				IRoute route = routeDAO.getRouteByStation(allStationTrainVisit.get(sourceStationIterator), allStationTrainVisit.get(sourceStationIterator + 1));

				distanceRequiredToReachSourceStationInKm += route.getDistance();
				timeRequiredByTrainToReachSourceStationInMinutes += MINUTES_TRAIN_STOPS_AT_EACH_STATION;
			}
			timeRequiredByTrainToReachSourceStationInMinutes = timeRequiredByTrainToReachSourceStationInMinutes
					+ (distanceRequiredToReachSourceStationInKm * TIME_REQUIRED_BY_TRAIN_TO_COVER_ONE_KILOMETER);
			for (int destinationStationIterator = 0; destinationStationIterator < destinationStationIndex; destinationStationIterator++) {
				IRoute route = routeDAO.getRouteByStation(allStationTrainVisit.get(destinationStationIterator), allStationTrainVisit.get(destinationStationIterator + 1));

				distanceRequiredForDestinationStationInKm += route.getDistance();
				timeRequiredByTrainToDestinationStationInMinutes += MINUTES_TRAIN_STOPS_AT_EACH_STATION;
			}
			timeRequiredByTrainToDestinationStationInMinutes = timeRequiredByTrainToDestinationStationInMinutes
					+ (distanceRequiredForDestinationStationInKm * TIME_REQUIRED_BY_TRAIN_TO_COVER_ONE_KILOMETER);
			calculateStartDateOfTrain(trains.get(index), searchTrain, timeRequiredByTrainToReachSourceStationInMinutes,
					timeTrainLeavesStartStationInMinutes);
			trainIsavailableOrNot = countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(
					timeTrainLeavesStartStationInMinutes, timeRequiredByTrainToReachSourceStationInMinutes,
					timeRequiredByTrainToDestinationStationInMinutes, trains.get(index), searchTrain, dayCalculation);
			if (trainIsavailableOrNot) {
				distanceCoveredDuringJourneyInKm = distanceRequiredForDestinationStationInKm
						- distanceRequiredToReachSourceStationInKm;
				trains.get(index).setTotalDistance(distanceCoveredDuringJourneyInKm);
				try {
					fare = this.calculateFareByTrainType(distanceCoveredDuringJourneyInKm,
							trains.get(index).getTrainType());
					trains.get(index).setFare(fare);
				} catch (Exception exception) {
					System.err.print(exception);
				}
				continue;
			} else {
				trainToBeRemoved.add(index);
			}
		}
		for (int trainIterator = trainToBeRemoved.size() - 1; trainIterator >= 0; trainIterator--) {
			trains.remove(trains.get(trainToBeRemoved.get(trainIterator)));
		}
		return trains;
	}

	@Override
	public boolean countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(double timeAtTrainStartItsJourneyInMinutes,
			double timeRequiredByTrainToReachSourceStationInMinutes,
			double timeRequiredByTrainForDestinationStationInMinutes, ITrain train, ISearchTrain searchTrain,
			IDayCalculation dayCalculation) {
		boolean istrainAvailableOnThatDate = true;
		double timeBetweenSourceAndDestinationStationInMinutes = 0;
		double totalTimeBetweenStartStationAndSourceStationInMinutes = 0;
		int daytoIncrementInSourceStation = 0;
		int daytoIncrementInDestinationStation = 0;
		double timeAtTrainReachSourceStationInMinutes = 0;
		double timeLeftInFirstDayOfTrainInMinutes = 0;
		double timeAtTrainWillReachDestinationStationInMinutes = 0;
		double timeNeedToCoverForOtherDestinationInMinutes = 0;
		double timeAtTrainReachDestinationStationInMinutes = 0;
		double timeLeftInDayAfterReachingSourceStationInMinutes = 0;
		boolean trainTravelOnUserdayWhenUserWant = true;
		String timeAtTrainReachSourceStationInHours = "0:00";
		String timeAtTrainReachDestinationStationStationInHours = "0:00";

		timeBetweenSourceAndDestinationStationInMinutes = timeRequiredByTrainForDestinationStationInMinutes
				- timeRequiredByTrainToReachSourceStationInMinutes;
		totalTimeBetweenStartStationAndSourceStationInMinutes = timeAtTrainStartItsJourneyInMinutes
				+ timeRequiredByTrainToReachSourceStationInMinutes;
		daytoIncrementInSourceStation = 0;
		daytoIncrementInDestinationStation = 0;
		timeAtTrainReachSourceStationInMinutes = 0;
		timeLeftInFirstDayOfTrainInMinutes = MINUTES_IN_24HOURS - timeAtTrainStartItsJourneyInMinutes;
		timeAtTrainWillReachDestinationStationInMinutes = 0;
		timeNeedToCoverForOtherDestinationInMinutes = 0;
		timeAtTrainReachDestinationStationInMinutes = 0;
		timeLeftInDayAfterReachingSourceStationInMinutes = 0;
		if (timeLeftInFirstDayOfTrainInMinutes > timeRequiredByTrainToReachSourceStationInMinutes) {
			daytoIncrementInSourceStation = 0;
			timeAtTrainReachSourceStationInMinutes = totalTimeBetweenStartStationAndSourceStationInMinutes;
			timeLeftInDayAfterReachingSourceStationInMinutes = MINUTES_IN_24HOURS
					- timeAtTrainReachSourceStationInMinutes;
			trainTravelOnUserdayWhenUserWant = checkWhetherTrainIsAvailableOrNotOnThatDay(train,
					daytoIncrementInSourceStation, searchTrain, dayCalculation);
			if (trainTravelOnUserdayWhenUserWant) {
				timeAtTrainReachSourceStationInHours = minuteToHoursConverter(timeAtTrainReachSourceStationInMinutes);
				train.setPickUPTime(timeAtTrainReachSourceStationInHours);
				java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateOfJourny().getTime());
				train.setPickUPDate(sqlDate);
			} else {
				istrainAvailableOnThatDate = false;
			}
		} else {
			daytoIncrementInSourceStation = (int) (totalTimeBetweenStartStationAndSourceStationInMinutes
					/ MINUTES_IN_24HOURS);
			timeAtTrainReachSourceStationInMinutes = totalTimeBetweenStartStationAndSourceStationInMinutes
					% MINUTES_IN_24HOURS;
			timeLeftInDayAfterReachingSourceStationInMinutes = MINUTES_IN_24HOURS
					- timeAtTrainReachSourceStationInMinutes;
			trainTravelOnUserdayWhenUserWant = checkWhetherTrainIsAvailableOrNotOnThatDay(train,
					daytoIncrementInSourceStation, searchTrain, dayCalculation);
			if (trainTravelOnUserdayWhenUserWant) {
				timeAtTrainReachSourceStationInHours = minuteToHoursConverter(timeAtTrainReachSourceStationInMinutes);
				train.setPickUPTime(timeAtTrainReachSourceStationInHours);
				java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateOfJourny().getTime());

				train.setPickUPDate(sqlDate);
			} else {
				istrainAvailableOnThatDate = false;
				return istrainAvailableOnThatDate;
			}
		}
		if (timeLeftInDayAfterReachingSourceStationInMinutes > timeBetweenSourceAndDestinationStationInMinutes) {
			timeAtTrainReachDestinationStationInMinutes = timeBetweenSourceAndDestinationStationInMinutes
					+ timeAtTrainReachSourceStationInMinutes;
			timeAtTrainReachDestinationStationStationInHours = minuteToHoursConverter(
					timeAtTrainReachDestinationStationInMinutes);
			train.setDropTime(timeAtTrainReachDestinationStationStationInHours);
			daytoIncrementInDestinationStation = 0;
			SetDateForDropUp(train, daytoIncrementInDestinationStation, searchTrain);
		} else {
			timeNeedToCoverForOtherDestinationInMinutes = timeAtTrainStartItsJourneyInMinutes
					+ timeRequiredByTrainForDestinationStationInMinutes;
			daytoIncrementInDestinationStation = (int) (timeNeedToCoverForOtherDestinationInMinutes
					/ MINUTES_IN_24HOURS);
			SetDateForDropUp(train, daytoIncrementInDestinationStation, searchTrain);
			timeAtTrainWillReachDestinationStationInMinutes = timeNeedToCoverForOtherDestinationInMinutes
					% MINUTES_IN_24HOURS;
			timeAtTrainReachSourceStationInHours = minuteToHoursConverter(
					timeAtTrainWillReachDestinationStationInMinutes);
			train.setDropTime(timeAtTrainReachSourceStationInHours);
		}
		return istrainAvailableOnThatDate;
	}

	@Override
	public void calculateStartDateOfTrain(ITrain train, ISearchTrain searchTrain,
			double timeRequiredByTrainToReachSourceStation, double trainStarttime) {
		double totalTimeIncludingTrainStartTime = 0;
		int dayToBeRemoved = 0;

		totalTimeIncludingTrainStartTime = timeRequiredByTrainToReachSourceStation + trainStarttime;
		dayToBeRemoved = (int) (totalTimeIncludingTrainStartTime / MINUTES_IN_24HOURS);
		setStartDateForTrain(train, dayToBeRemoved, searchTrain);
	}

	// reference
	// https://stackoverflow.com/questions/5387371/how-to-convert-minutes-to-hours-and-minutes-hhmm-in-java/5387398
	@Override
	public String minuteToHoursConverter(double minutes) {
		double minute = 0;
		int finalhours = 0;
		double finalminutes = 0;
		String formatedTime = "0:00";

		minute = minutes;
		finalhours = (int) minute / 60;
		finalminutes = minute % 60;
		formatedTime = finalhours + ":" + minuteFormater(finalminutes);
		return formatedTime;
	}

	// reference
	// https://stackoverflow.com/questions/60144953/how-to-convert-single-digit-integer-into-double-digit-javascript
	@Override
	public String minuteFormater(double number) {
		String minutesWithoutZero = "00";
		String minutesWithZero = "0";

		minutesWithoutZero = String.valueOf((int) number);
		if (minutesWithoutZero.length() == 1) {
			minutesWithZero = '0' + String.valueOf(Integer.parseInt(minutesWithoutZero));
		} else {
			minutesWithZero = String.valueOf(Integer.parseInt(minutesWithoutZero));
		}
		return minutesWithZero;
	}

	@Override
	public boolean checkWhetherTrainIsAvailableOrNotOnThatDay(ITrain train, int daytoIncrement,
			ISearchTrain searchTrain, IDayCalculation dayCalculation) {
		String days = "";
		String[] daysArray = null;
		String dayUserWantTotravel = "";
		boolean trainTravelThatDayOrNot = false;

		days = train.getDays();
		daysArray = days.split(",");
		for (int index = 0; index < daysArray.length; index++) {
			daysArray[index] = dayCalculation.getDay(daysArray[index], daytoIncrement);
		}
		java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateOfJourny().getTime());

		dayUserWantTotravel = getDaysNameFromDate(sqlDate);
		trainTravelThatDayOrNot = false;
		for (int daysIterator = 0; daysIterator < daysArray.length; daysIterator++) {
			if (daysArray[daysIterator].equals(dayUserWantTotravel)) {
				trainTravelThatDayOrNot = true;
				break;
			}
		}
		return trainTravelThatDayOrNot;
	}

	@Override
	public double hoursToMinuteConverter(String timeInMinute) {
		double hours = 0;
		double minutes = 0;
		double timeInMinutes = 0;

		hours = Integer.parseInt(timeInMinute.split(":")[0]);
		minutes = Integer.parseInt(timeInMinute.split(":")[1]);
		timeInMinutes = hours * 60 + minutes;
		return timeInMinutes;
	}

	// reference :
	// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
	@Override
	public void setStartDateForTrain(ITrain train, int dayToRemove, ISearchTrain searchTrain) {
		java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateOfJourny().getTime());
		Date date = Date.valueOf(sqlDate.toLocalDate().minusDays(dayToRemove));

		train.setStartDate(date);
	}

	// reference :
	// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
	@Override
	public void SetDateForDropUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain) {
		Date date = Date.valueOf(train.getStartDate().toLocalDate().plusDays(dayTOIncrement));

		train.setDropUpDate(date);
	}

	@Override
	public void SetDateForPickUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain) {
		java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateOfJourny().getTime());
		Date date = Date.valueOf(sqlDate.toLocalDate().plusDays(dayTOIncrement));

		train.setPickUPDate(date);
	}

	@Override
	public String getDaysNameFromDate(Date dateToBeformate) {
		Date currentTime = dateToBeformate;
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");

		return simpleDateformat.format(currentTime);
	}

	@Override
	public double calculateFareByDistance(double distance, double fare) {
		if (distance == 0) {
			return 0.0;
		} else if (distance < MINIMUM_KM_FOR_DISCOUNT) {
			return (double) fare;
		} else {
			double discount = fare * DISCOUNT_FOR_FAIR;
			double finalFare = fare - discount;
			return finalFare;
		}
	}

	@Override
	public double calculateFareByTrainType(double distance, String trainType) throws Exception {
		if (trainType.equals(NON_AC_SLEEPER)) {
			return distance * FAIR_FOR_NON_AC_SLEEPER_PER_KILOMETER;
		} else if (trainType.equals(AC_SLEEPER)) {
			return distance * FAIR_FOR_AC_SLEEPER_PER_KILOMETER;
		} else if (trainType.equals(NON_AC_SEATER)) {
			return distance * FAIR_FOR_NON_AC_SEATER_PER_KILOMETER;
		} else if (trainType.equals(AC_SEATER)) {
			return distance * FAIR_FOR_AC_PER_KILOMETER;
		} else {
			throw new Exception("Invalid Train Type");
		}
	}

	@Override
	public double calculateFareByAge(double fare, int age) {
		if (age == MINIMUM_AGE_FOR_CHILDREN) {
			return 0.0;
		} else if (age > MINIMUM_AGE_FOR_CHILDREN && age < MAXIMUM_AGE_FOR_CHILDREN) {
			return (fare * MULTIPLIER_FOR_CHILDREN);
		} else if (age >= MINIMUM_AGE_FOR_SENIO_CITIZEN) {
			return (fare * MULTIPLIER_FOR_SENIOR_CITIZEN);
		} else {
			return fare;
		}
	}

}
