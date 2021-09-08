package com.project.lookup;

import java.sql.Date;
import java.util.List;

import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;

public interface ITrainFilterAndFairCalculation {

	List<ITrain> filterTrain(List<ITrain> trains, ISearchTrain searchTrain, IRouteDAO routeDAO,
			IDayCalculation dayCalculation);

	boolean countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(double timeAtTrainStartItsJourneyInMinutes,
			double timeRequiredByTrainToReachSourceStation, double timeRequiredByTrainForDestinationStation,
			ITrain train, ISearchTrain searchTrain, IDayCalculation dayCalculation);

	void calculateStartDateOfTrain(ITrain train, ISearchTrain searchTrain,
			double timeRequiredByTrainToReachSourceStation, double trainStarttime);

	String minuteToHoursConverter(double minutes);

	String minuteFormater(double number);

	boolean checkWhetherTrainIsAvailableOrNotOnThatDay(ITrain train, int daytoIncrement, ISearchTrain searchTrain,
			IDayCalculation dayCalculation);

	double hoursToMinuteConverter(String time);

	void setStartDateForTrain(ITrain train, int dayToRemove, ISearchTrain searchTrain);

	void SetDateForDropUp(ITrain train, int dayToIncrement, ISearchTrain searchTrain);

	void SetDateForPickUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain);

	String getDaysNameFromDate(Date dateToBeformate);

	double calculateFareByDistance(double distance, double fare);

	double calculateFareByTrainType(double distance, String trainType) throws Exception;

	double calculateFareByAge(double fare, int age);

}
