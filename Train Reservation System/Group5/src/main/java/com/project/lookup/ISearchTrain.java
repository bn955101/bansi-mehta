package com.project.lookup;

import java.util.Date;

public interface ISearchTrain {

	String getSourceStation();

	void setSourceStation(String sourceStation);

	String getDestinationStation();

	void setDestinationStation(String destinationStation);

	Date getDateOfJourny();

	void setDateOfJourny(Date dateOfJourny);

	String getTrainType();

	void setTrainType(String trainType);

	boolean isSourceStationAndDestinationStationSame(String sourceStation, String destinationStation);

	boolean isDatePreviousDate(Date dateOfJourny);

	boolean isDateInWithinOneMonthPeriod(Date date);

}
