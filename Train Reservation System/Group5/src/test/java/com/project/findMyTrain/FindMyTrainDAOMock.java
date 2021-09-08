package com.project.findMyTrain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.project.setup.IRoute;
import com.project.setup.IStation;
import com.project.setup.ITrain;
import com.project.setup.RouteMock;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.setup.StationMock;
import com.project.setup.TrainMock;

public class FindMyTrainDAOMock implements IFindMyTrainDAO {

	@Override
	public ITrain getLiveTrainStatus(int trainCode, Date startDate) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		train = trainMock.createTrainMock(train);
		return train;
	}

	@Override
	public double getRouteInformation(Integer startStation, Integer endStation) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		IRoute route = setupAbstractFactory.createNewRoute();
		RouteMock routeMock = setupAbstractFactoryTest.createRouteMock();
		route = routeMock.createRouteMock(route);
		return route.getDistance();
	}

	@Override
	public Map<Integer, String> getStationInformation() {
		HashMap<Integer, String> hashMapOfStation = new HashMap<Integer, String>();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		IStation station = setupAbstractFactory.createNewStation();
		StationMock stationMock = setupAbstractFactoryTest.createStationMock();
		station = stationMock.createStationMock(station);
		hashMapOfStation.put(station.getStationId(), station.getStationName());
		return hashMapOfStation;
	}

}
