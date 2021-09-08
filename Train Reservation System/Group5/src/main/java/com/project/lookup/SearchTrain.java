package com.project.lookup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchTrain implements ISearchTrain {
	private String sourceStation;
	private String destinationStation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateOfJourny;
	public String trainType;

	public SearchTrain() {
	}

	public SearchTrain(String sourceStation, String destinationStation, Date dateofJourny, String trainType) {
		super();
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
		this.dateOfJourny = dateofJourny;
		this.trainType = trainType;
	}

	@Override
	public String getSourceStation() {
		return sourceStation;
	}

	@Override
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	@Override
	public String getDestinationStation() {
		return destinationStation;
	}

	@Override
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	@Override
	public Date getDateOfJourny() {
		return dateOfJourny;
	}

	@Override
	public void setDateOfJourny(Date dateofJourny) {
		this.dateOfJourny = dateofJourny;
	}

	@Override
	public String getTrainType() {
		return trainType;
	}

	@Override
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	@Override
	public boolean isSourceStationAndDestinationStationSame(String sourceStation, String destinationStation) {
		if (sourceStation.equals(destinationStation)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDatePreviousDate(Date date) {
		Date a = new Date(System.currentTimeMillis());
		Date b = (Date) date;

		if (a.compareTo(b) == 1) {
			return true;
		} else {
			return false;
		}
	}

	// source:
	// https://stackoverflow.com/questions/14892536/to-check-if-the-date-is-after-the-specified-date
	public boolean isDateInWithinOneMonthPeriod(Date date) {
		// https://stackoverflow.com/questions/11097256/how-to-convert-mon-jun-18-000000-ist-2012-to-18-06-2012
		String dateStr = date.toString();
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date dateParse;

		try {
			dateParse = (Date) formatter.parse(dateStr);
			Calendar cal = Calendar.getInstance();

			cal.setTime(dateParse);
			String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);

			String dateSplit[] = formatedDate.split("/");

			// https://mkyong.com/java8/java-check-if-the-date-is-older-than-6-months/
			LocalDate currentDate = LocalDate.now();
			LocalDate currentDatePlusMonths = currentDate.plusMonths(1);
			LocalDate date1 = LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]),
					Integer.parseInt(dateSplit[0]));

			if (date1.isBefore(currentDatePlusMonths)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		return true;
	}

}
