package com.project.setup;

import java.util.List;

public interface IStationDAO {

	boolean save(IStation station);

	List<IStation> getAllStation();

	IStation getStation(Integer stationId);

	void delete(Integer stationId);

	boolean isStationUnique(String stationName, String stationCode, int stationId);

}
