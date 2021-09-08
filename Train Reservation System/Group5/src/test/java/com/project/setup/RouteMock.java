package com.project.setup;

public class RouteMock {

	public IRoute createRouteMockWithSourceIdMissing(IRoute route) {
		route.setRouteId(1);
		route.setDestination(null);
		route.setDestinationId(1);
		route.setDistance(100);
		route.setSource(null);
		route.setSourceId(0);
		return route;
	}

	public IRoute createRouteMockWithDestinationIdMissing(IRoute route) {
		route.setRouteId(1);
		route.setDestination(null);
		route.setDestinationId(0);
		route.setDistance(100);
		route.setSource(null);
		route.setSourceId(1);
		return route;
	}

	public IRoute createRouteMockWithSourceIdAndDestinationIdSame(IRoute route) {
		route.setRouteId(1);
		route.setDestination(null);
		route.setDestinationId(1);
		route.setDistance(100);
		route.setSource(null);
		route.setSourceId(1);
		return route;
	}

	public IRoute createRouteMockWithDistanceZero(IRoute route) {
		route.setRouteId(1);
		route.setDestination(null);
		route.setDestinationId(1);
		route.setDistance(0);
		route.setSource(null);
		route.setSourceId(2);
		return route;
	}

	public IRoute createRouteMockWithDistanceNegative(IRoute route) {
		route.setRouteId(1);
		route.setDestination(null);
		route.setDestinationId(1);
		route.setDistance(-5);
		route.setSource(null);
		route.setSourceId(2);
		return route;
	}

	public IRoute createRouteMock(IRoute route) {
		route.setRouteId(1);
		route.setDestination(null);
		route.setDestinationId(2);
		route.setDistance(100);
		route.setSource(null);
		route.setSourceId(1);
		return route;
	}

}
