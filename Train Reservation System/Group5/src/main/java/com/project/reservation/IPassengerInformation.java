package com.project.reservation;

public interface IPassengerInformation {

	double getAmountPaid();

	void setAmountPaid(double amountPaid);

	int getPassengerInformationId();

	void setPassengerInformationId(int passengerInformationId);

	int getReservationId();

	void setReservationId(int reservationId);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getGender();

	void setGender(String gender);

	int getAge();

	void setAge(int age);

	String getBerthPreference();

	void setBerthPreference(String berthPreference);

	int getSeatNumber();

	void setSeatNumber(int seatNumber);

	String getCoachNumber();

	void setCoachNumber(String coachNumber);

	String isPassengerInformationValid();

	boolean isBerthPreferenceEmpty();

	boolean isAgeInvalid();

	boolean isGenderEmpty();

	boolean isLastNameEmpty();

	boolean isFirstNameEmpty();

	boolean isRowNonEmpty();

}