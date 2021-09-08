package com.project.setup;

public abstract class SetupAbstractFactoryTest {
	private static SetupAbstractFactoryTest instance = null;

	public static SetupAbstractFactoryTest instance() {
		if (instance == null) {
			instance = new SetupConcreteFactoryTest();
		}
		return instance;
	}

	public abstract StationMock createStationMock();

	public abstract RouteMock createRouteMock();

	public abstract TrainMock createTrainMock();

	public abstract RouteDAOMock createRouteDAOMock();

}
