package com.project.lookup;

import java.util.ArrayList;

public class DaysCalculation implements IDayCalculation {
	private final String MONDAY = "Monday";
	private final String TUESDAY = "Tuesday";
	private final String WEDNESDAY = "Wednesday";
	private final String THURSDAY = "Thursday";
	private final String FRIDAY = "Friday";
	private final String SATURDAY = "Saturday";
	private final String SUNDAY = "Sunday";
	private final int TOTAL_DAYS_IN_WEEK = 7;
	private final int CHANGE_INDEX_DAYS = 6;
	int currentIndex = 0;
	String dayNameFromDayClass = null;
	int newIndex = 0;
	String dayAfterIncrement = null;

	public class Day {
		public int dayIndex;
		public String dayName;

		public Day(int dayIndex, String dayName) {
			this.dayIndex = dayIndex;
			this.dayName = dayName;
		}
	}

	ArrayList<Day> dayList = new ArrayList<Day>();

	public DaysCalculation() {
		dayList.add(new Day(0, SUNDAY));
		dayList.add(new Day(1, MONDAY));
		dayList.add(new Day(2, TUESDAY));
		dayList.add(new Day(3, WEDNESDAY));
		dayList.add(new Day(4, THURSDAY));
		dayList.add(new Day(5, FRIDAY));
		dayList.add(new Day(6, SATURDAY));
	}

	public String getDay(String trainStartDay, int increment) {
		currentIndex = 0;
		for (int index = 0; index < dayList.size(); index++) {
			dayNameFromDayClass = dayList.get(index).dayName.toLowerCase();
			if (trainStartDay.toLowerCase().equals(dayNameFromDayClass)) {
				currentIndex = index;
				break;
			}
		}
		newIndex = currentIndex + increment;
		if (newIndex > CHANGE_INDEX_DAYS) {
			newIndex = newIndex - TOTAL_DAYS_IN_WEEK;
		}
		dayAfterIncrement = dayList.get(newIndex).dayName;
		return dayAfterIncrement;
	}

}
