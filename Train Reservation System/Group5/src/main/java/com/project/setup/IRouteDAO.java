package com.project.setup;

import java.util.List;

public interface IRouteDAO {

	void saveRoute(IRoute route);

	List<IRoute> getAllRoute();

	IRoute getRoute(Integer routeId);

	void deleteRoute(Integer routeId);

	IRoute getRouteByStation(int sourcePoint, int destinationPoint);
	
}
