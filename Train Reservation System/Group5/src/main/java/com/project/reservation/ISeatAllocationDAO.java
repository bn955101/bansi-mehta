package com.project.reservation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISeatAllocationDAO {

	int getTotalCoaches(IReservation reservation);
	
	List<Integer> getTrainStationsByTrainId(IReservation reservation);
	
	Map<String, Set<Integer>> getReservedPassengerData(IReservation reservation, List<Integer> trainStations);

}
