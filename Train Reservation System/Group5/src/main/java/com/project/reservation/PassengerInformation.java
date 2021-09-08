package com.project.reservation;

public class PassengerInformation implements IPassengerInformation {
	public int passengerInformationId;
	public int reservationId;
	public String firstName;
	public String lastName;
	public String gender;
	public int age;
	public String berthPreference;
	public int seatNumber;
	public String coachNumber;
	public double amountPaid;

	@Override
	public double getAmountPaid() {
		return amountPaid;
	}

	@Override
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public int getPassengerInformationId() {
		return passengerInformationId;
	}

	@Override
	public void setPassengerInformationId(int passengerInformationId) {
		this.passengerInformationId = passengerInformationId;
	}

	@Override
	public int getReservationId() {
		return reservationId;
	}

	@Override
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getGender() {
		return gender;
	}

	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String getBerthPreference() {
		return berthPreference;
	}

	@Override
	public void setBerthPreference(String berthPreference) {
		this.berthPreference = berthPreference;
	}

	@Override
	public int getSeatNumber() {
		return seatNumber;
	}

	@Override
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String getCoachNumber() {
		return coachNumber;
	}

	@Override
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}

	@Override
	public boolean isFirstNameEmpty() {
		String firstName = this.getFirstName();

		if (firstName.equals("")) {
			return true;
		} else if (lastName.equals(null)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLastNameEmpty() {
		String lastName = this.getLastName();

		if (lastName.equals(null)) {
			return true;
		} else if (lastName.equals("")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isGenderEmpty() {
		String gender = this.getGender();

		if (gender.equals(null)) {
			return true;
		} else if (gender.equals("")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAgeInvalid() {
		int age = this.getAge();

		if (age <= PassengerInformationConstants.ageLowerLimit) {
			return true;
		} else if (age >= PassengerInformationConstants.ageUpperLimit) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBerthPreferenceEmpty() {
		String berthPreference = this.getBerthPreference();

		if (berthPreference.equals(null)) {
			return true;
		} else if (berthPreference.equals("")) {
			return true;
		}
		return false;
	}

	@Override
	public String isPassengerInformationValid() {
		String errorMessages = "";

		if (this.isFirstNameEmpty()) {
			errorMessages += PassengerInformationErrorCodes.firstNameMissing;
		}
		if (this.isLastNameEmpty()) {
			errorMessages += PassengerInformationErrorCodes.lastNameMissing;
		}
		if (this.isAgeInvalid()) {
			errorMessages += PassengerInformationErrorCodes.ageInvalid;
		}
		if (this.isGenderEmpty()) {
			errorMessages += PassengerInformationErrorCodes.genderMissing;
		}
		if (this.isBerthPreferenceEmpty()) {
			errorMessages += PassengerInformationErrorCodes.berthPreferenceMissing;
		}
		return errorMessages;
	}

	@Override
	public boolean isRowNonEmpty() {
		if (this.isFirstNameEmpty() && this.isLastNameEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
