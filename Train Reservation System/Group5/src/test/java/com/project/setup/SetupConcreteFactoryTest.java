package com.project.setup;

public class SetupConcreteFactoryTest extends SetupAbstractFactoryTest {

	@Override
	public StationMock createStationMock() {
		return new StationMock();
	}

	@Override
	public RouteMock createRouteMock() {
		return new RouteMock();
	}

	@Override
	public RouteDAOMock createRouteDAOMock() {
		return new RouteDAOMock();
	}

	@Override
	public TrainMock createTrainMock() {
		return new TrainMock();
	}

}
