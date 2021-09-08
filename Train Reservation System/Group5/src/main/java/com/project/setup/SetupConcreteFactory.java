package com.project.setup;

public class SetupConcreteFactory extends SetupAbstractFactory {
	private IRouteDAO routeDAO;
	private IStationDAO stationDAO;
	private ITrainDAO trainDAO;
	private IRoute route;
	private IStation station;
	private ITrain train;
	private ICancelTrain cancelTrain;

	@Override
	public IRouteDAO createRouteDAO() {
		if (routeDAO == null) {
			routeDAO = new RouteDAO();
		}
		return routeDAO;
	}

	@Override
	public IRouteDAO createNewRouteDAO() {
		return new RouteDAO();
	}

	@Override
	public IRoute createRoute() {
		if (route == null) {
			route = new Route();
		}
		return route;
	}

	@Override
	public IRoute createNewRoute() {
		return new Route();
	}

	@Override
	public IStationDAO createStationDAO() {
		if (stationDAO == null) {
			stationDAO = new StationDAO();
		}
		return stationDAO;
	}

	@Override
	public IStationDAO createNewStationDAO() {
		return new StationDAO();
	}

	@Override
	public IStation createStation() {
		if (station == null) {
			station = new Station();
		}
		return station;
	}

	@Override
	public IStation createNewStation() {
		return new Station();
	}

	@Override
	public ITrainDAO createTrainDAO() {
		if (trainDAO == null) {
			trainDAO = new TrainDAO();
		}
		return trainDAO;
	}

	@Override
	public ITrainDAO createNewTrainDAO() {
		return new TrainDAO();
	}

	@Override
	public ITrain createTrain() {
		if (train == null) {
			train = new Train();
		}
		return train;
	}

	@Override
	public ITrain createNewTrain() {
		return new Train();
	}

	@Override
	public ICancelTrain createCancelTrain() {
		if (cancelTrain == null) {
			cancelTrain = new CancelTrain();
		}
		return cancelTrain;
	}

	@Override
	public ICancelTrain createNewCancelTrain() {
		return new CancelTrain();
	}

}
