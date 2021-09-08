package com.project.setup;

public class Route implements IRoute {
	public int routeId;
	public int sourceId;
	public int destinationId;
	public IStation source;
	public IStation destination;
	public double distance;

	public Route() {
	}

	public Route(int routeId, IStation source, int sourceId, IStation destination, int destinationId, double distance) {
		this.routeId = routeId;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.sourceId = sourceId;
		this.destinationId = destinationId;
	}

	@Override
	public IStation getSource() {
		return source;
	}

	@Override
	public void setSource(IStation source) {
		this.source = source;
	}

	@Override
	public IStation getDestination() {
		return destination;
	}

	@Override
	public void setDestination(IStation destination) {
		this.destination = destination;
	}

	@Override
	public double getDistance() {
		return distance;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int getRouteId() {
		return routeId;
	}

	@Override
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	@Override
	public int getSourceId() {
		return sourceId;
	}

	@Override
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public int getDestinationId() {
		return destinationId;
	}

	@Override
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	@Override
	public boolean isSourceStationIdNull() {
		if (this.sourceId <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isDestinationStationIdNull() {
		if (this.destinationId <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isDistanceInvalid() {
		if (this.distance <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isSourceAndDestinationSame() {
		if (this.sourceId == this.destinationId) {
			return true;
		} else {
			return false;
		}
	}

}
