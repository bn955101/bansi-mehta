package com.project.reservation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SeatAllocation implements ISeatAllocation {
	public final String UPPER_BERTH_PREFERENCE = "Upper Berth";
	public final String LOWER_BERTH_PREFERENCE = "Lower Berth";
	public final String NO_BERTH_PREFERENCE = "No Preference";

	@Override
	public IReservation allocateSeat(IReservation reservation, ISeatAllocationDAO seatAllocationDAO) {
		int totalCoaches = 0;

		totalCoaches = seatAllocationDAO.getTotalCoaches(reservation);
		if (0 < totalCoaches) {
			List<Integer> trainStations = seatAllocationDAO.getTrainStationsByTrainId(reservation);
			Map<String, Set<Integer>> trainCoachAndSeatsData = seatAllocationDAO.getReservedPassengerData(reservation,
					trainStations);
			List<IPassengerInformation> passengerInformation = reservation.getPassengerInformation();
			List<IPassengerInformation> newPassengerInformation = new ArrayList<>();

			for (int i = 0; i < passengerInformation.size(); i++) {
				IPassengerInformation passengerInfo = passengerInformation.get(i);
				String berthPreference = passengerInfo.getBerthPreference();
				boolean seatAllocated = false;
				int middleCoach = totalCoaches / 2;
				List<String> coachNumbers = new ArrayList<>();

				if (0 < totalCoaches % 2) {
					coachNumbers.add(Character.toString((char) 65 + middleCoach));
					for (int k = middleCoach - 1, l = middleCoach + 1; k >= 0; k--, l++) {
						coachNumbers.add(Character.toString((char) 65 + k));
						coachNumbers.add(Character.toString((char) 65 + l));
					}
				} else {
					for (int k = middleCoach - 1, l = middleCoach; k >= 0; k--, l++) {
						coachNumbers.add(Character.toString((char) 65 + k));
						coachNumbers.add(Character.toString((char) 65 + l));
					}
				}
				if (LOWER_BERTH_PREFERENCE.equals(berthPreference)) {
					int m = 0;

					while (Boolean.FALSE.equals(seatAllocated) && m < coachNumbers.size()) {
						if (trainCoachAndSeatsData.containsKey(coachNumbers.get(m))) {
							int j = 1;

							while (j > 0 && j <= 32) {
								if (trainCoachAndSeatsData.get(coachNumbers.get(m)).contains(j)) {
									j += 2;
								} else {
									passengerInfo.setCoachNumber(coachNumbers.get(m));
									passengerInfo.setSeatNumber(j);
									newPassengerInformation.add(passengerInfo);
									trainCoachAndSeatsData.get(coachNumbers.get(m)).add(j);
									j = -1;
									seatAllocated = true;
								}
							}
						} else {
							Set<Integer> seatNumberSet = new HashSet<>();

							seatNumberSet.add(1);
							trainCoachAndSeatsData.put(coachNumbers.get(m), seatNumberSet);
							passengerInfo.setCoachNumber(coachNumbers.get(m));
							passengerInfo.setSeatNumber(1);
							newPassengerInformation.add(passengerInfo);
							seatAllocated = true;
						}
						m++;
					}
				} else if (UPPER_BERTH_PREFERENCE.equals(berthPreference)) {
					int m = 0;

					while (Boolean.FALSE.equals(seatAllocated) && m < coachNumbers.size()) {
						if (trainCoachAndSeatsData.containsKey(coachNumbers.get(m))) {
							int j = 2;

							while (j > 0 && j <= 32) {
								if (trainCoachAndSeatsData.get(coachNumbers.get(m)).contains(j)) {
									j += 2;
								} else {
									passengerInfo.setCoachNumber(coachNumbers.get(m));
									passengerInfo.setSeatNumber(j);
									newPassengerInformation.add(passengerInfo);
									trainCoachAndSeatsData.get(coachNumbers.get(m)).add(j);
									j = -1;
									seatAllocated = true;
								}
							}
						} else {
							Set<Integer> seatNumberSet = new HashSet<>();

							seatNumberSet.add(1);
							trainCoachAndSeatsData.put(coachNumbers.get(m), seatNumberSet);
							passengerInfo.setCoachNumber(coachNumbers.get(m));
							passengerInfo.setSeatNumber(1);
							newPassengerInformation.add(passengerInfo);
							seatAllocated = true;
						}
						m++;
					}
				} else if (NO_BERTH_PREFERENCE.equals(berthPreference) || Boolean.FALSE.equals(seatAllocated)) {
					int m = 0;

					while (Boolean.FALSE.equals(seatAllocated) && m < coachNumbers.size()) {
						if (trainCoachAndSeatsData.containsKey(coachNumbers.get(m))) {
							int j = 1;

							while (j > 0 && j <= 32) {
								if (trainCoachAndSeatsData.get(coachNumbers.get(m)).contains(j)) {
									j += 1;
								} else {
									passengerInfo.setCoachNumber(coachNumbers.get(m));
									passengerInfo.setSeatNumber(j);
									newPassengerInformation.add(passengerInfo);
									trainCoachAndSeatsData.get(coachNumbers.get(m)).add(j);
									j = -1;
									seatAllocated = true;
								}
							}
						} else {
							Set<Integer> seatNumberSet = new HashSet<>();

							seatNumberSet.add(1);
							trainCoachAndSeatsData.put(coachNumbers.get(m), seatNumberSet);
							passengerInfo.setCoachNumber(coachNumbers.get(m));
							passengerInfo.setSeatNumber(1);
							newPassengerInformation.add(passengerInfo);
							seatAllocated = true;
						}
						m++;
					}
				}
			}
			reservation.setPassengerInformation(newPassengerInformation);
		}
		return reservation;
	}

}
