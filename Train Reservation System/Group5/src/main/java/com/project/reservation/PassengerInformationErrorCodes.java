package com.project.reservation;

public abstract class PassengerInformationErrorCodes {

	public static final String firstNameMissing = "First Name is mandatory. ";
	public static final String lastNameMissing = "Last Name is mandatory. ";
	public static final String ageInvalid = "Age should be between " + PassengerInformationConstants.ageLowerLimit
			+ " and " + PassengerInformationConstants.ageUpperLimit;
	public static final String genderMissing = "Gender is mandatory. ";
	public static final String berthPreferenceMissing = "Berth Preference is mandatory. ";

}
