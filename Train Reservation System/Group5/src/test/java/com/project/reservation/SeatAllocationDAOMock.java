package com.project.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SeatAllocationDAOMock implements ISeatAllocationDAO {
	
	@Override
	public int getTotalCoaches(IReservation reservation) {
		return 11;
	}
	
	public List<Integer> getTrainStationsByTrainId(IReservation reservation){
		List<Integer> newTrainList = new ArrayList<>();
		newTrainList.add(5);
		return newTrainList;
	}
	
	public Map<String, Set<Integer>> getReservedPassengerData(IReservation reservation, List<Integer> trainStations){
		Set<Integer> newPassengerList = new HashSet<>();
		newPassengerList.add(5);
		Map<String, Set<Integer>> passengerData = new HashMap<>();
		passengerData.put("1", newPassengerList);
		return passengerData;
	}

}
