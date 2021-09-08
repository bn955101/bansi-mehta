package com.project.findMyTrain;

import java.util.Date;
import java.util.Map;
import com.project.setup.ITrain;

public interface IFindMyTrainDAO {

	ITrain getLiveTrainStatus(int trainCode, Date startDate);

	double getRouteInformation(Integer startStation, Integer endStation);

	Map<Integer, String> getStationInformation();

}
