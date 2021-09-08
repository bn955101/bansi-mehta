package com.project.reservation;

public class PassengerMock {

	public IPassengerInformation createPassengerMock(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockFirstNameNull(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName(null);
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockFirstNameEmpty(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockLastNameNull(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName(null);
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockLastNameEmpty(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockGenderNull(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender(null);
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockGenderEmpty(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockBerthPreferenceNull(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference(null);
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockBerthPreferenceEmpty(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockBerthPreferenceSpace(IPassengerInformation passenger) {
		passenger.setAge(62);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference(" ");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockAgeZero(IPassengerInformation passenger) {
		passenger.setAge(0);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockAgeNegative(IPassengerInformation passenger) {
		passenger.setAge(-5);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

	public IPassengerInformation createPassengerMockAgeHuge(IPassengerInformation passenger) {
		passenger.setAge(150);
		passenger.setAmountPaid(100.0);
		passenger.setBerthPreference("Lower Berth");
		passenger.setCoachNumber("A1");
		passenger.setFirstName("Halifax");
		passenger.setLastName("University");
		passenger.setGender("Female");
		passenger.setPassengerInformationId(1);
		passenger.setReservationId(1);
		passenger.setSeatNumber(1);
		return passenger;
	}

}