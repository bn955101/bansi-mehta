package com.project.setup;

public interface IRoute {

	IStation getSource();

	void setSource(IStation source);

	IStation getDestination();

	void setDestination(IStation destintion);

	double getDistance();

	void setDistance(double distance);

	int getRouteId();

	void setRouteId(int routeId);

	int getSourceId();

	void setSourceId(int sourceId);

	int getDestinationId();

	void setDestinationId(int destinationId);

	boolean isSourceStationIdNull();

	boolean isDestinationStationIdNull();

	boolean isDistanceInvalid();

	boolean isSourceAndDestinationSame();

}